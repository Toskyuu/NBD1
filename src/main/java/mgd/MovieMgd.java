package mgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

@BsonDiscriminator(key = "_clazz", value = "movie")
public class MovieMgd extends ItemMgd {
    @BsonProperty("total_time")
    private int totalTime;

    @BsonCreator
    public MovieMgd(@BsonProperty("_id") UUID entityId,
                    @BsonProperty("year_of_premiere") int yearOfPremiere,
                    @BsonProperty("is_rented") boolean isRented,
                    @BsonProperty("is_archive") boolean isArchive,
                    @BsonProperty("name") String name,
                    @BsonProperty("style") String style,
                    @BsonProperty("author") String author,
                    @BsonProperty("base_price") double basePrice,
                    @BsonProperty("total_time") int totalTime) {
        super(entityId, yearOfPremiere, isRented, isArchive, name, style, author, basePrice);
        this.totalTime = totalTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
}
