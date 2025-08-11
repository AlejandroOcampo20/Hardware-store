package co.edu.sena.ferregestion.controller;

import co.edu.sena.ferregestion.repository.*;
import co.edu.sena.ferregestion.model.SaleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.time.LocalDateTime;

@Controller
public class HomeController {

    @Autowired private ProductRepository productRepository;
    @Autowired private SaleRepository saleRepository;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private SupplierRepository supplierRepository;

    @GetMapping({"", "/"})
    public String index(Model model) {
        // Estadísticas principales
        model.addAttribute("totalProducts", productRepository.findByIsActiveTrue().size());
        model.addAttribute("totalEmployees", employeeRepository.findByIsActiveTrue().size());
        model.addAttribute("totalClients", clientRepository.findByIsActiveTrue().size());
        model.addAttribute("totalSuppliers", supplierRepository.findByIsActiveTrue().size());

        // Ventas del día
        LocalDateTime today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime tomorrow = today.plusDays(1);
        model.addAttribute("todaySales", saleRepository.findBySaleDateBetween(today, tomorrow));

        // Productos con stock bajo
        model.addAttribute("lowStockProducts", productRepository.findLowStockProducts());

        // Ventas recientes
        model.addAttribute("recentSales",
                saleRepository.findByStatusOrderBySaleDateDesc(SaleStatus.COMPLETED)
                        .stream().limit(5).toList());

        return "dashboard/index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return index(model);
    }
}