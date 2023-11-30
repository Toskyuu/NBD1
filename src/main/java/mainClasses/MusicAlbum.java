package mainClasses;

public class MusicAlbum extends Item {
    private int numberOfSongs;

    public MusicAlbum(int id, int yearOfPremiere, int isRented, int isArchive, String name, String style, String author, double basePrice, int numberOfSongs) {
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
