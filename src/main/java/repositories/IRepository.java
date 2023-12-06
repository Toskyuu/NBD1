package repositories;

import java.util.List;

public interface IRepository<T> extends AutoCloseable {

    T findById(int id);
    boolean add(T entity);
    boolean remove(int id);
    boolean update(T entity);
    List<T> findAll();
}
