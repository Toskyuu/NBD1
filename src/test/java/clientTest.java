import mainClasses.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class clientTest {

    private final String f_name = "Robert";
    private final String s_name = "Laski";
    private final boolean is_archive = false;
    private final String phoneNumber = "123123123";

    private Client client0 = new Client();
    @Test
    void constructorTest() {
        Client client1 = new Client(f_name, s_name, phoneNumber);
        Assertions.assertEquals(client1.getFirstName(), f_name);
        Assertions.assertEquals(client1.getSecondName(), s_name);
        Assertions.assertEquals(client1.getPhoneNumber(), phoneNumber);
    }

    @Test
    void setGetFirstNameTest() {
        client0.setFirstName("Jaroslaw");
        Assertions.assertEquals(client0.getFirstName(), "Jaroslaw");
    }

    @Test
    void setGetSecondNameTest() {
        client0.setSecondName("Boberek");
        Assertions.assertEquals(client0.getSecondName(), "Boberek");
    }

    @Test
    void setGetPhoneNumberTest() {
        client0.setPhoneNumber("112");
        Assertions.assertEquals(client0.getPhoneNumber(), "112");
    }

}
