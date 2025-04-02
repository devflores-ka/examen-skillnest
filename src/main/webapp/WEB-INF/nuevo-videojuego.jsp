<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nuevo Videojuego</title>
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
            <li class="breadcrumb-item active" aria-current="page">Nuevo Videojuego</li>
        </ol>
    </nav>

    <div class="card">
        <div class="card-header">
            <h1>Registrar nuevo videojuego</h1>
        </div>
        <div class="card-body">
            <form:form action="/videojuegos/crear" method="post" modelAttribute="videojuego">
                <div class="mb-3">
                    <form:label path="titulo" class="form-label">Título:</form:label>
                    <form:input path="titulo" class="form-control" />
                    <form:errors path="titulo" class="text-danger" />
                </div>
                
                <div class="mb-3">
                    <form:label path="genero" class="form-label">Género:</form:label>
                    <form:input path="genero" class="form-control" />
                    <form:errors path="genero" class="text-danger" />
                </div>
                
                <div class="mb-3">
                    <form:label path="plataforma" class="form-label">Plataforma:</form:label>
                    <form:input path="plataforma" class="form-control" />
                    <form:errors path="plataforma" class="text-danger" />
                </div>
                
                <div class="mb-3">
                    <form:label path="desarrollador" class="form-label">Desarrollador:</form:label>
                    <form:input path="desarrollador" class="form-control" />
                    <form:errors path="desarrollador" class="text-danger" />
                </div>
                
                <div class="mb-3">
                    <form:label path="urlImagen" class="form-label">URL de la imagen:</form:label>
                    <form:input path="urlImagen" class="form-control" />
                    <form:errors path="urlImagen" class="text-danger" />
                    <small class="form-text text-muted">Ingresa una URL válida para la imagen del videojuego.</small>
                </div>
                
                <div class="mb-3">
                    <form:label path="lanzamiento" class="form-label">Fecha de lanzamiento:</form:label>
                    <form:input type="date" path="lanzamiento" class="form-control" />
                    <form:errors path="lanzamiento" class="text-danger" />
                </div>
                
                <button type="submit" class="btn btn-primary">Registrar videojuego</button>
            </form:form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>