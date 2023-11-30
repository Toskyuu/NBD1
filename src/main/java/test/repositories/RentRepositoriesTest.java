package test.repositories;

import mainClasses.Client;
import mainClasses.Item;
import mainClasses.MusicAlbum;
import mainClasses.Rent;
import mapper.ClientMapper;
import mapper.ItemMapper;
import mapper.RentMapper;
import mgd.ItemMgd;
import mgd.MusicAlbumMgd;
import mgd.RentMgd;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.MainRepositories.ClientMgdRepository;
import repositories.MainRepositories.ItemMgdRepository;
import repositories.MainRepositories.RentMgdRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentRepositoriesTest {
    RentMgdRepository rentMgdRepository;
    ClientMgdRepository clientMgdRepository;
    ItemMgdRepository itemMgdRepository;

    @BeforeEach
    public void before() {
        rentMgdRepository = new RentMgdRepository();
    }

    @Test
    public void adding() {
        Client client = new Client(1,"kacper", "pietrzak",false, "0000000000");
        Item item = new MusicAlbum(1,2001, 0, 0, "album", "rap", "ktos", 24.56, 14);

        clientMgdRepository = new ClientMgdRepository();
        itemMgdRepository = new ItemMgdRepository();

        clientMgdRepository.add(ClientMapper.clientToMongo(client));
        itemMgdRepository.add(ItemMapper.itemToMongo(item));

        Rent rent = new Rent(1,client,item);

        assertEquals(clientMgdRepository.findAll().size(), 1);
        assertEquals(itemMgdRepository.findAll().size(), 1);

        rentMgdRepository.add(RentMapper.rentToMongo(rent));
        rent.endRent(LocalDate.of(2023,11,28));
        rentMgdRepository.update(RentMapper.rentToMongo(rent));
    }

    @AfterEach
    public void after() throws Exception {
        rentMgdRepository.close();
    }
}
