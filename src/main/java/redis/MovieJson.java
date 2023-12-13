package redis;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class MovieJson extends ItemJson {
    @JsonbProperty("total_time")
    private int totalTime;

    @JsonbCreator
    public MovieJson(@JsonbProperty("_id") int id,
                     @JsonbProperty("year_of_premiere") int yearOfPremiere,
                     @JsonbProperty("rented") int isRented,
                     @JsonbProperty("archive") int isArchive,
                     @JsonbProperty("name") String name,
                     @JsonbProperty("style") String style,
                     @JsonbProperty("author") String author,
                     @JsonbProperty("base_price") double basePrice,
                     @JsonbProperty("total_time") int totalTime) {
        super(id, yearOfPremiere, isRented, isArchive, name, style, author, basePrice);
        this.totalTime = totalTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
}
