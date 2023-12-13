package mgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RentMgd extends AbstractEntityMgd {
    @BsonProperty("begin_date")
    private Date beginDate;
    @BsonProperty("end_date")
    private Date endDate;
    @BsonProperty("rent_cost")
    private double rentCost;
    @BsonProperty("client")
    private ClientMgd client;
    @BsonProperty("item")
    private ItemMgd item;

    @BsonCreator
    public RentMgd(@BsonProperty("_id") int id,
                   @BsonProperty("begin_date") Date beginDate,
                   @BsonProperty("end_date") Date endDate,
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

    public RentMgd(@BsonProperty("_id") int id,
                   @BsonProperty("begin_date") Date beginDate,
                   @BsonProperty("client") ClientMgd client,
                   @BsonProperty("item") ItemMgd item) {
        super(id);
        this.beginDate = beginDate;
        this.endDate = null;
        this.rentCost = 0;
        this.client = client;
        this.item = item;
    }

    public RentMgd(@BsonProperty("_id") int id,
                   @BsonProperty("client") ClientMgd client,
                   @BsonProperty("item") ItemMgd item) {
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

    public ClientMgd getClient() {
        return client;
    }

    public ItemMgd getItem() {
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
