package skipList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the key in the FlightNode. Stores origin, destination, date and
 * time. Implements Comparable<FlightKey>.
 */
public class FlightKey implements Comparable<FlightKey> {
	// Each key is a tuple: origin, destination, date, time
	private String origin;
	private String dest;
	private String date;
	private String time;


	/**
     *  FlightKey constructor
	 * @param or origin
	 * @param dest destination
	 * @param date date
	 * @param time time
	 */
    public FlightKey(String or, String dest, String date, String time) {
		origin = or;
		this.dest = dest;
		this.date = date;
		this.time = time;
	}

	/**
     * FlightKey - copy constructor
	 * @param other the other FlightKey
	 */
	public FlightKey(FlightKey other) {
		this.origin = other.origin;
		this.dest = other.dest;
		this.date = other.date;
		this.time = other.time;
	}

	/**
     * Compares a given flight key with the one given as a parameter.
	 * @param other FlightKey
     * @return -1, if this key is < other, > -1 if the opposite, and 0 if equal.  </>
	 */
	public int compareTo(FlightKey other) {
		int result = this.origin.compareTo(other.getOrigin()); //save the result in this temp variable, if it's not zero then return the temp, else...
		if (result == 0) {
			result = this.dest.compareTo(other.getDest());
			if (result == 0) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
				LocalDateTime date1 = LocalDateTime.parse(this.date + " " + this.time, formatter);
				LocalDateTime date2 = LocalDateTime.parse(other.getDate() + " " + other.getTime(), formatter);
				result = date1.compareTo(date2);
			}
		}
		if (result != 0) {
			result = (result < 0) ? -1 : 1;
		}
		return result;
	}

	/**
	 * Returns a string representation of the key
	 * @return String
	 */
	@Override
	public String toString() {
		return "origin: " + origin +
				", destination: " + dest +
				", date: " + date +
				", time: " + time + '.' ;
	}

	/**
	 * getOrigin
	 * @return origin as a String
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * getDest
	 * @return destination as a String
	 */
	public String getDest() {
		return dest;
	}

	/**
	 * getDate
	 * @return date as a String (format:  "01/03/2019")
	 */
	public String getDate() {
		return date;
	}

	/**
	 * getTime
	 * @return time as a String (format: "16:00")
	 */
	public String getTime() {
		return time;
	}
}
