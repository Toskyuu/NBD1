package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;

@Entity(defaultKeyspace = "rent_a_item")
@CqlName("items")
public class Movie extends Item {
    @CqlName("total_time")
    private final int totalTime;

    public Movie(int id, int yearOfPremiere, int isRented, String name, String style, String author, double basePrice,
                 int totalTime, String discriminator) {
        super(id, yearOfPremiere, isRented, name, style, author, basePrice, "movie");
        this.totalTime = totalTime;
    }

    public int getTotalTime() {
        return totalTime;
    }
}
