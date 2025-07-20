package com.marketMillenium.marketmillenium.controladores;

import com.marketMillenium.marketmillenium.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String dashboard() {
        return "admin";
    }



    @GetMapping("/productos")
    public String productos() {
        return "admin-productos";
    }

    @GetMapping("/reclamos")
    public String reclamos() {
        return "admin-reclamos";
    }
}
