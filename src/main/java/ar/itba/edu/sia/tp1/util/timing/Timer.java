package ar.itba.edu.sia.tp1.util.timing;

import java.util.ArrayList;
import java.util.List;

public class Timer<V> {
	public static <V> Timer<V> timer() {
		return new Timer<V>();
	}

	private boolean keepOnlyLast;
	private int times;
	private TimingSetUp timingSetUp;
	private Timeable<V> timeable;

	public Timer() {
		keepOnlyLast = true;
		timingSetUp = Timer::doNothing;
		times = 1;
	}

	private Timer(boolean keepOnlyLast, int times, TimingSetUp timingSetUp,
			Timeable<V> timeable) {
		this.keepOnlyLast = keepOnlyLast;
		this.times = times;
		this.timingSetUp = timingSetUp;
		this.timeable = timeable;
	}

	public Timer<V> keepOnlyLastResult() {
		this.keepOnlyLast = true;
		return this;
	}

	public Timer<V> keepAllResults() {
		this.keepOnlyLast = false;
		return this;
	}

	public Timer<V> timesToRun(int times) {
		this.times = times;
		return this;
	}

	public Timer<V> setUpWith(TimingSetUp timingSetUp) {
		this.timingSetUp = timingSetUp;
		return this;
	}

	public <T> Timer<T> toTime(Timeable<T> timeable) {
		return new Timer<>(keepOnlyLast, times, timingSetUp, timeable);
	}

	public Timer<Void> toTime(SideEffectfulTimeable timeable) {
		return new Timer<>(keepOnlyLast, times, timingSetUp, () -> {
			timeable.run();
			return null;
		});
	}

	public TimedResults<V> start() throws Exception {
		List<TimedResult<V>> elapsedTimes = new ArrayList<>(times);

		for (int i = 0; i < times; i++) {
			timingSetUp.run();

			long timeBefore = System.currentTimeMillis();
			V value = timeable.run();
			long elapsedTime = System.currentTimeMillis() - timeBefore;
			System.out.println("solution found in " + elapsedTime + " ms ("
					+ ((double) i * 100) / times + "%)");

			final V valueToStore;
			if (keepOnlyLast && i < times - 1) {
				valueToStore = null;
			} else {
				valueToStore = value;
			}
			elapsedTimes.add(new TimedResult<V>(valueToStore, elapsedTime));
		}

		return new TimedResults<>(elapsedTimes);
	}

	private static void doNothing() {
	}
}
