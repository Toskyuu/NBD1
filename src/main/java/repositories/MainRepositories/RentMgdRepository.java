package repositories.MainRepositories;

import com.mongodb.client.MongoDatabase;
import mgd.RentMgd;
import repositories.MongoDbRepository;

public class RentMgdRepository extends MongoDbRepository<RentMgd> {
    public RentMgdRepository(Class<RentMgd> className, String collectionName, MongoDatabase db) {
        super(className, collectionName, db);
    }
}
