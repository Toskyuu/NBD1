package mainClasses;

import managers.ClientManager;
import repositories.IRepository;

public class main {
    public static void main(String[] args) {
        IRepository<Client> clientIRepository = new IRepository<>();
        ClientManager cm = new ClientManager(clientIRepository);
        int id = cm.registerClient("Rober", "Laski", "323131");
         System.out.print(cm.getAllClients());
        System.out.print(cm.getClient(id));
        cm.unregisterClient(cm.getClient(id));
        System.out.print(cm.getAllClients());
    }
}
