package com.marketMillenium.marketmillenium.controladores;
import com.marketMillenium.marketmillenium.Dao.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

import com.marketMillenium.marketmillenium.Dao.Reclamo;
import com.marketMillenium.marketmillenium.Repository.ReclamoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReclamoController {

    private final ReclamoRepository reclamoRepository;

    public ReclamoController(ReclamoRepository reclamoRepository) {
        this.reclamoRepository = reclamoRepository;
    }

    @GetMapping("/reclamacion")
    public String mostrarFormulario(Model model) {
        model.addAttribute("reclamo", new Reclamo());
        return "reclamacion";
    }

    @PostMapping("/guardar-reclamo")
    public String guardarReclamo(@ModelAttribute Reclamo reclamo, HttpSession session) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado != null) {
            // Asociamos el reclamo al usuario logueado
            reclamo.setUsuario(usuarioLogueado);

            // Opcional: rellenamos datos duplicados si no vienen del formulario
            reclamo.setNombreCompleto(usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido());
            reclamo.setDni(usuarioLogueado.getDni());
            reclamo.setTelefono(usuarioLogueado.getTelefono());
            reclamo.setCorreo(usuarioLogueado.getEmail());
        } else {
            // Por seguridad, redirigir al login si no hay usuario en sesión
            return "redirect:/login";
        }

        reclamoRepository.save(reclamo);
        return "redirect:/reclamacion?exito";
    }
    @GetMapping("/misreclamos")
    public String verMisReclamos(HttpSession session, Model model) {
        Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado");

        if (usuarioLogueado != null) {
            List<Reclamo> misReclamos = reclamoRepository.findByUsuario(usuarioLogueado);
            model.addAttribute("reclamos", misReclamos);
            return "misreclamos"; // esta será la vista donde mostrarás los reclamos
        } else {
            return "redirect:/login";
        }
    }


}