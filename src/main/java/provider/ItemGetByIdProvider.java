package provider;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.mapper.MapperContext;
import com.datastax.oss.driver.api.mapper.entity.EntityHelper;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import model.Item;
import model.Movie;
import model.MusicAlbum;
import ids.ItemIds;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.literal;

public class ItemGetByIdProvider {
    private final CqlSession session;
    private EntityHelper<Movie> movieEntityHelper;
    private EntityHelper<MusicAlbum> musicAlbumEntityHelper ;
    public ItemGetByIdProvider(MapperContext ctx,EntityHelper<MusicAlbum> musicAlbumEntityHelper,
                               EntityHelper<Movie> movieEntityHelper) {
        this.session = ctx.getSession();
        this.musicAlbumEntityHelper = musicAlbumEntityHelper;
        this.movieEntityHelper = movieEntityHelper;
    }

    public Item findById(int id){
        Select selectItem = QueryBuilder
                .selectFrom(CqlIdentifier.fromCql("rent_items"), CqlIdentifier.fromCql("items"))
                .all()
                .where(Relation.column(CqlIdentifier.fromCql("id")).isEqualTo(literal(id)));
        Row row = session.execute(selectItem.build()).one();
        String discriminator = row.getString(ItemIds.DISCRIMINATOR);
        return switch (discriminator) {
            case "movie" -> getMovie(row);
            case "music_album" -> getMusicAlbum(row);
            default -> throw new IllegalArgumentException();
        };
    }

    public void create(Item item){
        session.execute(
                switch (item.getDiscriminator()) {
                    case "movie" -> {
                        Movie movie = (Movie) item;
                        yield session.prepare(movieEntityHelper.insert().build())
                                .bind()
                                .setInt(ItemIds.ID, movie.getId())
                                .setInt(ItemIds.YEAR_OF_PREMIERE, movie.getYearOfPremiere())
                                .setString(ItemIds.NAME, movie.getName())
                                .setString(ItemIds.STYLE, movie.getStyle())
                                .setString(ItemIds.AUTHOR, movie.getAuthor())
                                .setDouble(ItemIds.BASE_PRICE, movie.getBasePrice())
                                .setString(ItemIds.DISCRIMINATOR, movie.getDiscriminator())
                                .setInt(ItemIds.IS_RENTED, movie.isRented())
                                .setInt(ItemIds.TOTAL_TIME, movie.getTotalTime());
                    }
                    case "music_album" -> {
                        MusicAlbum musicAlbum = (MusicAlbum) item;
                        yield session.prepare(musicAlbumEntityHelper.insert().build())
                                .bind()
                                .setInt(ItemIds.ID, musicAlbum.getId())
                                .setInt(ItemIds.YEAR_OF_PREMIERE, musicAlbum.getYearOfPremiere())
                                .setString(ItemIds.NAME, musicAlbum.getName())
                                .setString(ItemIds.STYLE, musicAlbum.getStyle())
                                .setString(ItemIds.AUTHOR, musicAlbum.getAuthor())
                                .setDouble(ItemIds.BASE_PRICE, musicAlbum.getBasePrice())
                                .setString(ItemIds.DISCRIMINATOR, musicAlbum.getDiscriminator())
                                .setInt(ItemIds.IS_RENTED, musicAlbum.isRented())
                                .setInt(ItemIds.NUMBER_OF_SONGS, musicAlbum.getNumberOfSongs());
                    }
                    default -> throw new IllegalArgumentException();
                }
        );
    }

    public void update(Item item){
        session.execute(
                switch (item.getDiscriminator()) {
                    case "movie" -> {
                        Movie movie = (Movie) item;
                        yield session.prepare(movieEntityHelper.updateByPrimaryKey().build())
                                .bind()
                                .setInt(ItemIds.ID, movie.getId())
                                .setInt(ItemIds.YEAR_OF_PREMIERE, movie.getYearOfPremiere())
                                .setString(ItemIds.NAME, movie.getName())
                                .setString(ItemIds.STYLE, movie.getStyle())
                                .setString(ItemIds.AUTHOR, movie.getAuthor())
                                .setDouble(ItemIds.BASE_PRICE, movie.getBasePrice())
                                .setString(ItemIds.DISCRIMINATOR, movie.getDiscriminator())
                                .setInt(ItemIds.IS_RENTED, movie.isRented())
                                .setInt(ItemIds.TOTAL_TIME, movie.getTotalTime());

                    }
                    case "music_album" -> {
                        MusicAlbum musicAlbum = (MusicAlbum) item;
                        yield session.prepare(musicAlbumEntityHelper.updateByPrimaryKey().build())
                                .bind()
                                .setInt(ItemIds.ID, musicAlbum.getId())
                                .setInt(ItemIds.YEAR_OF_PREMIERE, musicAlbum.getYearOfPremiere())
                                .setString(ItemIds.NAME, musicAlbum.getName())
                                .setString(ItemIds.STYLE, musicAlbum.getStyle())
                                .setString(ItemIds.AUTHOR, musicAlbum.getAuthor())
                                .setDouble(ItemIds.BASE_PRICE, musicAlbum.getBasePrice())
                                .setString(ItemIds.DISCRIMINATOR, musicAlbum.getDiscriminator())
                                .setInt(ItemIds.IS_RENTED, musicAlbum.isRented())
                                .setInt(ItemIds.NUMBER_OF_SONGS, musicAlbum.getNumberOfSongs());
                    }
                    default -> throw new IllegalArgumentException();
                }
        );
    }


    private Item getMovie(Row row) {
        return new Movie(
                row.getInt(ItemIds.ID),
                row.getInt(ItemIds.TOTAL_TIME),
                row.getInt(ItemIds.YEAR_OF_PREMIERE),
                row.getString(ItemIds.NAME),
                row.getString(ItemIds.STYLE),
                row.getString(ItemIds.AUTHOR),
                row.getDouble(ItemIds.BASE_PRICE),
                row.getString(ItemIds.DISCRIMINATOR),
                row.getInt(ItemIds.IS_RENTED)
                );
    }

    private Item getMusicAlbum(Row row) {
        return new MusicAlbum(
                row.getInt(ItemIds.ID),
                row.getInt(ItemIds.NUMBER_OF_SONGS),
                row.getInt(ItemIds.YEAR_OF_PREMIERE),
                row.getString(ItemIds.NAME),
                row.getString(ItemIds.STYLE),
                row.getString(ItemIds.AUTHOR),
                row.getDouble(ItemIds.BASE_PRICE),
                row.getString(ItemIds.DISCRIMINATOR),
                row.getInt(ItemIds.IS_RENTED)
                );
    }
}
