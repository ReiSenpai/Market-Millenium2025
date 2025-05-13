package com.marketMillenium.marketmillenium.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controlador {
    @GetMapping({"/", "/index"})
    public String inicio(Model model) {
        return "index";
    }
    @GetMapping("/contacto")
    public String contacto(){
        return "contacto";
    }
    @GetMapping("/exa")
    public String exa(){
        return "exa";
    }
    @GetMapping("/nosotros")
    public String nosotros(){
        return "nosotros";
    }
    @GetMapping("/reclamacion")
    public String reclamacion(){
        return "reclamacion";
    } 
    @GetMapping("/sugerencias")
    public String sugerencias(){
        return "sugerencias";
    }
    @GetMapping("/videoProductos")
    public String videoProductos(){
        return "videoProductos";
    }
    
}
