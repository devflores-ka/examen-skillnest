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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="videojuegos")
public class Videojuego {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Ingresa un título.")
	@Size(min=5, max=100, message="El título debe tener al menos 5 caracteres.")
	private String titulo;
	
	@NotBlank(message="Ingresa un género.")
	@Size(min=5, max=100, message="El genero debe tener al menos 5 caracteres.")
	private String genero;
	
	@NotBlank(message="Ingresa la plataforma.")
	@Size(min=3, max=100, message="La plataforma deben tener al menos 3 caracteres.")
	private String plataforma;
	
	@NotBlank(message="Ingresa el desarrollador.")
	@Size(min=5, max=100, message="El desarrollador deben tener al menos 5 caracteres.")
	private String desarrollador;
	
	@NotBlank(message="Ingresa la URL dela imagen.")
	@Size(min=2, max=200, message="La URL debe tener al menos 10 caracteres.")
	@Pattern(regexp="https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)", message="Ingresa una URL válida.")
	private String urlImagen;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date lanzamiento;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario creador;
	
	@OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Resena> reseñas;
	
	@OneToMany(mappedBy = "videojuego", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resena> resenas;

    public double getCalificacionPromedio() {
        if (resenas == null || resenas.isEmpty()) {
            return 0.0; // Si no hay reseñas, el promedio es 0
        }
        double suma = 0;
        for (Resena resena : resenas) {
            suma += resena.getCalificacion();
        }
        return suma / resenas.size();
    }
	
	public Videojuego() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getDesarrollador() {
		return desarrollador;
	}

	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
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
	};
	
	public Date getLanzamiento() {
		return lanzamiento;
	}

	public void setLanzamiento(Date lanzamiento) {
		this.lanzamiento = lanzamiento;
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	public List<Resena> getReseñas() {
		return reseñas;
	}

	public void setReseñas(List<Resena> reseñas) {
		this.reseñas = reseñas;
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
