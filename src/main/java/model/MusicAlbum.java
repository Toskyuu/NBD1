package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;

@Entity(defaultKeyspace = "rent_a_item")
@CqlName("items")
public class MusicAlbum extends Item {
    @CqlName("number_of_songs")
    private final int numberOfSongs;

    public MusicAlbum(int id, int numberOfSongs, int yearOfPremiere, String name, String style, String author, double basePrice,
                      String discriminator,  int isRented) {
        super(id, yearOfPremiere, name, style, author, basePrice, "music_album", isRented);
        this.numberOfSongs = numberOfSongs;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }
}
