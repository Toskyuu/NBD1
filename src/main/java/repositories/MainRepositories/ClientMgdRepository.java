package repositories.MainRepositories;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import mgd.ClientMgd;
import repositories.AbstractMongoRepository;
import repositories.IRepository;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ClientMgdRepository extends AbstractMongoRepository implements IRepository<ClientMgd> {
    private final MongoCollection<ClientMgd> clients = getDatabase().getCollection("clients", ClientMgd.class);


    @Override
    public boolean add(ClientMgd entity) {
        try {
            clients.insertOne(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
        try {
            clients.deleteOne(eq("_id", id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ClientMgd entity) {
        try {
            clients.replaceOne(eq("_id", entity.getId()), entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ClientMgd findById(int id) {
        try {
            return clients.find(eq("_id", id)).first();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ClientMgd> findAll() {
        try {
            FindIterable<ClientMgd> mongoClientsMgd = clients.find();

            List<ClientMgd> mongoClients = new ArrayList<>();

            for (ClientMgd client : mongoClientsMgd) {
                mongoClients.add(client);
            }
            return mongoClients;
        } catch (Exception e) {
            return null;
        }
    }
}
