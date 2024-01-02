package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.datastax.oss.driver.api.mapper.annotations.PropertyStrategy;


@Entity(defaultKeyspace = "rent_items")
@CqlName("items")
@PropertyStrategy(mutable = false)
public class Item {

    @PartitionKey
    private final int id;
    @CqlName("year_of_premiere")
    private final int yearOfPremiere;
    @CqlName("is_rented")
    private final int isRented;
    private final String name;
    private final String style;
    private final String author;
    @CqlName("base_price")
    private final double basePrice;
    private final String discriminator;

    public Item(int id, int yearOfPremiere, String name, String style, String author, double basePrice,
                String discriminator, int isRented) {
        this.id = id;
        this.yearOfPremiere = yearOfPremiere;
        this.name = name;
        this.style = style;
        this.author = author;
        this.basePrice = basePrice;
        this.discriminator = discriminator;
        this.isRented = isRented;
    }

    public int getYearOfPremiere() {
        return yearOfPremiere;
    }
    public int isRented() {
        return isRented;
    }
    public String getName() {
        return name;
    }
    public String getStyle() {
        return style;
    }
    public String getAuthor() {
        return author;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public String getDiscriminator() {
        return discriminator;
    }
    public int getId() {
        return id;
    }
    public int getIsRented() {
        return isRented;
    }

    @Override
    public String toString() {
        return "mainClasses.Item{" +
                "yearOfPremiere=" + yearOfPremiere +
                ", isRented=" + isRented +
                ", name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", author='" + author + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
