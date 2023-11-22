package mgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import java.util.UUID;

@BsonDiscriminator(key = "_clazz")
public class ItemMgd extends AbstractEntityMgd {
    @BsonProperty("year_of_premiere")
    private int yearOfPremiere;
    @BsonProperty("is_rented")
    private boolean isRented;
    @BsonProperty("is_archive")
    private boolean isArchive;
    @BsonProperty("name")
    private String name;
    @BsonProperty("style")
    private String style;
    @BsonProperty("author")
    private String author;
    @BsonProperty("base_price")
    private double basePrice;

    @BsonCreator
    public ItemMgd(@BsonProperty("_id") UUID entityId,
                   @BsonProperty("year_of_premiere") int yearOfPremiere,
                   @BsonProperty("is_rented") boolean isRented,
                   @BsonProperty("is_archive") boolean isArchive,
                   @BsonProperty("name") String name,
                   @BsonProperty("style") String style,
                   @BsonProperty("author") String author,
                   @BsonProperty("base_price") double basePrice) {
        super(entityId);
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

    public boolean isArchive() {
        return isArchive;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
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

