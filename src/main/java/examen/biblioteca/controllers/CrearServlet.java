package examen.biblioteca.controllers;

import examen.biblioteca.model.Autor;
import examen.biblioteca.model.Libro;
import examen.biblioteca.repository.AutorDAO;
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


@WebServlet({"/libros/crear", "/autores/crear"})
public class CrearServlet extends HttpServlet {

    private GenericDAO<Libro,Long> libroDAO;
    private GenericDAO<Autor,Long> autorDAO;


    @Override
    public void init(ServletConfig config) throws ServletException {

        try {
            libroDAO = new LibroDAO();
            autorDAO = new AutorDAO();
        } catch (SQLException e) {
            throw new ServletException("error inicializando DAO",e);
        }
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String path = request.getServletPath();

     if(path.equals("/libros/crear")){
        List<Libro> libros = new ArrayList<>();

        try {
            libros = libroDAO.findAll();
            request.setAttribute("libros",libros);
            getServletContext().getRequestDispatcher("/formularioLibro.jsp").forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     } else if (path.equals("/autores/crear")){

         try {
             List<Autor> autores = new ArrayList<>();
             autores = autorDAO.findAll();
             request.setAttribute("autores",autores);
             getServletContext().getRequestDispatcher("/formularioAutor.jsp").forward(request,response);
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }

     }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // recojo los datos del formulario, lo que el usuario ingrea
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        LocalDate fecha= LocalDate.parse(request.getParameter("fechaPublicacion"));

    // estos datos, las variables, las meto al libro nuevo


        Libro libro = new Libro(null,fecha,Long.parseLong(autor),titulo);
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
