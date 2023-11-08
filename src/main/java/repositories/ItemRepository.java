package repositories;

import jakarta.persistence.*;
import mainClasses.Item;
import java.util.List;

public class ItemRepository implements IRepository<Item> {

    private final EntityManager entityManager;
    private EntityTransaction transaction;
    public ItemRepository() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public void Add(Item item) {
//        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(item);
        transaction.commit();
    }

    @Override
    public void Delete(Item item) {
//        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(item);
        transaction.commit();
    }

    @Override
    public void Update(Item item) {
//        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(item);
            transaction.commit();
        } catch(Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Item Find(long id) {
        return entityManager.find(Item.class, id);
    }

    @Override
    public List<Item> getAll() {
        Query q = entityManager.createNativeQuery("SELECT * FROM items", Item.class);
        return (List<Item>) q.getResultList();
    }
}
