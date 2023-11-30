package mainClasses;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Rent  {
    private int id;
    private LocalDate beginDate;
    private LocalDate endDate;
    private double rentCost;
    private Client client;
    private Item item;

    public Rent(int id, LocalDate beginDate, Client client, Item item) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = null;
        this.rentCost = 0;
        this.client = client;
        this.item = item;
    }

    public Rent(int id, Client client, Item item) {
        this.id = id;
        this.beginDate = LocalDate.now();
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

    public void endRent(LocalDate endDate) {
        item.setRented(0);
        this.endDate = endDate;
        setRentCost(item.getBasePrice()*getRentDays());
    }

    public int getRentDays() {
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
