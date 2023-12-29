package dao;

import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.QueryProvider;
import com.datastax.oss.driver.api.mapper.annotations.StatementAttributes;
import model.Item;
import model.Movie;
import model.MusicAlbum;
import provider.ItemGetByIdProvider;

@Dao
public interface ItemDao {
    @QueryProvider(providerClass = ItemGetByIdProvider.class,
            entityHelpers = {MusicAlbum.class, Movie.class})
    @StatementAttributes(consistencyLevel = "QUORUM")
    void create(Item item);

    @Delete
    @StatementAttributes(consistencyLevel = "QUORUM")
    void remove(Item item);

    @QueryProvider(providerClass = ItemGetByIdProvider.class,
            entityHelpers = {MusicAlbum.class, Movie.class})
    @StatementAttributes(consistencyLevel = "QUORUM")
    void update(Item item);

    @QueryProvider(providerClass = ItemGetByIdProvider.class,
            entityHelpers = {MusicAlbum.class, Movie.class})
    @StatementAttributes(consistencyLevel = "ONE")
    Item findById(int id);
}
