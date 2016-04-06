package ar.itba.edu.sia.tp1.calcudoku;

import static ar.itba.edu.sia.tp1.utils.ObjectUtils.toStringBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.gps.GPSRule;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuRule implements GPSRule {
	private static final Comparator<Position> comparator = (pos1, pos2) -> {
		int comp = Integer.compare(pos1.getRow(), pos2.getRow());
		if (comp != 0) {
			return comp;
		}
		return Integer.compare(pos1.getCol(), pos2.getCol());
	};

	/**
	 * r =((n.^2).*((n.^2)-1))/2
	 *
	 * n = 1 2 3 4 5 6 7 8 9 10 r = 0 6 36 120 300 630 1176 2016 3240 4950
	 *
	 *
	 * para testear:
	 *
	 * int n=3; CalcudokuRule.buildRules(n).size() == (new
	 * Double((Math.pow(n,2)*(Math.pow(n,2)-1))/2)).intValue();
	 *
	 * @param n
	 * @return
	 */
	public static List<CalcudokuRule> buildRules(int n) {
		Set<CalcudokuRule> ruleSet = new HashSet<>();
		for (int row1 = 0; row1 < n; row1++) {
			for (int col1 = 0; col1 < n; col1++) {
				for (int row2 = 0; row2 < n; row2++) {
					for (int col2 = 0; col2 < n; col2++) {
						if (!(row1 == row2 && col1 == col2)) {
							Position pos1 = new Position(row1, col1);
							Position pos2 = new Position(row2, col2);

							ruleSet.add(new CalcudokuRule(pos1, pos2));
						}
					}
				}
			}
		}
		System.out.println(ruleSet.size());
		return Collections.unmodifiableList(new ArrayList<>(ruleSet));
	}

	private final Position from;
	private final Position to;

	public CalcudokuRule(Position pos1, Position pos2) {
		if (comparator.compare(pos1, pos2) < 1) {
			this.from = pos1;
			this.to = pos2;
		} else {
			this.from = pos2;
			this.to = pos1;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + from.hashCode();
		result = prime * result + to.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalcudokuRule other = (CalcudokuRule) obj;
		if (!from.equals(other.from))
			return false;
		if (!to.equals(other.to))
			return false;
		return true;
	}

	@Override
	public Integer getCost() {
		return 1;
	}

	public Position getFrom() {
		return from;
	}

	public Position getTo() {
		return to;
	}

	@Override
	public String toString() {
		return to + "<->" + from;
	}
}
