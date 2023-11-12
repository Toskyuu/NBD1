package mainClasses;

public class Movie extends Item{
    public Movie(int yearOfPremiere, boolean isRented, String name, String itemID, String style, String author, double basePrice) {
        super(yearOfPremiere, isRented, name, style, author, basePrice);
    }
}
