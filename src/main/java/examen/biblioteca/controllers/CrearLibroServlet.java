package examen.biblioteca.controllers;

import examen.biblioteca.model.Libro;
import examen.biblioteca.repository.GenericDAO;
import examen.biblioteca.repository.LibroDAO;
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


@WebServlet("/libros/crear")
public class CrearLibroServlet extends HttpServlet {

    private GenericDAO<Libro,Long> libroDAO;


    @Override
    public void init(ServletConfig config) throws ServletException {

        try {
            libroDAO = new LibroDAO();
        } catch (SQLException e) {
            throw new ServletException("error inicializando DAO",e);
        }
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // de GenericDAO obtenemos todos los libros
        List<Libro> libros = new ArrayList<>();
        // esta lista de libros vacia añado los libros de la base de datos
        try {
            libros = libroDAO.findAll();
            request.setAttribute("libros",libros);
            getServletContext().getRequestDispatcher("/formularioLibro.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // recojo los datos del formulario, lo que el usuario ingrea
        String titulo = request.getParameter("titulo");
        int autor = Integer.parseInt(request.getParameter("autor"));
        LocalDate fecha= LocalDate.parse(request.getParameter("fechaPublicacion"));

    // estos datos, las variables, las meto al libro nuevo


        Libro libro = new Libro(titulo,autor,fecha);
    //ahora lo guardo en la base de datos
        try {
            libroDAO.save(libro);
            // hago redirect para que me devuelva a la lista de libros, a otro servlet
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        response.sendRedirect(request.getContextPath()+"/libros/list");


    }
}
