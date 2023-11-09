package managers;

import exceptions.RentException;
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

    public void rentItem(Client client, Item item) throws RentException {
        if (item.isRented()) {
            throw new RentException("This item is rented");
        } else {
            Rent rent = new Rent(LocalDate.now(), client, item);
            item.setRented(true);
            rentRepository.Add(rent);
        }
    }

    public void returnItem(long id, LocalDate date) throws RentException {
        Rent rent = getRentFromItemId(id);
        Item item = rent.getItem();
        item.setRented(false);
        rent.endRent(date);
        rentRepository.Update(rent);

    }
    public Rent getRentFromItemId(long id) throws RentException {
        Rent rent = rentRepository.findRentWithItemId(id);
        if (rent == null) {
            throw new RentException("No such rent exist");
        } else {
            return rent;
        }
    }

    public List<Rent> getAllRents(){
        return rentRepository.getAll();
    }

}
