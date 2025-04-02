package com.examen.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examen.modelos.Videojuego;
import com.examen.repositorios.RepositorioVideojuegos;

@Service
public class ServicioVideojuegos {
    
    @Autowired
    private RepositorioVideojuegos repoVideojuego;
    
    //Método que me regrese todos los videojuegos
    public List<Videojuego> todosVideoJuego(){
        return repoVideojuego.findAll(); // Corregido de repoJugos a repoVideojuego
    }
    
    //Método que guarde un videojuego. Nuevos registros y Actualizar registros
    public Videojuego guardarVideojuego(Videojuego nuevoVideojuego) {
        return repoVideojuego.save(nuevoVideojuego);
    }
    
    //Método que busque un videojuego en base a su id
    public Videojuego buscarVideojuego(Long id) {
        return repoVideojuego.findById(id).orElse(null);
    }
    
    public Optional<Videojuego> buscarVideojuego2(Long id) {
        return repoVideojuego.findById(id);
    }
    
    //Método para borrar un videojuegos en base a su id
    public void borrarVideojuego(Long id) {
        repoVideojuego.deleteById(id);
    }
    
    //Método que regresa una página específica
    public Page<Videojuego> videojuegoPorPagina(int numPagina){
        //Numero de Página, Cantidad Registros, Acomodo, Atributo del acomodo
        PageRequest pageRequest = PageRequest.of(numPagina, 2, Sort.Direction.ASC, "titulo"); // Cambiado de "nombre" a "titulo"
        return repoVideojuego.findAll(pageRequest);
    }
    
    public List<Videojuego> obtenerVideojuegosOrdenadosPorCalificacion() {
        List<Videojuego> videojuegos = repoVideojuego.findAll();
        
        videojuegos.sort((v1, v2) -> Double.compare(v2.getCalificacionPromedio(), v1.getCalificacionPromedio())); 
        
        return videojuegos;
    }
}