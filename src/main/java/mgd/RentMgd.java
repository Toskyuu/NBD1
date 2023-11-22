package mgd;

import mainClasses.Client;
import mainClasses.Item;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Date;
import java.util.UUID;

public class RentMgd extends AbstractEntityMgd {
    @BsonProperty("begin_date")
    private Date beginDate;
    @BsonProperty("end_date")
    private Date endDate;
    @BsonProperty("rent_cost")
    private float rentCost;
    @BsonProperty("client")
    private Client client;
    @BsonProperty("item")
    private Item item;

    @BsonCreator
    public RentMgd(@BsonProperty("begin_date") Date beginDate,
                   @BsonProperty("rent_cost") float rentCost,
                   @BsonProperty("client") Client client,
                   @BsonProperty("item") Item item) {
        this.beginDate = beginDate;
        this.rentCost = rentCost;
        this.client = client;
        this.item = item;
    }

    public RentMgd(UUID entityID, Date beginDate, float rentCost, Client client, Item item) {
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

    public float getRentCost() {
        return rentCost;
    }

    public Client getClient() {
        return client;
    }

    public Item getItem() {
        return item;
    }
}
