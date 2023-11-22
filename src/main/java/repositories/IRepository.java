package repositories;

import java.util.List;
import java.util.UUID;

public interface IRepository<T> {

    T FindById(UUID id);
    void Add(T entity);
    void Remove(T entity);
    void Update(T entity);
    List<T> FindAll();
}
