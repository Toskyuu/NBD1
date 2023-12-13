package managers;

import mainClasses.Rent;

import java.util.List;

public interface RentManager {
    boolean createRent(Rent rent);
    boolean updateRent(Rent rent);
    boolean removeRent(Rent rent);
    void endRent(Rent rent);
    List<Rent> findAllRent();
    Rent findRentById(int id);
    void close() throws Exception;
}
