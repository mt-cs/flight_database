package skipList;

public class TestMain {
    public static void main(String[] args) {
        FlightKey fk1 = new FlightKey("FRA", "JFK", "06/05/2020", "07:00");
        System.out.println(fk1.toString());
        FlightKey fk2 = new FlightKey("FRA", "JFK", "05/03/2021", "10:00");
        System.out.println(fk2.toString());
        System.out.println(fk1.compareTo(fk2));
    }


}
