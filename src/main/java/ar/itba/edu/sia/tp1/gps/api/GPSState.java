package ar.itba.edu.sia.tp1.gps.api;

import java.util.Optional;

/**
 * GPSState interface.
 */
public abstract class GPSState<R extends GPSRule> {

    public boolean isValid() {
        return true;
    }

    /**
     * Compares self to another state to determine whether they are the same or
     * not.
     *
     * @param state The state to compare to.
     * @return true if self is the same as the state given, false if they are
     * different.
     */
    public abstract boolean equals(Object state);

    public abstract int hashCode();

    public abstract Optional<GPSState> apply(R rule);
}
