package repositories.MainRepositories;

import com.mongodb.client.MongoDatabase;
import mgd.ItemMgd;
import repositories.MongoDbRepository;

public class ItemMgdRepository extends MongoDbRepository<ItemMgd> {
    public ItemMgdRepository(Class<ItemMgd> className, String collectionName, MongoDatabase db) {
        super(className, collectionName, db);
    }
}
