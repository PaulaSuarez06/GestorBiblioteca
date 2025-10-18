package examen.biblioteca.repository;

import examen.biblioteca.model.Autor;
import examen.biblioteca.model.Libro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutorDAO implements GenericDAO<Autor,Long>{

    private Connection connection;


    public AutorDAO() throws SQLException{
        connection = DBConnection.getConnection();
    }
    @Override
    public void save(Autor entity) throws SQLException {

        String sql = "INSERT INTO Autor (name) VALUES (?)";
        try (java.sql.Connection connection = DBConnection.getConnection();
             java.sql.PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,entity.getNombre());
            ps.executeUpdate();
        }

    }

    @Override
    public Optional<Autor> findById(Long aLong) throws SQLException {

        String sql = "SELECT * FROM Autor WHERE id = ?";
        try (java.sql.Connection connection = DBConnection.getConnection();
             java.sql.PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1,aLong);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return Optional.of(new Autor(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
            }
             }
         return Optional.empty();
        }

    @Override
    public List<Autor> findAll() throws SQLException {

        List <Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autor";
        try (java.sql.Connection connection = DBConnection.getConnection();
             java.sql.PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                autores.add(new Autor(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
            }
             }


    return autores;
    }

    @Override
    public void update(Autor entity) throws SQLException {

        String sql = "UPDATE Autor SET name = ? WHERE id = ?";
        try (java.sql.Connection connection = DBConnection.getConnection();
             java.sql.PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1,entity.getNombre());
            ps.setLong(2,entity.getId());
            ps.executeUpdate();
        }

    }

    @Override
    public void delete(Long aLong) throws SQLException {

        String sql = "DELETE FROM Autor WHERE id = ?";
        try (java.sql.Connection connection = DBConnection.getConnection();
             java.sql.PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1,aLong);
            ps.executeUpdate();
        }

    }
}
