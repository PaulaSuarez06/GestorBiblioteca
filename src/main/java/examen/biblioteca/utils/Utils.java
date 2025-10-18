package examen.biblioteca.utils;

import examen.biblioteca.model.Autor;

import java.util.List;

public class Utils {


    public static String obtenerNombreAutores (List<Autor> autores, Long id){

        return autores.stream()
                .filter(autor -> autor.getId().equals(id))
                .findFirst()
                .map(Autor::getNombre)
                .orElse("Desconocido");
    }
}
