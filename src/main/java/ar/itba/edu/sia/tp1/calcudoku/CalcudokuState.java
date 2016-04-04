package ar.itba.edu.sia.tp1.calcudoku;

import java.util.List;
import java.util.Optional;

import org.boon.json.annotations.JsonIgnore;

import ar.itba.edu.sia.tp1.calcudoku.domain.Board;
import ar.itba.edu.sia.tp1.calcudoku.domain.Group;
import ar.itba.edu.sia.tp1.gps.GPSState;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuState extends GPSState<CalcudokuRule, CalcudokuState> {

	private final Board board;

	public CalcudokuState(int n, List<Group> groups) {
		this.board = new Board(n, groups);
	}

	private CalcudokuState(CalcudokuState previousState) {
		this.board = previousState.board.deepCopy();
	}

	public int getN() {
		return board.getN();
	}

	public List<Group> getGroups() {
		return board.getGroups();
	}

	@Override
	public Optional<CalcudokuState> apply(CalcudokuRule rule) {
		CalcudokuState newState = new CalcudokuState(this);
		/* newState.board.put(rule.getPosition(), rule.getValue()); */

		if (!newState.isValid()) {
			return Optional.empty();
		}
		return Optional.of(newState);
	}

	@Override
	public boolean isValid() {
		return board.isValid();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CalcudokuState))
			return false;

		CalcudokuState that = (CalcudokuState) o;

		return board.equals(that.board);
	}

	@Override
	public int hashCode() {
		return board.hashCode();
	}

}
