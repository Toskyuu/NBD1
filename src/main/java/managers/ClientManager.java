package managers;

import mainClasses.Client;
import repositories.IRepository;

import java.util.ArrayList;

public class ClientManager {
    private IRepository<Client> clientRepo = new IRepository<>();

    public ClientManager(IRepository<Client> clientRepo) {
        this.clientRepo = clientRepo;
    }
    public int registerClient(String firstName, String secondName, String phoneNumber){
        clientRepo.add(new Client(firstName, secondName, phoneNumber));
        return clientRepo.getLastID();
    }

    public void unregisterClient(Client client){
        clientRepo.remove(client);
    }
    public Client getClient(int id){
        return clientRepo.getByID(id);
    }
    public String getAllClients(){
        return clientRepo.getAll();
    }
}
