package com.marketMillenium.marketmillenium.controladores;
import org.springframework.ui.Model;

import com.marketMillenium.marketmillenium.Dao.Usuario;
import com.marketMillenium.marketmillenium.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/Registrarse")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "Registrarse";  //
    }

    @PostMapping("/Registrarse")
    public String guardarUsuario(@ModelAttribute Usuario usuario, Model model) {
        usuarioService.guardar(usuario);
        return "Login";
    }
}