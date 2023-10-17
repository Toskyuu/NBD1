package managers;

import mainClasses.Client;
import mainClasses.Item;
import mainClasses.MusicAlbum;
import mainClasses.Rent;
import mainClasses.Movie;
import repositories.IRepository;

import java.util.Date;
import java.util.function.Predicate;

public class ItemManager {
    private IRepository<Item> itemRepo = new IRepository<>();

    public ItemManager(IRepository<Item> itemRepo) {
        this.itemRepo = itemRepo;
    }

    public void registerMusic(int yearOfPremiere, boolean isRented, String name,
                              String style, String author, double basePrice, int numberOfSongs){
        itemRepo.add(new MusicAlbum( yearOfPremiere, isRented, name, style, author, basePrice, numberOfSongs));
    }

    public void registerMovie(int yearOfPremiere, boolean isRented, String name,
                              String style, String author, double basePrice, int totalTime){
        itemRepo.add(new Movie(yearOfPremiere, isRented, name, style, author, basePrice, totalTime));
    }

    public void unregisterItem(Item item) {
        itemRepo.remove(item);
    }

    public Item getItem(int id) {
        return itemRepo.getByID(id);
    }

    public String getAllItems(){
        return itemRepo.getAll();
    }

}
