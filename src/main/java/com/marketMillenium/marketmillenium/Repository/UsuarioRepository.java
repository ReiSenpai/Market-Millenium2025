package com.marketMillenium.marketmillenium.Repository;

import com.marketMillenium.marketmillenium.Dao.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}