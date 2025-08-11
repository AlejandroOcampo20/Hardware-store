package co.edu.sena.ferregestion.controller;

import co.edu.sena.ferregestion.model.Supplier;
import co.edu.sena.ferregestion.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("suppliers", supplierRepository.findByIsActiveTrue());
        return "suppliers/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliers/create";
    }

    @PostMapping("/create")
    public String store(@ModelAttribute Supplier supplier, RedirectAttributes redirectAttributes) {
        try {
            supplierRepository.save(supplier);
            redirectAttributes.addFlashAttribute("success", "Proveedor creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el proveedor: " + e.getMessage());
        }
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        if (supplier == null) {
            return "redirect:/suppliers";
        }

        model.addAttribute("supplier", supplier);
        return "suppliers/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Supplier supplier,
                         RedirectAttributes redirectAttributes) {
        try {
            Supplier existingSupplier = supplierRepository.findById(id).orElse(null);
            if (existingSupplier == null) {
                redirectAttributes.addFlashAttribute("error", "Proveedor no encontrado");
                return "redirect:/suppliers";
            }

            supplier.setId(id);
            supplier.setCreatedAt(existingSupplier.getCreatedAt());
            supplierRepository.save(supplier);
            redirectAttributes.addFlashAttribute("success", "Proveedor actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el proveedor: " + e.getMessage());
        }
        return "redirect:/suppliers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Supplier supplier = supplierRepository.findById(id).orElse(null);
            if (supplier == null) {
                redirectAttributes.addFlashAttribute("error", "Proveedor no encontrado");
                return "redirect:/suppliers";
            }

            supplier.setActive(false);
            supplierRepository.save(supplier);
            redirectAttributes.addFlashAttribute("success", "Proveedor eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el proveedor: " + e.getMessage());
        }
        return "redirect:/suppliers";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("suppliers", supplierRepository.findByNameContainingIgnoreCase(query));
        model.addAttribute("query", query);
        return "suppliers/index";
    }
}