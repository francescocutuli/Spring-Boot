package dao;

import java.io.IOException;
import java.util.List;

public interface Dao<T> {

    T add(T obj) throws IOException;
    List<T> findAll() throws IOException;
    T findById(int id) throws IOException;
    void update(int id, T obj) throws IOException;
    void delete(int id)throws IOException;
    void deleteAll()throws IOException;
}