package examen.biblioteca.model;

import java.math.BigInteger;
import java.util.Objects;

public class Autor {
    private Long id;
    private String nombre;

    public Autor() {
    }


    public Autor(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Autor other = (Autor) obj;
        return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);


    }
}
