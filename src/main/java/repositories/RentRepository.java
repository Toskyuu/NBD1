package repositories;

import jakarta.persistence.*;
import mainClasses.Item;
import mainClasses.Rent;
import java.util.List;

public class RentRepository implements IRepository<Rent>{

    private final EntityManager entityManager;

    public RentRepository() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void Add(Rent rent) {
        EntityTransaction transaction = entityManager.getTransaction();
        Item item = rent.getItem();
        transaction.begin();

        //adding rent to db
        entityManager.persist(rent);

        //setting that item is rented and updating it in db
        item.setRented(true);
        entityManager.merge(item);

        transaction.commit();
    }

    @Override
    public void Delete(Rent rent) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.remove(rent);

        transaction.commit();
    }

    //ending rent
    @Override
    public void Update(Rent rent) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Item item = rent.getItem();
        item.setRented(false);
        entityManager.merge(rent);
        entityManager.merge(item);

        transaction.commit();
    }

    @Override
    public Rent Find(Long id) {
        return entityManager.find(Rent.class, id);
    }

    @Override
    public List<Rent> getAll() {
        Query q = entityManager.createNativeQuery("SELECT * FROM rents", Rent.class);
        return (List<Rent>) q.getResultList();
    }
}
