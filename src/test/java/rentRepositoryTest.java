import mainClasses.Client;
import mainClasses.MusicAlbum;
import mainClasses.Rent;
import managers.ClientManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repositories.ClientRepository;
import repositories.ItemRepository;
import repositories.RentRepository;

import java.time.LocalDate;

public class rentRepositoryTest {
    private final Client client = new Client("Jan", "Kowalski", "232332");
    private final MusicAlbum musicAlbum = new MusicAlbum(2020, false, "JJ", "Rap", "Eminem", 15, 11);
    private RentRepository rr = new RentRepository();
    private ClientRepository cR = new ClientRepository();
    private ItemRepository iM = new ItemRepository();
    private ClientManager cM = new ClientManager(cR);

    Rent rent = new Rent(LocalDate.now(), client, musicAlbum);

    @Test
    void addTest() {
        cM.registerClient(client);
        iM.Add(musicAlbum);

        rr.Add(rent);
        System.out.println(rent.getItem());
        System.out.println(rr.Find(rent.getId()));
        Assertions.assertEquals(rr.Find(rent.getId()), rent);
    }

    @Test
    void deleteTest(){
        cM.registerClient(client); //if we dont add client to repo client it wont work
        iM.Add(musicAlbum);
        rr.Add(rent);
        System.out.println(rr.getAll());
        Assertions.assertEquals(rr.Find(rent.getId()).getBeginDate(), rent.getBeginDate());
        rr.Delete(rent);
        Assertions.assertNull(rr.Find(rent.getId()));
    }
}
