package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;

@Entity(defaultKeyspace = "rent_a_item")
@CqlName("items")
public class MusicAlbum extends Item {
    @CqlName("number_of_songs")
    private final int numberOfSongs;

    public MusicAlbum(int id, int yearOfPremiere, int isRented, String name, String style, String author, double basePrice,
                      int numberOfSongs, String discriminator) {
        super(id, yearOfPremiere, isRented, name, style, author, basePrice, "music_album");
        this.numberOfSongs = numberOfSongs;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }
}
