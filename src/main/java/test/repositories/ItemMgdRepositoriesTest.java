package test.repositories;

import mgd.ItemMgd;
import mgd.MovieMgd;
import mgd.MusicAlbumMgd;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.MainRepositories.ItemMgdRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemMgdRepositoriesTest {
    ItemMgdRepository itemMgdRepository;

    @BeforeEach
    public void before(){
        itemMgdRepository= new ItemMgdRepository();
    }


    @Test
    public void addTest() {
        ItemMgd item = new MusicAlbumMgd(1,2001, 0,0, "album", "rap", "ktos", 24.56, 14);
        ItemMgd item2 = new MusicAlbumMgd(2,2020, 1, 0, "niealbum", "cos", "bam", 24.56, 14);
        itemMgdRepository.add(item);
        itemMgdRepository.add(item2);
        assertEquals(itemMgdRepository.findAll().size(), 2);
    }

    @Test
    public void removeTest() {
        ItemMgd item = new MusicAlbumMgd(3,2001, 0, 0, "album", "rap", "ktos", 24.56, 14);
        ItemMgd item2 = new MusicAlbumMgd(4,2020, 1,0, "niealbum", "cos", "bam", 24.56, 14);
        itemMgdRepository.add(item);
        itemMgdRepository.add(item2);
        assertEquals(itemMgdRepository.findAll().size(), 2);
        itemMgdRepository.remove(item.getId());
        assertEquals(itemMgdRepository.findAll().size(), 1);
    }

    @Test
    public void updateTest() {
        ItemMgd item = new MusicAlbumMgd(1,2001, 0,0, "album", "rap", "ktos", 24.56, 14);
        itemMgdRepository.add(item);
        assertEquals(itemMgdRepository.findById(item.getId()).getName(), "album");
        item.setName("changed");
        itemMgdRepository.update(item);
        assertEquals(itemMgdRepository.findById(item.getId()).getName(), "changed");
    }

    @Test
    public void checkIfRepositoryReturnsProperClasses() {
        ItemMgd item = new MusicAlbumMgd(1,2001, 0, 0, "album", "rap", "ktos", 24.56, 14);
        ItemMgd item2 = new MovieMgd(2,2001, 0, 0, "Batman", "przygodowy", "unknwon", 25, 200);

        itemMgdRepository.add(item);
        itemMgdRepository.add(item2);

        ItemMgd musicAlbumMgd = itemMgdRepository.findById(1);
        ItemMgd movieMgd = itemMgdRepository.findById(2);

        assertEquals(musicAlbumMgd.getClass(), MusicAlbumMgd.class);
        assertEquals(movieMgd.getClass(), MovieMgd.class);
    }

    @AfterEach
    public void after() throws Exception {
        itemMgdRepository.close();
    }
}
