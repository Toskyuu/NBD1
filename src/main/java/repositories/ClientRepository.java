package repositories;

import jakarta.persistence.*;
import mainClasses.Client;
import java.util.List;

public class ClientRepository implements IRepository<Client> {
    private final EntityManager entityManager;

    public ClientRepository() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void Add(Client client) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(client);
        System.out.println("New guest ID: " + client.getId());
        transaction.commit();
    }

    @Override
    public void Delete(Client client) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(client);
        transaction.commit();
    }

    // update client or add client to database if not exits
    @Override
    public void Update(Client client) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(client);
        transaction.commit();
    }

    @Override
    public Client Find(Long id) {
        return entityManager.find(Client.class, id);
    }

    @Override
    public List<Client> getAll() {
        Query q = entityManager.createNativeQuery("SELECT * FROM clients", Client.class);
        return (List<Client>) q.getResultList();
    }
}
