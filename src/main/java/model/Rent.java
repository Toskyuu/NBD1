package model;

import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import java.util.Date;

public class Rent {
    @PartitionKey
    private int id;
    private Date beginDate;
    private Date endDate;
    private float rentCost;
    private Client client;
    private Item item;

    public Rent(int id, Date beginDate, float rentCost, Client client, Item item) {
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
