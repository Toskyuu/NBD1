package repositories;

import java.util.List;

public interface IRepository<T> {

    T Find(Long id);
    void Add(T entity);
    void Delete(T entity);
    void Update(T entity);
    List<T> getAll();

}
