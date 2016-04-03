package ar.itba.edu.sia.tp1.gps.engine;

import ar.itba.edu.sia.tp1.gps.GPSRule;
import ar.itba.edu.sia.tp1.gps.GPSState;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class GPSNode<R extends GPSRule, S extends GPSState<R, S>> {
	private final S state;
	private final GPSNode<R, S> parent;
	private final int gValue;

	public GPSNode(GPSNode<R, S> parent, S state, int gValue) {
		this.parent = parent;
		this.state = state;
		this.gValue = gValue;
	}

	public GPSNode(S state, int g) {
		this(null, state, g);
	}

	public S getState() {
		return state;
	}

	public GPSNode<R, S> getParent() {
		return parent;
	}

	public int getGValue() {
		return gValue;
	}

	@Override
	public String toString() {
		return state.toString(); // TODO: redo!
	}

	@Override
	@SuppressFBWarnings(value = "HE_EQUALS_USE_HASHCODE", justification = "Eclipse knows what it's doing")
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.hashCode(gValue);
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
		@SuppressWarnings("unchecked")
		GPSNode<R, S> other = (GPSNode<R, S>) obj;
		if (gValue != other.gValue)
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
