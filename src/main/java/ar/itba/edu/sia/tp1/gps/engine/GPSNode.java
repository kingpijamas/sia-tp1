package ar.itba.edu.sia.tp1.gps.engine;

import static ar.itba.edu.sia.tp1.utils.ObjectUtils.toStringBuilder;
import ar.itba.edu.sia.tp1.gps.GPSRule;
import ar.itba.edu.sia.tp1.gps.GPSState;

public class GPSNode<R extends GPSRule, S extends GPSState<R, S>> {
	private final GPSNode<R, S> parent;
	private final R rule;
	private final S state;
	private final int gValue;
	private final int hValue;

	public GPSNode(GPSNode<R, S> parent, R rule, S state, int gValue, int hValue) {
		this.parent = parent;
		this.rule = rule;
		this.state = state;
		this.gValue = gValue;
		this.hValue = hValue;
	}

	public GPSNode(R rule, S state, int gValue, int hValue) {
		this(null, null, state, gValue, hValue);
	}

	public R getRule() {
		return rule;
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

	public int getHValue() {
		return hValue;
	}

	public int getFValue() {
		return gValue + hValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gValue;
		result = prime * result + hValue;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GPSNode<R, S> other = (GPSNode<R, S>) obj;
		if (gValue != other.gValue)
			return false;
		if (hValue != other.hValue)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return toStringBuilder(this).append("rule", rule)
				.append("gValue", gValue).append("hValue", hValue)
				.appendToString(state.toString()).toString();
	}

}
