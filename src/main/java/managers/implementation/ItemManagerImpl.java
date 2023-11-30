package managers.implementation;

import mainClasses.Item;
import managers.ItemManager;
import mapper.ItemMapper;
import mgd.ItemMgd;
import repositories.MainRepositories.ItemMgdRepository;

import java.util.ArrayList;
import java.util.List;

public class ItemManagerImpl implements ItemManager {

    private ItemMgdRepository itemMgdRepository;


    public ItemManagerImpl() {
        this.itemMgdRepository = new ItemMgdRepository();
    }

    @Override
    public void addItem(Item item) {
        itemMgdRepository.add(ItemMapper.itemToMongo(item));
    }

    @Override
    public void updateItem(Item item) {
        itemMgdRepository.update(ItemMapper.itemToMongo(item));
    }

    @Override
    public void removeItem(Item item) {
        itemMgdRepository.remove(item.getId());
    }

    @Override
    public List<Item> findAllItem() {
        List<Item> items = new ArrayList<>();
        List<ItemMgd> itemMgds = itemMgdRepository.findAll();
        for (ItemMgd itemMgd : itemMgds) {
            items.add(ItemMapper.itemFromMongo(itemMgd));
        }
        return items;
    }

    @Override
    public Item findItemById(int id) {
        return ItemMapper.itemFromMongo(itemMgdRepository.findById(id));
    }

    @Override
    public void close() throws Exception {
        itemMgdRepository.close();
    }
}
