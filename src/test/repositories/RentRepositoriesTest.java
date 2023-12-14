package test.repositories;

import mgd.ClientMgd;
import mgd.MusicAlbumMgd;
import mgd.RentMgd;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import repositories.MongoRepositories.ClientMgdRepository;
import repositories.MongoRepositories.ItemMgdRepository;
import repositories.MongoRepositories.RentMgdRepository;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RentRepositoriesTest {
    RentMgdRepository rentMgdRepository = new RentMgdRepository();
    ClientMgdRepository clientMgdRepository = new ClientMgdRepository();
    ItemMgdRepository itemMgdRepository = new ItemMgdRepository();

    @AfterEach
    public void afterEach() {
        rentMgdRepository.getDatabase().getCollection("rents").drop();
        clientMgdRepository.getDatabase().getCollection("clients").drop();
        itemMgdRepository.getDatabase().getCollection("items").drop();
    }

    @After
    public void after() throws Exception {
        itemMgdRepository.close();
        clientMgdRepository.close();
        rentMgdRepository.close();
    }

    @Test
    public void addingWithoutEnding() {
        ClientMgd clientMgd = new ClientMgd(1,"kacper", "pietrzak",false, "0000000000");
        MusicAlbumMgd musicAlbumMgd = new MusicAlbumMgd(1,2001, 0, 0, "album", "rap", "ktos", 24.56, 14);

        clientMgdRepository = new ClientMgdRepository();
        itemMgdRepository = new ItemMgdRepository();

        clientMgdRepository.add(clientMgd);
        itemMgdRepository.add(musicAlbumMgd);

        RentMgd rentMgd = new RentMgd(1, new Date(), null, 0, clientMgd, musicAlbumMgd);

        assertEquals(1, clientMgdRepository.findAll().size());
        assertEquals(1, itemMgdRepository.findAll().size());

        assertTrue(rentMgdRepository.add(rentMgd));

        assertEquals(1, rentMgdRepository.findAll().size());
    }

    @Test
    public void addingWithEnding() {
        ClientMgd clientMgd = new ClientMgd(1,"kacper", "pietrzak",false, "0000000000");
        MusicAlbumMgd musicAlbumMgd = new MusicAlbumMgd(1,2001, 0, 0, "album", "rap", "ktos", 24.56, 14);

        clientMgdRepository = new ClientMgdRepository();
        itemMgdRepository = new ItemMgdRepository();

        RentMgd rentMgd = new RentMgd(1, clientMgd, musicAlbumMgd);

        clientMgdRepository.add(rentMgd.getClient());
        itemMgdRepository.add(rentMgd.getItem());

        assertEquals(0, itemMgdRepository.findById(rentMgd.getItem().getId()).isRented());
        assertTrue(rentMgdRepository.add(rentMgd));

        rentMgdRepository.endRent(rentMgd, new Date(2023 - 1900, 12, 20));

        assertEquals(1, rentMgdRepository.findAll().size());
        assertEquals(0, rentMgdRepository.findById(1).getItem().isRented());
    }

}
