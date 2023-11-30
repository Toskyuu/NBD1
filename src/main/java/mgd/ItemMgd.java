package mgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import java.util.UUID;

@BsonDiscriminator(key = "_clazz")
public abstract class ItemMgd extends AbstractEntityMgd {
    @BsonProperty("year_of_premiere")
    private int yearOfPremiere;
    @BsonProperty("rented")
    private int isRented;
    @BsonProperty("archive")
    private int isArchive;
    @BsonProperty("name")
    private String name;
    @BsonProperty("style")
    private String style;
    @BsonProperty("author")
    private String author;
    @BsonProperty("base_price")
    private double basePrice;

    @BsonCreator
    public ItemMgd(@BsonProperty("_id") int id,
                   @BsonProperty("year_of_premiere") int yearOfPremiere,
                   @BsonProperty("rented") int isRented,
                   @BsonProperty("archive") int isArchive,
                   @BsonProperty("name") String name,
                   @BsonProperty("style") String style,
                   @BsonProperty("author") String author,
                   @BsonProperty("base_price") double basePrice) {
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
}

