package managers.implementation;

import mainClasses.Item;
import mainClasses.Rent;
import managers.ClientManager;
import managers.ItemManager;
import managers.RentManager;
import mapper.ClientMapper;
import mapper.ItemMapper;
import mapper.RentMapper;
import mgd.ItemMgd;
import mgd.RentMgd;
import repositories.MainRepositories.RentMgdRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentManagerImpl implements RentManager {
    private RentMgdRepository rentMgdRepository;
    private ClientManager clientManager;
    private ItemManager itemManager;

    public RentManagerImpl(RentMgdRepository rentMgdRepository) {
        this.rentMgdRepository = rentMgdRepository;
        this.clientManager = new ClientManagerImpl();
        this.itemManager = new ItemManagerImpl();
    }

    @Override
    public void createRent(Rent rent) {
        RentMgd rentMgd = new RentMgd(rent.getId(), LocalDate.now(), rent.getEndDate(), rent.getRentCost(), ClientMapper.clientToMongo(rent.getClient()), ItemMapper.itemToMongo(rent.getItem()));

        rentMgdRepository.add(rentMgd);
        itemManager.updateItem(rent.getItem());
    }

    @Override
    public void updateRent(Rent rent) {
        RentMgd rentMgd = new RentMgd(rent.getId(), LocalDate.now(), rent.getEndDate(), rent.getRentCost(), ClientMapper.clientToMongo(rent.getClient()), ItemMapper.itemToMongo(rent.getItem()));
        rentMgdRepository.update(rentMgd);
    }

    @Override
    public void removeRent(Rent rent) {

    }

    @Override
    public void endRent(Rent rent) {
        RentMgd rentMgd = new RentMgd(rent.getId(), LocalDate.now(), rent.getEndDate(), rent.getRentCost(), ClientMapper.clientToMongo(rent.getClient()), ItemMapper.itemToMongo(rent.getItem()));
        rentMgd.endRent(rent.getEndDate());
        rentMgdRepository.update(rentMgd);
        itemManager.updateItem(rent.getItem());
    }

    @Override
    public List<Rent> findAllRent() {
        List<Rent> rents = new ArrayList<>();
        List<RentMgd> rentMgds = rentMgdRepository.findAll();
        for (RentMgd rentMgd : rentMgds) {
            rents.add(RentMapper.rentFromMongo(rentMgd));
        }
        return rents;
    }

    @Override
    public Rent findRentById(int id) {
        return null;
    }
}
