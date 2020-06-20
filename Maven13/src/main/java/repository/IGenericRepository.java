package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IGenericRepository<T> {
    ArrayList<T> GetAll() throws SQLException;
    void Add(T obj) throws SQLException;
}
