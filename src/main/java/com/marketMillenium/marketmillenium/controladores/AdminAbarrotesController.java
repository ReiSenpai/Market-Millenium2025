package com.marketMillenium.marketmillenium.controladores;

import com.marketMillenium.marketmillenium.Dao.Abarrotes;
import com.marketMillenium.marketmillenium.Service.AbarrotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/admin/productos")
public class AdminAbarrotesController {

    @Autowired
    private AbarrotesService abarrotesService;

    private final String UPLOAD_DIR = "src/main/resources/static/img/img-products/";

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("abarrotes", abarrotesService.listarTodos());
        return "adminproductos";  // coincide con tu archivo adminproductos.html
    }

    @PostMapping
    public String guardarProducto(
            @ModelAttribute("abarrote") Abarrotes abarrotes,
            @RequestParam("imagenFile") MultipartFile imagenFile
    ) {
        try {
            if (!imagenFile.isEmpty()) {
                String nombreImagen = System.currentTimeMillis() + "_" + imagenFile.getOriginalFilename();
                Path ruta = Paths.get(UPLOAD_DIR + nombreImagen);
                Files.write(ruta, imagenFile.getBytes());
                abarrotes.setImagen(nombreImagen);
            } else if (abarrotes.getImagen() == null || abarrotes.getImagen().isEmpty()) {
                abarrotes.setImagen("default.png"); // Imagen por defecto
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        abarrotesService.guardar(abarrotes);
        return "redirect:/admin/productos";
    }

    @PostMapping("/{id}")
    public String actualizarProducto(
            @PathVariable Long id,
            @ModelAttribute("abarrote") Abarrotes abarrotes,
            @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile
    ) {
        Abarrotes existente = abarrotesService.buscarPorId(id);

        // Actualizamos campos
        existente.setNombre(abarrotes.getNombre());
        existente.setDescripcion(abarrotes.getDescripcion());
        existente.setPrecio(abarrotes.getPrecio());
        existente.setStock(abarrotes.getStock());
        existente.setCategoria(abarrotes.getCategoria());
        existente.setMarca(abarrotes.getMarca());
        existente.setUnidadMedida(abarrotes.getUnidadMedida());

        // Procesar imagen
        try {
            if (imagenFile != null && !imagenFile.isEmpty()) {
                String nombreImagen = System.currentTimeMillis() + "_" + imagenFile.getOriginalFilename();
                Path ruta = Paths.get(UPLOAD_DIR + nombreImagen);
                Files.write(ruta, imagenFile.getBytes());
                existente.setImagen(nombreImagen);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        abarrotesService.guardar(existente);
        return "redirect:/admin/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        abarrotesService.eliminar(id);
        return "redirect:/admin/productos";
    }
}
