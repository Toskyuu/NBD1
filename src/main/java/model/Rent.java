package model;

import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import java.util.Date;


public class Rent {
    @PartitionKey
    private final int id;
    private final Date beginDate;
    private Date endDate;
    private float rentCost;
    private final int client;
    private final Item item;

    public Rent(int id, Date beginDate, float rentCost, int client, Item item) {
        this.id = id;
        this.beginDate = beginDate;
        this.rentCost = rentCost;
        this.client = client;
        this.item = item;
    }

    public void endRent(Date endDate) {
//        item.setRented(false);
        this.endDate = endDate;
    }

    public int getId() {
        return id;
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
    public float getRentCost() {
        return rentCost;
    }
    public int getClient() {
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
                ", client=" + client +
                ", item=" + item.toString() +
                '}';
    }
}
