<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicia sesión</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<header>
			<div class="row">
				<div class="col-sm">
					<h1>Jugos</h1>
				</div>
				<div class="col-sm">
					<a href="/formulario-login" class="btn" >Login</a>
					<a href="/" class="btn">Registro</a>
				</div>
			</div>
		</header>
            <div class="col-6">
                <h2>Registrar</h2>
                <form:form action="/registro" method="POST" modelAttribute="nuevoUsuario">
                    <div>
                        <form:label path="nombre">Nombre:</form:label>
                        <form:input path="nombre" class="form-control" />
                        <form:errors path="nombre" class="text-danger" />
                    </div>
                    <div>
                        <form:label path="apellido">Apellido:</form:label>
                        <form:input path="apellido" class="form-control" />
                        <form:errors path="apellido" class="text-danger" />
                    </div>
                    <div>
                        <form:label path="email">Correo electrónico:</form:label>
                        <form:input path="email" class="form-control" />
                        <form:errors path="email" class="text-danger" />
                    </div>
                    <div>
                        <form:label path="password">Contraseña:</form:label>
                        <form:input path="password" class="form-control" />
                        <form:errors path="password" class="text-danger" />
                    </div>
                    <div>
                        <form:label path="confirmacion">Confirmar contraseña:</form:label>
                        <form:input path="confirmacion" class="form-control" />
                        <form:errors path="confirmacion" class="text-danger" />
                    </div>
                    <input type="submit" class="btn btn-primary mt-3" value="Registrarme" >
                </form:form>
            </div>
        </div>

</body>
</html>