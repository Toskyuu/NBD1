package mainClasses;

import managers.ClientManager;
import managers.ItemManager;
import managers.RentManager;
import repositories.ClientRepository;
import repositories.ItemRepository;
import repositories.RentRepository;
import java.time.LocalDate;

public class main {

    public static void main(String[] args) {

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
        Client client2 = new Client("Robert", "Pietrzak","00000000");

        itemManager.registerItem(movie1);
        itemManager.registerItem(movie2);
        itemManager.registerItem(musicAlbum1);
        itemManager.registerItem(musicAlbum2);
        clientManager.registerClient(client2);

        long rent1ID = rentManager.rentItem(client2, movie1);
        System.out.println(movie1.isRented());
        rentManager.returnItem(rent1ID, LocalDate.parse("2023-10-28"));
        System.out.println(movie1.isRented());
    }
}
