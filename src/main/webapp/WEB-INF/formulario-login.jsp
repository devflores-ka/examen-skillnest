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
        <div class="row">
            <div class="col-6">
                <h2>Iniciar sesión</h2>
                <form:form action="/login" method="POST" modelAttribute="loginUsuario">
                    <div>
                        <form:label path="emailLogin">Correo electrónico:</form:label>
                        <form:input path="emailLogin" class="form-control" />
                        <form:errors path="emailLogin" class="text-danger" />
                    </div>
                    <div>
                        <form:label path="passwordLogin">Contraseña:</form:label>
                        <form:input path="passwordLogin" class="form-control" />
                        <form:errors path="passwordLogin" class="text-danger" />
                    </div>
                    <input type="submit" class="btn btn-info mt-3" value="Inicar sesión">
                </form:form>
            </div>
        </div>
    </div>

</body>
</html>