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
        try {
            transaction.begin();
            entityManager.persist(item);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Item item) {
        try {
            transaction.begin();
            entityManager.remove(item);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void Update(Item item) {
        try {
            transaction.begin();

            Item item2 = entityManager.find(Item.class, item.getId());
            item2.setRented(item.isRented());
            item2.setAuthor(item.getAuthor());
            item2.setArchive(item.isArchive());
            item2.setName(item.getName());
            item2.setStyle(item.getStyle());
            item2.setBasePrice(item.getBasePrice());
            item2.setYearOfPremiere(item.getYearOfPremiere());

            entityManager.merge(item2);

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
