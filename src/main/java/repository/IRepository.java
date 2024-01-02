package repository;

import java.util.ArrayList;
import java.util.List;

public interface IRepository<T> extends AutoCloseable {
    T findById(int id);
    void add(T entity);
    void remove(T entity);
    void update(T entity);
    List<T> findAll();
}
