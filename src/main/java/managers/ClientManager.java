package managers;

import mainClasses.Client;
import repositories.ClientRepository;
import java.util.List;

public class ClientManager {
    private ClientRepository clientRepo;

    public ClientManager(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public void registerClient(Client client) {
        clientRepo.Add(client);
    }

    public void unregisterClient(Client client){
        clientRepo.Delete(client);
    }
    public Client getClient(Long id){
        return clientRepo.Find(id);
    }

    public void edit(Client client) {
        clientRepo.Update(client);
    }

    public List<Client> getAllClients(){
        return clientRepo.getAll();
    }
}
