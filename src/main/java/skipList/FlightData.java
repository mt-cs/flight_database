package skipList;

/**
 * Represents data in the FlightNode. Contains the flight number and the price
 */
public class FlightData {
	private String flightNumber;
	private double price;

	/**
     * Constructor for FlightData
	 * @param fnum flight number
	 * @param price price of the flight
	 */
	public FlightData(String fnum, double price) {
		flightNumber = fnum;
		this.price = price;
	}

	/**
     * Returns the number of the flight
	 * @return flight number
	 */
	public String getFlightNumber() { return flightNumber; }

	/**
	 * Returns the price of the flight
	 * @return price
	 */
	public double getPrice() { return price; }
}
