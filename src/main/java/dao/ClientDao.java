package dao;

import com.datastax.oss.driver.api.mapper.annotations.*;
import model.Client;

import java.util.List;

@Dao
public interface ClientDao {
    @Insert
    @StatementAttributes(consistencyLevel = "QUORUM")
    void create(Client client);

    @Delete
    @StatementAttributes(consistencyLevel = "QUORUM")
    void remove(Client client);

    @Update
    @StatementAttributes(consistencyLevel = "QUORUM")
    void update(Client client);

    @Select
    @StatementAttributes(consistencyLevel = "ONE")
    Client findById(int id);

    @Select
    @StatementAttributes(consistencyLevel = "ONE")
    List<Client> findAll();
}
