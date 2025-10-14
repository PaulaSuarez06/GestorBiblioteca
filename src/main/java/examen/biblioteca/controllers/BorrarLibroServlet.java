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


@WebServlet("/libros/borrar")
public class BorrarLibroServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            long id = Long.parseLong(request.getParameter("id"));
            libroDAO.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect(request.getContextPath() + "/libros/list");
    }
}
