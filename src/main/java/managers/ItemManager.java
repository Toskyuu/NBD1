package managers;

import mainClasses.Item;

import java.util.List;

public interface ItemManager {
    boolean addItem(Item item);
    boolean updateItem(Item item);
    boolean removeItem(Item item);
    List<Item> findAllItem();
    Item findItemById(int id);
    void close() throws Exception;
}
