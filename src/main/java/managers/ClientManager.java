package managers;

import mainClasses.Client;
import repositories.ClientRepository;
import java.util.List;

public class ClientManager {
    private ClientRepository clientRepository;

    public ClientManager(ClientRepository clientRepo) {
        this.clientRepository = clientRepo;
    }

    public void registerClient(Client client) {
        clientRepository.Add(client);
    }

    public void unregisterClient(Client client){
        clientRepository.Delete(client);
    }
    public Client getClient(Long id){
        return clientRepository.Find(id);
    }
    public void edit(Client client) {
        clientRepository.Update(client);
    }
    public List<Client> getAllClients(){
        return clientRepository.getAll();
    }
}
