package examen.biblioteca.repository;

import examen.biblioteca.model.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroDAO implements GenericDAO<Libro,Long>{
    private Connection connection;

    public LibroDAO() throws SQLException{
        connection = DBConnection.getConnection();
    }



    @Override
    public void save(Libro entity) throws SQLException {

        String sql = "INSERT INTO Libro (title, author_id, publication_date) VALUES (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,entity.getTitulo());
            ps.setLong(2,entity.getIdAutor());
            ps.setDate(3,java.sql.Date.valueOf(entity.getFechaPublicacion()));
            ps.executeUpdate();
        }

    }

    @Override
    public Optional<Libro> findById(Long aLong) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Libro> findAll() throws SQLException {
        // crea lista vacia

        List <Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM Libro";


        try (PreparedStatement ps = connection.prepareStatement(sql)){
            //ejecuta la consulta, es un cursor
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                libros.add(new Libro(
                                    rs.getLong("id"),
                                    rs.getString("title"),
                                    rs.getLong("author_id"),
                                    rs.getDate("publication_date").toLocalDate()
                ));

            }
        }
        return libros;

    }

    @Override
    public void update(Libro entity) throws SQLException {

    }

    @Override
    public void delete(Long aLong) throws SQLException {

    String sql = "DELETE FROM Libro WHERE id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)){
        ps.setLong(1,aLong);
        ps.executeUpdate();
    }

    }
}
