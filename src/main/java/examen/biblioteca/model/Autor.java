package examen.biblioteca.model;

import java.math.BigInteger;
import java.util.Objects;

public class Autor {
    private long id;
    private String nombre;

    public Autor() {
    }


    public Autor(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return id == autor.id && Objects.equals(nombre, autor.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
