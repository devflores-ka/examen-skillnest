package com.examen.modelos;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Ingresa tu nombre.")
	@Size(min=3, max=100, message="El nombre debe tener al menos 3 caracteres.")
	@Column(nullable=false)
	private String nombre;
	
	@NotBlank(message="Ingresa tu apellido.")
	@Size(min=3, max=100, message="El apellido debe tener al menos 3 caracteres.")
	@Column(nullable=false)
	private String apellido;
	
	@NotBlank(message="Ingresa tu correo electrónico.")
	@Email(message="Ingresa un correo electrónico válido.")
	@Column(nullable=false)
	private String email;
	
	@Size(min=8, message="La contraseña debe tener al menos 8 caracteres.")
	@Pattern(
	    regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
	    message="La contraseña debe contener al menos un número, una minúscula y una mayúscula."
	)
	@Column(nullable=false)
	private String password;
	
	@Transient
	private String confirmacion;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@OneToMany(mappedBy="creador", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Videojuego> videojuego;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Resena> resena;
	
	public List<Resena> getResena() { return resena; }
	public void setResena(List<Resena> resena) { this.resena = resena; }
	
	public Usuario() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(String confirmation) {
		this.confirmacion = confirmation;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public List<Videojuego> getVideojuego() {
	    return videojuego;
	}

	public void setVideojuego(List<Videojuego> videojuego) {
	    this.videojuego = videojuego;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
}
