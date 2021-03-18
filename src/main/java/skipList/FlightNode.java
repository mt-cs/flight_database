package skipList;

/**
 * The class that represents a node in a flight skip list. 
 * Contains the key of type FlightKey and the data of type FlightData. 
 * Also stores the following pointers to FlightNode(s): next, down, prev and up.
 */
public class FlightNode {
	// FILL IN CODE, declare instance variables (make them private)
	private FlightKey flightKey;
	private FlightData flightData;
	private FlightNode next;
	private FlightNode prev;
	private FlightNode up;
	private FlightNode down;

	/**
     * Copy constructor for FlightNode
	 * @param node FlightNode
	 */
	public FlightNode(FlightNode node) {
		// FILL IN CODE
		this.flightData = node.flightData;
		this.flightKey = node.flightKey;
		this.next = node.next;
		this.prev = node.prev;
		this.up = node.up;
		this.down = node.down;
	}

	/**
     * FlightNode Constructor
	 * @param key flight key
	 * @param data fight data
	 */
	public FlightNode(FlightKey key, FlightData data) {
		// FILL IN CODE
		flightKey = new FlightKey(key); // create a copy
		flightData = new FlightData("", 0.0);
		next = null;
		prev = null;
		up = null;
		down = null;

	}

	// FILL IN CODE: write getters and setters for all private variables
	/**
     * A getter for the key
	 * @return key
	 */
	public FlightKey getKey() {
		// FILL IN CODE
		return flightKey; // don't forget to change it
	}

	/**
	 * setter for the key
	 * @param key FlightKey
	 */
	public void setKey(FlightKey key) {
		this.flightKey = key;
	}

	/**
	 * getter for the data
	 * @return flightData
	 */
	public FlightData getData() {
		return flightData;
	}

	/**
	 * setter for the data
	 * @param data FlightData
	 */
	public void setData(FlightData data) {
		this.flightData = data;
	}

	/**
	 * getter for next
	 * @return next
	 */
	public FlightNode getNext() {
		return next;
	}

	/**
	 * setter for next
	 * @param next FlightNode
	 */
	public void setNext(FlightNode next) {
		this.next = next;
	}

	/**
	 * getter for prev
	 * @return prev
	 */
	public FlightNode getPrev() {
		return prev;
	}

	/**
	 * setter for prev
	 * @param prev FLightNode
	 */
	public void setPrev(FlightNode prev) {
		this.prev = prev;
	}

	/**
	 * getter for up
	 * @return up
	 */
	public FlightNode getUp() {
		return up;
	}

	/**
	 * setter for up
	 * @param up FlightNode
	 */
	public void setUp(FlightNode up) {
		this.up = up;
	}

	/**
	 * getter for down
	 * @return down
	 */
	public FlightNode getDown() {
		return down;
	}

	/**
	 * setter for down
	 * @param down FlightNode
	 */
	public void setDown(FlightNode down) {
		this.down = down;
	}
}
