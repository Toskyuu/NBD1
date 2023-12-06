package test.mapper;

import mainClasses.Movie;
import mainClasses.MusicAlbum;
import mapper.ItemMapper;
import mgd.MovieMgd;
import mgd.MusicAlbumMgd;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ItemMapperTest {

    @Test
    public void fromMongoItemTest() {
        MusicAlbumMgd musicAlbumMgd = new MusicAlbumMgd(1,2001, 0,0, "album", "rap", "ktos", 24.56, 14);
        MovieMgd movieMgd = new MovieMgd(2,2001, 0, 0, "Batman", "przygodowy", "unknwon", 25, 200);

        MusicAlbum musicAlbum = (MusicAlbum) ItemMapper.itemFromMongo(musicAlbumMgd);
        assertEquals(musicAlbum.getId(), musicAlbumMgd.getId());
        assertEquals(musicAlbum.getYearOfPremiere(), musicAlbumMgd.getYearOfPremiere());
        assertEquals(musicAlbum.isRented(), musicAlbumMgd.isRented());
        assertEquals(musicAlbum.isArchive(), musicAlbumMgd.isArchive());
        assertEquals(musicAlbum.getName(), musicAlbumMgd.getName());
        assertEquals(musicAlbum.getStyle(), musicAlbumMgd.getStyle());
        assertEquals(musicAlbum.getAuthor(), musicAlbumMgd.getAuthor());
        assertEquals(musicAlbum.getBasePrice(), musicAlbumMgd.getBasePrice(), 1);
        assertEquals(musicAlbum.getNumberOfSongs(), musicAlbumMgd.getNumberOfSongs());

        Movie movie = (Movie) ItemMapper.itemFromMongo(movieMgd);
        assertEquals(movie.getId(), movieMgd.getId());
        assertEquals(movie.getYearOfPremiere(), movieMgd.getYearOfPremiere());
        assertEquals(movie.isRented(), movieMgd.isRented());
        assertEquals(movie.isArchive(), movieMgd.isArchive());
        assertEquals(movie.getName(), movieMgd.getName());
        assertEquals(movie.getStyle(), movieMgd.getStyle());
        assertEquals(movie.getAuthor(), movieMgd.getAuthor());
        assertEquals(movie.getBasePrice(), movieMgd.getBasePrice(), 1);
        assertEquals(movie.getTotalTime(), movieMgd.getTotalTime());
    }

    @Test
    public void toMongoItemTest() {
        MusicAlbum musicAlbum = new MusicAlbum(1,2001, 0,0, "album", "rap", "ktos", 24.56, 14);
        Movie movie = new Movie(2,2001, 0, 0, "Batman", "przygodowy", "unknwon", 25, 200);

        MusicAlbumMgd musicAlbumMgd = (MusicAlbumMgd) ItemMapper.itemToMongo(musicAlbum);
        assertEquals(musicAlbumMgd.getId(), musicAlbum.getId());
        assertEquals(musicAlbumMgd.getYearOfPremiere(), musicAlbum.getYearOfPremiere());
        assertEquals(musicAlbumMgd.isRented(), musicAlbum.isRented());
        assertEquals(musicAlbumMgd.isArchive(), musicAlbum.isArchive());
        assertEquals(musicAlbumMgd.getName(), musicAlbum.getName());
        assertEquals(musicAlbumMgd.getStyle(), musicAlbum.getStyle());
        assertEquals(musicAlbumMgd.getAuthor(), musicAlbum.getAuthor());
        assertEquals(musicAlbumMgd.getBasePrice(), musicAlbum.getBasePrice(), 1);
        assertEquals(musicAlbumMgd.getNumberOfSongs(), musicAlbum.getNumberOfSongs());

        MovieMgd movieMgd = (MovieMgd) ItemMapper.itemToMongo(movie);
        assertEquals(movieMgd.getId(), movie.getId());
        assertEquals(movieMgd.getYearOfPremiere(), movie.getYearOfPremiere());
        assertEquals(movieMgd.isRented(), movie.isRented());
        assertEquals(movieMgd.isArchive(), movie.isArchive());
        assertEquals(movieMgd.getName(), movie.getName());
        assertEquals(movieMgd.getStyle(), movie.getStyle());
        assertEquals(movieMgd.getAuthor(), movie.getAuthor());
        assertEquals(movieMgd.getBasePrice(), movie.getBasePrice(), 1);
        assertEquals(movieMgd.getTotalTime(), movie.getTotalTime());
    }

    @Test
    public void toMusicAlbumMgdTest() {
        Document musicAlbumDoc = new Document();
        musicAlbumDoc.append("_id", 1)
                .append("_clazz", "music_album")
                .append("year_of_premiere", 2019)
                .append("rented", 0)
                .append("archive", 0)
                .append("name", "Test")
                .append("style", "Test")
                .append("author", "Autor")
                .append("base_price", 45.9)
                .append("number_of_songs", 29);


        Document movieDoc = new Document();
        movieDoc.append("_id", 2)
                .append("_clazz", "movie")
                .append("year_of_premiere", 2019)
                .append("rented", 0)
                .append("archive", 0)
                .append("name", "TestFilm")
                .append("style", "TestStyle")
                .append("author", "TestAuthor")
                .append("base_price", 45.9)
                .append("total_time", 245);


        MusicAlbumMgd musicAlbumMgd = (MusicAlbumMgd) ItemMapper.toItemMgd(musicAlbumDoc);
        assertEquals(musicAlbumMgd.getId(), musicAlbumDoc.get("_id"));
        assertEquals(musicAlbumMgd.getYearOfPremiere(), musicAlbumDoc.get("year_of_premiere"));
        assertEquals(musicAlbumMgd.isRented(), musicAlbumDoc.get("rented"));
        assertEquals(musicAlbumMgd.isArchive(), musicAlbumDoc.get("archive"));
        assertEquals(musicAlbumMgd.getName(), musicAlbumDoc.get("name"));
        assertEquals(musicAlbumMgd.getStyle(), musicAlbumDoc.get("style"));
        assertEquals(musicAlbumMgd.getAuthor(), musicAlbumDoc.get("author"));
        assertEquals(musicAlbumMgd.getBasePrice(), (Double) musicAlbumDoc.get("base_price"), 1);
        assertEquals(musicAlbumMgd.getNumberOfSongs(), musicAlbumDoc.get("number_of_songs"));

        MovieMgd movieMgd = (MovieMgd) ItemMapper.toItemMgd(movieDoc);
        assertEquals(movieMgd.getId(), movieDoc.get("_id"));
        assertEquals(movieMgd.getYearOfPremiere(), movieDoc.get("year_of_premiere"));
        assertEquals(movieMgd.isRented(), movieDoc.get("rented"));
        assertEquals(movieMgd.isArchive(), movieDoc.get("archive"));
        assertEquals(movieMgd.getName(), movieDoc.get("name"));
        assertEquals(movieMgd.getStyle(), movieDoc.get("style"));
        assertEquals(movieMgd.getAuthor(), movieDoc.get("author"));
        assertEquals(movieMgd.getBasePrice(), (Double) movieDoc.get("base_price"), 1);
        assertEquals(movieMgd.getTotalTime(), movieDoc.get("total_time"));

    }

}
