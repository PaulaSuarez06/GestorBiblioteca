package examen.biblioteca.controllers; // Paquete donde está este servlet

// Importo las clases necesarias del proyecto y de Java
import examen.biblioteca.model.Autor;
import examen.biblioteca.model.Libro;
import examen.biblioteca.repository.AutorDAO;
import examen.biblioteca.repository.GenericDAO;
import examen.biblioteca.repository.LibroDAO;
import examen.biblioteca.utils.Validador;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Este servlet se encarga de crear libros y autores
@WebServlet({"/libros/crear", "/autores/crear"})
public class CrearServlet extends HttpServlet {

    // Declaro los DAOs para poder acceder a la base de datos
    private GenericDAO<Libro,Long> libroDAO;
    private GenericDAO<Autor,Long> autorDAO;

    // Se ejecuta una sola vez al iniciar el servlet
    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            // Creo los objetos DAO (conexión con BD)
            libroDAO = new LibroDAO();
            autorDAO = new AutorDAO();
        } catch (SQLException e) {
            // Si falla la conexión, lanzo un error del tipo ServletException
            throw new ServletException("error inicializando DAO", e);
        }
        super.init(config); // Llamo al init original del padre (HttpServlet)
    }

    // Maneja las peticiones GET (cuando el usuario entra al formulario)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtengo la ruta del servlet que se está usando (/libros/crear o /autores/crear)
        String path = request.getServletPath();

        // Si el usuario entra a /libros/crear
        if (path.equals("/libros/crear")) {

            // Creo listas vacías de libros y autores
            List<Libro> libros = new ArrayList<>();
            List<Autor> autores = new ArrayList<>();

            try {
                // Lleno las listas consultando la base de datos
                libros = libroDAO.findAll();
                autores = autorDAO.findAll();

                // Guardo los datos como atributos del request (para usarlos en el JSP)
                request.setAttribute("libros", libros);
                request.setAttribute("autores", autores);

                // Redirijo (forward) al JSP que muestra el formulario para crear libros
                getServletContext().getRequestDispatcher("/formularioLibro.jsp").forward(request, response);

            } catch (SQLException e) {
                // Si algo sale mal con la BD, lanzo un RuntimeException
                throw new RuntimeException(e);
            }
        }

        // Si el usuario entra a /autores/crear
        else if (path.equals("/autores/crear")) {

            try {
                // Recupero todos los autores existentes
                List<Autor> autores = autorDAO.findAll();

                // Paso la lista al JSP como atributo
                request.setAttribute("autores", autores);

                // Redirijo al formulario para crear autores
                getServletContext().getRequestDispatcher("/formularioAutor.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Maneja las peticiones POST (cuando el usuario envía el formulario)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Vuelvo a obtener la ruta para saber qué formulario se envió
        String path = request.getServletPath();

        // Si el formulario enviado es el de libros
        if (path.equals("/libros/crear")) {

            // Recojo los datos del formulario
            String titulo = request.getParameter("titulo"); // campo del título
            String autor = request.getParameter("id_autor"); // id del autor
            String fechaStr = request.getParameter("fechaPublicacion"); // fecha como texto

            // ⚠️ No parseo la fecha aún, primero la valido

            // VALIDACIONES -------------------------------

            // Compruebo que el título tenga entre 2 y 100 caracteres
            if (!Validador.esTextoValido(titulo, 2, 100)) {
                request.setAttribute("errorTexto", "El título debe tener entre 2 y 100 caracteres.");
                request.getRequestDispatcher("/formularioLibro.jsp").forward(request, response);
                return; // corto la ejecución si hay error
            }

            // Compruebo que la fecha tenga formato válido (yyyy-MM-dd)
            if (!Validador.esFecha(fechaStr)) {
                request.setAttribute("errorFecha", "La fecha debe tener el formato yyyy-MM-dd.");
                request.getRequestDispatcher("/formularioLibro.jsp").forward(request, response);
                return;
            }

            // Convierto la fecha a LocalDate (ahora ya sé que es válida)
            LocalDate fecha = LocalDate.parse(fechaStr);

            // Creo el objeto Libro con los datos del formulario
            Libro libro = new Libro(null, fecha, Long.parseLong(autor), titulo);

            try {
                // Guardo el nuevo libro en la base de datos
                libroDAO.save(libro);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Redirijo a la lista de libros (otra página/servlet)
            response.sendRedirect(request.getContextPath() + "/libros/list");
        }

        // Si el formulario enviado es el de autores
        else if (path.equals("/autores/crear")) {

            // Recojo el nombre del formulario
            String nombre = request.getParameter("nombre");

            // Creo un objeto Autor con ese nombre
            Autor autor = new Autor(null, nombre);

            try {
                // Guardo el nuevo autor en la base de datos
                autorDAO.save(autor);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Redirijo a la lista de autores
            response.sendRedirect(request.getContextPath() + "/autores/list");
        }
    }
}
