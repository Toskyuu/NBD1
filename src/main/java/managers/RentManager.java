package managers;

import mainClasses.*;
import repositories.ItemRepository;
import repositories.RentRepository;

import java.time.LocalDate;
import java.util.List;

public class RentManager {
    private RentRepository rentRepository;

    public RentManager(RentRepository rentRepo) {
        this.rentRepository = rentRepo;
    }

    public void rentItem(Client client, Item item) {
        Rent rent = new Rent(LocalDate.now(), client, item);
        item.setRented(true);
        rentRepository.Add(rent);
    }

    public void returnItem(long id, LocalDate date) {
        Rent rent = getRentFromItemId(id);
        Item item = rent.getItem();
        item.setRented(false);
        rent.endRent(date);
        rentRepository.Update(rent);

    }
    public Rent getRentFromItemId(long id) {
        return rentRepository.findRentWithItemId(id);
    }

    public List<Rent> getAllRents(){
        return rentRepository.getAll();
    }

}
