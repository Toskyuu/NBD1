package test.repositories;

import mainClasses.Client;
import mapper.ClientMapper;
import mgd.ClientMgd;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.MainRepositories.ClientMgdRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClientMgdRepositoriesTest {
    ClientMgdRepository clientMgdRepository;

    @BeforeEach
    public void before(){
        clientMgdRepository= new ClientMgdRepository();
    }


    @Test
    public void addTest() {
        Client client = new Client(1,"kacper", "pietrzak",false, "0000000000");
        Client client2 = new Client(2,"kacper", "pietrzak",false, "0000000000");
        clientMgdRepository.add(ClientMapper.clientToMongo(client));
        clientMgdRepository.add(ClientMapper.clientToMongo(client2));
        assertEquals(clientMgdRepository.findAll().size(), 2);
    }

    @Test
    public void removeTest() {
        ClientMgd client = new ClientMgd(3, "test", "testtt", false, "1212212121");
        ClientMgd client2 = new ClientMgd(4, "niie", "taaaka", false, "1212212121");
        clientMgdRepository.add(client);
        clientMgdRepository.add(client2);
        assertEquals(clientMgdRepository.findAll().size(), 2);
        clientMgdRepository.remove(client.getId());
        assertEquals(clientMgdRepository.findAll().size(), 1);
    }

    @Test
    public void updateTest() {
        ClientMgd client = new ClientMgd(5, "niie", "taaaka", false, "1212212121");
        clientMgdRepository.add(client);
        client.setFirstName("changed");
        clientMgdRepository.update(client);
        assertEquals(clientMgdRepository.findById(client.getId()).getFirstName(), "changed");
        client.setLastName("changed");
        clientMgdRepository.update(client);
        assertEquals(clientMgdRepository.findById(client.getId()).getLastName(), "changed");
    }

    @AfterEach
    public void after() throws Exception {
        clientMgdRepository.close();
    }

}
