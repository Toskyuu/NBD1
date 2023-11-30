package test.managers;

import mainClasses.Client;
import managers.implementation.ClientManagerImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientManagerTest {
    ClientManagerImpl clientManager;

    @BeforeEach
    public void before() {
        clientManager = new ClientManagerImpl();
    }

    @Test
    public void addClients() {
        Client client1 = new Client(1, "Andrzej", "Kotek", false, "123123123");
        Client client2 = new Client(2, "Franek", "Lotek", false, "123123123");

        clientManager.addClient(client1);
        clientManager.addClient(client2);

        assertEquals(clientManager.findAllClients().size(), 2);
    }

    @Test
    public void removeClients() {
        Client client1 = new Client(3, "Andrzej", "Kotek", false, "123123123");
        Client client2 = new Client(4, "Franek", "Lotek", false, "123123123");

        clientManager.addClient(client1);
        clientManager.addClient(client2);

        clientManager.removeClient(client1);

        assertEquals(clientManager.findAllClients().size(), 1);
    }

    @Test
    public void checkingIfDbReturnsGoodValues() {
        Client client1 = new Client(5, "Andrzej", "Kotek", false, "123123123");

        clientManager.addClient(client1);

        assertEquals(client1.getId(), clientManager.findClientById(5).getId());
        assertEquals(client1.getFirstName(), clientManager.findClientById(5).getFirstName());
        assertEquals(client1.getLastName(), clientManager.findClientById(5).getLastName());
        assertEquals(client1.isArchive(), clientManager.findClientById(5).isArchive());
        assertEquals(client1.getPhoneNumber(), clientManager.findClientById(5).getPhoneNumber());
    }

    @Test
    public void checkingIfUpdatingWorks() {
        Client client1 = new Client(6, "Andrzej", "Kotek", false, "123123123");

        clientManager.addClient(client1);

        String name = "Radosław";

        client1.setFirstName(name);

        clientManager.updateClient(client1);

        assertEquals(name, clientManager.findClientById(6).getFirstName());
    }

    @AfterEach
    public void after() throws Exception {
        clientManager.close();
    }
}
