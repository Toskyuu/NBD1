package mgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;
import java.time.Period;

public class RentMgd extends AbstractEntityMgd {
    @BsonProperty("begin_date")
    private LocalDate beginDate;
    @BsonProperty("end_date")
    private LocalDate endDate;
    @BsonProperty("rent_cost")
    private double rentCost;
    @BsonProperty("client")
    private ClientMgd client;
    @BsonProperty("item")
    private ItemMgd item;

    @BsonCreator
    public RentMgd(@BsonProperty("_id") int id,
                   @BsonProperty("begin_date") LocalDate beginDate,
                   @BsonProperty("end_date") LocalDate endDate,
                   @BsonProperty("rent_cost") double rentCost,
                   @BsonProperty("client") ClientMgd client,
                   @BsonProperty("item") ItemMgd item) {
        super(id);
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.rentCost = rentCost;
        this.client = client;
        this.item = item;
    }

    public void endRent(LocalDate endDate) {
        item.setRented(0);
        this.endDate = endDate;
        setRentCost(item.getBasePrice()* returnRentDays());
    }

    public int returnRentDays() {
        Period period = Period.between(beginDate, endDate);
        return period.getDays();
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getRentCost() {
        return rentCost;
    }

    public ClientMgd getClient() {
        return client;
    }

    public ItemMgd getItem() {
        return item;
    }
    private void setRentCost(double rentCost) {
        this.rentCost = rentCost;
    }

}
