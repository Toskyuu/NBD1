package test.managers;

import mainClasses.Client;
import managers.implementation.ClientManagerImpl;
import org.junit.After;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientManagerTest {
    ClientManagerImpl clientManager = new ClientManagerImpl();

    @After
    public void after() throws Exception {
        clientManager.close();
    }

    @Test
    public void test() {
        //adding
        Client client1 = new Client(1, "Andrzej", "Kotek", false, "123123123");
        Client client2 = new Client(2, "Franek", "Lotek", false, "123123123");

        assertTrue(clientManager.addClient(client1));
        assertTrue(clientManager.addClient(client2));

        assertEquals(2, clientManager.findAllClients().size());

        // returning
        assertEquals(client1.getId(), clientManager.findClientById(1).getId());

        //remove
        Client client3 = new Client(3, "Andrzej", "Kotek", false, "123123123");
        Client client4 = new Client(4, "Franek", "Lotek", false, "123123123");

        clientManager.addClient(client3);
        clientManager.addClient(client4);

        clientManager.removeClient(client3);

        assertEquals(3, clientManager.findAllClients().size());

        //checking if db returns good values
        Client client5 = new Client(5, "Andrzej", "Kotek", false, "123123123");

        clientManager.addClient(client5);

        assertEquals(client5.getId(), clientManager.findClientById(5).getId());
        assertEquals(client5.getFirstName(), clientManager.findClientById(5).getFirstName());
        assertEquals(client5.getLastName(), clientManager.findClientById(5).getLastName());
        assertEquals(client5.isArchive(), clientManager.findClientById(5).isArchive());
        assertEquals(client5.getPhoneNumber(), clientManager.findClientById(5).getPhoneNumber());

        //updating
        Client client6 = new Client(6, "Andrzej", "Kotek", false, "123123123");

        clientManager.addClient(client6);

        String name = "Radosław";

        client6.setFirstName(name);
        clientManager.updateClient(client6);
        assertEquals(name, clientManager.findClientById(6).getFirstName());

        clientManager.removeClient(client1);
        clientManager.removeClient(client2);
        clientManager.removeClient(client4);
        clientManager.removeClient(client5);
        clientManager.removeClient(client6);
    }
}
