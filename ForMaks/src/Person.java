public class Person
{
    private String name, post, address;
    private String[] phoneNumbers;

    public Person(String receivedName, String receivedPost, String receivedAddress, String[] receivedPhoneNumbers) //Конструктор персоны
    {
        this.name = receivedName;
        this.address = receivedAddress;
        this.phoneNumbers = receivedPhoneNumbers;
        this.post = receivedPost;
    }

    public String get_name() {
        return name;
    }

    public String get_address() {
        return address;
    }

    public String get_post() {
        return post;
    }

    public String[] get_phoneNumbers() {
        return phoneNumbers;
    }
}
