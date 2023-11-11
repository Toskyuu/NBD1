package mainClasses;

import managers.ClientManager;
import managers.ItemManager;
import managers.RentManager;
import repositories.ClientRepository;
import repositories.ItemRepository;
import repositories.RentRepository;
import java.time.LocalDate;

public class main {

    public static void main(String[] args) throws Exception {

        ClientRepository clientRepository = new ClientRepository();
        ItemRepository itemRepository = new ItemRepository();
        RentRepository rentRepository = new RentRepository();

        ClientManager clientManager = new ClientManager(clientRepository);
        ItemManager itemManager = new ItemManager(itemRepository);
        RentManager rentManager = new RentManager(rentRepository);

        Item movie1 = new Movie(1990, false, "Stowarzyszenie Umarłych Poetów","dramat","Peter Weir", 49.99, 126);
        Item movie2 = new Movie(2023, false, "Czas krwawego księżyca","dramat","Martin Scorsese", 42.99, 206);
        Item musicAlbum1 = new MusicAlbum(2009, false, "Kilka numerów o czymś", "rap", "Małpa", 47.55, 15);
        Item musicAlbum2 = new MusicAlbum(2018, false, "Ground Zero Mixtape", "rap", "Pro8l3m", 52.55, 27);
        Client client1 = new Client("Janusz", "Tracz","666666666");
        Client client2 = new Client("Adaś", "Miauczyński","741852963");

        itemManager.registerItem(movie1);
        itemManager.registerItem(movie2);
        itemManager.registerItem(musicAlbum1);
        itemManager.registerItem(musicAlbum2);
        clientManager.registerClient(client1);
        clientManager.registerClient(client2);

        rentManager.rentItem(client1, movie2);
//        rentManager.rentItem(client1, movie2);
        rentManager.rentItem(client2, musicAlbum1);


        rentManager.returnItem(movie2.getId(), LocalDate.of(2023,11,10));
//        rentManager.returnItem(musicAlbum1.getId(), LocalDate.of(2023,11,7));
    }
}
