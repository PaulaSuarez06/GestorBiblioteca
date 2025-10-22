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
import java.util.List;
import java.util.Optional;


//➡ En listaAutores.jsp haces clic → va a /libro/porAutor
//➡Ese servlet filtra los libros por el idAutor
//➡  Muestra buscar.jsp con los resultados
//➡ Y si quieres volver a la lista, el botón “Volver” llama de nuevo al servlet /autores/list.

//------------AQUI SE PONE LA RUTA DEL SERVLET----------------
@WebServlet("/libro/porAutor")
public class BuscarServlet extends HttpServlet {
//AQUI VA EL INIT GENERICO, METER LAS DOS LISTAS POR LAS DUDAS, VA A PEDIR MEZCLAR

    GenericDAO<Libro, Long> libroDAO;
    GenericDAO<Autor, Long> autorDAO;
    //------ INICIALIZAR LOS DAOS PROPIOS!!!!!!!!!!!!! ------
    private LibroDAO libroDAO1;
//------ INICIALIZAR LOS DAOS  ------
    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            libroDAO = new LibroDAO();
            autorDAO = new AutorDAO();
            //------ INICIALIZAR LOS DAOS PROPIOS!!!!!!!!!!!!! ------
            libroDAO1 = new LibroDAO();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        super.init(config);
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //-----------DAO PROPIO ENVUELTO EN TRY CATCH---------------------------//
        try {
            // Leo el parámetro idAutor de la URL
            Long idAutor = Long.parseLong(request.getParameter("idAutor"));

            // Uso el DAO específico para buscar por autor
            List<Libro> libros = libroDAO1.findAllByAuthor(idAutor);

            // busco el autor para obtener el nombre

            Optional<Autor> autorOpt = autorDAO.findById(idAutor);
            if (autorOpt.isPresent()) {
                Autor autor = autorOpt.get();
                request.setAttribute("autor", autor);
            }else{
                request.setAttribute("errorAutor", "el autor no existe");
            }

            // Paso la lista al JSP
            request.setAttribute("libros", libros);

            // Redirijo al JSP que muestra los resultados
            getServletContext().getRequestDispatcher("/buscar.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
