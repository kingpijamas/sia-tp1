package ar.itba.edu.sia.tp1.gps;

import java.util.Optional;

/**
 * GPSState interface.
 */
public abstract class GPSState<R extends GPSRule, S extends GPSState<R, S>> {
	public boolean isValid() {
		return true;
	}

	public abstract Optional<S> apply(R rule);

	/**
	 * Compares self to another state to determine whether they are the same or
	 * not.
	 *
	 * @param state
	 *            The state to compare to.
	 * @return true if self is the same as the state given, false if they are
	 *         different.
	 */
	@Override
	public abstract boolean equals(Object state);

	@Override
	public abstract int hashCode();
}
