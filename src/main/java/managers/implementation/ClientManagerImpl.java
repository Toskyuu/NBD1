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
    public void addClient(Client client) {
        clientRepository.add(ClientMapper.clientToMongo(client));
    }

    @Override
    public void updateClient(Client client) {
        clientRepository.update(ClientMapper.clientToMongo(client));
    }

    @Override
    public void removeClient(Client client) {
        clientRepository.remove(client.getId());
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
