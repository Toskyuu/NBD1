package repositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import validationOption.ClientValidationOptions;
import validationOption.ItemValidationOptions;

import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;


public abstract class AbstractMongoRepository implements AutoCloseable {
    private ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017,localhost:27018,localhost:27019/?replicaSet=replica_set_single");
    private MongoCredential credential = MongoCredential.createCredential("admin","admin", "adminpassword".toCharArray());
    CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
    CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
    MongoClientSettings clientSettings = MongoClientSettings.builder()
            .credential(credential)
            .applyConnectionString(connectionString)
            .codecRegistry(codecRegistry)
            .build();
    private MongoClient mongoClient;
    private MongoDatabase mongoDB;

    public AbstractMongoRepository() {
        initDbConnection();
    }

    private void initDbConnection() {
        mongoClient = MongoClients.create(clientSettings);
        mongoDB = mongoClient.getDatabase("rentItemsDb");

        if (!collectionExist("clients"))
            mongoDB.createCollection("clients", new CreateCollectionOptions().validationOptions(ClientValidationOptions.options));
        if (!collectionExist("items"))
            mongoDB.createCollection("items", new CreateCollectionOptions().validationOptions(ItemValidationOptions.options));
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getDatabase() {
        return mongoDB;
    }

    public boolean collectionExist(String collectionName) {
        for (String existingCollectionName : mongoDB.listCollectionNames()) {
            if (existingCollectionName.equals(collectionName))
                return true;
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        mongoClient.close();
    }
}
