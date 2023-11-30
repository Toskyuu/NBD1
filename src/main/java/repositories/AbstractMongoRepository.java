package repositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import mgd.AbstractEntityMgd;
import mgd.ItemMgd;
import mgd.MovieMgd;
import mgd.MusicAlbumMgd;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import validationOption.ClientValidationOptions;
import validationOption.ItemValidationOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;


public abstract class AbstractMongoRepository implements AutoCloseable {
    private ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017,localhost:27018,localhost:27019/?replicaSet=replica_set_single");
    private MongoCredential credential = MongoCredential.createCredential("admin","admin", "adminpassword".toCharArray());
//    private CodecRegistry pojoCodecRegistry =
//            CodecRegistries.fromProviders(PojoCodecProvider.builder()
//                    .automatic(true)
//                    .conventions(List.of(Conventions.ANNOTATION_CONVENTION))
//                    .build());

    private final CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder()
                    .automatic(true)
                    .build()));

    private MongoClient mongoClient;
    private MongoDatabase mongoDB;
//    protected Class<T> clazz;
//    protected String collectionName;

    public AbstractMongoRepository() {
//        this.clazz = className;
//        this.collectionName = collectionName;
        clientConfiguration();
    }

    private void clientConfiguration() {
//        ClassModel<ItemMgd> itemModel = ClassModel.builder(ItemMgd.class).enableDiscriminator(true).build();
//        ClassModel<MovieMgd> movieModel = ClassModel.builder(MovieMgd.class).enableDiscriminator(true).build();
//        ClassModel<MusicAlbumMgd> musicAlbumModel = ClassModel.builder(MusicAlbumMgd.class).enableDiscriminator(true).build();

//        PojoCodecProvider itemMgdProvider = PojoCodecProvider.builder()
//                .conventions(List.of(Conventions.ANNOTATION_CONVENTION))
//                .register(itemModel, movieModel, musicAlbumModel)
//                .build();

//        MongoClientSettings settings = MongoClientSettings.builder()
//                .credential(credential)
//                .applyConnectionString(connectionString)
//                .codecRegistry(fromRegistries(
//                        MongoClientSettings.getDefaultCodecRegistry(),
//                                pojoCodecRegistry)
//                ).build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyConnectionString(connectionString)
                .codecRegistry(CodecRegistries.fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        pojoCodecRegistry))
                .build();

        mongoClient = MongoClients.create(settings);
        mongoDB = mongoClient.getDatabase("rentItemsDb");

        if (!collectionExist("clients"))
            mongoDB.createCollection("clients", new CreateCollectionOptions().validationOptions(ClientValidationOptions.options));
        if (!collectionExist("items"))
            mongoDB.createCollection("items", new CreateCollectionOptions().validationOptions(ItemValidationOptions.options));
    }

//    @Override
//    public T findById(int id) {
//        MongoCollection<T> collection = mongoDB.getCollection(collectionName, clazz);
//        Bson filter = Filters.eq("_id",id);
//        return collection.find(filter).first();
//    }
//
//    @Override
//    public void add(T entity) {
//        MongoCollection<T> collection = mongoDB.getCollection(collectionName, clazz);
//        collection.insertOne(entity);
//    }
//
//    @Override
//    public void remove(T entity) {
//        MongoCollection<T> collection = mongoDB.getCollection(collectionName, clazz);
//        Bson filter = Filters.eq("_id", entity.getId());
//        collection.deleteOne(filter);
//    }
//
//    @Override
//    public void update(T entity) {
//        MongoCollection<T> collection = mongoDB.getCollection(collectionName, clazz);
//        Bson filter = Filters.eq("_id", entity.getId());
//        collection.replaceOne(filter, entity);
//    }
//
//    @Override
//    public List<T> findAll() {
//        MongoCollection<T> collection = mongoDB.getCollection(collectionName, clazz);
//        return collection.find().into(new ArrayList<>());
//    }

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

    public void close() throws Exception {
//        mongoClient.getDatabase("test").drop();
        mongoClient.close();
    }
}
