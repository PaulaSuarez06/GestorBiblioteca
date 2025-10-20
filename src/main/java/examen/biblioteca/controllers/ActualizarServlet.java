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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@WebServlet({"/libros/editar" , "/autores/editar"})
public class ActualizarServlet extends HttpServlet {
    GenericDAO<Libro, Long> libroDAO;
    GenericDAO<Autor, Long> autorDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            libroDAO = new LibroDAO();
            autorDAO = new AutorDAO();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        super.init(config);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        if(path.equals("/libros/editar")){

        String id = request.getParameter("id");

        try {
            Optional<Libro> libroOptional = libroDAO.findById(Long.parseLong(id));

            if(libroOptional.isPresent()){
                request.setAttribute("libro",libroOptional.get());

                List<Autor> autores = autorDAO.findAll();
                request.setAttribute("autores",autores);

                getServletContext().getRequestDispatcher("/formularioLibro.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }


        else if(path.equals("/autores/editar")){

            String id = request.getParameter("id");

            try {
                Optional<Autor> autorOptional = autorDAO.findById(Long.parseLong(id));

                if(autorOptional.isPresent()){
                    request.setAttribute("autor",autorOptional.get());

                    getServletContext().getRequestDispatcher("/formularioAutor.jsp").forward(request,response);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }




        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        if (path.equals("/libros/editar")) {
            try {

                Long id = Long.parseLong(request.getParameter("id"));
                String titulo = request.getParameter("titulo");
                Long id_autor = Long.parseLong(request.getParameter("id_autor"));
                LocalDate fechaPublicacion = LocalDate.parse(request.getParameter("fechaPublicacion"), DateTimeFormatter.ISO_DATE);

                Libro libro = new Libro(id, fechaPublicacion, id_autor, titulo);


                libroDAO.update(libro);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            response.sendRedirect(request.getContextPath() + "/libros/list");
        } else if (path.equals("/autores/editar")) {
            try {

                Long id = Long.parseLong(request.getParameter("idAutor"));
                String nombre = request.getParameter("nombre");
                Autor autor = new Autor(id, nombre);
                autorDAO.update(autor);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect(request.getContextPath() + "/autores/list");
        }


    }

}