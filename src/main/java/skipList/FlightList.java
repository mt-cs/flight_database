package skipList;

import java.util.ArrayList;
import java.util.List;
//AAA for negative infinity
//zzz for positive infinity
/** The class that represents the flight database using a skip list */
public class FlightList {
	// FILL IN CODE: needs to store the head, the tail and the height of the skip
	// list
	private FlightNode head;
	//private FlightNode tail;
	private int height;

	/** Default constructor */
	public FlightList() {
		// FILL IN CODE
		// create dummy level with two nodes "AAA" to "zzz"
		FlightNode AAA = new FlightNode(new FlightKey("AAA", "AAA", "01/01/0001", "00:01"), new FlightData("", 0.0));
		FlightNode zzz = new FlightNode(new FlightKey("zzz", "zzz", "12/31/9999", "24:00"), new FlightData("", 0.0));
		AAA.setNext(zzz);
		head = AAA;
		height = 1;

	}

	/**
	 * Constructor.
	 * Reads flight data from the file and inserts it into this skip list.
	 * @param filename the name of he file
	 */
	public FlightList(String filename) {
		// FILL IN CODE
		// buffer reader read one line at the time
		// call split method
		// create FLight Key and Flight data and create insert
	}

	/**
	 * Returns true if the node with the given key exists in the skip list,
	 * false otherwise. This method needs to be efficient.
	 * 
	 * @param key flight key
	 * @return true if the key is in the skip list, false otherwise
	 */
	public boolean find(FlightKey key) {
		// FILL IN CODE
		// start with head on the top left corner "AAA"
		// create variable current that look for the same node on the same level
		// check if next is greater, less or equal than the input key
		// If the next key is larger than input key then go down
		// else if the key is less than the input key, go right
		// base case stopping condition: stop once you reach the bottom level and you can't gp down
		FlightNode current = head;
		for (int i = height; i >= 0; i++) {
			while (current.getNext() != null && current.getNext().getKey().compareTo(key) < 0) {
				current = current.getNext();
			}
			current = current.getNext();
			if (current.getKey().compareTo(key) == 0) {
				return true;
			}
		}
		return false; // don't forget to change it
	}

	/**
	 * Insert a (key, value) pair to the skip list. Returns true if it was able
	 * to insert.
	 *
	 * @param key flight key
	 * @param data associated flight data
	 * @return true if insertion was successful
	 */
	public boolean insert(FlightKey key, FlightData data) {
		// FILL IN CODE
		// create head and level origin "AAA" to "zzz"
		// always start with one element in the tower
		// CALL FIND to check if the input element is already in the list
		// If it's already in the list return false
		// if not, two cases:
		return false; // don't forget to change it
	}

	private void adjustHeight(int newHeight) {
		FlightNode temp = head;

		height = newHeight;
	}

	/**
	 * Returns the list of nodes that are successors of a given key. Refer to
	 * the project pdf for a detailed description of the method.
	 * 
	 * @param key flight key
	 * @return successors of the given key
	 */
	public List<FlightNode> successors(FlightKey key) {
		List<FlightNode> arr = new ArrayList<FlightNode>();
		// FILL IN CODE

		return arr;
	}

	/**
	 * Returns the list of nodes that are predecessors of a given key
	 * (that corresponds to flights that are earlier than the given flight).
	 *  Refer to the project pdf for a detailed description of the method.
	 * 
	 * @param key flight key
	 * @return predecessors of the given key
	 */
	public List<FlightNode> predecessors(FlightKey key, int timeFrame) {
		List<FlightNode> arr = new ArrayList<FlightNode>();

		// FILL IN CODE
		return arr;

	}

	/**
	 * Returns the string representing the SkipList (contains the elements on all levels starting at the
	 * top. Each level should be on a separate line, for instance:
	 * (SFO, PVD, 03/14, 09:15)
	 * (SFO, JFK, 03/15, 06:30), (SFO, PVD, 03/14, 09:15)
	 * (SFO, JFK, 03/15, 06:30),   (SFO, JFK, 03/15, 7:15), (SFO, JFK, 03/20, 5:00), (SFO, PVD, 03/14, 09:15)
	 */
	public String toString() {
		// FILL IN CODE
		StringBuilder sb = new StringBuilder();


		return sb.toString(); // don't forget to change it
	}

	/**
	 * Outputs the SkipList to a file
	 * (prints the elements on all levels starting at the top.
	 * Each level should be printed on a separate line.
	 * @param filename the name of the file
	 */
	public void print(String filename) {
		// FILL IN CODE
	}

	/**
	 * Returns a list of nodes that have the same origin and destination cities
	 * and the same date as the key, with departure times within the given time
	 * frame of the departure time of the key.
	 *
	 * @param key flight key
	 * @param timeFrame interval of time
	 * @return list of flight nodes that have the same origin, destination and date
	 * as the key, and whose departure time is within a given timeframe
	 */
	public List<FlightNode> findFlights(FlightKey key, int timeFrame) {
		List<FlightNode> resFlights = new ArrayList<FlightNode>();
		// FILL IN CODE

		return resFlights;
	}

}
