package examen.biblioteca.repository;

import examen.biblioteca.model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroDAO implements GenericDAO<Libro, Long> {
    private Connection connection;

    public LibroDAO() throws SQLException {
        connection = DBConnection.getConnection();
    }


    @Override
    public void save(Libro entity) throws SQLException {

    }

    @Override
    public Optional<Libro> findById(Long aLong) throws SQLException {
        String sql = "SELECT * FROM Libro WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1,aLong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Libro libro = new Libro(
                        rs.getLong("id"),
                        rs.getObject("publication_date", LocalDate.class),
                        rs.getLong("author_id"),
                        rs.getString("title")
                ); return Optional.of(libro);
            }

        }



        return Optional.empty();
    }

    @Override
    public List<Libro> findAll() throws SQLException {
        List <Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libro";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            libros.add(new Libro(
                    rs.getLong("id"),
                    rs.getDate("publication_date").toLocalDate(),
                    rs.getLong("author_id"),
                    rs.getString("title")
            ));
        }
        return libros;
    }

    @Override
    public void update(Libro entity) throws SQLException {
        String sql = "UPDATE Libro SET fechaPublicacion = ?, id_autor = ?, titulo = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setDate(1,java.sql.Date.valueOf(entity.getFechaPublicacion()));
            ps.setLong(2,entity.getId_autor());
            ps.setString(3,entity.getTitulo());
            ps.setLong(4,entity.getId());
            ps.executeUpdate();
        }

    }

    @Override
    public void delete(Long aLong) throws SQLException {

    }
}
