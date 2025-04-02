package com.examen.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.examen.modelos.LoginUsuario;
import com.examen.modelos.Usuario;
import com.examen.servicios.ServicioUsuarios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorUsuarios {
	
	@Autowired
	private ServicioUsuarios servicioUsuario;
	
	@GetMapping("/")
	public String index(@ModelAttribute("nuevoUsuario") Usuario nuevoUsuario,
					    @ModelAttribute("loginUsuario") LoginUsuario loginUsuario) {
		return "index";
	}
	
	@PostMapping("/registro")
	public String registro(@Valid @ModelAttribute("nuevoUsuario") Usuario nuevoUsuario,
								  BindingResult result,
								  Model model,
								  HttpSession session) {
		
		servicioUsuario.registrar(nuevoUsuario, result);
		
		if(result.hasErrors()) {
			model.addAttribute("loginUsuario", new LoginUsuario());
			return "index";
		} else {
			session.setAttribute("usuarioEnSesion", nuevoUsuario);
			return "redirect:/dashboard";
		}
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
								BindingResult result,
								Model model,
								HttpSession session) {
		
		Usuario usuarioIntentandoLogin = servicioUsuario.login(loginUsuario, result);
		if(result.hasErrors()) {
			model.addAttribute("nuevoUsuario", new Usuario());
			return "index";
		} else {
			session.setAttribute("usuarioEnSesion", usuarioIntentandoLogin);
			return "redirect:/dashboard";
		}
		
	}
	
	@GetMapping("/formulario-login")
	public String formularioLogin(@ModelAttribute("nuevoUsuario") Usuario nuevoUsuario,
		    					  @ModelAttribute("loginUsuario") LoginUsuario loginUsuario){
		return "formulario-login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("usuarioEnSesion");
		session.invalidate();
		return "redirect:/";
	}
	
}
