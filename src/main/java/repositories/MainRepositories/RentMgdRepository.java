package repositories.MainRepositories;

import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import mainClasses.Client;
import mainClasses.Item;
import mapper.ItemMapper;
import mgd.ClientMgd;
import mgd.ItemMgd;
import mgd.RentMgd;
import repositories.AbstractMongoRepository;
import repositories.IRepository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;

public class RentMgdRepository extends AbstractMongoRepository implements IRepository<RentMgd> {
    private final MongoCollection<RentMgd> rents = getDatabase().getCollection("rents", RentMgd.class);
    private final MongoCollection<ClientMgd> clients = getDatabase().getCollection("clients", ClientMgd.class);
    private final MongoCollection<ItemMgd> items = getDatabase().getCollection("items", ItemMgd.class);


    @Override
    public void add(RentMgd entity) {
        ClientSession clientSession = getMongoClient().startSession();
        try {
            clientSession.startTransaction();
            items.updateOne(clientSession, eq("_id", entity.getItem().getId()), inc("rented", 1));
            entity.getItem().setRented(1);
            rents.insertOne(clientSession, entity);
            clientSession.commitTransaction();
        } catch (Exception e) {
            clientSession.abortTransaction();
            e.printStackTrace();
        } finally {
            clientSession.close();
        }
    }

    @Override
    public void remove(int id) {
        try {
            rents.deleteOne(eq("_id", id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(RentMgd entity) {
        try {
            rents.replaceOne(eq("_id", entity.getId()), entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public RentMgd findById(int id) {
        try {
            rents.find(eq("_id", id)).first();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RentMgd> findAll() {
        try {
            FindIterable<RentMgd> mongoRentsMgd = rents.find();

            List<RentMgd> mongoRents = new ArrayList<>();

            for (RentMgd rent : mongoRentsMgd) {
                mongoRents.add(rent);
            }
            return mongoRents;
        } catch (Exception e) {
            return null;
        }
    }

}
