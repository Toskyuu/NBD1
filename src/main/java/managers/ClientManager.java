package managers;

import mainClasses.Client;

import java.util.List;

public interface ClientManager {
    boolean addClient(Client client);
    boolean updateClient(Client client);
    boolean removeClient(Client client);
    List<Client> findAllClients();
    Client findClientById(int id);
    void close() throws Exception;
}
