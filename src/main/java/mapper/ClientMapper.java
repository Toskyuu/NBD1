package mapper;

import mainClasses.Client;
import mgd.ClientMgd;

public class ClientMapper {
    private static final String ID = "_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String IS_ARCHIVE = "archive";
    private static final String PHONE_NUMBER = "phone_number";

    public static ClientMgd clientToMongo(Client client) {
//        return new Document(ID, client.getEntityID())
//                .append(FIRST_NAME, client.getFirstName())
//                .append(LAST_NAME, client.getLastName())
//                .append(IS_ARCHIVE, client.isArchive())
//                .append(PHONE_NUMBER, client.getPhoneNumber());
        return new ClientMgd(client.getId(), client.getFirstName(), client.getLastName(), client.isArchive(), client.getPhoneNumber());
    }

    public static Client clientFromMongo(ClientMgd client) {
        return new Client(client.getId(), client.getFirstName(), client.getLastName(), client.isArchive(), client.getPhoneNumber());
//        return new Client(document.get(ID, UUID.class),
//                          document.getString(FIRST_NAME),
//                          document.getString(LAST_NAME),
//                          document.getBoolean(IS_ARCHIVE),
//                          document.getString(PHONE_NUMBER));
    }
//    public static Document clientToMongo(Client client) {
//    Document clientDocument = new Document(ID, client.getLastName())
//            .append(FIRST_NAME, client.getFirstName())
//            .append(LAST_NAME, client.getLastName())
//            .append(IS_ARCHIVE, client.isArchive())
//            .append(PHONE_NUMBER, client.getPhoneNumber());
//    return clientDocument;
//}
//    public static Client fromMongoClient(Document rentDocument, ClientMgd clientMgd) {
//        Client clientModel = new Client(
//                rentDocument.get(ID, UUID.class),
//                rentDocument.getString(FIRST_NAME),
//                rentDocument.getString(LAST_NAME),
//                rentDocument.getBoolean(IS_ARCHIVE),
//                rentDocument.getString(PHONE_NUMBER);
//        return clientModel;
//    }

}
