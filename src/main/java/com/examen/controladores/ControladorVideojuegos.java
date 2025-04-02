package com.examen.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.examen.modelos.Usuario;
import com.examen.modelos.Videojuego;
import com.examen.servicios.ServicioVideojuegos;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorVideojuegos {
    
    @Autowired
    private ServicioVideojuegos servicioVideojuegos;
    
    // Mostrar todos los videojuegos (dashboard)
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Verificar que el usuario está en sesión
        if(session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        
        // Obtener todos los videojuegos
        List<Videojuego> listaVideojuegos = servicioVideojuegos.obtenerVideojuegosOrdenadosPorCalificacion();
        model.addAttribute("videojuegos", listaVideojuegos);
        
        // Obtener el usuario en sesión para verificaciones de permisos en la vista
        model.addAttribute("usuario", session.getAttribute("usuarioEnSesion"));
        
        return "dashboard";
    }
    
    // Formulario para crear un nuevo videojuego
    @GetMapping("/videojuegos/nuevo")
    public String nuevoVideojuego(@ModelAttribute("videojuego") Videojuego videojuego,
                                 HttpSession session) {
        // Verificar que el usuario está en sesión
        if(session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        
        return "nuevo-videojuego";
    }
    
    // Procesar la creación de un nuevo videojuego
    @PostMapping("/videojuegos/crear")
    public String crearVideojuego(@Valid @ModelAttribute("videojuego") Videojuego nuevoVideojuego,
                                 BindingResult result,
                                 HttpSession session) {
        
        if(session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        
        if(result.hasErrors()) {
            return "nuevo-videojuego";
        } else {
            // Asignar el usuario creador
            Usuario creador = (Usuario) session.getAttribute("usuarioEnSesion");
            nuevoVideojuego.setCreador(creador);
            
            // Guardar el videojuego
            servicioVideojuegos.guardarVideojuego(nuevoVideojuego);
            return "redirect:/dashboard";
        }
    }
    
    // Mostrar un videojuego específico con sus reseñas
    @GetMapping("/videojuegos/{id}")
    public String mostrarVideojuego(@PathVariable("id") Long id,
                                   Model model,
                                   HttpSession session) {
        
        if(session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        
        Videojuego videojuego = servicioVideojuegos.buscarVideojuego(id);
        
        if(videojuego == null) {
            return "redirect:/dashboard";
        }
        
        model.addAttribute("videojuego", videojuego);
        model.addAttribute("usuario", session.getAttribute("usuarioEnSesion"));
        
        return "detalle-videojuego";
    }
    
    // Formulario para editar un videojuego
    @GetMapping("/videojuegos/editar/{id}")
    public String editarVideojuego(@PathVariable("id") Long id,
                                  Model model,
                                  HttpSession session) {
        
        if(session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        
        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        Videojuego videojuego = servicioVideojuegos.buscarVideojuego(id);
        
        // Verificar que el videojuego existe
        if(videojuego == null) {
            return "redirect:/dashboard";
        }
        
        // Verificar que el usuario es el creador
        if(!videojuego.getCreador().getId().equals(usuarioEnSesion.getId())) {
            return "redirect:/dashboard";
        }
        
        model.addAttribute("videojuego", videojuego);
        return "editar-videojuego";
    }
    
    // Procesar la actualización de un videojuego
    @PutMapping("/videojuegos/actualizar/{id}")
    public String actualizarVideojuego(@Valid @ModelAttribute("videojuego") Videojuego videojuegoEditado,
                                      BindingResult result,
                                      @PathVariable("id") Long id,
                                      HttpSession session) {
        
        if(session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        
        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        Videojuego videojuegoOriginal = servicioVideojuegos.buscarVideojuego(id);
        
        // Verificar que el videojuego existe y el usuario es el creador
        if(videojuegoOriginal == null || !videojuegoOriginal.getCreador().getId().equals(usuarioEnSesion.getId())) {
            return "redirect:/dashboard";
        }
        
        if(result.hasErrors()) {
            return "editar-videojuego";
        } else {
            // Mantener el creador original
            videojuegoEditado.setCreador(usuarioEnSesion);
            servicioVideojuegos.guardarVideojuego(videojuegoEditado);
            return "redirect:/videojuegos/" + id;
        }
    }
    
    // Eliminar un videojuego
    @DeleteMapping("/videojuegos/eliminar/{id}")
    public String eliminarVideojuego(@PathVariable("id") Long id,
                                    HttpSession session) {
        
        if(session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        
        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        Videojuego videojuego = servicioVideojuegos.buscarVideojuego(id);
        
        // Verificar que el videojuego existe y el usuario es el creador
        if(videojuego != null && videojuego.getCreador().getId().equals(usuarioEnSesion.getId())) {
            servicioVideojuegos.borrarVideojuego(id);
        }
        
        return "redirect:/dashboard";
    }
}
