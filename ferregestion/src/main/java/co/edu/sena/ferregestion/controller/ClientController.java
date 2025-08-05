package co.edu.sena.ferregestion.controller;

import co.edu.sena.ferregestion.model.Client;
import co.edu.sena.ferregestion.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("view/client/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        
    }
}
