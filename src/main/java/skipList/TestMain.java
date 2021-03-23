package skipList;
import java.util.List;

/**
 * A helper main class to run and test the methods
 */
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
        FlightKey key1 = new FlightKey("SFO", "JFK", "03/15/2021", "08:00");
        FlightKey key2 = new FlightKey("SFO", "JFK", "03/15/2021", "08:15");
        FlightKey key3 = new FlightKey("SFO", "JFK", "03/15/2021", "09:00");
        FlightKey key4 = new FlightKey("SFO", "JFK", "03/15/2021", "13:40");
        FlightKey key5 = new FlightKey("SFO", "JFK", "03/15/2021", "19:00");
        FlightKey key6 = new FlightKey("SFO", "JFK", "03/15/2021", "08:10");
        FlightKey key7 = new FlightKey("SFO", "JFK", "03/15/2021", "06:30");
        FlightKey key8 = new FlightKey("SFO", "JFK", "03/15/2021", "07:15");

        /* FLIGHT DATA */
        FlightData data1 = new FlightData("LH122", 300.0);
        FlightData data2 = new FlightData("LH150", 500.0);
        System.out.println(data1.getFlightNumber() + " " + data1.getPrice());

        /* FLIGHT NODE */
        FlightNode node1 = new FlightNode(fk1, data1);
        FlightNode node2 = new FlightNode(node1);
        System.out.println(node2.getKey());
        System.out.println(node2.getData().getFlightNumber() + " " + node2.getData().getPrice());
        FlightNode node3 = new FlightNode(fk2, data2);
        node2.setNext(node3);

        /* FLIGHT LIST*/
        FlightList flight1 = new FlightList();
        FlightKey zzz_key =  new FlightKey("zzz", "zzz", "12/31/9999", "24:00");
        System.out.println(flight1.find(zzz_key));
        System.out.println(flight1.insert(fk1, data1));
        System.out.println(flight1.insert(fk2, data2));
        System.out.println(flight1.find(fk1));
        System.out.println(flight1.toString());
        flight1.print("output.txt");

        FlightList flight2 = new FlightList();
        flight2.insert(key1, data1);
        flight2.insert(fk1, data1);
        flight2.insert(fk2, data2);
        flight2.insert(key2, data1);
        flight2.insert(key3, data1);
        flight2.insert(key4, data1);
        flight2.insert(key5, data1);
        flight2.insert(key7, data1);
        flight2.insert(key8, data1);

        System.out.println(flight2.toString());
        System.out.println("\nSUCCESSORS");
        System.out.println("Key: " + key1.toString());
        List<FlightNode> arr = flight2.successors(key1);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).getKey());
        }
        System.out.println("\nPREDECESSORS");
        System.out.println("Key: " + key1.toString());
        arr = flight2.predecessors(key1, 15);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).getKey());
        }
        System.out.println("\nFIND FLIGHTS");
        arr = flight2.findFlights(key1, 2);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).getKey());
        }
    }
}
