package ar.itba.edu.sia.tp1.utils.timing;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimedResults<V> {
	private final List<TimedResult<V>> timedResults;

	public TimedResults(List<TimedResult<V>> timedResults) {
		this.timedResults = timedResults;
	}

	public long getTimesRun() {
		return timedResults.size();
	}

	public long getTotalTime() {
		return elapsedTimes()
				.collect(Collectors.summingLong(obj -> (long) obj));
	}

	public double getAvg() {
		return ((double) getTotalTime()) / getTimesRun();
	}

	// NOTE: sample std dev
	public double getStdDev() {
		double avgTime = getAvg();

		double diffsToMean = elapsedTimes().collect(
				Collectors.summingDouble(elapsedTime -> {
					double value = elapsedTime - avgTime;
					return value * value;
				}));

		return Math.sqrt(diffsToMean / (getTimesRun() - 1));
	}

	private Stream<Long> elapsedTimes() {
		return timedResults.stream().map(TimedResult::getElapsedTime);
	}

	public TimedResult<V> getLast() {
		return timedResults.get(timedResults.size() - 1);
	}
}
