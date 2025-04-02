package com.examen.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.examen.modelos.Resena;
import com.examen.modelos.Usuario;
import com.examen.modelos.Videojuego;
import com.examen.servicios.ServicioResenas;
import com.examen.servicios.ServicioVideojuegos;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorResenas {

    @Autowired
    private ServicioResenas servicioResenas;
    
    @Autowired
    private ServicioVideojuegos servicioVideojuegos;
    
    // Formulario para crear una nueva reseña
    @GetMapping("/videojuegos/{idVideojuego}/resenas/nueva")
    public String nuevaResena(@PathVariable("idVideojuego") Long idVideojuego,
                             @ModelAttribute("resena") Resena resena,
                             Model model,
                             HttpSession session) {
        
        if(session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        
        Videojuego videojuego = servicioVideojuegos.buscarVideojuego(idVideojuego);
        
        if(videojuego == null) {
            return "redirect:/dashboard";
        }
        
        model.addAttribute("videojuego", videojuego);
        return "nueva-resena";
    }
    
    // Procesar la creación de una nueva reseña
    @PostMapping("/videojuegos/{idVideojuego}/resenas/crear")
    public String crearResena(@PathVariable("idVideojuego") Long idVideojuego,
                             @Valid @ModelAttribute("resena") Resena nuevaResena,
                             BindingResult result,
                             Model model,
                             HttpSession session) {
        
        if(session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        
        Videojuego videojuego = servicioVideojuegos.buscarVideojuego(idVideojuego);
        
        if(videojuego == null) {
            return "redirect:/dashboard";
        }
        
        if(result.hasErrors()) {
            model.addAttribute("videojuego", videojuego);
            return "nueva-resena";
        } else {
            Usuario usuario = (Usuario) session.getAttribute("usuarioEnSesion");
            
            // Asignar la reseña al videojuego y al usuario
            nuevaResena.setVideojuego(videojuego);
            nuevaResena.setUsuario(usuario);
            
            // Guardar la reseña
            servicioResenas.guardarResena(nuevaResena);
            
            return "redirect:/videojuegos/" + idVideojuego;
        }
    }
    
    // Eliminar una reseña (solo el creador puede borrarla)
    @DeleteMapping("/resenas/eliminar/{id}")
    public String eliminarResena(@PathVariable("id") Long id,
                                HttpSession session) {
        
        if(session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        
        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        Resena resena = servicioResenas.buscarResena(id);
        
        if(resena == null) {
            return "redirect:/dashboard";
        }
        
        // Verificar que el usuario es el creador de la reseña
        if(resena.getUsuario().getId().equals(usuarioEnSesion.getId())) {
            Long idVideojuego = resena.getVideojuego().getId();
            servicioResenas.borrarResena(id);
            return "redirect:/videojuegos/" + idVideojuego;
        } else {
            return "redirect:/dashboard";
        }
    }
}