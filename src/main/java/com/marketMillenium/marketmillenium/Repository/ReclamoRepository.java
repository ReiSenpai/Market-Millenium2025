package com.marketMillenium.marketmillenium.Repository;

import com.marketMillenium.marketmillenium.Dao.Reclamo;
import com.marketMillenium.marketmillenium.Dao.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
    List<Reclamo> findByUsuario(Usuario usuario);
}