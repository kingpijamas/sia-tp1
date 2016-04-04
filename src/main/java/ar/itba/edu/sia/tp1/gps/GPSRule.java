package ar.itba.edu.sia.tp1.gps;

/**
 * GPSRule interface.
 */
public interface GPSRule {
	/**
	 * Provides the cost of the rule.
	 *
	 * @return The cost of the rule.
	 */
	Integer getCost();

	/**
	 * Provides the name of the rule so it can be clearly identified by an human
	 * being.
	 *
	 * @return The name of the rule.
	 */
	String getName();
}
