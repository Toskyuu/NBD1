package redis;

import jakarta.json.bind.annotation.JsonbProperty;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RentJson extends AbstractEntityJson {
    @JsonbProperty("begin_date")
    private Date beginDate;
    @JsonbProperty("end_date")
    private Date endDate;
    @JsonbProperty("rent_cost")
    private double rentCost;
    @JsonbProperty("client")
    private ClientJson client;
    @JsonbProperty("item")
    private ItemJson item;

    @BsonCreator
    public RentJson(@JsonbProperty("_id") int id,
                    @JsonbProperty("begin_date") Date beginDate,
                    @JsonbProperty("end_date") Date endDate,
                    @JsonbProperty("rent_cost") double rentCost,
                    @JsonbProperty("client") ClientJson client,
                    @JsonbProperty("item") ItemJson item) {
        super(id);
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.rentCost = rentCost;
        this.client = client;
        this.item = item;
    }

    public RentJson(@BsonProperty("_id") int id,
                    @BsonProperty("begin_date") Date beginDate,
                    @BsonProperty("client") ClientJson client,
                    @BsonProperty("item") ItemJson item) {
        super(id);
        this.beginDate = beginDate;
        this.endDate = null;
        this.rentCost = 0;
        this.client = client;
        this.item = item;
    }

    public RentJson(@BsonProperty("_id") int id,
                    @BsonProperty("client") ClientJson client,
                    @BsonProperty("item") ItemJson item) {
        super(id);
        this.beginDate = new Date();
        this.rentCost = 0;
        this.client = client;
        this.item = item;
    }

    public void endRent(Date endDate) {
        item.setRented(0);
        this.endDate = endDate;
        setRentCost(item.getBasePrice()* returnRentDays());
    }

    public int returnRentDays() {
        long ms = endDate.getTime() - beginDate.getTime();
        return (int) TimeUnit.DAYS.convert(ms, TimeUnit.MILLISECONDS);
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getRentCost() {
        return rentCost;
    }

    public ClientJson getClient() {
        return client;
    }

    public ItemJson getItem() {
        return item;
    }
    private void setRentCost(double rentCost) {
        this.rentCost = rentCost;
    }

    @Override
    public String toString() {
        return "RentMgd{" +
                "beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", rentCost=" + rentCost +
                ", client=" + client +
                ", item=" + item +
                '}';
    }
}
