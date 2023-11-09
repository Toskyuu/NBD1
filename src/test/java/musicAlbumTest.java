import mainClasses.MusicAlbum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class musicAlbumTest {

    private final String name = "Shinning";
    private final int yearOfPremiere = 1988;
    private final boolean isRented = false;
    private final String author = "Stanley Kubrick";
    private final String style = "Horror";
    private final double basePrie = 13;
    private final int numberOfSongs = 13;

    private MusicAlbum musicAlbum0 = new MusicAlbum();
    @Test
    void constructorTest() {
        MusicAlbum musicAlbum1 = new MusicAlbum(yearOfPremiere, isRented, name,  style, author, basePrie, numberOfSongs);
        Assertions.assertEquals(musicAlbum1.getYearOfPremiere(), yearOfPremiere);
        Assertions.assertFalse(musicAlbum1.isRented());
        Assertions.assertEquals(musicAlbum1.getName(), name);
        Assertions.assertEquals(musicAlbum1.getStyle(), style);
        Assertions.assertEquals(musicAlbum1.getAuthor(), author);
        Assertions.assertEquals(musicAlbum1.getBasePrice(), basePrie);
        Assertions.assertEquals(musicAlbum1.getNumberOfSongs(), numberOfSongs);
    }

    @Test
    void setGetYearOfPremiereTest() {
        musicAlbum0.setYearOfPremiere(2024);
        Assertions.assertEquals(musicAlbum0.getYearOfPremiere(), 2024);
    }

    @Test
    void setGetIsRentedTest() {
        musicAlbum0.setRented(true);
        Assertions.assertEquals(musicAlbum0.isRented(), true);
    }

    @Test
    void setGetNameTest() {
        musicAlbum0.setName("Space Odyssey");
        Assertions.assertEquals(musicAlbum0.getName(), "Space Odyssey");
    }

    @Test
    void setGetStyleTest() {
        musicAlbum0.setStyle("Action");
        Assertions.assertEquals(musicAlbum0.getStyle(), "Action");
    }
    @Test
    void setGetAuthorTest() {
        musicAlbum0.setAuthor("Quentin Tarantino");
        Assertions.assertEquals(musicAlbum0.getAuthor(), "Quentin Tarantino");
    }
    @Test
    void setGetBasePriceTest() {
        musicAlbum0.setBasePrice(10.25);
        Assertions.assertEquals(musicAlbum0.getBasePrice(), 10.25);
    }
    @Test
    void setGetNumberOfSongsTest() {
        musicAlbum0.setNumberOfSongs(9);
        Assertions.assertEquals(musicAlbum0.getNumberOfSongs(), 9);
    }
}
