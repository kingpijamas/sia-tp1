package ar.itba.edu.sia.tp1.eight_puzzle;

import java.awt.Point;
import java.util.Arrays;
import java.util.Optional;

import ar.itba.edu.sia.tp1.gps.GPSState;
import ar.itba.edu.sia.tp1.utils.Copies;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class PuzzleState extends GPSState<PuzzleRule, PuzzleState> {
	public static final int LENGTH = 3;
	public static final int BLANK = -1;

	private final int[][] map;

	public PuzzleState() {
		this.map = new int[LENGTH][LENGTH];
	}

	@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Not our code")
	public PuzzleState(int[][] map) {
		this.map = map;
	}

	@Override
	public Optional<PuzzleState> apply(PuzzleRule rule) {
		Point delta = rule.getDirection().getDelta();
		Point blank = getBlankCoords();
		rule.destination = (Point) blank.clone();
		rule.destination.translate(delta.x, delta.y);
		if (!isValid(rule)) {
			return Optional.empty();
		}

		int[][] newMap = Copies.deepCopy(map);
		newMap[blank.x][blank.y] = newMap[rule.destination.x][rule.destination.y];
		newMap[rule.destination.x][rule.destination.y] = PuzzleState.BLANK;
		return Optional.of(new PuzzleState(newMap));
	}

	public boolean isValid(PuzzleRule rule) {
		Point destination = rule.destination;
		return !(destination.getX() < 0
				|| destination.getX() >= PuzzleState.LENGTH
				|| destination.getY() < 0
				|| destination.getY() >= PuzzleState.LENGTH);
	}

	public Point getBlankCoords() {
		int x, y;
		for (x = 0; x < LENGTH; x++) {
			for (y = 0; y < LENGTH; y++) {
				if (map[x][y] == BLANK) {
					return new Point(x, y);
				}
			}
		}
		throw new IllegalStateException();
	}

	@Override
	public int hashCode() {
		int hash = 0;
		int i = 0;
		for (int[] row : map) {
			for (int slot : row) {
				if (slot != -1) {
					hash += Math.pow(10, i) * slot;
				}
				i++;
			}
		}
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PuzzleState other = (PuzzleState) obj;
		if (!Arrays.deepEquals(map, other.map))
			return false;
		return true;
	}

	@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Not our code")
	public int[][] getMap() {
		return map;
	}

	@Override
	@SuppressFBWarnings(value = "SBSC_USE_STRINGBUFFER_CONCATENATION", justification = "Not our code")
	public String toString() {
		String resp = "\n";
		for (int[] row : map) {
			for (int value : row) {
				resp += String.valueOf(value) + ' ';
			}
			resp += '\n';
		}
		return resp;
	}
}
