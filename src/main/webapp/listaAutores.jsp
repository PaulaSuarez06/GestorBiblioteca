<%@ page import="examen.biblioteca.model.Autor" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Autores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

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
                <thead class="table-primary">
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
