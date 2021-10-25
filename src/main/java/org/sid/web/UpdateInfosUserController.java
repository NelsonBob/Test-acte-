package org.sid.web;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.sid.entity.User;
import org.sid.service.UserService;
import org.sid.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/changeinfos")
public class UpdateInfosUserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("userForm")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
    
    @GetMapping
    public String changenfos(Model model, Principal principal) {

        User user = new User();
        if (principal != null) {
            user = this.userService.findByEmail(principal.getName());
        }
        model.addAttribute("userForm", user);
        List<String> listSexe = Arrays.asList("Feminin", "Masculin");
        model.addAttribute("listSexe", listSexe);
        return "security/changeinfos";
    }
    
    @RequestMapping
    public String changenfos(@ModelAttribute("userForm") UserRegistrationDto userForm, BindingResult bindingResult, Model model,
            Principal principal) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("userForm", userForm);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "security/changeinfos";
        }

        User user = new User();
        List<String> listSexe = Arrays.asList("Feminin", "Masculin");
        model.addAttribute("listSexe", listSexe);
        if (principal != null) {
            user = this.userService.findByEmail(principal.getName());
            user.setFirstName(userForm.getFirstName());
            user.setLastName(userForm.getLastName());
            user.setDateNaissance(userForm.getDateNaissance());
            user.setSexe(userForm.getSexe());
            user.setProfession(userForm.getProfession());
            user.setTelephone(userForm.getTelephone());
            try {
                userService.update(user, user.getId());
            } catch (Exception e) {

            }
            model.addAttribute("success", true);
        }

        return "security/changeinfos";
    }
}
