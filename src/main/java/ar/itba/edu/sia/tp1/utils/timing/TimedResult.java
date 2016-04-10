package ar.itba.edu.sia.tp1.utils.timing;

public class TimedResult<V> {
	private V value;
	private long elapsedTime;

	TimedResult(V value, long elapsedTime) {
		this.value = value;
		this.elapsedTime = elapsedTime;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public V getValue() {
		return value;
	}
}