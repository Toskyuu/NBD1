package mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mainClasses.Item;
import mainClasses.Movie;
import mainClasses.MusicAlbum;
import mgd.ItemMgd;
import mgd.MovieMgd;
import mgd.MusicAlbumMgd;
import org.bson.Document;
import redis.ItemJson;
import redis.MovieJson;
import redis.MusicAlbumJson;

public class ItemMapper {
    private static final String ID = "_id";
    private static final String YEAR_OF_PREMIERE = "year_of_premiere";
    private static final String IS_RENTED = "rented";
    private static final String IS_ARCHIVE = "archive";
    private static final String NAME = "name";
    private static final String STYLE = "style";
    private static final String AUTHOR = "author";
    private static final String BASE_PRICE = "base_price";
    private static final String TOTAL_TIME = "total_time";
    private static final String NUMBER_OF_SONGS = "number_of_songs";
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static ItemMgd itemToMongo(Item item) {
        if (item instanceof Movie) {
            return new MovieMgd(item.getId(), item.getYearOfPremiere(), item.isRented(), item.isArchive(), item.getName(), item.getStyle(), item.getAuthor(), item.getBasePrice(), ((Movie) item).getTotalTime());
        } else if (item instanceof MusicAlbum) {
            return new MusicAlbumMgd(item.getId(), item.getYearOfPremiere(), item.isRented(), item.isArchive(), item.getName(), item.getStyle(), item.getAuthor(), item.getBasePrice(), ((MusicAlbum) item).getNumberOfSongs());
        } else {
            return null;
        }
    }

    public static Item itemFromMongo(ItemMgd item) {
        if (item instanceof MovieMgd) {
            return new Movie(item.getId(), item.getYearOfPremiere(), item.isRented(), item.isArchive(), item.getName(), item.getStyle(), item.getAuthor(), item.getBasePrice(), ((MovieMgd) item).getTotalTime());
        } else if(item instanceof MusicAlbumMgd) {
            return new MusicAlbum(item.getId(), item.getYearOfPremiere(), item.isRented(), item.isArchive(), item.getName(), item.getStyle(), item.getAuthor(), item.getBasePrice(), ((MusicAlbumMgd) item).getNumberOfSongs());
        } else {
            return null;
        }
    }

    public static ItemMgd toItemMgd(Document itemDocument) {
        if (itemDocument.get("_clazz").equals("movie")) {
            return toMovieMgd(itemDocument);
        }
        if (itemDocument.get("_clazz").equals("music_album")) {
            return toMusicAlbumMgd(itemDocument);
        }
        return null;
    }

    private static MovieMgd toMovieMgd(Document courtDocument) {
        return new MovieMgd(
                courtDocument.get(ID, Integer.class),
                courtDocument.get(YEAR_OF_PREMIERE, Integer.class),
                courtDocument.get(IS_RENTED, Integer.class),
                courtDocument.get(IS_ARCHIVE, Integer.class),
                courtDocument.get(NAME, String.class),
                courtDocument.get(STYLE, String.class),
                courtDocument.get(AUTHOR, String.class),
                courtDocument.get(BASE_PRICE, Double.class),
                courtDocument.get(TOTAL_TIME, Integer.class)
        );
    }

    private static MusicAlbumMgd toMusicAlbumMgd(Document courtDocument) {
        return new MusicAlbumMgd(
                courtDocument.get(ID, Integer.class),
                courtDocument.get(YEAR_OF_PREMIERE, Integer.class),
                courtDocument.get(IS_RENTED, Integer.class),
                courtDocument.get(IS_ARCHIVE, Integer.class),
                courtDocument.get(NAME, String.class),
                courtDocument.get(STYLE, String.class),
                courtDocument.get(AUTHOR, String.class),
                courtDocument.get(BASE_PRICE, Double.class),
                courtDocument.get(NUMBER_OF_SONGS, Integer.class)
        );
    }

    public static String toItemJson(Item item) throws JsonProcessingException {
        return objectMapper.writeValueAsString(item);
    }

    public static Item mapJsonToItem(String json) throws JsonProcessingException {
        return objectMapper.readValue(json, Item.class);
    }

    public static ItemJson itemToRedis(Item item){
        if(item instanceof MusicAlbum){
            return musicAlbumToRedis((MusicAlbum) item);
        }
        else if(item instanceof Movie){
            return movieToRedis((Movie) item);
        }
        return null;
    }

    public static Item itemFromRedis(ItemJson item){
        if(item instanceof MusicAlbumJson){
            return musicAlbumFromRedis((MusicAlbumJson) item);
        }
        else if(item instanceof MovieJson){
            return movieFromRedis((MovieJson) item);
        }
        return null;
    }

    private static ItemJson musicAlbumToRedis(MusicAlbum item){
        return new MusicAlbumJson(item.getId(), item.getYearOfPremiere(), item.isRented(), item.isArchive(), item.getName(),
                item.getStyle(), item.getAuthor(), item.getBasePrice(), item.getNumberOfSongs());
    }
    private static ItemJson movieToRedis(Movie item){
        return new MovieJson(item.getId(), item.getYearOfPremiere(), item.isRented(), item.isArchive(), item.getName(),
                item.getStyle(), item.getAuthor(), item.getBasePrice(), item.getTotalTime());
    }

    private static Item musicAlbumFromRedis(MusicAlbumJson item) {
        return new MusicAlbum(item.getId(), item.getYearOfPremiere(), item.isRented(), item.isArchive(), item.getName(),
                item.getStyle(), item.getAuthor(), item.getBasePrice(), item.getNumberOfSongs());
    }

    private static Item movieFromRedis(MovieJson item) {
        return new Movie(item.getId(), item.getYearOfPremiere(), item.isRented(), item.isArchive(), item.getName(),
                item.getStyle(), item.getAuthor(), item.getBasePrice(), item.getTotalTime());
    }
}
