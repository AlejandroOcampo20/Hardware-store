package co.edu.sena.ferregestion.controller;

import co.edu.sena.ferregestion.model.Client;
import co.edu.sena.ferregestion.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/clients")

public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("view/client")
    public String list(Model model) {
        model.addAttribute("clients", clientRepository.findAll());
        return "client";
    }

    @GetMapping("view/client/form")
    public String form(Model model) {
        model.addAttribute("client", new Client());
        return "client_form";
    }
    @PostMapping("view/client/save")
    public String save(@ModelAttribute Client client, RedirectAttributes ra) {
        clientRepository.save(client);
        ra.addFlashAttribute("success", "Cliente guardado");
        return "redirect:/view/client";
    }
    
    @PutMapping("view/client/edit/{id}")
   public String update(@PathVariable Long id, @ModelAttribute("client") Client client) {
        client.setId(id);
        clientRepository.save(client);
        return "redirect:/view/client";
    }
}
