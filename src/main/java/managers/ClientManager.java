package managers;

import mainClasses.Client;

import java.util.List;

public interface ClientManager {
    void addClient(Client client);
    void updateClient(Client client);
    void removeClient(Client client);
    List<Client> findAllClients();
    Client findClientById(int id);
    void close() throws Exception;
}
