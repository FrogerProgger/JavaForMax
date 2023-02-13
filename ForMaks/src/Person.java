public class Person {
    private String name, post, address;
    private String[] phoneNumbers;
    private int id;

    public Person(int receivedId,String receivedName, String receivedPost, String receivedAddress, String[] receivedPhoneNumbers) //Конструктор персоны
    {
        this.id = receivedId;
        this.name = receivedName;
        this.address = receivedAddress;
        this.phoneNumbers = receivedPhoneNumbers;
        this.post = receivedPost;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPost() {
        return post;
    }

    public String[] getPhoneNumbers() {
        return phoneNumbers;
    }

    public int getId() {
        return id;
    }
}
