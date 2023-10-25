package mainClasses;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "music_album_ID")
@Table(name = "music_albums")
public class MusicAlbum extends Item {

    private int numberOfSongs;

    public MusicAlbum(int yearOfPremiere, boolean isRented, String name, String style, String author, double basePrice, int numberOfSongs) {
        super(yearOfPremiere, isRented, name, style, author, basePrice);
        this.numberOfSongs = numberOfSongs;
    }

    public MusicAlbum() {

    }
}
