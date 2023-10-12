import java.util.ArrayList;

public interface IRepository<T> {
    T getByID(int id);
    void Add(T object);
    void Update(T object);
    void Remove(T object);
}
