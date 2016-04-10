package ar.itba.edu.sia.tp1.utils.timing;

import java.util.ArrayList;
import java.util.List;

public class Timer {
	public static <V> TimedResults<V> time(int times, Timeable<V> timeable)
			throws Exception {
		List<TimedResult<V>> elapsedTimes = new ArrayList<>(times);

		for (int i = 0; i < times; i++) {
			long timeBefore = System.currentTimeMillis();
			V value = timeable.run();
			long elapsedTime = System.currentTimeMillis() - timeBefore;

			elapsedTimes.add(new TimedResult<V>(value, elapsedTime));
		}

		return new TimedResults<>(elapsedTimes);
	}

	public static <V> TimedResults<V> time(Timeable<V> timeable)
			throws Exception {
		return time(1, timeable);
	}
}
