<!-- Formulario Autor -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-header bg-primary text-white text-center">
                    <h4>Registrar Autor</h4>
                </div>
                <div class="card-body">




                    <form action="<%=request.getContextPath()%>/libros/<%=accion%>" method="post">

                        <!-- Campo oculto para el ID (autoincremental) -->
                        <input type="hidden" name="idAutor" value="${autor.idAutor}">

                        <!-- Nombre del autor -->
                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre del Autor</label>
                            <input
                                    type="text"
                                    class="form-control"
                                    id="nombre"
                                    name="nombre"
                                    placeholder="Ej: Gabriel García Márquez"
                                    value="${autor.nombre}"
                                    required
                            >
                        </div>

                        <!-- Botones de acción -->
                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-success">Guardar</button>
                            <a href="listaAutores.jsp" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

