package mainClasses;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "rents")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "begin_date", nullable = false)
    private LocalDate beginDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "cost", nullable = false)
    private double rentCost;
    @ManyToOne()
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @OneToOne()
    @PrimaryKeyJoinColumn(name = "item_id")
    private Item item;

    public Rent(LocalDate beginDate, Client client, Item item) {
        this.beginDate = beginDate;
        this.client = client;
        this.item = item;
    }

    public Rent() {

    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void endRent(LocalDate endDate) {
        if(endDate.isAfter(beginDate)) {
            item.setRented(false);
            this.endDate = endDate;
            rentCost = item.getBasePrice() * beginDate.until(endDate, ChronoUnit.DAYS);
        }

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

    public void setRentCost(double rentCost) {
        this.rentCost = rentCost;
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
