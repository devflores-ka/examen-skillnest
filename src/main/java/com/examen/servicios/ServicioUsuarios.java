package com.examen.servicios;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.examen.modelos.LoginUsuario;
import com.examen.modelos.Usuario;
import com.examen.repositorios.RepositorioUsuarios;

@Service
public class ServicioUsuarios {
	
	@Autowired
	private RepositorioUsuarios repoUsuarios;
	
	public Usuario registrar(Usuario nuevoUsuario, BindingResult result) {
	    // Si ya hay errores de validación (ej: nombre/apellido vacíos), salimos de inmediato
	    if (result.hasErrors()) {
	        return null;
	    }

	    if (nuevoUsuario == null) {
	        result.reject("nuevoUsuario", "El usuario no puede ser nulo");
	        return null;
	    }

	    String password = nuevoUsuario.getPassword();
	    String confirmacion = nuevoUsuario.getConfirmacion();

	    if (password == null || confirmacion == null || !password.equals(confirmacion)) {
	        result.rejectValue("confirmacion", "Matches", "Las contraseñas no coinciden.");
	    }

	    String email = nuevoUsuario.getEmail();
	    if (email == null || email.isBlank()) {
	        result.rejectValue("email", "Unique", "El correo electrónico es obligatorio.");
	    } else {
	        Usuario existeUsuario = repoUsuarios.findByEmail(email);
	        if (existeUsuario != null) {
	            result.rejectValue("email", "Unique", "El correo ya está registrado.");
	        }
	    }

	    // Volvemos a revisar si hay errores antes de guardar
	    if (result.hasErrors()) {
	        return null;
	    }

	    String passwordHasheado = BCrypt.hashpw(password, BCrypt.gensalt());
	    nuevoUsuario.setPassword(passwordHasheado);

	    return repoUsuarios.save(nuevoUsuario);
	}


	
	public Usuario login(LoginUsuario loginUsuario, BindingResult result) {
		
		String email = loginUsuario.getEmailLogin();
		Usuario existeUsuario = repoUsuarios.findByEmail(email);
		if(existeUsuario == null) {
			result.rejectValue("emailLogin", "Unique", "Correo electrónico no registrado");
		} else if(! BCrypt.checkpw(loginUsuario.getPasswordLogin(), existeUsuario.getPassword() ) ) {
			result.rejectValue("passwordLogin", "Matches", "Contraseña incorrecta.");
		}
		
		if(result.hasErrors()) {
			return null;
		} else {
			return existeUsuario;
		}
	}
	
	//Método que me regrese todos los usuarios
	public List<Usuario> todosUsuarios(){
		return repoUsuarios.findAll();
	}
		
	//Método que guarde un usuario. Nuevos registros y Actualizar registros
	public Usuario guardarUsuario(Usuario nuevoUsuario) {
		return repoUsuarios.save(nuevoUsuario);
	}
		
	//Método que busque un usuario en base a su id
	public Usuario buscarUsuario(Long id) {
		return repoUsuarios.findById(id).orElse(null);
	}
		
	public Optional<Usuario> buscarUsuario2(Long id) {
		return repoUsuarios.findById(id);
	}
		
	//Método para borrar usuario en base a su id
	public void borrarUsuario(Long id) {
		repoUsuarios.deleteById(id);
	}

}
