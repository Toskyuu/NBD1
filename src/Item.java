public class Item {
    private int yearOfPremiere;
    private boolean isRented;
    private boolean isArchive;
    private String name;
    private String itemID;
    private String style;
    private String author;
    private double basePrice;

    public Item(int yearOfPremiere, boolean isRented, String name, String itemID, String style, String author, double basePrice) {
        this.yearOfPremiere = yearOfPremiere;
        this.isRented = isRented;
        this.name = name;
        this.itemID = itemID;
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

    public String getItemID() {
        return itemID;
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

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "yearOfPremiere=" + yearOfPremiere +
                ", isRented=" + isRented +
                ", name='" + name + '\'' +
                ", itemID='" + itemID + '\'' +
                ", style='" + style + '\'' +
                ", author='" + author + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
