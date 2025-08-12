package co.edu.sena.ferregestion.controller;

import co.edu.sena.ferregestion.model.*;
import co.edu.sena.ferregestion.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sales")
@SessionAttributes("cart")
public class SaleController {

    @Autowired private SaleRepository saleRepository;
    @Autowired private ProductRepository productRepository;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private ClientRepository clientRepository;
    @Autowired private SaleDetailRepository saleDetailRepository;

    @ModelAttribute("cart")
    public Map<Integer, CartItem> cart() {
        return new HashMap<>();
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("sales", saleRepository.findAll());
        return "sales/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("products", productRepository.findByIsActiveTrue());
        model.addAttribute("employees", employeeRepository.findByIsActiveTrue());
        model.addAttribute("clients", clientRepository.findByIsActiveTrue());
        return "sales/create";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Integer productId,
                            @RequestParam Integer quantity,
                            @ModelAttribute("cart") Map<Integer, CartItem> cart,
                            RedirectAttributes redirectAttributes) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
            return "redirect:/sales/create";
        }

        if (quantity > product.getStock()) {
            redirectAttributes.addFlashAttribute("error", "Stock insuficiente. Stock disponible: " + product.getStock());
            return "redirect:/sales/create";
        }

        CartItem existingItem = cart.get(productId);
        if (existingItem != null) {
            Integer newQuantity = existingItem.getQuantity() + quantity;
            if (newQuantity > product.getStock()) {
                redirectAttributes.addFlashAttribute("error", "Stock insuficiente. Stock disponible: " + product.getStock());
                return "redirect:/sales/create";
            }
            existingItem.setQuantity(newQuantity);
            existingItem.setSubtotal(product.getPrice() * newQuantity);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProductId(productId);
            cartItem.setProductName(product.getName());
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setQuantity(quantity);
            cartItem.setSubtotal(product.getPrice() * quantity);
            cart.put(productId, cartItem);
        }

        redirectAttributes.addFlashAttribute("success", "Producto agregado al carrito");
        return "redirect:/sales/create";
    }

    @PostMapping("/remove-from-cart")
    public String removeFromCart(@RequestParam Integer productId,
                                 @ModelAttribute("cart") Map<Integer, CartItem> cart,
                                 RedirectAttributes redirectAttributes) {
        cart.remove(productId);
        redirectAttributes.addFlashAttribute("success", "Producto eliminado del carrito");
        return "redirect:/sales/create";
    }

    @PostMapping("/complete-sale")
    public String completeSale(@RequestParam Integer employeeId,
                               @RequestParam(required = false) Integer clientId,
                               @RequestParam(required = false) String notes,
                               @ModelAttribute("cart") Map<Integer, CartItem> cart,
                               RedirectAttributes redirectAttributes) {

        if (cart.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "El carrito está vacío");
            return "redirect:/sales/create";
        }

        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            redirectAttributes.addFlashAttribute("error", "Empleado no encontrado");
            return "redirect:/sales/create";
        }

        // Crear la venta
        Sale sale = new Sale();
        sale.setEmployee(employee);

        if (clientId != null && clientId > 0) {
            Client client = clientRepository.findById(clientId).orElse(null);
            sale.setClient(client);
        }

        sale.setNotes(notes);
        sale.setStatus(SaleStatus.COMPLETED);

        // Calcular total
        Integer total = cart.values().stream()
                .mapToInt(CartItem::getSubtotal)
                .sum();
        sale.setTotal(total);

        // Guardar venta
        sale = saleRepository.save(sale);

        // Crear detalles de venta y actualizar stock
        List<SaleDetail> details = new ArrayList<>();
        for (CartItem item : cart.values()) {
            Product product = productRepository.findById(item.getProductId()).orElse(null);
            if (product != null) {
                // Verificar stock nuevamente
                if (item.getQuantity() > product.getStock()) {
                    redirectAttributes.addFlashAttribute("error", "Stock insuficiente para " + product.getName());
                    return "redirect:/sales/create";
                }

                SaleDetail detail = new SaleDetail();
                detail.setSale(sale);
                detail.setProduct(product);
                detail.setQuantity(item.getQuantity());
                detail.setUnitPrice(item.getUnitPrice());
                detail.setSubtotal(item.getSubtotal());
                details.add(detail);

                // Actualizar stock
                product.setStock(product.getStock() - item.getQuantity());
                productRepository.save(product);
            }
        }

        saleDetailRepository.saveAll(details);

        // Limpiar carrito
        cart.clear();

        redirectAttributes.addFlashAttribute("success", "Venta realizada exitosamente. ID: " + sale.getId());
        return "redirect:/sales";
    }

    @PostMapping("/cancel/{id}")
    public String cancelSale(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Sale sale = saleRepository.findById(id).orElse(null);

        if (sale == null) {
            redirectAttributes.addFlashAttribute("error", "Venta no encontrada");
            return "redirect:/sales";
        }

        if (sale.getStatus() == SaleStatus.CANCELLED) {
            redirectAttributes.addFlashAttribute("error", "La venta ya está cancelada");
            return "redirect:/sales";
        }

        // Restaurar stock
        List<SaleDetail> details = saleDetailRepository.findBySaleId(id);
        for (SaleDetail detail : details) {
            Product product = detail.getProduct();
            product.setStock(product.getStock() + detail.getQuantity());
            productRepository.save(product);
        }

        sale.setStatus(SaleStatus.CANCELLED);
        saleRepository.save(sale);

        redirectAttributes.addFlashAttribute("success", "Venta cancelada exitosamente");
        return "redirect:/sales";
    }

    @GetMapping("/view/{id}")
    public String viewSale(@PathVariable Integer id, Model model) {
        Sale sale = saleRepository.findById(id).orElse(null);
        if (sale == null) {
            return "redirect:/sales";
        }

        List<SaleDetail> details = saleDetailRepository.findBySaleId(id);
        model.addAttribute("sale", sale);
        model.addAttribute("details", details);
        return "sales/view";
    }
}