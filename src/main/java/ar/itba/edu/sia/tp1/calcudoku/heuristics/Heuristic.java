package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;

public abstract class Heuristic {

	
	public int getH(Board board){
		int incorrect_groups=0;
		int incorrect_columns=0;
		return getH(incorrect_groups,incorrect_columns);
		
	}
	
	abstract int getH(int incorrect_groups,int incorrect_columns); 
	
}
