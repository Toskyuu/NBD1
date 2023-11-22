package repositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import mainClasses.Item;
import mainClasses.Movie;
import mainClasses.MusicAlbum;
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.ClassModel;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

public class AbstractMongoRepository {
    private ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
    private MongoCredential credential = MongoCredential.createCredential("admin","admin", "adminpassword".toCharArray());
    private CodecRegistry pojoCodecRegistry =
            CodecRegistries.fromProviders(PojoCodecProvider.builder()
                    .automatic(true)
                    .conventions(List.of(Conventions.ANNOTATION_CONVENTION))
                    .build());

    private MongoClient mongoClient;
    private MongoDatabase mongoDB;

    public AbstractMongoRepository() {
        clientConfiguration();
    }

    private void clientConfiguration() {
        ClassModel<Item> itemModel = ClassModel.builder(Item.class).enableDiscriminator(true).build();
        ClassModel<Movie> movieModel = ClassModel.builder(Movie.class).enableDiscriminator(true).build();
        ClassModel<MusicAlbum> musicAlbumModel = ClassModel.builder(MusicAlbum.class).enableDiscriminator(true).build();

        PojoCodecProvider itemMgdProvider = PojoCodecProvider.builder()
                .conventions(List.of(Conventions.ANNOTATION_CONVENTION))
                .register(itemModel, movieModel, musicAlbumModel)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .credential(credential)
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .codecRegistry(CodecRegistries.fromRegistries(
                        CodecRegistries.fromProviders(MongoClientSettings.getDefaultCodecRegistry(),
                                pojoCodecRegistry,
                                itemMgdProvider)
                )).build();

        mongoClient = MongoClients.create(settings);
        mongoDB = mongoClient.getDatabase("test");
    }

    public MongoDatabase getMongoDB() {
        return mongoDB;
    }
}
