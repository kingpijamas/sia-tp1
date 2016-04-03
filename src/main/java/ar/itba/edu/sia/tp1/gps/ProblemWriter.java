package ar.itba.edu.sia.tp1.gps;

public interface ProblemWriter<P extends GPSProblem<?, ?>> {
	void writeInitialState(P problem);
}
