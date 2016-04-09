package ar.itba.edu.sia.tp1.calcudoku;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.calcudoku.domain.Operator;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.calcudoku.heuristics.H1;
import ar.itba.edu.sia.tp1.calcudoku.heuristics.H2;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.engine.GPSNode;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuApplication {
	public static void main(String[] args) throws IOException {
		// List<Position> positions = asList(position(0, 0), position(0, 1),
		// position(0, 2));
		// List<Group> groups = asList(new Group(positions, Operator.PLUS, 5));
		// try (FileOutputStream fis = new FileOutputStream(new
		// File("pepe.json"))) {
		// CalcudokuJsonSerializer writer = new CalcudokuJsonSerializer(fis);
		// writer.serialize(calcudoku);
		// }

		int n = 3;
		//Board board = new Board(n, Arrays.asList()); //getBoard3X3();

		GPSHeuristic<CalcudokuState> heuristic = new H2();
		Calcudoku calcudoku = new Calcudoku(new CalcudokuState(getBoard3X3()),
				heuristic);
		calcudoku.fillBoardWithRandomValues();

		CalcudokuState state = calcudoku.getInitialState();
		System.out.println(state.getBoard().fullToString());

		CalcudokuEngine engine = new CalcudokuEngine(calcudoku,
				SearchStrategy.A_STAR);

		GPSSolution<CalcudokuRule, CalcudokuState> solution = null;
		try {
			solution = engine.solve();
			StringBuffer aSolution = new StringBuffer();
			aSolution.append("var ");
			aSolution.append(structureForDraw(solution, n));
			aSolution.append("\n\nvar solutionPath = ");
			aSolution.append(drawCalcudokuPath(solution));
			String finalSolution = aSolution.toString();
			System.out.print(1);
		} catch (StackOverflowError e) {
			System.out.println("Solution (if any) too deep for stack.");
		}

	}

	private static String structureForDraw(GPSSolution<CalcudokuRule, CalcudokuState> solution, int n) {
		StringBuffer sb = new StringBuffer();

		int width = n * 50;
		List<String> nodeVariableNames = new ArrayList<>();
		nodeVariableNames.add("config");
		Iterator<GPSNode<CalcudokuRule, CalcudokuState>> it = solution.getPath().iterator();
		while (it.hasNext()){
			GPSNode<CalcudokuRule, CalcudokuState> node = it.next();
			CalcudokuRule swap = node.getRule();
			String stateId = "n"+node.getState().hashCode();
			if(swap != null){
				String swapStr = String.format("swap:{i0: %d, j0: %d, i1: %d, j1: %d}",swap.getFrom().getRow(),swap.getFrom().getCol()
						, swap.getTo().getRow(), swap.getTo().getCol());
				int parent = node.getState().apply(node.getRule()).hashCode();

				sb.append(String.format(",\n %s = { parent : n%d , innerHTML: '<canvas id=\"%s\" width=\"%d\" height=\"%d\"/>'}"
										,stateId, parent, stateId, width, width ));
			}else{
				sb.append(String.format("%s = { innerHTML: '<canvas id=\"%s\" width=\"%d\" height=\"%d\"/>'}"
						,stateId, stateId, width, width));
			}

			nodeVariableNames.add(String.format("%s",stateId));
		}

		sb.append("\n, chart_config = "+nodeVariableNames.toString()+";");
		//chart_config = [config, malory, lana, figgs, sterling, woodhouse, pseudo, pam, cheryl];

		return sb.toString().replaceAll("n-","nL");
	}

	private static List<String> drawCalcudokuPath(GPSSolution<CalcudokuRule, CalcudokuState> solution) {
		List<String> list = new ArrayList<>();

		Iterator<GPSNode<CalcudokuRule, CalcudokuState>> it = solution.getPath().iterator();
		while (it.hasNext()){
			GPSNode<CalcudokuRule, CalcudokuState> node = it.next();
			String values = node.getState().getBoard().getAllValues().toString();
			CalcudokuRule swap = node.getRule();
			String stateId = ("n"+node.getState().hashCode()).replaceAll("n-","nL");
			String nodeInfo = null;
			if(swap != null){
				String swapStr = String.format("swap:{i0: %d, j0: %d, i1: %d, j1: %d}\n",swap.getFrom().getRow(),swap.getFrom().getCol()
						, swap.getTo().getRow(), swap.getTo().getCol());
				int parent = node.getState().apply(node.getRule()).hashCode();
				nodeInfo = String.format("{id : '%s',values: %s , %s, parent : n%d }\n",stateId, values, swapStr, parent);
			}else{
				nodeInfo = String.format("{ id : '%s', values : %s }",stateId, values);
			}
			list.add(nodeInfo);
		}

		return list;
	}

	private static Position position(int row, int col) {
		return new Position(row, col);
	}

	//ONE swap to complete
	private static Board getBoard2X2(){
		int n = 2;

		List<Group> groups = new ArrayList<>();
		Group gsum = new Group(Arrays.asList(position(0, 0), position(0, 1)),
				Operator.PLUS, 3);
		groups.add(gsum);

		Board board = new Board(n, groups);

		board.put(position(0, 0), 1);
		board.put(position(0, 1), 2);

		board.put(position(1, 0), 1);
		board.put(position(1, 1), 2);

		return board;
	}

	//ONE swap to complete
	private static Board getBoard3X3(){
		int n = 3;

		List<Group> groups = new ArrayList<>();
		// Group gSum = new Group(
		// Arrays.asList(new Position(0, 0), new Position(0, 1),
		// new Position(1, 0), new Position(2, 0)),
		// Operator.PLUS, 7);
		// groups.add(gSum);

		Group gdiv = new Group(Arrays.asList(position(2, 1), position(2, 2)),
				Operator.PLUS, 3);
		groups.add(gdiv);

		Board board = new Board(n, groups);

		board.put(position(0, 0), 1);
		board.put(position(0, 1), 2);
		board.put(position(0, 2), 3);

		board.put(position(1, 0), 1);
		board.put(position(1, 1), 3);
		board.put(position(1, 2), 2);

		board.put(position(2, 0), 3);
		board.put(position(2, 1), 2);
		board.put(position(2, 2), 1);
		return board;
	}
}
