package test.managers;

import mainClasses.Client;
import mainClasses.Item;
import mainClasses.Movie;
import mainClasses.Rent;
import managers.implementation.ClientManagerImpl;
import managers.implementation.ItemManagerImpl;
import managers.implementation.RentManagerImpl;
import org.junit.After;
import org.junit.jupiter.api.Test;
import repositories.MainRepositories.RentMgdRepository;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentManagerTest {
    ItemManagerImpl itemManager = new ItemManagerImpl();
    ClientManagerImpl clientManager = new ClientManagerImpl();
    RentMgdRepository rentMgdRepository = new RentMgdRepository();
    RentManagerImpl rentManager = new RentManagerImpl(rentMgdRepository);

    @After
    public void after() throws Exception {
        rentManager.close();
    }

    @Test
    public void addTest() {
        Client client = new Client(1,"Adam","Karaś",false, "123123123");
        Item movie = new Movie(1, 2003,0,0,"Shrek","Bajka","Autor", 24.99, 123);

        Rent rent = new Rent(1, client, movie);

        clientManager.addClient(client);
        itemManager.addItem(movie);
        rentManager.createRent(rent);
        assertEquals(rentManager.findAllRent().size(), 1);

        rent.endRent(new Date((2023 - 1900), 11, 20));
        rentManager.endRent(rent);
        clientManager.removeClient(client);
        itemManager.removeItem(movie);
        rentManager.removeRent(rent);
    }

}
