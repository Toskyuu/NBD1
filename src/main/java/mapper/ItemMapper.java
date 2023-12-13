package mapper;

import mainClasses.Item;
import mainClasses.Movie;
import mainClasses.MusicAlbum;
import mgd.ItemMgd;
import mgd.MovieMgd;
import mgd.MusicAlbumMgd;
import org.bson.Document;

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




//    public static Document itemToDocument(Item item) {
//        Document document = new Document(ID, item.getEntityID())
//                .append(YEAR_OF_PREMIERE, item.getYearOfPremiere())
//                .append(IS_RENTED, item.isRented())
//                .append(IS_ARCHIVE, item.isArchive())
//                .append(NAME, item.getName())
//                .append(STYLE, item.getStyle())
//                .append(AUTHOR, item.getAuthor())
//                .append(BASE_PRICE, item.getBasePrice());
//
//        if (item instanceof Movie) {
//            document.append(TOTAL_TIME, ((Movie) item).getTotalTime());
//        } else if (item instanceof MusicAlbum) {
//            document.append(NUMBER_OF_SONGS, ((MusicAlbum) item).getNumberOfSongs());
//        }
//
//        return document;
//    }
//
//    public static Item itemFromMongoDocument(Document document) {
//        UUID entityId = document.get(ID, UUID.class);
//        int yearOfPremiere = document.getInteger(YEAR_OF_PREMIERE);
//        boolean isRented = document.getBoolean(IS_RENTED);
//        boolean isArchive = document.getBoolean(IS_ARCHIVE);
//        String name = document.getString(NAME);
//        String style = document.getString(STYLE);
//        String author = document.getString(AUTHOR);
//        double basePrice = document.getDouble(BASE_PRICE);
//
//        if (document.containsKey(TOTAL_TIME)) {
//            // MOVIE
//            int totalTime = document.getInteger(TOTAL_TIME);
//            return new Movie(entityId, yearOfPremiere, isRented, isArchive, name, style, author, basePrice, totalTime);
//        } else if (document.containsKey(NUMBER_OF_SONGS)) {
//            // MUSIC ALBUM
//            int numberOfSongs = document.getInteger(NUMBER_OF_SONGS);
//            return new MusicAlbum(entityId, yearOfPremiere, isRented, isArchive, name, style, author, basePrice, numberOfSongs);
//        } else {
//            return new Item(entityId, yearOfPremiere, isRented, isArchive, name, style, author, basePrice);
//        }
//    }


//    public static Document itemToDocument(Item item) {
//        Document document = new Document(ID, item.getEntityID())
//                .append(YEAR_OF_PREMIERE, item.getYearOfPremiere())
//                .append(IS_RENTED, item.isRented())
//                .append(IS_ARCHIVE, item.isArchive())
//                .append(NAME, item.getName())
//                .append(STYLE, item.getStyle())
//                .append(AUTHOR, item.getAuthor())
//                .append(BASE_PRICE, item.getBasePrice());
//
//        if (item instanceof Movie) {
//            document.append(TOTAL_TIME, ((Movie) item).getTotalTime());
//        } else if (item instanceof MusicAlbum) {
//            document.append(NUMBER_OF_SONGS, ((MusicAlbum) item).getNumberOfSongs());
//        }
//
//        return document;
//    }
//
//    public static Item itemFromMongoDocument(Document document) {
//        UUID entityId = document.get(ID, UUID.class);
//        int yearOfPremiere = document.getInteger(YEAR_OF_PREMIERE);
//        boolean isRented = document.getBoolean(IS_RENTED);
//        boolean isArchive = document.getBoolean(IS_ARCHIVE);
//        String name = document.getString(NAME);
//        String style = document.getString(STYLE);
//        String author = document.getString(AUTHOR);
//        double basePrice = document.getDouble(BASE_PRICE);
//
//        if (document.containsKey(TOTAL_TIME)) {
//            // MOVIE
//            int totalTime = document.getInteger(TOTAL_TIME);
//            return new Movie(entityId, yearOfPremiere, isRented, isArchive, name, style, author, basePrice, totalTime);
//        } else if (document.containsKey(NUMBER_OF_SONGS)) {
//            // MUSIC ALBUM
//            int numberOfSongs = document.getInteger(NUMBER_OF_SONGS);
//            return new MusicAlbum(entityId, yearOfPremiere, isRented, isArchive, name, style, author, basePrice, numberOfSongs);
//        } else {
//            return new Item(entityId, yearOfPremiere, isRented, isArchive, name, style, author, basePrice);
//        }
//    }

//    public static Document movieToDocument(Movie movie) {
//        return new Document(ID, movie.getEntityID())
//                .append(YEAROFPREMIERE, movie.getYearOfPremiere())
//                .append(IS_RENTED, movie.isRented())
//                .append(NAME, movie.getName())
//                .append(STYLE, movie.getStyle())
//                .append(AUTHOR, movie.getAuthor())
//                .append(BASE_PRICE, movie.getBasePrice())
//                .append(TOTAL_TIME, movie.getTotalTime());
//    }
//
//    public static Document musicAlbumToDocument(MusicAlbum musicAlbum) {
//        return new Document(ID, musicAlbum.getEntityID())
//                .append(YEAROFPREMIERE, musicAlbum.getYearOfPremiere())
//                .append(IS_RENTED, musicAlbum.isRented())
//                .append(NAME, musicAlbum.getName())
//                .append(STYLE, musicAlbum.getStyle())
//                .append(AUTHOR, musicAlbum.getAuthor())
//                .append(BASE_PRICE, musicAlbum.getBasePrice())
//                .append(NUMBER_OF_SONGS, musicAlbum.getNumberOfSongs());
//    }
//
//    public static Movie movieFromMongoDocument(Document document) {
//        return new Movie(document.get(ID, UUID.class),
//                         document.getInteger(YEAROFPREMIERE),
//                         document.getBoolean(IS_RENTED),
//                         document.getBoolean(IS_ARCHIVE),
//                         document.getString(NAME),
//                         document.getString(STYLE),
//                         document.getString(AUTHOR),
//                         document.getDouble(BASE_PRICE),
//                         document.getInteger(TOTAL_TIME));
//    }
//
//    public static MusicAlbum musicAlbumFromMongoDocument(Document document) {
//        return new MusicAlbum(document.get(ID, UUID.class),
//                              document.getInteger(YEAROFPREMIERE),
//                              document.getBoolean(IS_RENTED),
//                              document.getBoolean(IS_ARCHIVE),
//                              document.getString(NAME),
//                              document.getString(STYLE),
//                              document.getString(AUTHOR),
//                              document.getDouble(BASE_PRICE),
//                              document.getInteger(NUMBER_OF_SONGS));
//    }
}
