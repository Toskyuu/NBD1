package managers.implementation;

import mainClasses.Client;
import managers.ClientManager;
import mapper.ClientMapper;
import mgd.ClientMgd;
import repositories.MainRepositories.ClientMgdRepository;
import java.util.ArrayList;
import java.util.List;

public class ClientManagerImpl implements ClientManager {
    private ClientMgdRepository clientRepository;

    public ClientManagerImpl() {
        this.clientRepository = new ClientMgdRepository();
    }

    @Override
    public boolean addClient(Client client) {
        return clientRepository.add(ClientMapper.clientToMongo(client));
    }

    @Override
    public boolean updateClient(Client client) {
        return clientRepository.update(ClientMapper.clientToMongo(client));
    }

    @Override
    public boolean removeClient(Client client) {
        return clientRepository.remove(client.getId());
    }

    @Override
    public List<Client> findAllClients() {
        List<Client> clients = new ArrayList<>();
        List<ClientMgd> clientMgds = clientRepository.findAll();
        for (ClientMgd clientMgd : clientMgds) {
            clients.add(ClientMapper.clientFromMongo(clientMgd));
        }
        return clients;
    }

    @Override
    public Client findClientById(int id) {
        return ClientMapper.clientFromMongo(clientRepository.findById(id));
    }

    @Override
    public void close() throws Exception {
        clientRepository.close();
    }
}
