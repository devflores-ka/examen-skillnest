package com.examen.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examen.modelos.Videojuego;

@Repository
public interface RepositorioVideojuegos extends JpaRepository <Videojuego, Long>{
	
	List<Videojuego> findAll();

}
