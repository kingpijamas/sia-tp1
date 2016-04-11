package ar.itba.edu.sia.tp1.calcudoku;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.heuristic.*;
import ar.itba.edu.sia.tp1.calcudoku.marshall.CalcudokuJsonParser;
import ar.itba.edu.sia.tp1.gps.GPSHeuristic;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;
import ar.itba.edu.sia.tp1.gps.engine.SearchStrategy;
import ar.itba.edu.sia.tp1.util.timing.TimedResults;
import ar.itba.edu.sia.tp1.util.timing.Timer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * ./src/test/resources/3x3_easy1.json a_star 7
 */
public class CalcudokuApplication {

	private SearchStrategy searchStrategy;
	private GPSHeuristic<CalcudokuState> heuristic;
	private Board board;



	public CalcudokuApplication(SearchStrategy searchStrategy, GPSHeuristic<CalcudokuState> heuristic, Board board) {
		this.searchStrategy = searchStrategy;
		this.heuristic = heuristic;
		this.board = board;

	}

	public GPSSolution<CalcudokuRule, CalcudokuState> run() throws Exception {
		CalcudokuProblem calcudoku = new CalcudokuProblem(board, heuristic);
		CalcudokuEngine engine = new CalcudokuEngine(calcudoku, searchStrategy);

		Board board = calcudoku.getInitialState().getBoard();
		System.out.println(board.fullToString());

		try {
			TimedResults<GPSSolution<CalcudokuRule, CalcudokuState>> timedResults = Timer
					.timer().timesToRun(1).keepOnlyLastResult()
					.setUpWith(calcudoku::fillBoardWithRandomValuesInRows)
					.toTime(engine::solve).start();
			GPSSolution<CalcudokuRule, CalcudokuState> solution = timedResults
					.getLast().getValue();

			System.out.println("Time taken: " + timedResults.getAvg() + " ms");


			System.out.println("Explosions: " + solution.getExplosionCount());
			System.out
					.println("Analyzed nodes: " + solution.getAnalyzedNodes());
			System.out.println("Path: " + solution.getPath());
			if (solution.isSuccess()) {
				System.out.println("Success:\n" + solution.getPath());
			} else {
				System.out.println("Solution not found\n");
			}
			// System.out.println("\n--JSON--");
			// System.out.println(new CalcudokuJsPrinter().print(solution,
			// board.getN()));
			return solution;
		} catch (StackOverflowError e) {
			System.out.println("Solution (if any) too deep for stack.");
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		List<GPSHeuristic<CalcudokuState>> heuristics = Arrays.asList(new H1(), new H2(), new H3(), new H3(), new H5(), new H6(),
				new H7(), new H8(), new H9(), new H13(), new H14(), new H15(), new H16());

		if(args.length>3 || args.length <0){
			System.out.println("cantidad incorrecta de parametros");
			help(heuristics);
			return;
		}
		Board board = readBoard(args[0]);
		if(board == null){
			System.out.println("No se encontro el tablero: " + args[0]);
			help(heuristics);
			return;
		}

		SearchStrategy searchStrategy = readStrategy(args[1]);
		if(searchStrategy == null){
			System.out.println("Estrategia invalidad" );
			help(heuristics);
			return;
		}

		GPSHeuristic<CalcudokuState> heuristic = readHeuristic(args[2],heuristics);
		if(heuristic == null){
			System.out.println("Heuristica invalidad: elija una entre 0 y " + heuristics.size());
			help(heuristics);
			return;
		}


		CalcudokuApplication app = new CalcudokuApplication(searchStrategy, heuristic, board);
		app.run();


	}

	private static String heuristicName(GPSHeuristic<CalcudokuState> heuristic){
		String []arr = heuristic.getClass().getName().split("\\.");
		return arr[arr.length-1];

	}

	private static void help(List<GPSHeuristic<CalcudokuState>> heuristics){
		StringBuffer sb = new StringBuffer();
		sb.append("Ayuda:\n");
		sb.append("Pasar tres argumentos: arhivoDeEntrada.json estrategia heuristica\n");
		sb.append("archivo de entrada: pasar path\n");
		sb.append("estrategia: dfs | bfs | iddfs | a_star |greedy\n");
		sb.append("heuristica:\n");
		for(int i = 0 ; i < heuristics.size(); i++){
			sb.append(String.format("%d - %s\n",i,heuristicName(heuristics.get(i))));
		}
		sb.append("Ejempo: APP 3x3_easy1.json a_star 7");
		System.out.println(sb.toString());
	}

	private static GPSHeuristic<CalcudokuState> readHeuristic(String arg, List<GPSHeuristic<CalcudokuState>> heuristics) {
		Integer index = Integer.parseInt(arg);
		if(index <0 || index>heuristics.size()){
			return null;
		}
		return heuristics.get(index);
	}

	private static SearchStrategy readStrategy(String strategy) {
		switch (strategy){
			case "dfs": return SearchStrategy.DFS;
			case "bfs": return SearchStrategy.BFS;
			case "iddfs": return SearchStrategy.IDDFS;
			case "a_star": return SearchStrategy.A_STAR;
			case "greedy": return SearchStrategy.GREEDY;
			default: return null;
		}
	}

	private static Board readBoard(String path) {
		try {
			return setUp(path);
		} catch (IOException e) {
			return null;
		}
	}

	private static  Board setUp(String fileName) throws IOException {
		InputStream is = new FileInputStream(fileName);
		CalcudokuJsonParser parser = new CalcudokuJsonParser(is);

		CalcudokuState stateRead = parser.parse();
		Board b = stateRead.getBoard();
		System.out.println("reading file:" + fileName);
		return b;
	}
}
