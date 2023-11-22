package mainClasses;

import org.bson.codecs.pojo.annotations.BsonId;

import java.util.Date;
import java.util.UUID;

public class Rent extends AbstractEntity {
    private Date beginDate;
    private Date endDate;
    private double rentCost;
    private Client client;
    private Item item;

    public Rent(Date beginDate, double rentCost, Client client, Item item) {
        this.beginDate = beginDate;
        this.rentCost = rentCost;
        this.client = client;
        this.item = item;
    }

    public Rent(UUID entityID, Date beginDate, double rentCost, Client client, Item item) {
        super(entityID);
        this.beginDate = beginDate;
        this.rentCost = rentCost;
        this.client = client;
        this.item = item;
    }

    public void endRent(Date endDate) {
        item.setRented(false);
        this.endDate = endDate;
    }

    public int getRentDays() {
        return endDate.getDate() - beginDate.getDate();
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

    public Client getClient() {
        return client;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "mainClasses.Rent{" +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", rentCost=" + rentCost +
                ", client=" + client.toString() +
                ", item=" + item.toString() +
                '}';
    }
}
