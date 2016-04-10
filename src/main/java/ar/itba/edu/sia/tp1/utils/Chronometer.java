package ar.itba.edu.sia.tp1.utils;
public class Chronometer {
	private long initialTime;

	public Chronometer() {
		initialTime = System.currentTimeMillis();
	}

	public void reset() {
		initialTime = System.currentTimeMillis();
	}

	public long stop() {
		return System.currentTimeMillis() - initialTime;
	}
}
