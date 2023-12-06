package test.repositories;

import mgd.ClientMgd;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import repositories.MainRepositories.ClientMgdRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClientMgdRepositoriesTest {
    ClientMgdRepository clientMgdRepository = new ClientMgdRepository();

    @AfterEach
    public void before() {
        clientMgdRepository.getDatabase().getCollection("clients").drop();
        clientMgdRepository.getDatabase().getCollection("items").drop();
    }

    @After
    public void after() throws Exception {
        clientMgdRepository.close();
    }

    @Test
    public void addTest() {
        ClientMgd client = new ClientMgd(1,"kacper", "pietrzak",false, "0000000000");
        clientMgdRepository.add(client);
        assertEquals(clientMgdRepository.findAll().size(), 1);
    }

    @Test
    public void removeTest() {
        ClientMgd client = new ClientMgd(1,"kacper", "pietrzak",false, "0000000000");
        ClientMgd client2 = new ClientMgd(2, "test", "testtt", false, "1212212121");
        clientMgdRepository.add(client);
        clientMgdRepository.add(client2);
        assertEquals(clientMgdRepository.findAll().size(), 2);
        clientMgdRepository.remove(client2.getId());
        assertEquals(clientMgdRepository.findAll().size(), 1);
    }

    @Test
    public void updateTest() {
        ClientMgd client3 = new ClientMgd(3, "niie", "taaaka", false, "1212212121");
        clientMgdRepository.add(client3);
        String newFirstName = "changed";
        client3.setFirstName(newFirstName);
        clientMgdRepository.update(client3);
        assertEquals(client3.getFirstName(), newFirstName);
        ClientMgd newClient = clientMgdRepository.findById(client3.getId());
        assertEquals(newClient.getFirstName(), newFirstName);
        client3.setLastName("changed");
        clientMgdRepository.update(client3);
        assertEquals(clientMgdRepository.findById(client3.getId()).getLastName(), "changed");
    }

}
