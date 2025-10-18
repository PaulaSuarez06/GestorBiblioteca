package examen.biblioteca.model;

import java.time.LocalDate;
import java.util.Objects;

public class Libro {

    private long id;
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;

    public Libro(String titulo, String autor, LocalDate fecha) {
    }

    public Libro(long id, String titulo, String autor, LocalDate fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return id == libro.id && Objects.equals(titulo, libro.titulo) && Objects.equals(autor, libro.autor) && Objects.equals(fechaPublicacion, libro.fechaPublicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, autor, fechaPublicacion);
    }
}


