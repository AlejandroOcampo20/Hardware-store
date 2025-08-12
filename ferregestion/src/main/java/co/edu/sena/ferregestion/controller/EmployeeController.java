package co.edu.sena.ferregestion.controller;

import co.edu.sena.ferregestion.model.Employee;
import co.edu.sena.ferregestion.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("employees", employeeRepository.findByIsActiveTrue());
        return "employees/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/create";
    }

    @PostMapping("/create")
    public String store(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
        try {
            // Verificar si ya existe el documento
            if (employeeRepository.findByDocument(employee.getDocument()).isPresent()) {
                redirectAttributes.addFlashAttribute("error", "El documento ya está registrado");
                return "redirect:/employees/create";
            }

            employeeRepository.save(employee);
            redirectAttributes.addFlashAttribute("success", "Empleado creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el empleado: " + e.getMessage());
        }
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return "redirect:/employees";
        }

        model.addAttribute("employee", employee);
        return "employees/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Employee employee,
                         RedirectAttributes redirectAttributes) {
        try {
            Employee existingEmployee = employeeRepository.findById(id).orElse(null);
            if (existingEmployee == null) {
                redirectAttributes.addFlashAttribute("error", "Empleado no encontrado");
                return "redirect:/employees";
            }

            // Verificar documento único (excluyendo el actual)
            Employee employeeWithSameDoc = employeeRepository.findByDocument(employee.getDocument()).orElse(null);
            if (employeeWithSameDoc != null && !employeeWithSameDoc.getId().equals(id)) {
                redirectAttributes.addFlashAttribute("error", "El documento ya está registrado");
                return "redirect:/employees/edit/" + id;
            }

            employee.setId(id);
            employee.setCreatedAt(existingEmployee.getCreatedAt());
            employeeRepository.save(employee);
            redirectAttributes.addFlashAttribute("success", "Empleado actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el empleado: " + e.getMessage());
        }
        return "redirect:/employees";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee == null) {
                redirectAttributes.addFlashAttribute("error", "Empleado no encontrado");
                return "redirect:/employees";
            }

            employee.setActive(false);
            employeeRepository.save(employee);
            redirectAttributes.addFlashAttribute("success", "Empleado eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el empleado: " + e.getMessage());
        }
        return "redirect:/employees";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("employees", employeeRepository.findByNameContainingIgnoreCase(query));
        model.addAttribute("query", query);
        return "employees/index";
    }
}