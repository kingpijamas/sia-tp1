package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;

public class HeuristicsTest {

	
	@Test
	public void test1(){
		List<Position> positions1 = asList(position(0, 0), position(0, 1)
				);
		Group group1=new Group(positions1, Operator.PLUS, 3);
		
		
		List<Position> positions2 = asList(position(1, 0));
		Group group2=new Group(positions2, Operator.IDENTITY,2);
		
		List<Position> positions3 = asList(position(1, 1));
		Group group3=new Group(positions3, Operator.IDENTITY,1);
		
		
			
		List<Group> groups = asList(group1,group2,group3);
		
		
		
		
		Board board=new Board(2, groups);
		
		board.put(position(0,0), 2);
		board.put(position(0,1), 1);
		board.put(position(1, 0), 1);
		board.put(position(1, 1), 2);
		
		int invalid_columns=board.invalidColumnsCount();
		int invalid_groups=board.invalidGroupsCount();
		
		
		assertEquals(0,invalid_columns);
		
		assertEquals(2,invalid_groups);
		H1 h1=new H1();
		assertEquals(1,h1.getH(board));
		H2 h2=new H2();
		assertEquals(1,h2.getH(board));
		
		
	}
	private Position position(int row, int col) {
		return new Position(row, col);
	}

}
