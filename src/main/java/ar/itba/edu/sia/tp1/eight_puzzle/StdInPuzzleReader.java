package ar.itba.edu.sia.tp1.eight_puzzle;

import java.util.Scanner;

import ar.itba.edu.sia.tp1.gps.ProblemParser;

/**
 * Created by scamisay on 30/03/16.
 */
public class StdInPuzzleReader implements ProblemParser<PuzzleState> {
	@Override
	public PuzzleState parse() {
		try (Scanner s = new Scanner(System.in)) {
			int len = PuzzleState.LENGTH;
			int[][] map = new int[len][len];

			for (int index = 0; index < len * len; index++) {
				map[index / len][index % len] = s.nextInt();
			}

			return new PuzzleState(map);
		}
	}
}
