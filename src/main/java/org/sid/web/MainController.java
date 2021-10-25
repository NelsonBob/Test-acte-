package org.sid.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "page/home";
    }

    @GetMapping("/e")
    public String h() {
        return "page/h1";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "security/login";
    }

    @RequestMapping(value = "/403")
    public String accessDenied() {
        return "security/403";
    }

    @GetMapping("/login?success")
    public String loginSucces(Model model) {
        return "redirect:/";
    }

    @GetMapping("/fonts/glyphicons-halflings-regular.ttf")
    public String redi(Model model) {
        return "redirect:/";
    }
}
