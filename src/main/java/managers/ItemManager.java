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

    public void registerMusic(String name, int yearOfPremiere, int itemID, boolean isRented,
                              String author, String style, double basePrice, int numberOfSongs){
        itemRepo.add(new MusicAlbum(name, yearOfPremiere, itemID, isRented, author, style, basePrice, numberOfSongs));
    }

    public void registerMovie(String name, int yearOfPremiere, int itemID, boolean isRented,
                              String author, String style, double basePrice, int totalTime){
        itemRepo.add(new Movie(name, yearOfPremiere, itemID, isRented, author, style, basePrice, totalTime));
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
