package redis;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbSubtype;
import jakarta.json.bind.annotation.JsonbTypeInfo;
import mainClasses.MusicAlbum;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@JsonbTypeInfo({
        @JsonbSubtype(alias = "movie", type = MovieJson.class),
        @JsonbSubtype(alias = "musicAlbum", type = MusicAlbumJson.class)
})
public abstract class ItemJson extends AbstractEntityJson {
    @JsonbProperty("year_of_premiere")
    private int yearOfPremiere;
    @JsonbProperty("rented")
    private int isRented;
    @JsonbProperty("archive")
    private int isArchive;
    @JsonbProperty("name")
    private String name;
    @JsonbProperty("style")
    private String style;
    @JsonbProperty("author")
    private String author;
    @JsonbProperty("base_price")
    private double basePrice;

    @JsonbCreator
    public ItemJson(@JsonbProperty("_id") int id,
                    @JsonbProperty("year_of_premiere") int yearOfPremiere,
                    @JsonbProperty("rented") int isRented,
                    @JsonbProperty("archive") int isArchive,
                    @JsonbProperty("name") String name,
                    @JsonbProperty("style") String style,
                    @JsonbProperty("author") String author,
                    @JsonbProperty("base_price") double basePrice) {
        super(id);
        this.yearOfPremiere = yearOfPremiere;
        this.isRented = isRented;
        this.isArchive = isArchive;
        this.name = name;
        this.style = style;
        this.author = author;
        this.basePrice = basePrice;
    }

    public int getYearOfPremiere() {
        return yearOfPremiere;
    }

    public void setYearOfPremiere(int yearOfPremiere) {
        this.yearOfPremiere = yearOfPremiere;
    }

    public int isArchive() {
        return isArchive;
    }

    public void setArchive(int archive) {
        isArchive = archive;
    }

    public int isRented() {
        return isRented;
    }

    public void setRented(int rented) {
        isRented = rented;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "ItemMgd{" +
                "yearOfPremiere=" + yearOfPremiere +
                ", isRented=" + isRented +
                ", isArchive=" + isArchive +
                ", name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", author='" + author + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}

