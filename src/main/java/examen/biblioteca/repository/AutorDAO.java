package examen.biblioteca.repository;

import examen.biblioteca.model.Autor;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AutorDAO implements GenericDAO<Autor,Long>{
    @Override
    public void save(Autor entity) throws SQLException {

    }

    @Override
    public Optional<Autor> findById(Long aLong) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Autor> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public void update(Autor entity) throws SQLException {

    }

    @Override
    public void delete(Long aLong) throws SQLException {

    }
}
