package ar.itba.edu.sia.tp1.gps;

import ar.itba.edu.sia.tp1.gps.api.GPSState;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class GPSNode {

    private GPSState state;
    private GPSNode parent;
    private int g;

    public GPSNode(GPSState state, Integer g) {
        this.state = state;
        this.g = g;
    }

    public GPSNode getParent() {
        return parent;
    }

    public void setParent(GPSNode parent) {
        this.parent = parent;
    }

    public GPSState getState() {
        return state;
    }

    public int getG() {
        return g;
    }

    @Override
    public String toString() {
        return state.toString();
    }

    public String getSolution() {
        if (this.parent == null) {
            return state.toString();
        }
        return parent.getSolution() + state.toString();
    }

    @Override
    @SuppressFBWarnings(value = "HE_EQUALS_USE_HASHCODE", justification = "Eclipse knows what it's doing")
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.hashCode(g);
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        return result;
    }

    @Override
    @SuppressFBWarnings(value = "HE_EQUALS_USE_HASHCODE", justification = "Eclipse knows what it's doing")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GPSNode other = (GPSNode) obj;
        if (g != other.g)
            return false;
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        return true;
    }

}
