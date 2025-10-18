package examen.biblioteca.controllers;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examen.biblioteca.model.Autor;
import examen.biblioteca.model.Libro;
import examen.biblioteca.repository.AutorDAO;
import examen.biblioteca.repository.GenericDAO;
import examen.biblioteca.repository.LibroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
@WebServlet(name = "ListarLibrosServlet", value ={"/libros/list", "/autores/list"})
public class ListarServlet extends HttpServlet {
    private String message;



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String path = request.getServletPath();

        if(path.equals("/libros/list")){

        List<Libro> libros = new ArrayList<>();
        List<Autor> autores = new ArrayList<>();



        try {
            GenericDAO<Libro, Long> daoLibro = new LibroDAO();
            GenericDAO<Autor, Long> daoAutor = new AutorDAO();
            libros = daoLibro.findAll();
            autores = daoAutor.findAll();

        } catch (SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }
        request.setAttribute("libros",libros);
        request.setAttribute("autores",autores);
        getServletContext().getRequestDispatcher("/listaLibros.jsp").forward(request,response);
        }

        else if(path.equals("/autores/list")) {

        try {

            List <Autor> autores = new ArrayList<>();
            GenericDAO<Autor, Long> daoAutor = new AutorDAO();
            autores = daoAutor.findAll();
            request.setAttribute("autores",autores);
            getServletContext().getRequestDispatcher("/listaAutores.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }

        }




    }}