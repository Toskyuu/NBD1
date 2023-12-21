package managers.implementation;

import mainClasses.Rent;
import managers.ClientManager;
import managers.ItemManager;
import managers.RentManager;
import mapper.ClientMapper;
import mapper.ItemMapper;
import mapper.RentMapper;
import mgd.RentMgd;
import redis.RentJson;
import repositories.MongoRepositories.RentMgdRepository;
import repositories.RedisRepositories.RentRedisRepository;

import java.util.ArrayList;
import java.util.List;

public class RentManagerImpl implements RentManager {
    private RentMgdRepository rentMgdRepository;
    private RentRedisRepository rentRedisRepository;
    private ClientManager clientManager;
    private ItemManager itemManager;

    public RentManagerImpl(ClientManager clientManager, ItemManager itemManager) {
        this.rentMgdRepository = new RentMgdRepository();
        this.rentRedisRepository = new RentRedisRepository();
        this.clientManager = clientManager;
        this.itemManager = itemManager;
    }

    public void clearCache() {
        rentRedisRepository.clearCache();
    }

    @Override
    public boolean createRent(Rent rent) {
        RentMgd rentMgd = new RentMgd(rent.getId(), rent.getBeginDate(), rent.getEndDate(), rent.getRentCost(), ClientMapper.clientToMongo(rent.getClient()), ItemMapper.itemToMongo(rent.getItem()));

        rent.getItem().setRented(1);

        if (rentRedisRepository.checkConnection()){
            rentRedisRepository.add(RentMapper.rentToRedis(rent));
        }
        return rentMgdRepository.add(rentMgd) && itemManager.updateItem(rent.getItem());
    }

    @Override
    public boolean updateRent(Rent rent) {
        RentMgd rentMgd = new RentMgd(rent.getId(), rent.getBeginDate(), rent.getEndDate(), rent.getRentCost(), ClientMapper.clientToMongo(rent.getClient()), ItemMapper.itemToMongo(rent.getItem()));

        rentRedisRepository.update(RentMapper.rentToRedis(rent));
        return rentMgdRepository.update(rentMgd);
    }

    @Override
    public boolean removeRent(Rent rent) {
        rentRedisRepository.remove(rent.getId());
        return rentMgdRepository.remove(rent.getId());
    }

    @Override
    public void endRent(Rent rent) {
        RentMgd rentMgd = new RentMgd(rent.getId(), rent.getBeginDate(), rent.getEndDate(), rent.getRentCost(), ClientMapper.clientToMongo(rent.getClient()), ItemMapper.itemToMongo(rent.getItem()));
        rentMgd.endRent(rent.getEndDate());
        rentMgdRepository.update(rentMgd);
        itemManager.updateItem(rent.getItem());
    }

    @Override
    public List<Rent> findAllRent() {
        if(rentRedisRepository.checkConnection()) {
            List<Rent> rents = new ArrayList<>();
            List<RentJson> rentJsons = rentRedisRepository.findAll();
            for (RentJson rentJson : rentJsons) {
                rents.add(RentMapper.rentFromRedis(rentJson));
            }
            return rents;
        } else {
            List<Rent> rents = new ArrayList<>();
            List<RentMgd> rentMgds = rentMgdRepository.findAll();
            for (RentMgd rentMgd : rentMgds) {
                rents.add(RentMapper.rentFromMongo(rentMgd));
            }
            return rents;
        }

    }

    @Override
    public Rent findRentById(Rent rent) {
        if (rentRedisRepository.checkConnection()) {
            if (rentRedisRepository.findById(rent.getId()) == null) {
                rentRedisRepository.add(RentMapper.rentToRedis(rent));
                return RentMapper.rentFromMongo(rentMgdRepository.findById(rent.getId()));
            } else {
                return RentMapper.rentFromRedis(rentRedisRepository.findById(rent.getId()));
            }
        } else {
            return RentMapper.rentFromMongo(rentMgdRepository.findById(rent.getId()));

        }
    }

    @Override
    public void close() throws Exception {
        rentMgdRepository.close();
        rentRedisRepository.close();
    }
}
