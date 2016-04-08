import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonParser;

public class GeneralTest {
	private CalcudokuJsonParser parser;

	private InputStream is;

	
	public Board setUp(String fileName) throws IOException {
		is =new FileInputStream(fileName);

		parser = new CalcudokuJsonParser(is);
		
		CalcudokuState stateRead = parser.parse();
		Board b=stateRead.getBoard();
		
		return b;
	}
	
	
	
	//2x2 
	@Test
	public void test1() throws IOException{
		String fileName="./src/test/resources/A.json";
		Board board=setUp(fileName);
		
		
	}
}
