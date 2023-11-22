package mainClasses;

import java.util.UUID;

public class MusicAlbum extends Item {
    private int numberOfSongs;

    public MusicAlbum(int yearOfPremiere, boolean isRented, String name, String style, String author, double basePrice, int numberOfSongs) {
        super(yearOfPremiere, isRented, name, style, author, basePrice);
        this.numberOfSongs = numberOfSongs;
    }

    public MusicAlbum(UUID entityID, int yearOfPremiere, boolean isRented, boolean isArchive, String name, String style, String author, double basePrice, int numberOfSongs) {
        super(entityID, yearOfPremiere, isRented, isArchive, name, style, author, basePrice);
        this.numberOfSongs = numberOfSongs;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }
}
