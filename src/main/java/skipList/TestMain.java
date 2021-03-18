package skipList;

public class TestMain {
    public static void main(String[] args) {
        /* FLIGHT KEY */
        FlightKey fk1 = new FlightKey("FRA", "JFK", "06/05/2020", "07:00");
        System.out.println(fk1.toString());
        System.out.println(fk1.getDate());
        System.out.println(fk1.getTime());
        FlightKey fk2 = new FlightKey("FRA", "JFK", "05/03/2021", "10:00");
        System.out.println(fk2.toString());
        System.out.println(fk1.compareTo(fk2));

        FlightKey fk3 = new FlightKey(fk2);
        System.out.println(fk3.toString());
        System.out.println(fk2.compareTo(fk3));

        /* FLIGHT DATA */
        FlightData data1 = new FlightData("LH122", 300.0);
        System.out.println(data1.getFlightNumber() + " " + data1.getPrice());

        /* FLIGHT NODE */
        FlightNode node1 = new FlightNode(fk1, data1);
        FlightNode node2 = new FlightNode(node1);


    }


}
