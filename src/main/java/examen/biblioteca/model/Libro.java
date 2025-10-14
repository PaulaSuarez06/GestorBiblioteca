package examen.biblioteca.model;

import java.time.LocalDate;
import java.util.Objects;

public class Libro {

    private long id;
    private String titulo;
    private long idAutor;
    private LocalDate fechaPublicacion;

    public Libro() {
    }

    public Libro(long id, String titulo, long idAutor, LocalDate fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Libro(String titulo, long idAutor, LocalDate fechaPublicacion) {
        this.titulo = titulo;
        this.idAutor = idAutor;
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

    public long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(long idAutor) {
        this.idAutor = idAutor;
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
                ", idAutor=" + idAutor +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return id == libro.id && idAutor == libro.idAutor && Objects.equals(titulo, libro.titulo) && Objects.equals(fechaPublicacion, libro.fechaPublicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, idAutor, fechaPublicacion);
    }
}
