package com.examen.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examen.modelos.Resena;
import com.examen.repositorios.RepositorioResenas;

@Service
public class ServicioResenas {
    
    @Autowired
    private RepositorioResenas repoResena;
    
    // Obtener todas las reseñas
    public List<Resena> todasResenas() {
        return repoResena.findAll();
    }
    
    // Guardar una reseña
    public Resena guardarResena(Resena nuevaResena) {
        return repoResena.save(nuevaResena);
    }
    
    // Buscar una reseña por ID
    public Resena buscarResena(Long id) {
        return repoResena.findById(id).orElse(null);
    }
    
    // Método alternativo para buscar reseña
    public Optional<Resena> buscarResena2(Long id) {
        return repoResena.findById(id);
    }
    
    // Borrar una reseña
    public void borrarResena(Long id) {
        repoResena.deleteById(id);
    }
    
    // Buscar reseñas por videojuego
    public List<Resena> resenasPorVideojuego(Long idVideojuego) {
        return repoResena.findByVideojuegoId(idVideojuego);
    }
    
    // Buscar reseñas por usuario
    public List<Resena> resenasPorUsuario(Long idUsuario) {
        return repoResena.findByUsuarioId(idUsuario);
    }
}