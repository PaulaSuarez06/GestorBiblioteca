<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="examen.biblioteca.model.Libro" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Libros</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">
<div class="container mt-5">

    <!-- Encabezado -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h1 class="text-primary">📚 Lista de Libros</h1>
        <div class="d-flex gap-2">
            <a href="<%= request.getContextPath() %>/libros/crear" class="btn btn-success">➕ Añadir libro</a>
            <a href="<%= request.getContextPath() %>/index.jsp" class="btn btn-outline-secondary">Volver al inicio</a>
        </div>
    </div>

    <% // traigo la lista de libros con java
        List <Libro> libros = (List<Libro>)request.getAttribute("libros");

        if (libros != null && !libros.isEmpty()){
    %>

    <!-- Tabla -->
    <div class="card shadow-sm">
        <div class="card-body">
            <table class="table table-hover align-middle w-100">
                <thead class="table-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Título</th>
                    <th scope="col">Autor</th>
                    <th scope="col">Fecha de publicación</th>
                    <th scope="col">Editar</th>
                    <th scope="col">Borrar</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (Libro l :libros){
                %>

                <tr>
                    <td><%= l.getId() %></td>
                    <td><%= l.getTitulo() %></td>
                    <td><%= l.getAutor() %></td>
                    <td><%= l.getFechaPublicacion() %></td>
                    <td>
                        <form action="<%=request.getContextPath()%>/libros/editar" method="get">
                            <input type="hidden" name="id" value="<%= l.getId() %>">
                            <button type="submit" class="btn btn-warning btn-sm">✏️</button>
                        </form>
                    </td>
                    <td>
                        <form action="<%=request.getContextPath()%>/libros/borrar" method="post" onsubmit="return confirm('¿Seguro que deseas borrar este producto?');">
                            <input type="hidden" name="id" value="<%= l.getId() %>">
                            <button type="submit" class="btn btn-danger btn-sm">🗑️</button>
                        </form>
                    </td>
                </tr>

                <% } %>
                </tbody>
            </table>
            <% } %>
        </div>
    </div>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
