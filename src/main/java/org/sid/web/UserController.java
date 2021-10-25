package org.sid.web;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.sid.entity.User;
import org.sid.repository.UserRepository;
import org.sid.service.UserService;
import org.sid.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping
    public String listUser(Model model, @RequestParam(name = "motCle", defaultValue = "") String motCle,
            @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(5);
        Page<User> pageUsers = userRepository.findByFirstNameLikeOrLastNameLike("%" + motCle + "%", "%" + motCle + "%", PageRequest.of(currentPage - 0, pageSize));

        model.addAttribute("listUser", pageUsers.getContent());
        int[] pages = new int[pageUsers.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("pageCourante", currentPage);
        model.addAttribute("motCle", motCle);
        return "security/listUser";
    }

    @GetMapping("/infos/{id}")
    public String showUpdateUser(Model model, @PathVariable("id") Long id, Principal principal) {
        User user = userRepository.findByID(id);
        if (user != null) {
            if (user == userRepository.findByEmail(principal.getName())) {
                return "redirect:/changeinfos";
            }
            List<String> listEnable = Arrays.asList("true", "false");
            model.addAttribute("listEnable", listEnable);
            List<String> listSexe = Arrays.asList("Feminin", "Masculin");
            model.addAttribute("listSexe", listSexe);
            model.addAttribute("userForm", userRepository.findByID(id));
            return "security/changeinfosAll";
        }
        return "redirect:/user";
    }

    @PostMapping("/infos/{id}")
    public String UpdateUser(Model model, @PathVariable("id") Long code,
            User userForm, BindingResult result) {
        if (result.hasErrors()) {
            userForm.setId(code);
            return "security/changeinfosAll";
        }
        List<String> listEnable = Arrays.asList("true", "false");
        model.addAttribute("listEnable", listEnable);
        List<String> listSexe = Arrays.asList("Feminin", "Masculin");
        model.addAttribute("listSexe", listSexe);
        try {
            User user = new User();
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getLastName());
            user.setEmail(userForm.getEmail());
            user.setSexe(userForm.getSexe());
            user.setNationalite(userForm.getNationalite());
            user.setProfession(userForm.getProfession());
            user.setTelephone(userForm.getTelephone());
            user.setDateNaissance(userForm.getDateNaissance());

            if (listEnable.contains("true") == userForm.isEnabled()) {
                user.setEnabled(true);
            }
            user.setEnabled(false);
            if (!userForm.getPassword().isEmpty()) {
                userService.update(userForm, code);
                model.addAttribute("userForm", userRepository.findAll());
                return "redirect:/user/infos/" + code + "?success";

            }
            userService.update(userForm, code);
            model.addAttribute("userForm", userRepository.findAll());
        } catch (Exception e) {

        }
        return "redirect:/user/infos/" + code + "?success";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id, Principal principal) {
        User user = userRepository.findByID(id);
        if (user != null) {
            if (user == userRepository.findByEmail(principal.getName())) {
                return "redirect:/user?error";
            }
            userRepository.delete(user);
            model.addAttribute("userForm", userRepository.findAll());
            return "redirect:/user?success";
        }

        return "redirect:/user?success";
    }

    @GetMapping("/add")
    public String showRegistrationForm(Model model) {
        List<String> listSexe = Arrays.asList("Feminin", "Masculin");
        model.addAttribute("listSexe", listSexe);
        List<String> listEnable = Arrays.asList("true", "false");
        model.addAttribute("listEnable", listEnable);
        model.addAttribute("userFor", new User());
        model.addAttribute("userForm", userRepository.findTopByOrderByIdDesc());
        return "security/userAdd";
    }

    @PostMapping("/add")
    public String registerUserAccount(UserRegistrationDto userDto,
            BindingResult result, Model model) {
        model.addAttribute("userFor", new User());
        List<String> listSexe = Arrays.asList("Feminin", "Masculin");
        model.addAttribute("listSexe", listSexe);
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Il y a déjà un compte enregistré avec cet e-mail");
        }

        if (result.hasErrors()) {
            return "redirect:/user/add?error";
        }
        userService.save(userDto);

        return "redirect:/user/add?success";
    }

    @GetMapping("/pass/{id}")
    public String showUpdatePassUser(Model model, @PathVariable("id") Long id, Principal principal) {
        User user = userRepository.findByID(id);
        if (user != null) {

            model.addAttribute("userForm", userRepository.findByID(id));
            return "security/changeinfosPass";
        }
        return "redirect:/user";
    }

    @PostMapping("/pass/{id}")
    public String UpdatePassUser(Model model, @PathVariable("id") Long code,
            User userForm, BindingResult result) {
        if (result.hasErrors()) {
            userForm.setId(code);
            return "security/changeinfosPass";
        }
        try {
            userService.updatePassword(userForm.getPassword(), code);

        } catch (Exception e) {

        }
        return "redirect:/user/pass/" + code + "?success";
    }

    @GetMapping("/password")
    public String showUpdatePassLog(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        if (user != null) {

            model.addAttribute("userForm", user);
            return "security/changeinfosPass";
        }
        return "redirect:/user";
    }

}
