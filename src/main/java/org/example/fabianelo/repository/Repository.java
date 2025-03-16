package org.example.fabianelo.repository;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{
    public T findById(int id) throws SQLException;
    public List<T> findAll() throws SQLException;
    void save(T object) throws SQLException;
    void update(T object) throws SQLException;
    void delete(int id) throws SQLException;
}
