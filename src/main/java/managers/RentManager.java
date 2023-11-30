package managers;

import mainClasses.Item;
import mainClasses.Rent;

import java.util.List;

public interface RentManager {
    void createRent(Rent rent);
    void updateRent(Rent rent);
    void removeRent(Rent rent);
    void endRent(Rent rent);
    List<Rent> findAllRent();
    Rent findRentById(int id);
}
