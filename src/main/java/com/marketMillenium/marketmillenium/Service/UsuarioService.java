package com.marketMillenium.marketmillenium.Service;

import com.marketMillenium.marketmillenium.Dao.Usuario;
import com.marketMillenium.marketmillenium.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void guardar(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("ROLE_USER");
        }
        repo.save(usuario);
    }

    public Usuario buscarPorCorreo(String email) {
        return repo.findByEmail(email);
    }


    public List<Usuario> listarUsuarios() {
        return repo.findAll();
    }


    public void eliminarUsuario(Long id) {
        repo.deleteById(id);
    }


    public Optional<Usuario> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public void actualizarUsuario(Usuario usuario) {
        Usuario usuarioExistente = repo.findById(usuario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        /* Actualizar solo los campos editables*/
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        usuarioExistente.setDireccion(usuario.getDireccion());
        usuarioExistente.setTelefono(usuario.getTelefono());
        usuarioExistente.setRol(usuario.getRol());

        /* No modificar el password ni el email*/
        repo.save(usuarioExistente);
    }

    public long contarUsuarios() {
        return repo.count();
    }
}
