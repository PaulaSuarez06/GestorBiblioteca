<%@ page import="examen.biblioteca.model.Autor" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<!-- Formulario Autor -->


<%
    //                        primero me traigo al autor
    Autor autor = (Autor) request.getAttribute("autor");
//                        creo un flag para si es edicion el autor no me viene nulo, o sea que existe
    boolean esEdicion = autor != null;
//                     creo el titulo dinamico, el autor existe? pues editar, si no existe, creo nuevo autor
    String titulo = esEdicion ? "Editar Autor" : "Nuevo Autor";
//                    creo la accion, si esEdicion seria editar, y si no es seria crear
    String accion = esEdicion ? "editar" : "crear";


%>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white text-center">
                    <h4><%=titulo%></h4>
                </div>
                <div class="card-body">
                    <form action="<%=request.getContextPath()%>/autores/<%=accion%>" method="post">

                        <!-- Campo oculto para el ID (autoincremental) -->
                        <input type="hidden" name="idAutor" value="${autor.getId()}">

                        <!-- Nombre del autor -->
                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre del Autor</label>
                            <input
                                    type="text"
                                    class="form-control"
                                    id="nombre"
                                    name="nombre"
                                    placeholder="Ej: Gabriel García Márquez"
                                    value="${autor.getNombre() != null ? autor.getNombre() : ''}"
                                    required
                            >
                        </div>

                        <!-- Botones de acción -->
                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-success">Guardar</button>
                            <a href="<%= request.getContextPath() %>/autores/list" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</div>

