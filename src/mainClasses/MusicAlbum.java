package mainClasses;

public class MusicAlbum extends Item {
    private int numberOfSongs;

    public MusicAlbum(int yearOfPremiere, boolean isRented, String name, String itemID, String style, String author, double basePrice, int numberOfSongs) {
        super(yearOfPremiere, isRented, name, style, author, basePrice);
        this.numberOfSongs = numberOfSongs;
    }
}
