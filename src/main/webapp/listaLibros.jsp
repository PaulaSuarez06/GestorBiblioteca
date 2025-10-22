<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="examen.biblioteca.model.Libro" %>
<%@ page import="examen.biblioteca.model.Autor" %>
<%@ page import="examen.biblioteca.utils.Utils" %>
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

            <!-- Filtro por fecha de publicación -->
            <form action="<%= request.getContextPath() %>/libros/list" method="get" class="row g-3 mb-4">
                <div class="col-md-4">
                    <label for="fechaDesde" class="form-label">Desde:</label>
                    <input type="date" class="form-control" id="fechaDesde" name="fechaDesde"
                           value="<%= request.getParameter("fechaDesde") != null ? request.getParameter("fechaDesde") : "" %>">
                </div>
                <div class="col-md-4">
                    <label for="fechaHasta" class="form-label">Hasta:</label>
                    <input type="date" class="form-control" id="fechaHasta" name="fechaHasta"
                           value="<%= request.getParameter("fechaHasta") != null ? request.getParameter("fechaHasta") : "" %>">
                </div>
                <div class="col-md-4 d-flex align-items-end">
                    <button type="submit" class="btn btn-primary w-100">📅 Filtrar por fecha</button>
                </div>
            </form>
        </div>
    </div>

    <%
        List <Autor>  autores = (List<Autor>)request.getAttribute("autores");
    %>
    <!------------------------- Filtro por autor ---------------------------->
    <form action="<%= request.getContextPath() %>/libros/list" method="get" class="row g-3 mb-4">
        <div class="col-md-6">
            <label for="autor" class="form-label">Filtrar por autor:</label>
            <select id="autor" name="autorId" class="form-select">
                <option value="">-- Todos --</option>
                <%
                    for (Autor a : autores) {
                        String selected = (request.getParameter("autorId") != null
                                && request.getParameter("autorId").equals(String.valueOf(a.getId()))) ? "selected" : "";
                %>
                <option value="<%= a.getId() %>" <%= selected %>><%= a.getNombre() %></option>
                <% } %>
            </select>
        </div>
        <div class="col-md-4 d-flex align-items-end">
            <button type="submit" class="btn btn-primary w-100">🔍 Filtrar</button>
        </div>
    </form>




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
                    <th scope="col">Filtrar por fecha</th>
                    <th scope="col">Editar</th>
                    <th scope="col">Borrar</th>
                </tr>
                </thead>
                <tbody>

<%----------------------    PARA SACAR EL NOMBRE DEL AUTOR------------------------------------%>
                <%

                    for (Libro l :libros){
                       String nombreAutor = "";
                    for(Autor a: autores){
                        if(a.getId().equals(l.getId_autor())) {
                            nombreAutor = a.getNombre();
                            break;
                    }
                    }
                %>


                <tr>
                    <td><%= l.getId() %></td>
                    <td><%= l.getTitulo() %></td>
                    <td><%= nombreAutor %></td>
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
