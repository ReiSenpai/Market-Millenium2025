package com.marketMillenium.marketmillenium.controladores;

import com.marketMillenium.marketmillenium.Dao.Usuario;
import com.marketMillenium.marketmillenium.Service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/misdatos")
    public String verDatosUsuario(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuario != null) {
            model.addAttribute("usuario", usuario); // enviar datos a la vista 
            return "misdatos";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/misdatos")
    public String actualizarDatosUsuario(
            @RequestParam Long id,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String direccion,
            @RequestParam String telefono,
            HttpSession session,
            Model model
    ) {
        // verficiar el usuario en sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        // Verificamos que haya  usuario y que el ID sean lso nmismos
        if (usuario == null || !usuario.getId().equals(id)) {
            return "redirect:/login"; // r
        }

        //se actualiza lso datos
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDireccion(direccion);
        usuario.setTelefono(telefono);

        usuarioService.guardar(usuario);
        session.setAttribute("usuarioLogueado", usuario);


        return "misdatos";
    }

    @PostMapping("/actualizar")
    public String actualizarDatos(@ModelAttribute Usuario usuarioForm, HttpSession session) {
        Usuario usuarioSesion = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioSesion != null) {
            // Actualizar campos editables
            usuarioSesion.setNombre(usuarioForm.getNombre());
            usuarioSesion.setApellido(usuarioForm.getApellido());
            usuarioSesion.setDireccion(usuarioForm.getDireccion());
            usuarioSesion.setTelefono(usuarioForm.getTelefono());

            usuarioService.guardar(usuarioSesion); // guarda los cambios

            session.setAttribute("usuarioLogueado", usuarioSesion); // actualiza en sesión

            return "redirect:/misdatos"; // redirige nuevamente a la vista
        } else {
            return "redirect:/login";
        }

}}
