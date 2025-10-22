<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="examen.biblioteca.model.Libro" %>
<%@ page import="examen.biblioteca.model.Autor" %>

<html>
<head>
    <title>Buscar libros por autor</title>
</head>
<body>

<h1>Buscar libros por autor</h1>

<form action="${pageContext.request.contextPath}/libro/porAutor" method="get">
    <label for="idAutor">Selecciona un autor:</label>
    <select name="idAutor" id="idAutor">
        <option value="">-- Elige un autor --</option>
        <%
            List<Autor> autores = (List<Autor>) request.getAttribute("autores");
            if (autores != null) {
                for (Autor a : autores) {
        %>
        <option value="<%= a.getId() %>"
                <%= (request.getParameter("idAutor") != null &&
                        request.getParameter("idAutor").equals(String.valueOf(a.getId()))) ? "selected" : "" %>>
            <%= a.getNombre() %>
        </option>
        <%
                }
            }
        %>
    </select>
    <input type="submit" value="Buscar">
</form>

<%
    Autor autor = (Autor) request.getAttribute("autor");
    List<Libro> libros = (List<Libro>) request.getAttribute("libros");
%>

<% if (autor != null) { %>
<h2>Libros de <%= autor.getNombre() %></h2>
<% if (libros != null && !libros.isEmpty()) { %>
<ul>
    <% for (Libro l : libros) { %>
    <li><%= l.getTitulo() %> — publicado el <%= l.getFechaPublicacion() %></li>
    <% } %>
</ul>
<% } else { %>
<p>Este autor no tiene libros registrados.</p>
<% } %>
<% } %>

</body>
</html>

