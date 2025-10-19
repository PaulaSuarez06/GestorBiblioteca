<%@ page import="examen.biblioteca.model.Autor" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.Cookie" %>
<!DOCTYPE html>
<%
    String color = "table-light";

    if (request.getCookies() != null) {
        for (Cookie c : request.getCookies()) {
            if ("color".equals(c.getName())) {
                switch (c.getValue()) {
                    case "light":
                        color = "table-light";
                        break;
                    case "dark":
                        color = "table-dark";
                        break;
                    case "primary":
                        color = "table-primary";
                        break;
                    case "success":
                        color = "table-success";
                        break;
                    default:
                        color = "table-light";
                        break;
                }
            }
        }
    }
%>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Autores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-dark px-4 mb-4">
    <div class="container-fluid d-flex justify-content-between align-items-center">
        <h1 class="text-light fs-4 mb-0">Biblioteca</h1>

        <form action="<%= request.getContextPath() %>/color" method="get" class="d-flex align-items-center">
            <label for="color" class="form-label me-2 text-light fw-bold mb-0">Color de tabla:</label>
            <select id="color" name="color" class="form-select form-select-sm me-2" style="width: 150px;">
                <option value="light"<%= color.equals("table-light") ? "selected" : "" %>>Claro</option>
                <option value="dark" <%= color.equals("table-dark") ? "selected": "" %>>Oscuro</option>
                <option value="primary"<%= color.equals("table-primary") ? "selected": "" %>>Azul</option>>
                <option value="success"<%= color.equals("table-success") ? "selected": "" %>>Verde</option>>

            </select>
            <button type="submit" class="btn btn-outline-light btn-sm">Cambiar</button>
        </form>
    </div>
</nav>

<div class="container py-5">

    <!-- Encabezado -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary mb-0">Autores registrados</h2>
        <div>
            <a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-outline-secondary me-2">Volver al inicio</a>
            <a href="<%= request.getContextPath() %>/autores/crear" class="btn btn-success">Añadir nuevo autor</a>
        </div>
    </div>

    <%
        List<Autor> autores = (List<Autor>) request.getAttribute("autores");
        if (autores != null && !autores.isEmpty()) {
    %>

    <!-- Tabla de autores -->
    <div class="card shadow-sm">
        <div class="card-body">
            <table class="table table-hover table-bordered align-middle text-center">
                <thead class="<%= color %>">
                <tr>
                    <th>Nombre</th>
                    <th>Editar</th>
                    <th>Borrar</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Autor autor : autores) {
                %>
                <tr>
                    <!-- ID oculto -->
                    <input type="hidden" name="id" value="<%= autor.getId() %>">

                    <td><%= autor.getNombre() %></td>
                    <td>
                        <form action="<%=request.getContextPath()%>/autores/editar" method="get">
                            <input type="hidden" name="id" value="<%= autor.getId() %>">
                            <button type="submit" class="btn btn-warning btn-sm">✏️</button>
                        </form>
                    </td>
                    <td>
                        <form action="<%=request.getContextPath()%>/autores/borrar" method="post"
                              onsubmit="return confirm('¿Seguro que deseas borrar este autor?');">
                            <input type="hidden" name="id" value="<%= autor.getId() %>">
                            <button type="submit" class="btn btn-danger btn-sm">🗑️</button>
                        </form>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <% } else { %>
    <div class="alert alert-info text-center" role="alert">
        No hay autores registrados.
    </div>
    <% } %>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
