package mapper;

import mainClasses.Rent;
import mgd.RentMgd;
import org.bson.Document;

import java.time.LocalDate;
import java.util.Date;

public class RentMapper {
    private static final String ID = "_id";
    private static final String BEGIN_DATE = "begin_date";
    private static final String END_DATE = "end_date";
    private static final String RENT_COST = "rent_cost";
    private static final String CLIENT = "client";
    private static final String ITEM = "item";

    public static RentMgd rentToMongo(Rent rent) {
//        return new Document(ID, rent.getEntityID())
//                .append(BEGIN_DATE, rent.getBeginDate())
//                .append(END_DATE, rent.getEndDate())
//                .append(RENT_COST, rent.getRentCost())
//                .append(CLIENT, rent.getClient())
//                .append(ITEM, rent.getItem());
        return new RentMgd(rent.getId(), rent.getBeginDate(), rent.getEndDate(), rent.getRentCost(), ClientMapper.clientToMongo(rent.getClient()), ItemMapper.itemToMongo(rent.getItem()));
//        return null;
    }

    public static Rent rentFromMongo(RentMgd rent) {
            return new Rent(rent.getId(), rent.getBeginDate(), ClientMapper.clientFromMongo(rent.getClient()), ItemMapper.itemFromMongo(rent.getItem()));
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

//    public static Rent fromMongoDocument(Document document) {
//        Document clientDocument = document.get(CLIENT, Document.class);
//        Client client = ClientMapper.fromMongoDocument(clientDocument);
//
//        Document itemDocument = document.get(ITEM, Document.class);
//        Item item = ItemMapper.itemFromMongoDocument(itemDocument);
//
//        return new Rent(document.get(ID, UUID.class),
//                        document.getDate(BEGIN_DATE),
//                        document.getDouble(RENT_COST),
//                        document.get(CLIENT, client.getClass()),
//                        document.get(ITEM, item.getClass())
//        );
//    }

}
