package co.edu.sena.ferregestion.controller;

import co.edu.sena.ferregestion.model.Client;
import co.edu.sena.ferregestion.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("clients", clientRepository.findByIsActiveTrue());
        return "clients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("client", new Client());
        return "clients/create";
    }

    @PostMapping("/create")
    public String store(@ModelAttribute Client client, RedirectAttributes redirectAttributes) {
        try {
            // Verificar si ya existe el documento (si se proporciona)
            if (client.getDocument() != null && !client.getDocument().isEmpty()) {
                if (clientRepository.findByDocument(client.getDocument()).isPresent()) {
                    redirectAttributes.addFlashAttribute("error", "El documento ya está registrado");
                    return "redirect:/clients/create";
                }
            }

            clientRepository.save(client);
            redirectAttributes.addFlashAttribute("success", "Cliente creado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al crear el cliente: " + e.getMessage());
        }
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null) {
            return "redirect:/clients";
        }

        model.addAttribute("client", client);
        return "clients/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Client client,
                         RedirectAttributes redirectAttributes) {
        try {
            Client existingClient = clientRepository.findById(id).orElse(null);
            if (existingClient == null) {
                redirectAttributes.addFlashAttribute("error", "Cliente no encontrado");
                return "redirect:/clients";
            }

            // Verificar documento único (excluyendo el actual)
            if (client.getDocument() != null && !client.getDocument().isEmpty()) {
                Client clientWithSameDoc = clientRepository.findByDocument(client.getDocument()).orElse(null);
                if (clientWithSameDoc != null && !clientWithSameDoc.getId().equals(id)) {
                    redirectAttributes.addFlashAttribute("error", "El documento ya está registrado");
                    return "redirect:/clients/edit/" + id;
                }
            }

            client.setId(id);
            client.setCreatedAt(existingClient.getCreatedAt());
            clientRepository.save(client);
            redirectAttributes.addFlashAttribute("success", "Cliente actualizado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el cliente: " + e.getMessage());
        }
        return "redirect:/clients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Client client = clientRepository.findById(id).orElse(null);
            if (client == null) {
                redirectAttributes.addFlashAttribute("error", "Cliente no encontrado");
                return "redirect:/clients";
            }

            client.setActive(false);
            clientRepository.save(client);
            redirectAttributes.addFlashAttribute("success", "Cliente eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el cliente: " + e.getMessage());
        }
        return "redirect:/clients";
    }

    @GetMapping("/search")
    public String search(@RequestParam String query, Model model) {
        model.addAttribute("clients", clientRepository.findByNameContainingIgnoreCase(query));
        model.addAttribute("query", query);
        return "clients/index";
    }
}