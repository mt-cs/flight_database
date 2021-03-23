package skipList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

/** The class that represents the flight database using a skip list */
public class FlightList {
	private FlightNode head;
	private FlightNode tail;
	private int height;

	/** Default constructor */
	public FlightList() {
		head = new FlightNode(
				new FlightKey("AAA", "AAA", "01/01/0001", "00:01"),
				new FlightData("", 0.0));
		tail = new FlightNode(
				new FlightKey("zzz", "zzz", "12/31/9999", "24:00"),
				new FlightData("", 0.0));
		head.setNext(tail);
		tail.setPrev(head);
		height = 1;
	}

	/**
	 * Constructor.
	 * Reads flight data from the file and inserts it into this skip list.
	 * @param filename the name of he file
	 */
	public FlightList(String filename) {
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
			if (current.getNext() != null && current.getNext().getKey().compareTo(key) == 0) {
				return true;
			}
			if (current.getDown() != null) {
				current = current.getDown();
			}
		}
		return false;
	}

	/**
	 * Move current node to the right, stop when next is bigger than key node
	 * @param current current FLightNode pointer
	 * @param key key to be inserted
	 * @return position of current node
	 */
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
		}
		// build tower
		FlightNode newNode= new FlightNode(key, data);
		int towerHeight = getHeight();
		for (int i = 1; i < towerHeight; i++) {
			newNode = addToTower(newNode);
		}
		FlightNode current = head;
		FlightNode tempNext;
		if (towerHeight <= height) {
			for (int i = height - 1; i >= 0; i--) {
				// skip the top level
				if (i >= towerHeight) {
					current = current.getDown();
				} else {
					current = moveRight(current, key);
					tempNext = current.getNext();
					current.setNext(newNode);
					newNode.setPrev(current);
					newNode.setNext(tempNext);
					tempNext.setPrev(newNode);
					if (current.getNext().getKey().compareTo(key) <= 0 && current.getDown() != null && newNode.getDown() != null) {
						current = current.getDown();
						newNode = newNode.getDown();
					}
				}
			}
		} else {
			current = adjustLevel(towerHeight - height);
			for (int i = 0; i < height; i++) {
				current = moveRight(current, key);
				tempNext = current.getNext();
				current.setNext(newNode);
				newNode.setPrev(current);
				newNode.setNext(tempNext);
				tempNext.setPrev(newNode);
				if (current.getNext().getKey().compareTo(key) <= 0 && current.getDown() != null) {
					current = current.getDown();
					newNode = newNode.getDown();
				}
			}
		}
		return true;
	}

	/**
	 * get new height of the tower
	 * @return tower height
	 */
	private static int getHeight() {
		int towerHeight = 1;
		while (flipCoin() == 1) {
			towerHeight++;
		}
		return towerHeight;
	}

	/**
	 * random toss of 0 or 1
	 * @return toss result
	 */
	private static int flipCoin() {
		Random ran = new Random();
		return ran.nextInt(2);
	}

	/**
	 * building up node tower
	 * @param node new node to be inserted
	 * @return node with new level
	 */
	private FlightNode addToTower(FlightNode node) {
		FlightNode copy = new FlightNode(node);
		node.setUp(copy);
		copy.setDown(node);
		return copy;
	}

	/**
	 * Add extra level on the skip list
	 * @param heightDifference the difference of new height and start height
	 * @return head at the top left level
	 */
	private FlightNode adjustLevel(int heightDifference) {
		for (int i = 1; i <= heightDifference; i++) {
			head.setUp(new FlightNode(
					new FlightKey("AAA", "AAA", "01/01/0001", "00:01"),
					new FlightData("", 0.0)));
			tail.setUp(new FlightNode(
					new FlightKey("zzz", "zzz", "12/31/9999", "24:00"),
					new FlightData("", 0.0)));
			head.getUp().setDown(head);
			tail.getUp().setDown(tail);
			head = head.getUp();
			tail = tail.getUp();
			head.setNext(tail);
			tail.setPrev(head);
		}
		height += heightDifference;
		return head;
	}

	/**
	 * Returns the node if the node with the given key exists in the skip list,
	 * otherwise one node before it.
	 *
	 * @param key flight key
	 * @return true if the key is in the skip list, false otherwise
	 */
	private FlightNode findNode(FlightKey key) {
		FlightNode current = head;
		for (int i = height; i > 0; i--) {
			current = moveRight(current, key);
			if (current.getNext() != null && current.getNext().getKey().compareTo(key) == 0) {
				current = current.getNext();
				while (current.getDown() != null) {
					current = current.getDown();
				}
				return current;
			}
			if (current.getDown() != null) {
				current = current.getDown();
			}
		}
		return current;
	}

	/**
	 * Returns the list of nodes that are successors of a given key. Refer to
	 * the project pdf for a detailed description of the method.
	 * 
	 * @param key flight key
	 * @return successors of the given key
	 */
	public List<FlightNode> successors(FlightKey key) {
		List<FlightNode> arr = new ArrayList<>();
		FlightNode current = findNode(key);
		try {
			current = current.getNext();
			while (current.getKey().compareTo(key) > 0 && compare(current.getKey(), key) == 0) {
				arr.add(current);
				current = current.getNext();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
		List<FlightNode> arr = new ArrayList<>();
		FlightNode current = findNode(key);
		try {
			while (current.getKey().compareTo(key) <= 0 && compare(current.getKey(), key) == 0) {
				if (compareTime(current.getKey(), key) < 0) {
					arr.add(0, current);
				}
				current = current.getPrev();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return arr;
	}

	/**
	 * A helper method that compares origin, destination and date
	 * @param key1 FlightKey this
	 * @param key2 FlightKey other
	 * @return result 0 if equal, -1 if smaller, 1 if bigger
	 * @throws ParseException Simple Date Format
	 */
	private int compare(FlightKey key1, FlightKey key2) throws ParseException {
		int result = key1.getOrigin().compareTo(key2.getOrigin()); //save the result in this temp variable, if it's not zero then return the temp, else...
		if (result == 0) {
			result = key1.getDest().compareTo(key2.getDest());
			if (result == 0) {
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				Date date1 = formatter.parse(key1.getDate());
				Date date2 = formatter.parse(key2.getDate());
				result = date1.compareTo(date2);
			}
		}
		if (result != 0) {
			result = (result < 0) ? -1 : 1;
		}
		return result;
	}

	/**
	 * A helper method that compares time
	 * @param key1 FlightKey this
	 * @param key2 FlightKey other
	 * @return result 0 if equal, -1 if smaller, 1 if bigger
	 */
	private int compareTime(FlightKey key1, FlightKey key2) {
		LocalTime time1 = LocalTime.parse(key1.getTime());
		LocalTime time2 = LocalTime.parse(key2.getTime());
		return time1.compareTo(time2);
	}

	/**
	 * Returns the string representing the SkipList (contains the elements on all levels starting at the
	 * top. Each level should be on a separate line, for instance:
	 * (SFO, PVD, 03/14, 09:15)
	 * (SFO, JFK, 03/15, 06:30), (SFO, PVD, 03/14, 09:15)
	 * (SFO, JFK, 03/15, 06:30), (SFO, JFK, 03/15, 7:15), (SFO, JFK, 03/20, 5:00), (SFO, PVD, 03/14, 09:15)
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		FlightNode prev = head;
		FlightNode current = prev.getNext();
		for (int i = 1; i <= height; i++) {
			while (current != null && current.getNext() != null) {
				String[] arr = current.getKey().toString().split(", ");
				sb.append(arr[0]).append(", ").append(arr[1]).append(", ").append(arr[2], 0, 5).
						append(", ").append(arr[3]);
				if (current.getNext().getNext() != null) {
					sb.append(", ");
				}
				current = current.getNext();
			}
			if (prev.getDown() != null) {
				prev = prev.getDown();
				current = prev.getNext();
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * Outputs the SkipList to a file
	 * (prints the elements on all levels starting at the top.
	 * Each level should be printed on a separate line.
	 * @param filename the name of the file
	 */
	public void print(String filename) {
		String s = toString();
		try {
			FileWriter myWriter = new FileWriter(filename);
			myWriter.write(s);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
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

		List<FlightNode> tempFlights = predecessors(key, timeFrame);
		for (FlightNode flight : tempFlights) {
			if (timeDifference(flight.getKey(), key, timeFrame)) {
				resFlights.add(flight);
			}
		}

		FlightNode current = findNode(key);
		if (current.getKey().compareTo(key) == 0) {
			resFlights.add(current);
		}

		tempFlights = successors(key);
		for (FlightNode tempFlight : tempFlights) {
			if (timeDifference(key, tempFlight.getKey(), timeFrame)) {
				resFlights.add(tempFlight);
			}
		}
		return resFlights;
	}

	/**
	 * Calculating time difference between two FlightKeys
	 * @param key1 FlightKey with earlier time
	 * @param key2 FlightKey with later time
	 * @param timeFrame time frame
	 * @return true if the difference is smaller than the time frame, false otherwise
	 */
	private boolean timeDifference (FlightKey key1, FlightKey key2, int timeFrame) {
		Duration duration = Duration.between(LocalTime.parse(key1.getTime()), LocalTime.parse(key2.getTime()));
		return  duration.toHours() <= timeFrame;
	}
}
