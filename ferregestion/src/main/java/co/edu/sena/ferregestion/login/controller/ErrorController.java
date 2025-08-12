package co.edu.sena.ferregestion.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error/access-denied")
    public String accessDenied() {
        return "login/access-denied";
    }
}
