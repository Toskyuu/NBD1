package tests;

import mainClasses.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repositories.ClientRepository;

public class clientRepositoryTest {
    private ClientRepository cr = new ClientRepository();
    private Client client = new Client("Jan", "Kowalski", "232332");
    @Test
    void addTest(){
        cr.Add(client);
        Assertions.assertEquals(cr.Find(client.getId()), client);
    }

    @Test
    void updateTest(){
        cr.Add(client);
        Assertions.assertEquals(cr.Find(client.getId()).getFirstName(), client.getFirstName());
        client.setFirstName("Miłosz");
        Assertions.assertEquals(cr.Find(client.getId()).getFirstName(), client.getFirstName());

    }

    @Test
    void deleteTest(){
        cr.Add(client);
        Assertions.assertEquals(cr.Find(client.getId()).getFirstName(), client.getFirstName());
        cr.Delete(client);
        Assertions.assertNull(cr.Find(client.getId()));

    }
}
