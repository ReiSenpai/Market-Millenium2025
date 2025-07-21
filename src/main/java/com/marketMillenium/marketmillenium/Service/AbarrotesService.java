package com.marketMillenium.marketmillenium.Service;

import com.marketMillenium.marketmillenium.Dao.Abarrotes;
import com.marketMillenium.marketmillenium.Repository.AbarroteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbarrotesService {

    @Autowired
    private AbarroteRepository abarroteRepository;

    public List<Abarrotes> listarTodos() {
        return abarroteRepository.findAll();
    }

    public void guardar(Abarrotes abarrotes) {
        abarroteRepository.save(abarrotes);
    }

    public Abarrotes buscarPorId(Long id) {
        return abarroteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + id));
    }

    public void eliminar(Long id) {
        abarroteRepository.deleteById(id);
    }
}

