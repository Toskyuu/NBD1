package mainClasses;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@PrimaryKeyJoinColumn(name = "movie_ID")
@Table(name = "movies")
public class Movie extends Item{

    private int totalTime;

    public Movie(int yearOfPremiere, boolean isRented, String name, String style, String author, double basePrice, int totalTime) {
        super(yearOfPremiere, isRented, name, style, author, basePrice);
        this.totalTime = totalTime;
    }

    public Movie() {

    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }
}
