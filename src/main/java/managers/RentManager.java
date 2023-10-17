package managers;

import mainClasses.*;
import repositories.IRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.function.Predicate;

public class RentManager {
    private IRepository<Rent> rentRepo = new IRepository<>();

    public RentManager(IRepository<Rent> rentIRepository) {
    }

    public int rentItem(LocalDate beginDate, Client client, Item item){
        rentRepo.add(new Rent(beginDate, client, item));
        return rentRepo.getLastID();
    }
    public void returnItem(int rentID){
        rentRepo.getByID(rentID).endRent(LocalDate.now());
    }
    public Rent getRent(Item item){
        Predicate<Rent> sameItem = rent -> rent.getItem() == item;
        return rentRepo.find(sameItem);
    }

    public String getAllRents(){
        return rentRepo.getAll();
    }

    public Rent getRent(int id){
        return rentRepo.getByID(id);
    }

}
