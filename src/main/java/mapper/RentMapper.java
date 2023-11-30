package mapper;

import mainClasses.Client;
import mainClasses.Item;
import mainClasses.Rent;
import mgd.ClientMgd;
import mgd.ItemMgd;
import mgd.RentMgd;

public class RentMapper {
//    private static final String ID = "_id";
//    private static final String BEGIN_DATE = "begin_date";
//    private static final String END_DATE = "end_date";
//    private static final String RENT_COST = "rent_cost";
//    private static final String CLIENT = "client";
//    private static final String ITEM = "item";

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
