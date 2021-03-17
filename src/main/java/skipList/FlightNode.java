package skipList;

/**
 * The class that represents a node in a flight skip list. 
 * Contains the key of type FlightKey and the data of type FlightData. 
 * Also stores the following pointers to FlightNode(s): next, down, prev and up.
 */
public class FlightNode {

	// FILL IN CODE, declare instance variables (make them private)
	private FlightKey flight_key;


	/**
     * Copy constructor for FlightNode
	 * @param node FlightNode
	 */
	public FlightNode(FlightNode node) {
		// FILL IN CODE
		// str key, value

		// Node up, down, right

	}

	/**
     * FlightNode Constructor
	 * @param key flight key
	 * @param data fight daya
	 */
	public FlightNode(FlightKey key, FlightData data) {
		// FILL IN CODE

	}

	// FILL IN CODE: write getters and setters for all private variables

	/**
     * A getter for the key
	 * @return key
	 */
	public FlightKey getKey() {
		// FILL IN CODE
		return null; // don't forget to change it
	}

}
