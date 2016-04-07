package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;

public abstract class Heuristic {

	
	public int getH(Board board){
		int incorrect_groups=board.invalidGroupsCount();
		int incorrect_columns=board.invalidColumnsCount();
		return getH(incorrect_groups,incorrect_columns);
		
	}
	
	abstract int getH(int incorrect_groups,int incorrect_columns); 
	
}
