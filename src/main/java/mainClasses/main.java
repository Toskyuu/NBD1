package mainClasses;

import exceptions.ClientException;
import jakarta.persistence.NamedQuery;
import managers.ClientManager;
import managers.ItemManager;
import managers.RentManager;
import repositories.ClientRepository;
import repositories.ItemRepository;
import repositories.RentRepository;
import java.time.LocalDate;
import java.util.List;

public class main {

    public static void main(String[] args) throws Exception {

        ClientRepository clientRepository = new ClientRepository();
        ItemRepository itemRepository = new ItemRepository();
        RentRepository rentRepository = new RentRepository();

        ClientManager clientManager = new ClientManager(clientRepository);
        ItemManager itemManager = new ItemManager(itemRepository);
        RentManager rentManager = new RentManager(rentRepository);

        Item movie1 = new Movie(2020, false, "srak","fajny","daniel craig", 49.99, 128);
        Item movie2 = new Movie(2023, false, "szpak","rak","Tarantino", 42.99, 130);
        Item musicAlbum1 = new MusicAlbum(2011, false, "bor", "rap", "Paluch", 47.55, 15);
        Item musicAlbum2 = new MusicAlbum(2016, false, "sar", "rap", "Sarius", 52.55, 19);
        Client client1 = new Client("Kuba", "Korez","99999999");
        Client client2 = new Client("Robert", "Pietrzak","00000000");

        itemManager.registerItem(movie1);
        itemManager.registerItem(movie2);
        itemManager.registerItem(musicAlbum1);
        itemManager.registerItem(musicAlbum2);
        clientManager.registerClient(client1);
        clientManager.registerClient(client2);

        rentManager.rentItem(client1, movie2);
        rentManager.rentItem(client2, musicAlbum1);

        movie2.setAuthor("noname");

        System.out.println(movie2);
        rentManager.returnItem(movie2.getId(), LocalDate.of(2023,11,10));
        rentManager.returnItem(musicAlbum1.getId(), LocalDate.of(2023,11,7));
        System.out.println(movie2);

    }
}
