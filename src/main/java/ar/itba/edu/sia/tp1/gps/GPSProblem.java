package ar.itba.edu.sia.tp1.gps;

import java.util.List;

/**
 * GPSProblem interface.
 */
public interface GPSProblem<R extends GPSRule, S extends GPSState<R, S>> {
	/**
	 * Provides the initial state for the GPS to start from.
	 *
	 * @return The initial state of the problem to be solved.
	 */
	S getInitialState();

	/**
	 * Given a state, resolves if it is a solution to the problem.
	 *
	 * @param state
	 *            The state to establish if it is a goal state.
	 * @return TRUE if the state is a goal state, FALSE otherwise.
	 */
	boolean isGoal(S state);

	/**
	 * Provides the list of all the rules that the problem involves. These rules
	 * are state independent.
	 *
	 * @return The initial state of the problem to be solved.
	 */
	List<R> getRules();

	/**
	 * Computes the value of the Heuristic for the given state.
	 *
	 * @param state
	 *            The state where the Heuristic should be computed.
	 * @return The value of the Heuristic.
	 */
	int getHValue(S state);
}
