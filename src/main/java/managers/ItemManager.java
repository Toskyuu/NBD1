package managers;

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

    public Item getItem(Long id) {
        return itemRepository.Find(id);
    }

    public void edit(Item item) {
        itemRepository.Update(item);
    }

    public List<Item> getAllItems(){
        return itemRepository.getAll();
    }
}
