package mainClasses;

public class Movie extends Item {
    private int totalTime;

    public Movie(int yearOfPremiere, boolean isRented, String name, String style, String author, double basePrice, int totalTime) {
        super(yearOfPremiere, isRented, name, style, author, basePrice);
        this.totalTime = totalTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
}
