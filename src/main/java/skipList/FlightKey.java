package skipList;

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
		// FILL IN CODE
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
		// FILL IN CODE
		this.origin = other.origin;
		this.dest = other.dest;
		this.date = other.date;
		this.time = other.time;

	}

	// FILL IN CODE: Write getters for origin, destination, date and time

	/**
     * Compares a given flight key with the one given as a parameter.
	 * @param other
     * @return -1, if this key is < other, > -1 if the opposite, and 0 if equal.  </>
	 */
	public int compareTo(FlightKey other) {
		// FILL IN CODE
		if (this.origin.compareTo(other.getOrigin()) < 0) {
			return -1;
		} else if (this.origin.compareTo(other.getOrigin()) > 0) {
			return 1;
		} else {
			if (this.dest.compareTo(other.getDest()) < 0) {
				return -1;
			} else if (this.dest.compareTo(other.getDest()) > 0) {
				return 1;
			} else {
				if (this.date.compareTo(other.getDate()) < 0) {
					return -1;
				} else if (this.date.compareTo(other.getDate()) > 0) {
					return 1;
				} else {
					if (this.time.compareTo(other.getTime()) < 0) {
						return -1;
					} else if (this.time.compareTo(other.getTime()) > 0) {
						return 1;
					}
				}
			}
		}
		return 0; // don't forget to change it
	}

	/**
	 * Returns a string representation of the key
	 *
	 * @return String
	 */
	@Override
	public String toString() {
		return "FlightKey: " +
				"origin='" + origin + '\'' +
				", dest='" + dest + '\'' +
				", date='" + date + '\'' +
				", time='" + time + '.' ;
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
