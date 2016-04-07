package ar.itba.edu.sia.tp1.calcudoku.heuristics;

public class H2 extends Heuristic{

	@Override
	int getH(int incorrect_groups, int incorrect_columns) {
		return (int) Math.ceil((incorrect_columns+incorrect_groups)/4);
	}

}
