package tests;

import mainClasses.Client;
import mainClasses.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class movieTest {

    private final String name = "Shinning";
    private final int yearOfPremiere = 1988;
    private final boolean isRented = false;
    private final String author = "Stanley Kubrick";
    private final String style = "Horror";
    private final double basePrie = 13;
    private final int totalTime = 112;

    private Movie movie0 = new Movie();
    @Test
    void constructorTest() {
        Movie movie1 = new Movie(yearOfPremiere, isRented, name,  style, author, basePrie, totalTime);
        Assertions.assertEquals(movie1.getYearOfPremiere(), yearOfPremiere);
        Assertions.assertFalse(movie1.isRented());
        Assertions.assertEquals(movie1.getName(), name);
        Assertions.assertEquals(movie1.getStyle(), style);
        Assertions.assertEquals(movie1.getAuthor(), author);
        Assertions.assertEquals(movie1.getBasePrice(), basePrie);
        Assertions.assertEquals(movie1.getTotalTime(), totalTime);
    }

    @Test
    void setGetYearOfPremiereTest() {
        movie0.setYearOfPremiere(2024);
        Assertions.assertEquals(movie0.getYearOfPremiere(), 2024);
    }

    @Test
    void setGetIsRentedTest() {
        movie0.setRented(true);
        Assertions.assertEquals(movie0.isRented(), true);
    }

    @Test
    void setGetNameTest() {
    movie0.setName("Space Odyssey");
    Assertions.assertEquals(movie0.getName(), "Space Odyssey");
    }

    @Test
    void setGetStyleTest() {
        movie0.setStyle("Action");
        Assertions.assertEquals(movie0.getStyle(), "Action");
    }
    @Test
    void setGetAuthorTest() {
        movie0.setAuthor("Quentin Tarantino");
        Assertions.assertEquals(movie0.getAuthor(), "Quentin Tarantino");
    }
    @Test
    void setGetBasePriceTest() {
        movie0.setBasePrice(10.25);
        Assertions.assertEquals(movie0.getBasePrice(), 10.25);
    }
    @Test
    void setGetTotalTimeTest() {
        movie0.setTotalTime(100);
        Assertions.assertEquals(movie0.getTotalTime(), 100);
    }
}
