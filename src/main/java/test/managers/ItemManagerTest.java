package test.managers;

import mainClasses.Item;
import mainClasses.Movie;
import mainClasses.MusicAlbum;
import managers.implementation.ItemManagerImpl;
import org.junit.After;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemManagerTest {
    ItemManagerImpl itemManager = new ItemManagerImpl();

    @After
    public void after() throws Exception {
        itemManager.close();
    }

    @Test
    public void test() {
        //adding
        Item item1 = new Movie(1,2001, 0,0,"Kosmiczna Odyseja", "Fantyastyczny", "IDK", 42.99, 212);
        Item item2 = new MusicAlbum(2,2021, 0,0,"Czerwony Dywan", "Hip hop", "Paluchs", 62.99, 15);

        assertTrue(itemManager.addItem(item1));
        assertTrue(itemManager.addItem(item2));

        assertEquals(itemManager.findAllItem().size(), 2);

        //removing
        Item item3 = new Movie(3,2001, 0,0,"Kosmiczna Odyseja", "Fantyastyczny", "IDK", 42.99, 212);
        Item item4 = new MusicAlbum(4,2021, 0,0,"Czerwony Dywan", "Hip hop", "Paluchs", 62.99, 15);


        assertTrue(itemManager.addItem(item3));
        assertTrue(itemManager.addItem(item4));

        itemManager.removeItem(item3);

        assertEquals(itemManager.findAllItem().size(), 3);

        //checking if db returns good values
        Movie item5 = new Movie(5,2001, 0,0,"Kosmiczna Odyseja", "Fantyastyczny", "IDK", 42.99, 212);

        itemManager.addItem(item5);

        assertEquals(item5.getId(), itemManager.findItemById(5).getId());
        assertEquals(item5.getYearOfPremiere(), itemManager.findItemById(5).getYearOfPremiere());
        assertEquals(item5.isRented(), itemManager.findItemById(5).isRented());
        assertEquals(item5.isArchive(), itemManager.findItemById(5).isArchive());
        assertEquals(item5.getName(), itemManager.findItemById(5).getName());
        assertEquals(item5.getStyle(), itemManager.findItemById(5).getStyle());
        assertEquals(item5.getAuthor(), itemManager.findItemById(5).getAuthor());
        assertEquals(item5.getBasePrice(), itemManager.findItemById(5).getBasePrice());

        // updating
        Movie item6 = new Movie(6,2001, 0,0,"Kosmiczna Odyseja", "Fantyastyczny", "IDK", 42.99, 212);

        itemManager.addItem(item6);

        String name = "Shrek";

        item6.setName(name);

        itemManager.updateItem(item6);

        assertEquals(name, itemManager.findItemById(6).getName());

        itemManager.removeItem(item1);
        itemManager.removeItem(item2);
        itemManager.removeItem(item4);
        itemManager.removeItem(item5);
        itemManager.removeItem(item6);
    }
}

