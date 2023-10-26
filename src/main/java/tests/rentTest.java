package tests;

import mainClasses.Client;
import mainClasses.Movie;
import mainClasses.Rent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class rentTest {

    private final LocalDate beginDate = LocalDate.now();
    private final LocalDate endDate = LocalDate.of(2023, 12, 10);
    private final Client client0 = new Client();
    private final Movie movie0 = new Movie(2022, true, "Harry Potter", "Fantasy", "Rowling", 15, 122);

    private final Rent rent0 = new Rent();

    @Test
    void constructorTest() {
        Rent rent1 = new Rent(beginDate, client0, movie0);
        Assertions.assertEquals(rent1.getBeginDate(), beginDate);
        Assertions.assertEquals(rent1.getClient(), client0);
        Assertions.assertEquals(rent1.getItem(), movie0);
    }

    @Test
    void setGetEndDateTest() {
        rent0.setEndDate(endDate);
        Assertions.assertEquals(rent0.getEndDate(), endDate);
    }
    @Test
    void endRentTest(){
        Rent rent2 = new Rent(beginDate, client0, movie0);
        rent2.endRent(endDate);
        Assertions.assertEquals(rent2.getEndDate(), endDate);
        Assertions.assertEquals(rent2.getRentCost(), beginDate.until(endDate, ChronoUnit.DAYS) * movie0.getBasePrice());
    }
    @Test
    void getRentDaysTest(){
        Rent rent2 = new Rent(LocalDate.of(2023,10,24), client0, movie0);
        rent2.endRent(LocalDate.of(2023,10,26));
        Assertions.assertEquals(rent2.getRentDays(), 2);
    }
    @Test
    void setGetRentCost(){
        rent0.setRentCost(12.5);
        Assertions.assertEquals(rent0.getRentCost(), 12.5);
    }

    @Test
    void getClientTest(){
        Client client1 = new Client("Jaro","Budzyn", "22222233");
        Rent rent2 = new Rent(beginDate, client1, movie0);
        Assertions.assertEquals(rent2.getClient().getFirstName(), "Jaro");
    }

    @Test
    void getItemTest(){
        Movie movie1 = new Movie(1992, true, "Ola", "Comedy", "John Paul", 7, 112);
        Rent rent2 = new Rent(beginDate, client0, movie1);
        Assertions.assertEquals(rent2.getItem().getAuthor(), "John Paul");
    }
}
