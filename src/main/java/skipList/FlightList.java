package skipList;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

//AAA for negative infinity
//zzz for positive infinity
/** The class that represents the flight database using a skip list */
public class FlightList {
	// FILL IN CODE: needs to store the head, the tail and the height of the skip
	// list
	private FlightNode head;
	private FlightNode tail;
	private int height;

	/** Default constructor */
	public FlightList() {
		// FILL IN CODE
		// create dummy level with two nodes "AAA" to "zzz"

		FlightNode AAA = new FlightNode(new FlightKey("AAA", "AAA", "01/01/0001", "00:01"), new FlightData("", 0.0));
		FlightNode zzz = new FlightNode(new FlightKey("zzz", "zzz", "12/31/9999", "24:00"), new FlightData("", 0.0));
		AAA.setNext(zzz);
		zzz.setPrev(AAA);
		head = AAA;
		tail = zzz;
		height = 1;

	}

	/**
	 * Constructor.
	 * Reads flight data from the file and inserts it into this skip list.
	 * @param filename the name of he file
	 */
	public FlightList(String filename) {
//		FlightNode AAA = new FlightNode(new FlightKey("AAA", "AAA", "01/01/0001", "00:01"), new FlightData("", 0.0));
//		FlightNode zzz = new FlightNode(new FlightKey("zzz", "zzz", "12/31/9999", "24:00"), new FlightData("", 0.0));
//		AAA.setNext(zzz);
//		zzz.setPrev(AAA);
//		head = AAA;
//		tail = zzz;
//		height = 1;
		this();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String s;
			while ((s = br.readLine()) != null) {
				String[] arr = s.split(" ");
				if (arr.length != 6)
					throw new IndexOutOfBoundsException();
				FlightKey key = new FlightKey(arr[0], arr[1], arr[2], arr[3]);
				FlightData data = new FlightData(arr[4], Double.parseDouble(arr[5]));
				insert(key, data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns true if the node with the given key exists in the skip list,
	 * false otherwise. This method needs to be efficient.
	 * 
	 * @param key flight key
	 * @return true if the key is in the skip list, false otherwise
	 */
	public boolean find(FlightKey key) {
		FlightNode current = head;
		for (int i = height; i > 0; i--) {
			current = moveRight(current, key);
			current = current.getNext();
			if (current != null && current.getKey().compareTo(key) == 0) {
				return true;
			}
		}
		return false;
	}

	private FlightNode moveRight (FlightNode current, FlightKey key) {
		if (current != null) {
			while (current.getNext() != null && current.getNext().getKey().compareTo(key) < 0) {
				current = current.getNext();
			}
		}
		return current;
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
		if (find(key)) {
			return false;
		} else {
			FlightNode newNode= new FlightNode(key, data);
			// keep tossing a coin until you get heads.
			int startHeight = height;
			ArrayList<FlightNode> update = new ArrayList<>();
			update.add(newNode);
			int toss = flipCoin();
			while (toss == 1) {
				newNode = addToTower(newNode);
				update.add(newNode);
				toss = flipCoin();
			}
			int heightDifference = height - startHeight;
			if (heightDifference != 0) {
				adjustLevel(heightDifference);
			}
			FlightNode current = head;
			FlightNode tempNext;
			FlightNode tempCurrent;
			for (int i = 0; i < update.size(); i++) {
				current = moveRight(current, key);
				tempNext = current.getNext();
				tempCurrent = current;
				current.setNext(update.get(i));
				update.get(i).setPrev(tempCurrent);
				update.get(i).setNext(tempNext);
				tempNext.setPrev(update.get(i));
				if (current.getDown() != null) {
					current = current.getDown();
				}
			}
			return true;
		}
	}

	private static int flipCoin() {
		Random ran = new Random();
		return ran.nextInt(2);
	}

	private FlightNode addToTower(FlightNode node) {
		FlightNode copy = new FlightNode(node);
		node.setUp(copy);
		copy.setDown(node);
		height++;
		return copy;
	}

	private void adjustLevel(int newHeight) {
		FlightNode tempHead = head;
		FlightNode tempTail = tail;
		for (int i = 1; i <= newHeight; i++) {
			FlightNode AAA = new FlightNode(new FlightKey("AAA", "AAA", "01/01/0001", "00:01"), new FlightData("", 0.0));
			FlightNode zzz = new FlightNode(new FlightKey("zzz", "zzz", "12/31/9999", "24:00"), new FlightData("", 0.0));
			AAA.setNext(zzz);
			zzz.setPrev(AAA);
			AAA.setUp(tempHead);
			zzz.setUp(tempTail);
			tempHead.setDown(AAA);
			tempTail.setDown(zzz);
			tempHead = AAA;
			tempTail = zzz;
		}
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
