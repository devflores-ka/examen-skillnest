<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
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
    
    <div class="row">
        <div class="col-12">
            <p class="fw-bold fs-5 mb-4">Bienvenido de vuelta, ${usuarioEnSesion.nombre} ${usuarioEnSesion.apellido}</p>
        </div>
    </div>

    <div class="row row-cols-1 row-cols-md-3 g-4">
        <c:forEach var="videojuego" items="${videojuegos}">
            <div class="col">
                <div class="card h-100">
                    <img src="${videojuego.urlImagen}" class="card-img-top" alt="${videojuego.titulo}">
                    <div class="card-body">
                        <h5 class="card-title">${videojuego.titulo}</h5>
                        <p class="card-text">
                            <strong>Género:</strong> ${videojuego.genero}<br>
                            <strong>Plataforma:</strong> ${videojuego.plataforma}<br>
                            <strong>Desarrollador:</strong> ${videojuego.desarrollador}<br>
                            <c:if test="${videojuego.lanzamiento != null}">
                                <strong>Lanzamiento:</strong> <fmt:formatDate value="${videojuego.lanzamiento}" pattern="dd/MM/yyyy"/>
                            </c:if>
                            <p><strong>Calificación:</strong> ${videojuego.calificacionPromedio}/5</p>
                        </p>
                    </div>
                    <div class="card-footer d-flex justify-content-between">
                        <a href="/videojuegos/${videojuego.id}" class="btn btn-primary">Ver detalles</a>
                        
                        <!-- Solo mostrar opciones de editar/eliminar si el usuario es el creador -->
                        <c:if test="${videojuego.creador.id == usuarioEnSesion.id}">
                            <div>
                                <a href="/videojuegos/editar/${videojuego.id}" class="btn btn-warning">Editar</a>
                                <form action="/videojuegos/eliminar/${videojuego.id}" method="post" class="d-inline">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <button type="submit" class="btn btn-danger">Eliminar</button>
                                </form>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    
    <!-- Si no hay videojuegos, mostrar mensaje -->
    <c:if test="${empty videojuegos}">
        <div class="alert alert-info mt-4">
            No hay videojuegos disponibles. ¡Sé el primero en agregar uno!
        </div>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>