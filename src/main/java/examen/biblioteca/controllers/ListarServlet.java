package examen.biblioteca.controllers;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
        if (path.equals("/libros/list")) {

            try {
                GenericDAO<Libro, Long> daoLibro = new LibroDAO();
                GenericDAO<Autor, Long> daoAutor = new AutorDAO();
                List<Libro> libros = daoLibro.findAll();
                List<Autor> autores = daoAutor.findAll();

                //--------------------->>>>   FILTRAR POR FECHA  <<<--------------------->>>>

                String fechaDesde = request.getParameter("fechaDesde");
                String fechaHasta = request.getParameter("fechaHasta");

                if (fechaDesde != null && !fechaDesde.isEmpty() && fechaHasta != null && !fechaHasta.isEmpty()) {
                    LocalDate desde = LocalDate.parse(fechaDesde);
                    LocalDate hasta = LocalDate.parse(fechaHasta);
                    List<Libro> librosFiltrados = new ArrayList<>();

                    for (Libro libro : libros) {
                        LocalDate fechaPublicacion = libro.getFechaPublicacion();
                        if (fechaPublicacion != null &&
                                !fechaPublicacion.isBefore(desde) &&
                                !fechaPublicacion.isAfter(hasta)) {
                            librosFiltrados.add(libro);
                        }
                    }
                    libros = librosFiltrados;
                }

                String autorIdParam = request.getParameter("autorId");
                if (autorIdParam != null && !autorIdParam.isEmpty()) {
                    Long autorId = Long.parseLong(autorIdParam);
                    List<Libro> filtrados = new ArrayList<>();
                    for (Libro l : libros) {
                        if (l.getId_autor() != null && l.getId_autor().equals(autorId)) {
                            filtrados.add(l);
                        }
                    }
                    libros = filtrados;
                }


                Collections.sort(autores);
                request.setAttribute("libros", libros);
                request.setAttribute("autores", autores);
                getServletContext().getRequestDispatcher("/listaLibros.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else if (path.equals("/autores/list")) {
        try {

            List <Autor> autores = new ArrayList<>();
            GenericDAO<Autor, Long> daoAutor = new AutorDAO();
            autores = daoAutor.findAll();


//--------------------->>>>   BUSCAR AUTORES POR NOMBRE <<<--------------------->>>>


            String nombre = request.getParameter("nombre");


            if(nombre != null && !nombre.isEmpty()){
                List<Autor> autoresFiltrados = new ArrayList<>();

                for(Autor autor : autores){
                    if(autor.getNombre().toLowerCase().contains(nombre.toLowerCase())){
                        autoresFiltrados.add(autor);
                    }
                }
                autores = autoresFiltrados;
            }


            Collections.sort(autores);
            request.setAttribute("autores",autores);
            getServletContext().getRequestDispatcher("/listaAutores.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }

        }




    }}