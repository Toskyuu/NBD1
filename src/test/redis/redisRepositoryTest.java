package test.redis;

import mainClasses.Client;
import mainClasses.MusicAlbum;
import mapper.ClientMapper;
import mapper.ItemMapper;
import mapper.RentMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import redis.RentJson;
import repositories.RedisRepositories.RentRedisRepository;

import static org.junit.jupiter.api.Assertions.*;

public class redisRepositoryTest {
    RentRedisRepository rents = new RentRedisRepository();

    @AfterEach
    public void after() {
        rents.clearCache();
    }

    @Test
    public void addTest() {
        Client client = new Client(1,"Janusz","Kowalski", false,"545656357");
        MusicAlbum musicAlbum1 = new MusicAlbum(1,2001, 0,0, "album", "rap", "ktos", 24.56, 14);
        MusicAlbum musicAlbum2 = new MusicAlbum(2,2021, 0,0, "album2", "pop", "ktos2", 29.56, 18);

        RentJson rentJson = new RentJson(1, ClientMapper.toClientJson(client), ItemMapper.itemToRedis(musicAlbum1));
        RentJson rentJson2 = new RentJson(2, ClientMapper.toClientJson(client), ItemMapper.itemToRedis(musicAlbum2));

        assertTrue(rents.add(rentJson));
        assertTrue(rents.add(rentJson2));

        assertEquals(2, rents.findAll().size());
    }

    @Test
    public void removeTest() {
        Client client = new Client(1,"Janusz","Kowalski", false,"545656357");
        MusicAlbum musicAlbum1 = new MusicAlbum(1,2001, 0,0, "album", "rap", "ktos", 24.56, 14);
        MusicAlbum musicAlbum2 = new MusicAlbum(2,2021, 0,0, "album2", "pop", "ktos2", 29.56, 18);

        RentJson rentJson = new RentJson(1, ClientMapper.toClientJson(client), ItemMapper.itemToRedis(musicAlbum1));
        RentJson rentJson2 = new RentJson(2, ClientMapper.toClientJson(client), ItemMapper.itemToRedis(musicAlbum2));

        assertTrue(rents.add(rentJson));
        assertTrue(rents.add(rentJson2));

        rents.remove(2);

        assertEquals(1, rents.findAll().size());
    }

    @Test
    public void clearCacheTest() {
        Client client = new Client(1,"Janusz","Kowalski", false,"545656357");
        MusicAlbum musicAlbum1 = new MusicAlbum(1,2001, 0,0, "album", "rap", "ktos", 24.56, 14);
        MusicAlbum musicAlbum2 = new MusicAlbum(2,2021, 0,0, "album2", "pop", "ktos2", 29.56, 18);

        RentJson rentJson = new RentJson(1, ClientMapper.toClientJson(client), ItemMapper.itemToRedis(musicAlbum1));
        RentJson rentJson2 = new RentJson(2, ClientMapper.toClientJson(client), ItemMapper.itemToRedis(musicAlbum2));

        assertTrue(rents.add(rentJson));
        assertTrue(rents.add(rentJson2));

        rents.clearCache();

        assertEquals(0, rents.findAll().size());
    }

    @Test
    public void returnTest() {
        Client client = new Client(1,"Janusz","Kowalski", false,"545656357");
        MusicAlbum musicAlbum1 = new MusicAlbum(1,2001, 0,0, "album", "rap", "ktos", 24.56, 14);

        RentJson rentJson = new RentJson(1, ClientMapper.toClientJson(client), ItemMapper.itemToRedis(musicAlbum1));

        assertTrue(rents.add(rentJson));

        RentJson returnedRent = rents.findById(1);

        assertEquals(rentJson.getClient().getFirstName(), returnedRent.getClient().getFirstName());
        assertEquals(rentJson.getClient().getLastName(), returnedRent.getClient().getLastName());
        assertEquals(rentJson.getClient().getPhoneNumber(), returnedRent.getClient().getPhoneNumber());

        assertEquals(rentJson.getItem().getAuthor(), returnedRent.getItem().getAuthor());
        assertEquals(rentJson.getItem().getName(), returnedRent.getItem().getName());
        assertEquals(rentJson.getItem().getStyle(), returnedRent.getItem().getStyle());
    }

}
