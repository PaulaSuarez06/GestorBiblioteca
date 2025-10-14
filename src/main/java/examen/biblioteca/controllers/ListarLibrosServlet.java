package examen.biblioteca.controllers;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examen.biblioteca.model.Libro;
import examen.biblioteca.repository.GenericDAO;
import examen.biblioteca.repository.LibroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "ListarLibrosServlet", value ="/libros/list")
public class ListarLibrosServlet extends HttpServlet {
    private String message;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Libro> libros = new ArrayList<>();

        try {
            GenericDAO<Libro, Long> daoLibro = new LibroDAO();
            libros = daoLibro.findAll();

        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }
        request.setAttribute("libros",libros);
        getServletContext().getRequestDispatcher("/listaLibros.jsp").forward(request,response);


}}