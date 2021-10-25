package org.sid.web;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.sid.entity.User;
import org.sid.service.UserService;
import org.sid.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @ModelAttribute("userForm")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        List<String> listSexe = Arrays.asList("Feminin", "Masculin");
        model.addAttribute("listSexe", listSexe);
        return "security/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("userForm") @Valid UserRegistrationDto userDto,
            BindingResult result, Model model) {

        List<String> listSexe = Arrays.asList("Feminin", "Masculin");
        model.addAttribute("listSexe", listSexe);
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Il y a déjà un compte enregistré avec cet e-mail");
        }

        if (result.hasErrors()) {
            return "security/registration";
        }

        userService.save(userDto);

        userService.autologin(userDto.getEmail(), userDto.getPassword());
        return "redirect:/";
    }
    
    @GetMapping("/admin")
    public String showRegistrationFormAdmin(Model model) {
        List<String> listSexe = Arrays.asList("Feminin", "Masculin");
        model.addAttribute("listSexe", listSexe);
        return "security/registrationAdmin";
    }

    @PostMapping("/admin")
    public String registerUserAccountAdmin(@ModelAttribute("userForm") @Valid UserRegistrationDto userDto,
            BindingResult result, Model model) {

        List<String> listSexe = Arrays.asList("Feminin", "Masculin");
        model.addAttribute("listSexe", listSexe);
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "Il y a déjà un compte enregistré avec cet e-mail");
        }

        if (result.hasErrors()) {
            return "redirect:/registration/admin";
        }

        userService.saveAdmin(userDto);

        userService.autologin(userDto.getEmail(), userDto.getPassword());
        return "redirect:/";
    }

}
