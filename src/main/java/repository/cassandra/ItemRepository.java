package repository.cassandra;

import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import dao.ItemDao;
import ids.ItemIds;
import mapper.ItemMapper;
import mapper.ItemMapperBuilder;
import model.Item;
import repository.IRepository;

import java.util.List;

public class ItemRepository extends AbstractCassandraRepository implements IRepository<Item> {

    private String keySpace = "rent_items";
    private String table = "items";

    @Override
    public void add(Item entity) {
        ItemMapper itemMapper = new ItemMapperBuilder(session).build();
        ItemDao itemDao = itemMapper.itemDao(keySpace, table);
        itemDao.create(entity);
    }

    @Override
    public void update(Item entity) {
        ItemMapper itemMapper = new ItemMapperBuilder(session).build();
        ItemDao itemDao = itemMapper.itemDao(keySpace, table);
        itemDao.update(entity);
    }

    @Override
    public void remove(Item entity) {
        ItemMapper itemMapper = new ItemMapperBuilder(session).build();
        ItemDao itemDao = itemMapper.itemDao(keySpace, table);
        itemDao.remove(entity);
    }

    @Override
    public Item findById(int id) {
        ItemMapper itemMapper = new ItemMapperBuilder(session).build();
        ItemDao itemDao = itemMapper.itemDao(keySpace, table);
        return itemDao.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public void close() throws Exception {
        session.close();
    }

    public void createTable() {
        SimpleStatement createTableIfNotExist =
                SchemaBuilder.createTable(keySpace, table)
                        .ifNotExists()
                        .withPartitionKey(ItemIds.ID, DataTypes.INT)
                        .withColumn(ItemIds.YEAR_OF_PREMIERE, DataTypes.INT)
                        .withColumn(ItemIds.NAME, DataTypes.ASCII)
                        .withColumn(ItemIds.STYLE, DataTypes.ASCII)
                        .withColumn(ItemIds.AUTHOR, DataTypes.ASCII)
                        .withColumn(ItemIds.BASE_PRICE, DataTypes.DOUBLE)
                        .withColumn(ItemIds.DISCRIMINATOR, DataTypes.ASCII)
                        .withColumn(ItemIds.IS_RENTED, DataTypes.INT)
                        .withColumn(ItemIds.TOTAL_TIME, DataTypes.INT)
                        .withColumn(ItemIds.NUMBER_OF_SONGS, DataTypes.INT)
                        .build();

        session.execute(createTableIfNotExist);
    }

}
