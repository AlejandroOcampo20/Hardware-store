package co.edu.sena.ferregestion.login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard") // ✅ Cambiado de /dashboard a /admin/dashboard
public class DashboardController {

    @GetMapping
    public String dashboard(Authentication authentication, Model model) {
        return "login/dashboard";
    }
}