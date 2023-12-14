package mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import mainClasses.Client;
import mgd.ClientMgd;
import org.bson.Document;
import redis.ClientJson;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ClientMapper {
    private static final String ID = "_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String IS_ARCHIVE = "archive";
    private static final String PHONE_NUMBER = "phone_number";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ClientMgd clientToMongo(Client client) {
        return new ClientMgd(client.getId(), client.getFirstName(), client.getLastName(), client.isArchive(), client.getPhoneNumber());
    }

    public static Client clientFromMongo(ClientMgd client) {
        return new Client(client.getId(), client.getFirstName(), client.getLastName(), client.isArchive(), client.getPhoneNumber());
    }

    public static ClientMgd toClientMgd(Document clientDocument) {
        return new ClientMgd(
                clientDocument.get(ID, Integer.class),
                clientDocument.get(FIRST_NAME, String.class),
                clientDocument.get(LAST_NAME, String.class),
                clientDocument.get(IS_ARCHIVE, Boolean.class),
                clientDocument.get(PHONE_NUMBER, String.class)
        );
    }

    public static ClientJson toClientJson(Client client) {
        return new ClientJson(client.getId(), client.getFirstName(), client.getLastName(), client.isArchive(), client.getPhoneNumber());
    }

    public static Client mapJsonToClient(ClientJson client) {
        return new Client(client.getId(), client.getFirstName(), client.getLastName(), client.isArchive(), client.getPhoneNumber());
    }

}
