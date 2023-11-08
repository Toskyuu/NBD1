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
//        entityManager.getTransaction().begin();
//        try {
//            if(!entityManager.contains(rent)) {
//                rent = entityManager.merge(rent);
//            }
//
//            Item item = rent.getItem();
//
//            if(!entityManager.contains(item)) {
//                item = entityManager.merge(item);
//            }
//
//            //blocking item
////            entityManager.lock(item, LockModeType.PESSIMISTIC_READ);
//
////            item.setRented(true);
////            System.out.println(item);
//
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//            e.printStackTrace();
//        }

        //adding rent to db
        try {
            transaction.begin();

            Item item = rent.getItem();
            entityManager.persist(rent);
            entityManager.merge(item);

            transaction.commit();
        } catch(OptimisticLockException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
//        transaction.begin();
////        entityManager.lock(rent.getItem(), LockModeType.PESSIMISTIC_READ);
//
//        entityManager.persist(rent);
//
//        //setting that item is rented and updating it in db
////        item.setRented(true);
////        entityManager.merge(item);
//
//        transaction.commit();
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
//        transaction.begin();
//
//        Item item = rent.getItem();
//        entityManager.merge(rent);
////        entityManager.merge(item);
//
//        transaction.commit();
        try {
            transaction.begin();

            Item item = rent.getItem();
            entityManager.merge(rent);
//            entityManager.merge(item);

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
