<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nueva Reseña para ${videojuego.titulo}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

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

<div class="container mt-4">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="/dashboard">Dashboard</a></li>
            <li class="breadcrumb-item"><a href="/videojuegos/${videojuego.id}">Detalles</a></li>
            <li class="breadcrumb-item active" aria-current="page">Nueva Reseña</li>
        </ol>
    </nav>

    <div class="card">
        <div class="card-header">
            <h1>Escribir reseña para: ${videojuego.titulo}</h1>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-4">
                    <img src="${videojuego.urlImagen}" alt="${videojuego.titulo}" class="img-fluid rounded">
                </div>
                <div class="col-md-8">
                    <form:form action="/videojuegos/${videojuego.id}/resenas/crear" method="post" modelAttribute="resena">
                        <div class="mb-3">
                            <form:label path="calificacion" class="form-label">Calificación (1-5):</form:label>
                            <form:select path="calificacion" class="form-select">
                                <form:option value="1">1 - Muy malo</form:option>
                                <form:option value="2">2 - Malo</form:option>
                                <form:option value="3">3 - Regular</form:option>
                                <form:option value="4">4 - Bueno</form:option>
                                <form:option value="5">5 - Excelente</form:option>
                            </form:select>
                            <form:errors path="calificacion" class="text-danger" />
                        </div>
                        
                        <div class="mb-3">
                            <form:label path="contenido" class="form-label">Tu reseña:</form:label>
                            <form:textarea path="contenido" class="form-control" rows="5" placeholder="Escribe aquí tu reseña..."></form:textarea>
                            <form:errors path="contenido" class="text-danger" />
                        </div>
                        
                        <button type="submit" class="btn btn-primary">Publicar reseña</button>
                        <a href="/videojuegos/<c:out value="${videojuego.id}"/>" class="btn btn-primary btn-sm">Volver al detalle</a>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>