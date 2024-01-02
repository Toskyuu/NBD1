package repository.cassandra;

import codec.RentCodec;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import ids.RentIds;
import model.Rent;
import repository.IRepository;

import java.util.List;

public class RentRepository extends AbstractCassandraRepository implements IRepository<Rent> {

    private String keySpace = "rent_items";
    private String table = "rents_by_client";
    private static RentCodec rentCodec = new RentCodec();

    @Override
    public void add(Rent entity) {

    }

    @Override
    public void update(Rent entity) {

    }

    @Override
    public void remove(Rent entity) {

    }

    @Override
    public Rent findById(int id) {
        return null;
    }

    @Override
    public List<Rent> findAll() {
        return null;
    }

    @Override
    public void close() throws Exception {
        session.close();
    }

    public void createTable() {
        SimpleStatement createOrdersByClient =
                SchemaBuilder.createTable(keySpace, table)
                        .ifNotExists()
                        .withPartitionKey(RentIds.ID, DataTypes.INT)
                        .withClusteringColumn(RentIds.CLIENT_ID, DataTypes.INT)
                        .build();
        session.execute(createOrdersByClient);
    }
}
