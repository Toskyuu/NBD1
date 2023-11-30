package repositories;

import java.util.List;

public interface IRepository<T> extends AutoCloseable {

    T findById(int id);
    void add(T entity);
    void remove(int id);
    void update(T entity);
    List<T> findAll();
}
