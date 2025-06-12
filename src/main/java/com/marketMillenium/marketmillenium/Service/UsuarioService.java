package com.marketMillenium.marketmillenium.Service;

import com.marketMillenium.marketmillenium.Dao.Usuario;
import com.marketMillenium.marketmillenium.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public void guardar(Usuario usuario) {
        repo.save(usuario);
    }

    public Usuario buscarPorCorreo(String correo) {
        return repo.findByEmail(correo);
    }

}