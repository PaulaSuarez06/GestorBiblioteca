package examen.biblioteca.model;

import java.time.LocalDate;
import java.util.Objects;

public class Libro {

    private Long id;
    private String titulo;
    private Long id_autor;
    private LocalDate fechaPublicacion;


    public Libro() {
    }

    public Libro(Long id, LocalDate fechaPublicacion, Long id_autor, String titulo) {
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.id_autor = id_autor;
        this.titulo = titulo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId_autor() {
        return id_autor;
    }

    public void setId_autor(Long id_autor) {
        this.id_autor = id_autor;
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
                ", id_autor=" + id_autor +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(id, libro.id) && Objects.equals(titulo, libro.titulo) && Objects.equals(id_autor, libro.id_autor) && Objects.equals(fechaPublicacion, libro.fechaPublicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, id_autor, fechaPublicacion);
    }
}

