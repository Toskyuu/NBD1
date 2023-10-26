package mainClasses;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private long id;
    @Column(name = "premiere", nullable = false)
    private int yearOfPremiere;
    @Column(name = "is_rented", nullable = false)
    private boolean isRented;
    @Column(name = "is_archive", nullable = false)
    private boolean isArchive;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "style", nullable = false)
    private String style;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "price", nullable = false)
    private double basePrice;

    public Item(int yearOfPremiere, boolean isRented, String name, String style, String author, double basePrice) {
        this.yearOfPremiere = yearOfPremiere;
        this.isRented = isRented;
        this.name = name;
        this.style = style;
        this.author = author;
        this.basePrice = basePrice;
    }

    public Item() {

    }

    public long getId() {
        return this.id;
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


    public String getStyle() {
        return style;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
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
