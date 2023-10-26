package tests;

import mainClasses.Client;
import mainClasses.MusicAlbum;
import mainClasses.Rent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repositories.RentRepository;

import java.time.LocalDate;

public class rentRepositoryTest {
    private final Client client = new Client("Jan", "Kowalski", "232332");
    private final MusicAlbum musicAlbum = new MusicAlbum(2020, false, "JJ", "Rap", "Eminem", 15, 11);
    private RentRepository rr = new RentRepository();
    Rent rent = new Rent(LocalDate.now(), client, musicAlbum);
    @Test
    void addTest(){
        rr.Add(rent);
        Assertions.assertEquals(rr.Find(rent.getId()), rent);
    }

//    @Test
//    void updateTest(){
//        rr.Add(rent);
//        Assertions.assertEquals(cr.Find(client.getId()).getFirstName(), client.getFirstName());
//        client.setFirstName("Miłosz");
//        Assertions.assertEquals(cr.Find(client.getId()).getFirstName(), client.getFirstName());
//
//    }

    @Test
    void deleteTest(){
        rr.Add(rent);
        Assertions.assertEquals(rr.Find(rent.getId()).getBeginDate(), rent.getBeginDate());
        rr.Delete(rent);
        Assertions.assertNull(rr.Find(rent.getId()));

    }
}
