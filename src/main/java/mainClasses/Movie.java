package mainClasses;

public class Movie extends Item {
    private int totalTime;

    public Movie(int id, int yearOfPremiere, int isRented, int isArchive, String name, String style, String author, double basePrice, int totalTime) {
        super(id, yearOfPremiere, isRented, isArchive, name, style, author, basePrice);
        this.totalTime = totalTime;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
}
