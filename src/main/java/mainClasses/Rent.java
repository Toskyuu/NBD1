package mainClasses;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Rent  {
    private int id;
    private Date beginDate;
    private Date endDate;
    private double rentCost;
    private Client client;
    private Item item;

    public Rent(int id, Date beginDate, Client client, Item item) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = null;
        this.rentCost = 0;
        this.client = client;
        this.item = item;
    }

    public Rent(int id, Client client, Item item) {
        this.id = id;
        this.beginDate = new Date();
        this.endDate = null;
        this.rentCost = 0;
        this.client = client;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void endRent(Date endDate) {
        item.setRented(0);
        this.endDate = endDate;
        setRentCost(item.getBasePrice()*getRentDays());
    }

    public int getRentDays() {
        long ms = endDate.getTime() - beginDate.getTime();
        return (int)TimeUnit.DAYS.convert(ms, TimeUnit.MILLISECONDS);
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

    private void setRentCost(double rentCost) {
        this.rentCost = rentCost;
    }
}
