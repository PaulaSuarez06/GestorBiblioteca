<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<%@ page import="jakarta.servlet.http.Cookie" %>
<%
    String lang = "es"; // idioma por defecto

    if (request.getCookies() != null) {
        for (Cookie c : request.getCookies()) {
            if ("lang".equals(c.getName())) {
                lang = c.getValue();
                break;
            }
        }
    }
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Biblioteca JDBC</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #f0f4f8, #e6ebef);
            font-family: 'Segoe UI', sans-serif;
        }
        .hero {
            text-align: center;
            padding: 3rem 1rem;
        }
        .hero h1 {
            font-weight: 700;
            color: #0d6efd;
        }
        .card-container {
            display: flex;
            justify-content: center;
            gap: 2rem;
            flex-wrap: wrap;
        }
        .card {
            width: 280px;
            border: none;
            border-radius: 1rem;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            transition: transform 0.2s ease-in-out;
        }
        .card:hover {
            transform: translateY(-5px);
        }
        .btn-custom {
            border-radius: 20px;
        }
        footer {
            text-align: center;
            color: #888;
            margin-top: 3rem;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-dark bg-dark px-4 mb-4">
    <div class="d-flex align-items-center justify-content-end mb-3" style="gap: 10px;">
        <form action="<%= request.getContextPath() %>/idioma" method="get" class="d-flex align-items-center">
            <label for="idioma" class="form-label me-2 fw-bold">
                <%= lang.equals("en") ? "Language:" : "Idioma:" %>
            </label>

            <select id="idioma" name="lang" class="form-select form-select-sm me-2" style="width: 150px;">
                <option value="es" <%= lang.equals("es") ? "selected" : "" %>>Español</option>
                <option value="en" <%= lang.equals("en") ? "selected" : "" %>>Inglés</option>
            </select>

            <!-- 🔘 Botón de envío -->
            <button type="submit" class="btn btn-primary btn-sm">
                <%= lang.equals("en") ? "Change" : "Cambiar" %>
            </button>
        </form>
    </div>
</nav>
<div class="container mt-5">




<section class="hero">
<h2><%= lang.equals("en") ? "Library Management" : "Gestión de Biblioteca" %></h2>
        <p class="lead text-secondary"><%= lang.equals("en") ? "Easy books and authors management" : "Gestión sencilla de autores y libros" %></p>


    </section>

    <div class="card-container">
        <!-- Card de Libros -->
        <div class="card text-center">
            <div class="card-body">
                <h4 class="card-title text-primary">📖 Libros</h4>
                <p class="card-text text-muted">Consulta, añade o edita libros de la biblioteca.</p>
                <a href="<%= request.getContextPath() %>/libros/list" class="btn btn-primary btn-custom">Gestionar libros</a>
            </div>
        </div>

        <!-- Card de Autores -->
        <div class="card text-center">
            <div class="card-body">
                <h4 class="card-title text-success">✍️ Autores</h4>
                <p class="card-text text-muted">Administra el catálogo de autores registrados.</p>
                <a href="<%= request.getContextPath() %>/autores/list" class="btn btn-success btn-custom">Gestionar autores</a>
            </div>
        </div>
    </div>

    <footer>
        <hr>
        <p class="small">Desarrollado con Servlets + JSP + JDBC</p>
    </footer>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>