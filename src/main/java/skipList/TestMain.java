package skipList;

import java.util.Random;

public class TestMain {

    public static void main(String[] args) {
        /* FLIGHT KEY */
        FlightKey fk1 = new FlightKey("FRA", "JFK", "06/05/2020", "07:00");
//        System.out.println(fk1.toString());
//        System.out.println(fk1.getDate());
//        System.out.println(fk1.getTime());
        FlightKey fk2 = new FlightKey("FRA", "JFK", "05/03/2021", "10:00");
//        System.out.println(fk2.toString());
//        System.out.println(fk1.compareTo(fk2));
//
//        FlightKey fk3 = new FlightKey(fk2);
//        System.out.println(fk3.toString());
//        System.out.println(fk2.compareTo(fk3));

        /* FLIGHT DATA */
        FlightData data1 = new FlightData("LH122", 300.0);
        FlightData data2 = new FlightData("LH150", 500.0);
//        System.out.println(data1.getFlightNumber() + " " + data1.getPrice());

        /* FLIGHT NODE */
//        FlightNode node1 = new FlightNode(fk1, data1);
//        FlightNode node2 = new FlightNode(node1);
//        System.out.println(node2.getKey());
//        System.out.println(node2.getData().getFlightNumber() + " " + node2.getData().getPrice());
//        FlightNode node3 = new FlightNode(fk2, data2);
//        node2.setNext(node3);

        /* FLIGHT LIST*/
        FlightList flight1 = new FlightList();
//        FlightKey zzz_key =  new FlightKey("zzz", "zzz", "12/31/9999", "24:00");
//        System.out.println(flight1.find(zzz_key));
        System.out.println(flight1.insert(fk1, data1));
        System.out.println(flight1.insert(fk2, data2));
//        System.out.println(flight1.find(fk1));
        System.out.println(flight1.toString());

    }


}
