package redis;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class MusicAlbumJson extends ItemJson {
    @JsonbProperty("number_of_songs")
    private int numberOfSongs;

    @JsonbCreator
    public MusicAlbumJson(@JsonbProperty("_id") int id,
                          @JsonbProperty("year_of_premiere") int yearOfPremiere,
                          @JsonbProperty("rented") int isRented,
                          @JsonbProperty("archive") int isArchive,
                          @JsonbProperty("name") String name,
                          @JsonbProperty("style") String style,
                          @JsonbProperty("author") String author,
                          @JsonbProperty("base_price") double basePrice,
                          @JsonbProperty("number_of_songs") int numberOfSongs) {
        super(id, yearOfPremiere, isRented, isArchive, name, style, author, basePrice);
        this.numberOfSongs = numberOfSongs;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }
}
