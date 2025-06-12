package com.marketMillenium.marketmillenium.controladores;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import com.marketMillenium.marketmillenium.Dao.Usuario;
import com.marketMillenium.marketmillenium.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login"; // tu archivo login.html
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam("correo") String correo, // Guarda el correo del formulario
            @RequestParam("contrasena") String contrasena,
            HttpSession session,
            Model model) {
        // Buscar el correo del usuario en la bd
        Usuario usuario = usuarioService.buscarPorCorreo(correo);

        if (usuario != null && usuario.getPassword().equals(contrasena)) {
            session.setAttribute("usuarioLogueado", usuario);  //  Guarda usuario en sesión
            return "redirect:/index";  // inicio
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos"); // si falla
            return "login";
        }
    }

}