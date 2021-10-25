package org.sid.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.sid.entity.Mail;
import org.sid.entity.PasswordResetToken;
import org.sid.entity.User;
import org.sid.repository.PasswordResetTokenRepository;
import org.sid.service.EmailService;
import org.sid.service.UserService;
import org.sid.web.dto.PasswordForgotDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private EmailService emailService;

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "security/forgot-password";
    }

    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
            BindingResult result,
            HttpServletRequest request) {

        if (result.hasErrors()) {
            return "security/forgot-password";
        }

        User user = userService.findByEmail(form.getEmail());
        if (user == null) {
            result.rejectValue("email", null, "Nous n'avons pas pu trouver de compte pour cette adresse e-mail.");
            return "security/forgot-password";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("Acte Naissance <koumwinnie@zohomail.com>");
        mail.setTo(user.getEmail());
        mail.setSubject("Demande de réinitialisation du mot de passe");
        mail.setText("\n"
                + "Pour terminer le processus de réinitialisation du mot de passe," + "\n" + " veuillez cliquer ici:"
                + "https://acte-naissance.herokuapp.com/reset-password?token=" + token.getToken());

        emailService.sendEmail(mail);

        return "redirect:/forgot-password?success";

    }

}
