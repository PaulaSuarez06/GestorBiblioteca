<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="examen.biblioteca.model.Libro" %>
<%@ page import="examen.biblioteca.model.Autor" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Libro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<%
    List<Autor> autores = (List<Autor>) request.getAttribute("autores");
    Libro libro = (Libro) request.getAttribute("libro");
    boolean esEdicion = libro != null; // o sea que el libro tiene que existir
    String titulo = esEdicion ? "Editar libro" : "Crear nuevo libro"; // entonces si exite puedo editar, si no creo uno
    String accion = esEdicion ? "editar" : "crear";
%>


<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-body">
            <h1 class="text-primary mb-4"><%= titulo%>
            </h1>

            <form action="<%=request.getContextPath()%>/libros/<%=accion%>" method="post">

                <%-- ✅ Campo oculto solo si estamos editando --%>
                <% if (esEdicion) { %>
                <input type="hidden" name="id" value="<%= libro.getId() %>">
                <% } %>

                <div class="mb-3">
                    <label for="titulo" class="form-label fw-bold">Título</label>
                    <input type="text" id="titulo" name="titulo" class="form-control"
                           required placeholder="Introduce el título del libro"
                           value="<%= esEdicion ? libro.getTitulo() : "" %>">
                    <%
                        String error = (String) request.getAttribute("ErrorTexto");
                        if (error != null) {
                    %>
                    <p style="color: red;"><%= error %></p>
                    <%
                        }
                    %>
                </div>


                <div class="mb-3">
                    <label for="autor" class="form-label fw-bold">Autor</label>
                    <select id="autor" name="id_autor" class="form-select" required>
                        <option value="">Selecciona un autor</option>
                        <%
                            if (autores != null && !autores.isEmpty()) {
                                for (Autor autor : autores) {
                                    boolean seleccionado = esEdicion && autor.getId().equals(libro.getId_autor());
                        %>
                        <option value="<%= autor.getId() %>" <%= seleccionado ? "selected" : "" %>>
                            <%= autor.getNombre() %>
                        </option>
                        <%
                            }
                        } else {
                        %>
                        <option disabled>No hay autores disponibles</option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="fechaPublicacion" class="form-label fw-bold">Fecha de publicación</label>
                    <input type="date" id="fechaPublicacion" name="fechaPublicacion" class="form-control"
                           value="<%= esEdicion && libro.getFechaPublicacion() != null ? libro.getFechaPublicacion().toString() : "" %>">
                </div>
                    <%
                        String error2 = (String) request.getAttribute("errorFecha");
                        if (error2 != null) {
                    %>
                    <p style="color: red;"><%= error2 %></p>
                    <%
                        }
                    %>


                <div class="d-flex justify-content-between mt-4">
                    <a href="<%= request.getContextPath() %>/libros/list" class="btn btn-outline-secondary">Volver</a>
                    <button type="submit" class="btn btn-success">Guardar cambios</button>
                </div>
            </form>

        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
