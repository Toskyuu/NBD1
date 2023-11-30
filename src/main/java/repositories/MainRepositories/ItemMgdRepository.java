package repositories.MainRepositories;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import mgd.ClientMgd;
import mgd.ItemMgd;
import org.bson.Document;
import repositories.AbstractMongoRepository;
import repositories.IRepository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class ItemMgdRepository extends AbstractMongoRepository implements IRepository<ItemMgd> {
    private final MongoCollection<ItemMgd> items = getDatabase().getCollection("items", ItemMgd.class);
    private final MongoCollection<Document> docItems = getDatabase().getCollection("courts");


    @Override
    public void add(ItemMgd entity) {
        try {
            items.insertOne(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try {
            items.deleteOne(eq("_id", id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ItemMgd entity) {
        try {
            items.replaceOne(eq("_id", entity.getId()), entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ItemMgd findById(int id) {
        try {
            return items.find(eq("_id", id)).first();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemMgd> findAll() {
        try {
            FindIterable<ItemMgd> mongoItemsMgd = items.find();

            List<ItemMgd> mongoItems = new ArrayList<>();

            for (ItemMgd item : mongoItems) {
                mongoItems.add(item);
            }
            return mongoItems;
        } catch (Exception e) {
            return null;
        }

//    @Override
//    public void close() {super.close()}

    }
}
