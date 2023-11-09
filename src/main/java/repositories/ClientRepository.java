package repositories;

import exceptions.ClientException;
import jakarta.persistence.*;
import mainClasses.Client;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements IRepository<Client> {
    private final EntityManager entityManager;
    private final EntityTransaction transaction;

    public ClientRepository() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        this.entityManager = entityManagerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    @Override
    public void Add(Client client) {
        try {
            transaction.begin();
            entityManager.persist(client);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void Delete(Client client) {
        try {
            transaction.begin();
            entityManager.remove(client);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    // update client or add client to database if not exits
    @Override
    public void Update(Client client) {
        try {
            transaction.begin();
            entityManager.merge(client);
            transaction.commit();
        } catch(Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public Client Find(long id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public List<Client> getAll() {
        Query q = entityManager.createNativeQuery("SELECT * FROM clients", Client.class);
        return (List<Client>) q.getResultList();
    }
}
