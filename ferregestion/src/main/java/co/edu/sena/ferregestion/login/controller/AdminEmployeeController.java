package co.edu.sena.ferregestion.login.controller;

import co.edu.sena.ferregestion.login.model.UserAuth;
import co.edu.sena.ferregestion.login.repository.UserAuthRepository;
import co.edu.sena.ferregestion.model.Employee;
import co.edu.sena.ferregestion.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/employees")
public class AdminEmployeeController {

    private final EmployeeRepository employeeRepository;
    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminEmployeeController(EmployeeRepository employeeRepository,
                                   UserAuthRepository userAuthRepository,
                                   PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.userAuthRepository = userAuthRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/create")
    public String showCreateForm() {
        return "admin/create-employee";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Employee employee,
                                 @RequestParam String role,
                                 Model model) {

        // Guardar empleado
        employee.setActive(true);
        employeeRepository.save(employee);

        // Crear usuario de login con contraseña temporal
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(employee.getEmail());
        userAuth.setPassword(passwordEncoder.encode("temporal123")); // Cambiar luego
        userAuth.setRole(role);
        userAuth.setIsActive(true);
        userAuthRepository.save(userAuth);

        model.addAttribute("message", "Empleado creado con éxito. Contraseña temporal: temporal123");
        return "admin/create-employee";
    }
}
