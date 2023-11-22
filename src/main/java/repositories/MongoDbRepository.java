package repositories;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import mgd.AbstractEntityMgd;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MongoDbRepository<T extends AbstractEntityMgd> implements IRepository<T> {

    private Class<T> className;
    private String collectionName;
    private MongoDatabase mongoDatabase;


    public MongoDbRepository(Class<T> className, String collectionName, MongoDatabase db) {
        this.className = className;
        this.collectionName = collectionName;
        this.mongoDatabase = db;
    }

    @Override
    public T FindById(UUID id) {
        MongoCollection<T> collection = mongoDatabase.getCollection(collectionName, className);
        Bson filter = Filters.eq("_id",id);
        return collection.find(filter).first();
    }

    @Override
    public void Add(T entity) {
        MongoCollection<T> collection = mongoDatabase.getCollection(collectionName, className);
        collection.insertOne(entity);
    }

    @Override
    public void Remove(T entity) {
        MongoCollection<T> collection = mongoDatabase.getCollection(collectionName, className);
        Bson filter = Filters.eq("_id", entity.getEntityId());
        collection.deleteOne(filter);
    }

    @Override
    public void Update(T entity) {
        MongoCollection<T> collection = mongoDatabase.getCollection(collectionName, className);
        Bson filter = Filters.eq("_id", entity.getEntityId());
        collection.replaceOne(filter, entity);
    }

    @Override
    public List<T> FindAll() {
        MongoCollection<T> collection = mongoDatabase.getCollection(collectionName, className);
        return collection.find().into(new ArrayList<>());
    }
}
