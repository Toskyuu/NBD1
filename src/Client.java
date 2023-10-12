public class Client {
    private String firstName;
    private String secondName;
    private String personalID;
    private boolean isArchive;
    private String phoneNumber;

    public Client(String firstName, String secondName, String personalID, String phoneNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.personalID = personalID;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPersonalID() {
        return personalID;
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
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", personalID='" + personalID + '\'' +
                ", isArchive=" + isArchive +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


}
