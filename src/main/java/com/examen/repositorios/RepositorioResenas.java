package com.examen.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.modelos.Resena;

@Repository
public interface RepositorioResenas extends JpaRepository<Resena, Long> {
    
    List<Resena> findAll();
    
    List<Resena> findByVideojuegoId(Long idVideojuego);
    
    List<Resena> findByUsuarioId(Long idUsuario);
}