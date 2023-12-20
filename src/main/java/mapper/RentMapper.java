package mapper;

import mainClasses.Rent;
import mgd.RentMgd;
import org.bson.Document;
import redis.RentJson;

import java.util.Date;

public class RentMapper {
    private static final String ID = "_id";
    private static final String BEGIN_DATE = "begin_date";
    private static final String END_DATE = "end_date";
    private static final String RENT_COST = "rent_cost";
    private static final String CLIENT = "client";
    private static final String ITEM = "item";

    public static RentMgd rentToMongo(Rent rent) {
        return new RentMgd(rent.getId(), rent.getBeginDate(), rent.getEndDate(), rent.getRentCost(),
                ClientMapper.clientToMongo(rent.getClient()), ItemMapper.itemToMongo(rent.getItem()));
    }

    public static Rent rentFromMongo(RentMgd rent) {
            return new Rent(rent.getId(), rent.getBeginDate(), ClientMapper.clientFromMongo(rent.getClient()),
                    ItemMapper.itemFromMongo(rent.getItem()));
    }

    public static RentMgd toRentMgd(Document rentDocument) {
        return new RentMgd(
                rentDocument.get(ID, Integer.class),
                rentDocument.get(BEGIN_DATE, Date.class),
                rentDocument.get(END_DATE, Date.class),
                rentDocument.get(RENT_COST, Double.class),
                ClientMapper.toClientMgd((Document) rentDocument.get(CLIENT)),
                ItemMapper.toItemMgd((Document) rentDocument.get(ITEM))
        );
    }

    public static RentJson rentToRedis(Rent rent) {
        return new RentJson(rent.getId(), rent.getBeginDate(), rent.getEndDate(), rent.getRentCost(),
                ClientMapper.toClientJson(rent.getClient()), ItemMapper.itemToRedis(rent.getItem()));
    }

    public static Rent rentFromRedis(RentJson rent) {
        return new Rent(rent.getId(), rent.getBeginDate(), ClientMapper.mapJsonToClient(rent.getClient()),
                ItemMapper.itemFromRedis(rent.getItem()));
    }
}
