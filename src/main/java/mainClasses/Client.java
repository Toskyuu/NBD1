package mainClasses;

import java.util.UUID;

public class Client extends AbstractEntity {
    private String firstName;
    private String lastName;
    private boolean isArchive;
    private String phoneNumber;

    public Client(String firstName, String secondName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = secondName;
        this.phoneNumber = phoneNumber;
    }

    public Client(UUID entityID, String firstName, String lastName, boolean isArchive, String phoneNumber) {
        super(entityID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.isArchive = isArchive;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isArchive() {
        return isArchive;
    }

    public void setArchive(boolean archive) {
        isArchive = archive;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "mainClasses.Client{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + lastName + '\'' +
                ", isArchive=" + isArchive +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


}
