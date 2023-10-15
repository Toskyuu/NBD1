package mainClasses;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "begin_date", nullable = false)
    private Date beginDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "cost")
    private float rentCost;
    @ManyToOne()
    private Client client;
    @ManyToOne
    private Item item;

    public Rent(Date beginDate, float rentCost, Client client, Item item) {
        this.beginDate = beginDate;
        this.rentCost = rentCost;
        this.client = client;
        this.item = item;
    }

    public Rent() {

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
