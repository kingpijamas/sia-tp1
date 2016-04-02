package ar.itba.edu.sia.tp1.gps.api;

/**
 * GPSState interface.
 */
public abstract class GPSState {

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
}
