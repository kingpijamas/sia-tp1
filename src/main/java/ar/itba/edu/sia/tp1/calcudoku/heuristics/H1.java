package ar.itba.edu.sia.tp1.calcudoku.heuristics;

public class H1 extends Heuristic{

	@Override
	protected  int getH(int incorrect_groups, int incorrect_columns){
		return (int) Math.ceil(Math.max(incorrect_groups, incorrect_columns)/2.0);
		
		
	}
	
}
