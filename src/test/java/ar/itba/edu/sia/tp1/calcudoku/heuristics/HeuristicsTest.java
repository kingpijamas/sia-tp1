package ar.itba.edu.sia.tp1.calcudoku.heuristics;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonParser;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonSerializer;

public class HeuristicsTest {
	private CalcudokuJsonParser parser;

	private InputStream is;

	
	public Board setUp(String fileName) throws IOException {
		is =new FileInputStream(fileName);

		parser = new CalcudokuJsonParser(is);
		
		CalcudokuState stateRead = parser.parse();
		Board b=stateRead.getBoard();
		
		return b;
	}
	
	@Test
	public void testA(){
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
	@Test
	public void testB() throws IOException{
		String fileName="./src/test/resources/B.json";
		Board board=setUp(fileName);
		board.put(position(0,0),1);
		board.put(position(0, 1), 2);
		board.put(position(1, 0), 2);
		board.put(position(1, 1), 1);
		
		int invalid_columns=board.invalidColumnsCount();
		int invalid_groups=board.invalidGroupsCount();
		
		assertEquals(0,invalid_columns);
		assertEquals(4, invalid_groups);
		H1 h1=new H1();
		assertEquals(2, h1.getH(board));
		H2 h2=new H2();
		
		assertEquals(1, h2.getH(board));
		
		
	}
	
	@Test
	public void testc() throws IOException{
		String fileName="./src/test/resources/C.json";
		Board board=setUp(fileName);
		board.put(position(0,0),1);
		board.put(position(0, 1), 2);
		board.put(position(1, 0), 1);
		board.put(position(1, 1), 2);
		
		int invalid_columns=board.invalidColumnsCount();
		int invalid_groups=board.invalidGroupsCount();
		
		assertEquals(2,invalid_columns);
		assertEquals(2, invalid_groups);
		H1 h1=new H1();
		assertEquals(1, h1.getH(board));
		H2 h2=new H2();
		
		assertEquals(1, h2.getH(board));
		
			
	}
	
	@Test
	public void testD() throws IOException{
		String fileName="./src/test/resources/D.json";
		Board board=setUp(fileName);
		board.put(position(0,0),2);
		board.put(position(0, 1), 1);
		board.put(position(1, 0), 2);
		board.put(position(1, 1), 1);
		
		int invalid_columns=board.invalidColumnsCount();
		int invalid_groups=board.invalidGroupsCount();
		
		assertEquals(2,invalid_columns);
		assertEquals(2, invalid_groups);
		H1 h1=new H1();
		assertEquals(1, h1.getH(board));
		H2 h2=new H2();
		
		assertEquals(1, h2.getH(board));
		
			
	}
	
	
	@Test
	public void testE() throws IOException{
		String fileName="./src/test/resources/3x3_easy1.json";
		Board board=setUp(fileName);
		board.put(position(0,0),3);
		board.put(position(0,1),2);
		board.put(position(0,2),1);
		board.put(position(1,0),2);
		board.put(position(1,1),1);
		board.put(position(1,2),3);
		board.put(position(2,0),1);
		board.put(position(2,1),2);
		board.put(position(2,2),3);
		
		int invalid_columns=board.invalidColumnsCount();
		int invalid_groups=board.invalidGroupsCount();
		
		assertEquals(2,invalid_columns);
		assertEquals(2, invalid_groups);
		H1 h1=new H1();
		assertEquals(1, h1.getH(board));
		H2 h2=new H2();
		
		assertEquals(1, h2.getH(board));
		
			
	}
	
	@Test
	public void testF() throws IOException{
		String fileName="./src/test/resources/3x3_easy1.json";
		Board board=setUp(fileName);
		board.put(position(0,0),3);
		board.put(position(0,1),1);
		board.put(position(0,2),2);
		board.put(position(1,0),3);
		board.put(position(1,1),2);
		board.put(position(1,2),1);
		board.put(position(2,0),1);
		board.put(position(2,1),2);
		board.put(position(2,2),3);
		
		int invalid_columns=board.invalidColumnsCount();
		int invalid_groups=board.invalidGroupsCount();
		
		assertEquals(2,invalid_columns);
		assertEquals(0, invalid_groups);
		H1 h1=new H1();
		assertEquals(1, h1.getH(board));
		H2 h2=new H2();
		
		assertEquals(1, h2.getH(board));
		
			
	}
	
	@Test
	public void testG() throws IOException{
		String fileName="./src/test/resources/3x3_easy1.json";
		Board board=setUp(fileName);
		board.put(position(0,0),2);
		board.put(position(0,1),1);
		board.put(position(0,2),3);
		board.put(position(1,0),3);
		board.put(position(1,1),1);
		board.put(position(1,2),2);
		board.put(position(2,0),1);
		board.put(position(2,1),2);
		board.put(position(2,2),3);
		
		int invalid_columns=board.invalidColumnsCount();
		int invalid_groups=board.invalidGroupsCount();
		
		assertEquals(2,invalid_columns);
		assertEquals(4, invalid_groups);
		H1 h1=new H1();
		assertEquals(2, h1.getH(board));
		H2 h2=new H2();
		
		assertEquals(2, h2.getH(board));
		
			
	}
	
	
	@Test
	public void testH() throws IOException{
		String fileName="./src/test/resources/h.json";
		Board board=setUp(fileName);
		board.put(position(0,0),2);
		board.put(position(0,1),3);
		board.put(position(0,2),4);
		board.put(position(0,3),1);
		
		board.put(position(1,0),3);
		board.put(position(1,1),4);
		board.put(position(1,2),1);
		board.put(position(1,3),2);
		
		board.put(position(2,0),3);
		board.put(position(2,1),1);
		board.put(position(2,2),2);
		board.put(position(2,3),4);
		
		board.put(position(3,0),1);
		board.put(position(3,1),2);
		board.put(position(3,2),3);
		board.put(position(3,3),4);
		
		int invalid_columns=board.invalidColumnsCount();
		int invalid_groups=board.invalidGroupsCount();
		
		assertEquals(2,invalid_columns);
		assertEquals(15, invalid_groups);
		H1 h1=new H1();
		assertEquals(8, h1.getH(board));
		H2 h2=new H2();
		
		assertEquals(5, h2.getH(board));
		
			
	}
	
	@Test
	public void testI() throws IOException{
		String fileName="./src/test/resources/I.json";
		Board board=setUp(fileName);
		board.put(position(0,0),1);
		board.put(position(0,1),3);
		board.put(position(0,2),2);
		board.put(position(1,0),3);
		board.put(position(1,1),1);
		board.put(position(1,2),2);
		board.put(position(2,0),1);
		board.put(position(2,1),2);
		board.put(position(2,2),3);
		
		int invalid_columns=board.invalidColumnsCount();
		int invalid_groups=board.invalidGroupsCount();
		
		assertEquals(2,invalid_columns);
		assertEquals(8, invalid_groups);
		H1 h1=new H1();
		assertEquals(4, h1.getH(board));
		H2 h2=new H2();
		
		assertEquals(3, h2.getH(board));
		
			
	}
	private Position position(int row, int col) {
		return new Position(row, col);
	}

}
