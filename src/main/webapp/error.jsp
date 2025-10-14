<%--
  Created by IntelliJ IDEA.
  User: paula
  Date: 13/10/2025
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Error</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Iconos Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .error-card {
            max-width: 600px;
            width: 90%;
            text-align: center;
            border: none;
            border-radius: 1rem;
            box-shadow: 0 5px 20px rgba(0,0,0,0.1);
        }

        .error-icon {
            font-size: 5rem;
            color: #dc3545;
        }

        .btn-home {
            margin-top: 1.5rem;
        }
    </style>
</head>

<body>

<div class="card error-card p-5">
    <i class="bi bi-exclamation-triangle-fill error-icon"></i>
    <h2 class="mt-3 text-danger">¡Ups! Ha ocurrido un error</h2>

    <p class="text-muted mt-2">
        <strong>Mensaje:</strong>
        <br>
        ${mensajeError != null ? mensajeError : "Algo salió mal al procesar tu solicitud."}
    </p>

    <a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-outline-primary btn-home">
        <i class="bi bi-arrow-left-circle"></i> Volver al inicio
    </a>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
