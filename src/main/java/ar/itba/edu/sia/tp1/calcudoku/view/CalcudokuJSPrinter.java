package ar.itba.edu.sia.tp1.calcudoku.view;

import java.util.ArrayList;
import java.util.List;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuRule;
import ar.itba.edu.sia.tp1.calcudoku.CalcudokuState;
import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.gps.engine.GPSNode;
import ar.itba.edu.sia.tp1.gps.engine.GPSSolution;

/**
 * Created by scamisay on 09/04/16.
 */
public class CalcudokuJSPrinter {
	private static final int RADIO = 25;
	private static final int CIRCLE_DIAMETER = 2 * RADIO + 10;

	public String printJS(GPSSolution<CalcudokuRule, CalcudokuState> solution,
			int n) {
		StringBuffer aSolution = new StringBuffer();
		aSolution.append("var ");
		aSolution.append(structureForDraw(solution, n));
		aSolution.append("\n\nvar solutionPath = ");
		aSolution.append(drawCalcudokuPath(solution));
		aSolution.append(String.format("\n\nvar n = %d;", n));

		return aSolution.toString().replaceAll("n-", "nL");
	}

	private String structureForDraw(
			GPSSolution<CalcudokuRule, CalcudokuState> solution, int n) {
		StringBuffer sb = new StringBuffer();
		int width = CIRCLE_DIAMETER + n * 50;
		int height = n * 50;

		List<String> nodeVariableNames = new ArrayList<>();
		nodeVariableNames.add("config");

		for (GPSNode<CalcudokuRule, CalcudokuState> node : solution.getPath()) {
			CalcudokuState state = node.getState();
			String stateId = "n" + state.hashCode();

			final String str;
			CalcudokuRule rule = node.getRule();
			if (rule != null) {
				int parentId = state.apply(rule).hashCode(); // XXX
				str = String
						.format(",\n %s = { parent : n%d , innerHTML: '<canvas id=\"%s\" width=\"%d\" height=\"%d\"/>'}",
								stateId, parentId, stateId, width, height);
			} else {
				str = String
						.format("%s = { innerHTML: '<canvas id=\"%s\" width=\"%d\" height=\"%d\"/>'}",
								stateId, stateId, width, height);
			}
			sb.append(str);
			nodeVariableNames.add(String.format("%s", stateId));
		}

		sb.append("\n, chart_config = " + nodeVariableNames.toString() + ";");
		sb.append(String.format("\nvar r = %d;", RADIO));
		// chart_config = [config, malory, lana, figgs, sterling, woodhouse,
		// pseudo, pam, cheryl];

		return sb.toString().replaceAll("n-", "nL");
	}

	private List<String> drawCalcudokuPath(
			GPSSolution<CalcudokuRule, CalcudokuState> solution) {
		List<String> list = new ArrayList<>();

		for (GPSNode<CalcudokuRule, CalcudokuState> node : solution.getPath()) {
			CalcudokuState state = node.getState();
			CalcudokuRule rule = node.getRule();

			String boardValuesStr = state.getBoard().getAllValues().toString();
			String stateId = String.format("n%d", state.hashCode()).replaceAll(
					"n-", "nL");

			final String nodeInfo;
			if (rule != null) {
				int hStar = solution.getCost() - node.getGValue();
				String functionValues = String.format(
						"functionValues: {g: %d, h: %d, hStar: %d}",
						node.getGValue(), node.getHValue(), hStar);

				Position from = rule.getFrom();
				Position to = rule.getTo();
				String swapStr = String.format(
						"swap:{i0: %d, j0: %d, i1: %d, j1: %d}\n",
						from.getRow(), from.getCol(), to.getRow(), to.getCol());

				int parentId = state.apply(rule).hashCode(); // XXX

				nodeInfo = String.format(
						"{id : '%s',values: %s , %s, parent : n%d , %s}\n",
						stateId, boardValuesStr, swapStr, parentId,
						functionValues);
			} else {
				nodeInfo = String.format("{ id : '%s', values : %s }", stateId,
						boardValuesStr);
			}
			list.add(nodeInfo);
		}

		return list;
	}
}
