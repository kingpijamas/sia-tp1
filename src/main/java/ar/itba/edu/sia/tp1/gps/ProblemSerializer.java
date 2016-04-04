package ar.itba.edu.sia.tp1.gps;

public interface ProblemSerializer<P extends GPSProblem<R, S>, R extends GPSRule, S extends GPSState<R, S>> {
	void serialize(S initialState);

	default void serialize(P problem) {
		serialize(problem.getInitialState());
	}
}
