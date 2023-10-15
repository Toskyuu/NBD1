package mainClasses;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import managers.ClientManager;
import repositories.IRepository;

public class main {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("my-persistence-unit");
    private static final EntityManager em  = factory.createEntityManager();
    public static void main(String[] args) {
//        IRepository<Client> clientIRepository = new IRepository<>();
//        ClientManager cm = new ClientManager(clientIRepository);
//        int id = cm.registerClient("Rober", "Laski", "323131");
//         System.out.print(cm.getAllClients());
//        System.out.print(cm.getClient(id));
//        cm.unregisterClient(cm.getClient(id));
//        System.out.print(cm.getAllClients());

    }
}
