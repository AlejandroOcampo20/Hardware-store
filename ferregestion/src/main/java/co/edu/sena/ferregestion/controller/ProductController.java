package co.edu.sena.ferregestion.controller;

import co.edu.sena.ferregestion.model.Product;
import co.edu.sena.ferregestion.repository.ProductRepository;
import co.edu.sena.ferregestion.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("products", productRepository.findByIsActiveTrue());
        model.addAttribute("lowStockProducts", productRepository.findLowStockProducts());
        return "products/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("suppliers", supplierRepository.findByIsActiveTrue());
        model.addAttribute("categories", productRepository.findDistinctCategories());
        return "products/create";
    }

    @PostMapping("/create")
    public String store(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        try {
            // Verificar si ya existe el código
            if (productRepository.findByCode(product.getCode()).isPresent()) {
                redirectAttributes.addFlashAttribute("error", "El código del producto ya existe");
                return "redirect:/products/create";
            }

            productRepository.save(product);
            redirectAttributes.addFlashAttribute("success", "Producto creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el producto: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        model.addAttribute("suppliers", supplierRepository.findByIsActiveTrue());
        model.addAttribute("categories", productRepository.findDistinctCategories());
        return "products/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Product product,
                         RedirectAttributes redirectAttributes) {
        try {
            Product existingProduct = productRepository.findById(id).orElse(null);
            if (existingProduct == null) {
                redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
                return "redirect:/products";
            }

            // Verificar código único (excluyendo el actual)
            Product productWithSameCode = productRepository.findByCode(product.getCode()).orElse(null);
            if (productWithSameCode != null && !productWithSameCode.getId().equals(id)) {
                redirectAttributes.addFlashAttribute("error", "El código del producto ya existe");
                return "redirect:/products/edit/" + id;
            }

            product.setId(id);
            product.setCreatedAt(existingProduct.getCreatedAt());
            productRepository.save(product);
            redirectAttributes.addFlashAttribute("success", "Producto actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el producto: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Product product = productRepository.findById(id).orElse(null);
            if (product == null) {
                redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
                return "redirect:/products";
            }

            product.setActive(false);
            productRepository.save(product);
            redirectAttributes.addFlashAttribute("success", "Producto eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el producto: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("products", productRepository.findByNameContainingIgnoreCase(query));
        model.addAttribute("query", query);
        return "products/index";
    }

    @GetMapping("/category/{category}")
    public String byCategory(@PathVariable String category, Model model) {
        model.addAttribute("products", productRepository.findByCategory(category));
        model.addAttribute("selectedCategory", category);
        model.addAttribute("categories", productRepository.findDistinctCategories());
        return "products/index";
    }
}