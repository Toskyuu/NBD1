package managers;

import mainClasses.*;
import repositories.RentRepository;

import java.time.LocalDate;
import java.util.List;

public class RentManager {
    private RentRepository rentRepository;

    public RentManager(RentRepository rentRepo) {
        this.rentRepository = rentRepo;
    }

    public long rentItem(Client client, Item item) {
        Rent rent = new Rent(LocalDate.now(), item.getBasePrice(), client, item);
        rentRepository.Add(rent);
        return rent.getId();
    }
    public void returnItem(long id, LocalDate date) {
        Rent rent = getRent(id);
        rent.setEndDate(date);
        rentRepository.Update(rent);
    }
    public Rent getRent(long id) {
        return rentRepository.Find(id);
    }

    public List<Rent> getAllRents(){
        return rentRepository.getAll();
    }

}
