package mainClasses;

public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private boolean isArchive;
    private String phoneNumber;

    public Client(int id, String firstName, String lastName, boolean isArchive, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isArchive = isArchive;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "main.java.mainClasses.Client{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + lastName + '\'' +
                ", isArchive=" + isArchive +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
