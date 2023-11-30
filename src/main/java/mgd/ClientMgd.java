package mgd;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import java.util.UUID;

public class ClientMgd extends AbstractEntityMgd {

    @BsonProperty("first_name")
    private String firstName;
    @BsonProperty("last_name")
    private String lastName;
    @BsonProperty("archive")
    private boolean isArchive;
    @BsonProperty("phone_number")
    private String phoneNumber;

    @BsonCreator
    public ClientMgd(@BsonProperty("_id") int id,
                     @BsonProperty("first_name") String firstName,
                     @BsonProperty("last_name") String lastName,
                     @BsonProperty("archive") boolean isArchive,
                     @BsonProperty("phone_number") String phoneNumber) {
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
}
