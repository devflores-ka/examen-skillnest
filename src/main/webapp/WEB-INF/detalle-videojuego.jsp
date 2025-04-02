<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${videojuego.titulo} - Detalles</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<div class="container mt-4">
	
	<header class="mb-4">
        <div class="row align-items-center">
            <div class="col-sm-6">
                <h1>Videojuegos</h1>
            </div>
            <div class="col-sm-6 text-end">
            	<a href="/dashboard" class="btn btn-primary btn-sm">Todos los videojuegos</a>
            	<a href="/videojuegos/nuevo" class="btn btn-primary btn-sm">Agregar videojuego</a>
                <a href="/logout" class="btn btn-secondary btn-sm">Cerrar sesión</a>
                
            </div>
        </div>
    </header>

    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard">Dashboard</a></li>
            <li class="breadcrumb-item active" aria-current="page">Detalles del videojuego</li>
        </ol>
    </nav>

    <div class="row">
        <div class="col-md-4">
            <img src="${videojuego.urlImagen}" alt="${videojuego.titulo}" class="img-fluid rounded">
        </div>
        <div class="col-md-8">
            <h1>${videojuego.titulo}</h1>
            
            <div class="card mb-4">
                <div class="card-body">
                    <p><strong>Género:</strong> ${videojuego.genero}</p>
                    <p><strong>Plataforma:</strong> ${videojuego.plataforma}</p>
                    <p><strong>Desarrollador:</strong> ${videojuego.desarrollador}</p>
                    <c:if test="${videojuego.lanzamiento != null}">
                        <p><strong>Fecha de lanzamiento:</strong> <fmt:formatDate value="${videojuego.lanzamiento}" pattern="dd/MM/yyyy"/></p>
                    </c:if>
                    <p><strong>Agregado por:</strong> ${videojuego.creador.nombre} ${videojuego.creador.apellido}</p>
                    <p><strong>Fecha de registro:</strong> <fmt:formatDate value="${videojuego.createdAt}" pattern="dd/MM/yyyy"/></p>
                </div>
                
                <!-- Solo mostrar opciones de editar/eliminar si el usuario es el creador -->
                <c:if test="${videojuego.creador.id == usuarioEnSesion.id}">
                    <div class="card-footer d-flex gap-2">
                        <a href="/videojuegos/editar/${videojuego.id}" class="btn btn-warning">Editar</a>
                        <form action="/videojuegos/eliminar/${videojuego.id}" method="post">
                            <input type="hidden" name="_method" value="DELETE">
                            <button type="submit" class="btn btn-danger">Eliminar</button>
                        </form>
                    </div>
                </c:if>
            </div>
            
            <!-- Sección de reseñas -->
<div class="mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Reseñas</h2>
        
        <c:set var="usuarioYaReseño" value="false"/>
        <c:forEach var="resena" items="${videojuego.reseñas}">
            <c:if test="${resena.usuario.id == usuarioEnSesion.id}">
                <c:set var="usuarioYaReseño" value="true"/>
            </c:if>
        </c:forEach>
        
        <c:if test="${!usuarioYaReseño}">
            <a href="/videojuegos/${videojuego.id}/resenas/nueva" class="btn btn-success">Escribir una reseña</a>
        </c:if>
    </div>

    <c:if test="${empty videojuego.reseñas}">
        <div class="alert alert-info">
            No hay reseñas para este videojuego. ¡Sé el primero en dejar tu opinión!
        </div>
    </c:if>

    <div class="list-group">
        <c:forEach var="resena" items="${videojuego.reseñas}">
            <div class="list-group-item">
                <div class="d-flex justify-content-between">
                    <h5 class="mb-1">${resena.usuario.nombre} ${resena.usuario.apellido}</h5>
                    <div>
                        <span class="badge bg-primary rounded-pill">
                            ${resena.calificacion}/5
                        </span>
                        <small class="text-muted ms-2">
                            <fmt:formatDate value="${resena.createdAt}" pattern="dd/MM/yyyy"/>
                        </small>
                    </div>
                </div>
                <p class="mb-1">${resena.contenido}</p>

                <!-- Solo mostrar eliminar si el usuario es el creador de la reseña -->
                <c:if test="${resena.usuario.id == usuarioEnSesion.id}">
                    <div class="mt-2 text-end">
                        <form action="/resenas/eliminar/${resena.id}" method="post">
                            <input type="hidden" name="_method" value="DELETE">
                            <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
                        </form>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>