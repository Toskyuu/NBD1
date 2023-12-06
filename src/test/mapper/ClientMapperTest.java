package test.mapper;

import mainClasses.Client;
import mapper.ClientMapper;
import mgd.ClientMgd;
import org.bson.Document;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ClientMapperTest {

    @Test
    public void fromMongoClientTest() {
        ClientMgd clientMgd = new ClientMgd(1, "Marek", "Kowalski", false, "123123123");

        Client client = ClientMapper.clientFromMongo(clientMgd);
        assertEquals(client.getId(), clientMgd.getId());
        assertEquals(client.getFirstName(), clientMgd.getFirstName());
        assertEquals(client.getLastName(), clientMgd.getLastName());
        assertEquals(client.isArchive(), clientMgd.isArchive());
        assertEquals(client.getPhoneNumber(), clientMgd.getPhoneNumber());
    }

    @Test
    public void toMongoClientTest() {
        Client client = new Client(1, "Marek", "Kowalski", false, "123123123");

        ClientMgd clientMgd = ClientMapper.clientToMongo(client);
        assertEquals(clientMgd.getId(), client.getId());
        assertEquals(clientMgd.getFirstName(), client.getFirstName());
        assertEquals(clientMgd.getLastName(), client.getLastName());
        assertEquals(client.isArchive(), clientMgd.isArchive());
        assertEquals(client.getPhoneNumber(), clientMgd.getPhoneNumber());
    }

    @Test
    public void toClientMgdTest() {
        Document clientDoc = new Document();
        clientDoc.append("_id", 1)
                .append("first_name", "Marek")
                .append("last_name", "Kowalski")
                .append("archive", false)
                .append("PHONE_NUMBER", "123456789");

        ClientMgd clientMgd = ClientMapper.toClientMgd(clientDoc);
        assertEquals(clientMgd.getId(), clientDoc.get("_id"));
        assertEquals(clientMgd.getFirstName(), clientDoc.get("first_name"));
        assertEquals(clientMgd.getLastName(), clientDoc.get("last_name"));
        assertEquals(clientMgd.isArchive(), clientDoc.get("archive"));
        assertEquals(clientMgd.getPhoneNumber(), clientDoc.get("phone_number"));
    }
}

