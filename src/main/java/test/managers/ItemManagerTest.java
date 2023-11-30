package test.managers;

import mainClasses.Client;
import mainClasses.Item;
import mainClasses.Movie;
import mainClasses.MusicAlbum;
import managers.ItemManager;
import managers.implementation.ItemManagerImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemManagerTest {
    ItemManagerImpl itemManager;

    @BeforeEach
    public void before() {
        itemManager = new ItemManagerImpl();
    }

    @Test
    public void addItems() {
        Item item1 = new Movie(1,2001, 0,0,"Kosmiczna Odyseja", "Fantyastyczny", "IDK", 42.99, 212);
        Item item2 = new MusicAlbum(2,2021, 0,0,"Czerwony Dywan", "Hip hop", "Paluchs", 62.99, 15);

        itemManager.addItem(item1);
        itemManager.addItem(item2);

        assertEquals(itemManager.findAllItem().size(), 2);
    }

    @Test
    public void removeItems() {
        Item item1 = new Movie(1,2001, 0,0,"Kosmiczna Odyseja", "Fantyastyczny", "IDK", 42.99, 212);
        Item item2 = new MusicAlbum(2,2021, 0,0,"Czerwony Dywan", "Hip hop", "Paluchs", 62.99, 15);


        itemManager.addItem(item1);
        itemManager.addItem(item2);

        itemManager.removeItem(item1);

        assertEquals(itemManager.findAllItem().size(), 1);
    }

    @Test
    public void checkingIfDbReturnsGoodValues() {
        Movie item1 = new Movie(1,2001, 0,0,"Kosmiczna Odyseja", "Fantyastyczny", "IDK", 42.99, 212);

        itemManager.addItem(item1);

        assertEquals(item1.getId(), itemManager.findItemById(1).getId());
        assertEquals(item1.getYearOfPremiere(), itemManager.findItemById(1).getYearOfPremiere());
        assertEquals(item1.isRented(), itemManager.findItemById(1).isRented());
        assertEquals(item1.isArchive(), itemManager.findItemById(1).isArchive());
        assertEquals(item1.getName(), itemManager.findItemById(1).getName());
        assertEquals(item1.getStyle(), itemManager.findItemById(1).getStyle());
        assertEquals(item1.getAuthor(), itemManager.findItemById(1).getAuthor());
        assertEquals(item1.getBasePrice(), itemManager.findItemById(1).getBasePrice());

    }

    @Test
    public void checkingIfUpdatingWorks() {
        Movie item1 = new Movie(1,2001, 0,0,"Kosmiczna Odyseja", "Fantyastyczny", "IDK", 42.99, 212);

        itemManager.addItem(item1);

        String name = "Shrek";

        item1.setName(name);

        itemManager.updateItem(item1);

        assertEquals(name, itemManager.findItemById(1).getName());
    }

    @AfterEach
    public void after() throws Exception {
        itemManager.close();
    }
}

