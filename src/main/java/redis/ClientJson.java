package redis;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class ClientJson extends AbstractEntityJson {

    @JsonbProperty("first_name")
    private String firstName;
    @JsonbProperty("last_name")
    private String lastName;
    @JsonbProperty("archive")
    private boolean isArchive;
    @JsonbProperty("phone_number")
    private String phoneNumber;

    @JsonbCreator
    public ClientJson(@JsonbProperty("_id") int id,
                      @JsonbProperty("first_name") String firstName,
                      @JsonbProperty("last_name") String lastName,
                      @JsonbProperty("archive") boolean isArchive,
                      @JsonbProperty("phone_number") String phoneNumber) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.isArchive = isArchive;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isArchive() {
        return isArchive;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ClientMgd{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isArchive=" + isArchive +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
