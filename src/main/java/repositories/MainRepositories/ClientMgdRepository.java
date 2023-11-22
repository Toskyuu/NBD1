package repositories.MainRepositories;

import com.mongodb.client.MongoDatabase;
import mgd.ClientMgd;
import repositories.MongoDbRepository;


public class ClientMgdRepository extends MongoDbRepository<ClientMgd> {
    public ClientMgdRepository(Class<ClientMgd> className, String collectionName, MongoDatabase db) {
        super(className, collectionName, db);
    }
}
