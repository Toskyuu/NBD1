package managers;

import mainClasses.Item;

import java.util.List;

public interface ItemManager {
    void addItem(Item item);
    void updateItem(Item item);
    void removeItem(Item item);
    List<Item> findAllItem();
    Item findItemById(int id);
    void close() throws Exception;
}
