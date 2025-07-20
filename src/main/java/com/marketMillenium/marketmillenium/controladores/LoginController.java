package com.marketMillenium.marketmillenium.controladores;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.marketMillenium.marketmillenium.Dao.Usuario;
import com.marketMillenium.marketmillenium.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Para validar la contraseña encriptada

    @GetMapping("/login")
    public String mostrarFormularioLogin() {
        return "login"; //
    }

    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam("email") String email,
            @RequestParam("contrasena") String contrasena,
            HttpSession session,
            Model model) {

        System.out.println("Intentando login con: " + email + " / " + contrasena);

        Usuario usuario = usuarioService.buscarPorCorreo(email);
        System.out.println("Usuario encontrado: " + (usuario != null ? usuario.getEmail() : "NO"));

        if (usuario != null) {
            boolean passwordOk = passwordEncoder.matches(contrasena, usuario.getPassword());
            System.out.println("Password OK? " + passwordOk);
            if (passwordOk) {
                session.setAttribute("usuarioLogueado", usuario);

                /* Verificar rol*/
                if ("ROLE_ADMIN".equals(usuario.getRol())) {
                    return "redirect:/admin";
                } else {
                    return "redirect:/index";
                }
            }
        }

        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
