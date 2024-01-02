package model;

import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;

@Entity(defaultKeyspace = "rent_a_item")
@CqlName("items")
public class Movie extends Item {
    @CqlName("total_time")
    private final int totalTime;

    public Movie(int id, int totalTime, int yearOfPremiere, String name, String style, String author, double basePrice,
                 String discriminator, int isRented) {
        super(id, yearOfPremiere, name, style, author, basePrice, "movie", isRented);
        this.totalTime = totalTime;
    }

    public int getTotalTime() {
        return totalTime;
    }
}
