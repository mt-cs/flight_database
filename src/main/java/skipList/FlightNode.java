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


	/**
     * Copy constructor for FlightNode
	 * @param node FlightNode
	 */
	public FlightNode(FlightNode node) {
		// FILL IN CODE
		this.flightData = node.flightData;
		this.flightKey = node.flightKey;

	}

	/**
     * FlightNode Constructor
	 * @param key flight key
	 * @param data fight daya
	 */
	public FlightNode(FlightKey key, FlightData data) {
		// FILL IN CODE
		flightKey = key;
		flightData = data;

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
}
