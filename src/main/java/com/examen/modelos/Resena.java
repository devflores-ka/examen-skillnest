package com.examen.modelos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="resenas")
public class Resena {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Ingresa tu reseña.")
	@Size(min=2, max=100, message="La reseña debe tener al menos 3 caracteres.")
	private String contenido;
	
	@Min(value = 1, message = "La calificación mínima es 1.")
	@Max(value = 5, message = "La calificación máxima es 5.")
	private int calificacion;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "videojuego_id")
	private Videojuego videojuego;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Resena() {}

	// Getters y Setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getContenido() { return contenido; }
	public void setContenido(String contenido) { this.contenido = contenido; }

	public int getCalificacion() { return calificacion; }
	public void setCalificacion(int calificacion) { this.calificacion = calificacion; }

	public Date getCreatedAt() { return createdAt; }
	public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

	public Date getUpdatedAt() { return updatedAt; }
	public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

	public Videojuego getVideojuego() { return videojuego; }
	public void setVideojuego(Videojuego videojuego) { this.videojuego = videojuego; }

	public Usuario getUsuario() { return usuario; }
	public void setUsuario(Usuario usuario) { this.usuario = usuario; }

	@PrePersist
	protected void onCreate() { this.createdAt = new Date(); }

	@PreUpdate
	protected void onUpdate() { this.updatedAt = new Date(); }
}
