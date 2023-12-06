package mainClasses;

public abstract class Item {
    private int id;
    private int yearOfPremiere;
    private int isRented;
    private int isArchive;
    private String name;
    private String style;
    private String author;
    private double basePrice;

    public Item(int id, int yearOfPremiere, int isRented, int isArchive, String name, String style, String author, double basePrice) {
        this.id = id;
        this.yearOfPremiere = yearOfPremiere;
        this.isRented = isRented;
        this.isArchive = isArchive;
        this.name = name;
        this.style = style;
        this.author = author;
        this.basePrice = basePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
