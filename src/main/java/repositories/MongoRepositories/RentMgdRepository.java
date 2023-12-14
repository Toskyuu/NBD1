package repositories.MongoRepositories;

import com.mongodb.client.ClientSession;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import mapper.RentMapper;
import mgd.ClientMgd;
import mgd.ItemMgd;
import mgd.RentMgd;
import org.bson.Document;
import repositories.AbstractMongoRepository;
import repositories.IRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.inc;
import static com.mongodb.client.model.Updates.set;


public class RentMgdRepository extends AbstractMongoRepository implements IRepository<RentMgd> {
    private final MongoCollection<RentMgd> rents = getDatabase().getCollection("rents", RentMgd.class);
    private final MongoCollection<Document> docRents = getDatabase().getCollection("rents");
    private final MongoCollection<ClientMgd> clients = getDatabase().getCollection("clients", ClientMgd.class);
    private final MongoCollection<ItemMgd> items = getDatabase().getCollection("items", ItemMgd.class);


    @Override
    public boolean add(RentMgd entity) {
        ClientSession clientSession = getMongoClient().startSession();
        try {
            if(entity.getEndDate() != null) {
                if (entity.getEndDate().before(entity.getBeginDate())) {
                    throw new Exception();
                }
            }

            if(entity.getItem().isRented() == 1) {
                throw new Exception();
            }

            clientSession.startTransaction();
            items.updateOne(clientSession, eq("_id", entity.getItem().getId()), inc("rented", 1));
            entity.getItem().setRented(1);
            rents.insertOne(clientSession, entity);
            clientSession.commitTransaction();
            return true;
        } catch (Exception e) {
            clientSession.abortTransaction();
            e.printStackTrace();
            return false;
        } finally {
            clientSession.close();
        }
    }

    @Override
    public boolean remove(int id) {
        try {
            rents.deleteOne(eq("_id", id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(RentMgd entity) {
        try {
            rents.replaceOne(eq("_id", entity.getId()), entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public RentMgd findById(int id) {
        try {
            Document rent = docRents.find(eq("_id", id)).first();
            return rent != null ? RentMapper.toRentMgd(rent) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RentMgd> findAll() {
        try {
            FindIterable<Document> documentRents = docRents.find();

            List<RentMgd> mongoRent = new ArrayList<>();

            for (Document rent : documentRents) {
                mongoRent.add(RentMapper.toRentMgd(rent));
            }
            return mongoRent;
        } catch (Exception e) {
            return null;
        }
    }

    public void endRent(RentMgd rent, Date date) {
        ClientSession clientSession = getMongoClient().startSession();
        try {
            if(rent.getEndDate() != null) {
                if (rent.getEndDate().before(rent.getBeginDate())) {
                    throw new Exception();
                }
            }

            clientSession.startTransaction();
            items.updateOne(clientSession, eq("_id", rent.getItem().getId()), set("rented", 0));
            rent.getItem().setRented(0);
            rents.replaceOne(eq("_id", rent.getId()), rent);
            clientSession.commitTransaction();
        } catch (Exception e) {
            clientSession.abortTransaction();
            e.printStackTrace();
        } finally {
            clientSession.close();
        }
    }
}
