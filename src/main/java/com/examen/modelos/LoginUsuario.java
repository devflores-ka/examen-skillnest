package com.examen.modelos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

//NO ENTITY, NOTHING, JUST GET-USE-STORAGE-TOKEN
public class LoginUsuario {
	
	@NotBlank(message="El correo electrónico es obligatorio.")
	@Email(message="Ingresa un correo electrónico válido.")
	private String emailLogin;
	
	@NotBlank(message="La contraseña es obligatoria.")
	private String passwordLogin;
	
	public LoginUsuario() {}

	public String getEmailLogin() {
		return emailLogin;
	}

	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	public String getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}
	
	
	
}
