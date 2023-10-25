package repositories;

import jakarta.persistence.*;
import mainClasses.Movie;
import java.util.List;


public class MovieRepository implements IRepository<Movie>{

    private final EntityManager entityManager;

    public MovieRepository() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void Add(Movie movie) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(movie);
        transaction.commit();
    }

    @Override
    public void Delete(Movie movie) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(movie);
        transaction.commit();
    }

    // update movie or add movie to database if not exist
    @Override
    public void Update(Movie movie) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(movie);
        transaction.commit();
    }

    @Override
    public Movie Find(Long id) {
        return entityManager.find(Movie.class, id);
    }

    @Override
    public List<Movie> getAll() {
        Query q = entityManager.createNativeQuery("SELECT * FROM movies", Movie.class);
        return (List<Movie>) q.getResultList();
    }
}
