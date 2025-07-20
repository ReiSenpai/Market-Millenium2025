package com.marketMillenium.marketmillenium.controladores;
import com.marketMillenium.marketmillenium.Repository.AbarroteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.marketMillenium.marketmillenium.Service.AbarrotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AbarroteController {

    @Autowired
    private AbarroteRepository abarrotesRepo;

    @GetMapping("/objetos")
    public String listarAbarrotes(Model model) {
        model.addAttribute("abarrotes", abarrotesRepo.findAll());
        return "Objetos";
    }
}


