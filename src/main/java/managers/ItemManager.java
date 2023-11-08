package managers;

import exceptions.ClientException;
import exceptions.ItemException;
import mainClasses.Client;
import mainClasses.Item;
import repositories.ItemRepository;
import java.util.List;

public class ItemManager {
    private ItemRepository itemRepository;
    public ItemManager(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void registerItem(Item item){
        itemRepository.Add(item);
    }

    public void unregisterItem(Item item) {
        itemRepository.Delete(item);
    }

    public Item getItem(Long id) throws ItemException {
        Item item = itemRepository.Find(id);
        if(item == null) {
            throw new ItemException("No such client exist");
        } else {
            return item;
        }
    }

    public void edit(Item item) {
        itemRepository.Update(item);
    }

    public List<Item> getAllItems(){
        return itemRepository.getAll();
    }
}
