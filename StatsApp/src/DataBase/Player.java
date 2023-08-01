package DataBase;

public class Player {
    private String firstName;
    private String lastName;
    private String position;
    private int number;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public int getNumber() {
        return number;
    }

    public Player(String firstName, String lastName, String position, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + number;
    }
}
