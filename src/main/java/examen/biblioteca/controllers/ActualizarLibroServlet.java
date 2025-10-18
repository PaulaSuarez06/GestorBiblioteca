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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebServlet("/libros/editar")
public class ActualizarLibroServlet extends HttpServlet {
    GenericDAO<Libro, Long> libroDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            libroDAO = new LibroDAO();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        super.init(config);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        try {
            Optional<Libro> libroOptional = libroDAO.findById(Long.parseLong(id));
            if(libroOptional.isPresent()){
                request.setAttribute("libro",libroOptional.get());
                getServletContext().getRequestDispatcher("/formularioLibro.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {

            String titulo = request.getParameter("titulo");
            String autor = request.getParameter("autor");
            LocalDate fecha= LocalDate.parse(request.getParameter("fechaPublicacion"));

            libroDAO.update(new Libro(titulo,autor,fecha));

        } catch (SQLException e) {
            throw new RuntimeException(e);


        }


        response.sendRedirect(request.getContextPath() + "/libros/list");

    }
}
