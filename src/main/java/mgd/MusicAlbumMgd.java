package mgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

@BsonDiscriminator(key = "_clazz", value = "music_album")
public class MusicAlbumMgd extends ItemMgd {
    @BsonProperty("number_of_songs")
    private int numberOfSongs;

    @BsonCreator
    public MusicAlbumMgd(@BsonProperty("_id") int id,
                         @BsonProperty("year_of_premiere") int yearOfPremiere,
                         @BsonProperty("rented") int isRented,
                         @BsonProperty("archive") int isArchive,
                         @BsonProperty("name") String name,
                         @BsonProperty("style") String style,
                         @BsonProperty("author") String author,
                         @BsonProperty("base_price") double basePrice,
                         @BsonProperty("number_of_songs") int numberOfSongs) {
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
