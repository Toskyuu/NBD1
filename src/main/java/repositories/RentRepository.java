package repositories;

import jakarta.persistence.*;
import mainClasses.Item;
import mainClasses.Rent;
import java.util.List;

public class RentRepository implements IRepository<Rent>{

    private final EntityManager entityManager;
    private final EntityTransaction transaction;

    public RentRepository() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public void Add(Rent rent) {
        //adding rent to db
        try {
            transaction.begin();

            entityManager.persist(rent);
            entityManager.merge(rent.getItem());

            transaction.commit();
        } catch(OptimisticLockException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void Delete(Rent rent) {
        try {
            transaction.begin();

            rent = entityManager.merge(rent);
            entityManager.remove(rent);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void Update(Rent rent) {
        try {
            transaction.begin();

            Item item = rent.getItem();

            Item item2 = entityManager.find(Item.class, rent.getItem().getId());

            item2.setRented(item.isRented());
            item2.setAuthor(item.getAuthor());
            item2.setArchive(item.isArchive());
            item2.setName(item.getName());
            item2.setStyle(item.getStyle());
            item2.setBasePrice(item.getBasePrice());
            item2.setYearOfPremiere(item.getYearOfPremiere());

            entityManager.merge(rent);
            entityManager.merge(item2);

            transaction.commit();
        } catch(OptimisticLockException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Rent Find(long id) {
        return entityManager.find(Rent.class, id);
    }

    @Override
    public List<Rent> getAll() {
        Query q = entityManager.createNativeQuery("SELECT * FROM rents", Rent.class);
        return (List<Rent>) q.getResultList();
    }

    public Rent findRentWithItemId(long id) {
        Query q = entityManager.createNativeQuery("SELECT * FROM rents r WHERE r.item_id = ?1", Rent.class)
                .setParameter(1, id);
        List<Rent> rents = q.getResultList();

        return rents.get(0);
    }
}
