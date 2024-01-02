package repository.cassandra;

import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import dao.ClientDao;
import mapper.ClientMapper;
import mapper.ClientMapperBuilder;
import model.Client;
import repository.IRepository;
import ids.ClientIds;
import java.util.List;

public class ClientRepository extends AbstractCassandraRepository implements IRepository<Client> {

    private String keySpace = "rent_items";
    private String table = "clients";

    @Override
    public void add(Client entity) {
        ClientMapper clientMapper = new ClientMapperBuilder(session).build();
        ClientDao clientDao = clientMapper.clientDao(keySpace, table);
        clientDao.create(entity);
    }

    @Override
    public void update(Client entity) {
        ClientMapper clientMapper = new ClientMapperBuilder(session).build();
        ClientDao clientDao = clientMapper.clientDao(keySpace, table);
        clientDao.update(entity);
    }

    @Override
    public void remove(Client entity) {
        ClientMapper clientMapper = new ClientMapperBuilder(session).build();
        ClientDao clientDao = clientMapper.clientDao(keySpace, table);
        clientDao.remove(entity);
    }

    @Override
    public Client findById(int id) {
        ClientMapper clientMapper = new ClientMapperBuilder(session).build();
        ClientDao clientDao = clientMapper.clientDao(keySpace, table);
        return clientDao.findById(id);
    }

    @Override
    public List<Client> findAll() {
        ClientMapper clientMapper = new ClientMapperBuilder(session).build();
        ClientDao clientDao = clientMapper.clientDao(keySpace, table);
        return clientDao.findAll();
    }

    @Override
    public void close() throws Exception {
        session.close();
    }

    public void createTable() {
        SimpleStatement createTableIfNotExist =
                SchemaBuilder.createTable(keySpace, table)
                        .ifNotExists()
                        .withPartitionKey(ClientIds.ID, DataTypes.INT)
                        .withColumn(ClientIds.FIRST_NAME, DataTypes.ASCII)
                        .withColumn(ClientIds.SECOND_NAME, DataTypes.ASCII)
                        .withColumn(ClientIds.PHONE_NUMBER, DataTypes.ASCII)
                        .build();

        session.execute(createTableIfNotExist);
    }
}
