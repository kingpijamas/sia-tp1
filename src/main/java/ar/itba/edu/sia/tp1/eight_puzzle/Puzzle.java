package ar.itba.edu.sia.tp1.eight_puzzle;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import ar.itba.edu.sia.tp1.gps.GPSEngine;
import ar.itba.edu.sia.tp1.gps.SearchStrategy;
import ar.itba.edu.sia.tp1.gps.api.GPSProblem;
import ar.itba.edu.sia.tp1.gps.api.GPSRule;
import ar.itba.edu.sia.tp1.gps.api.GPSState;

public class Puzzle implements GPSProblem {

    static GPSEngine pEngine;

    public static void main(String[] args) {
        pEngine = new PuzzleEngine();
        try {
            pEngine.engine(new Puzzle(), SearchStrategy.DFS);
        } catch (StackOverflowError e) {
            System.out.println("Solution (if any) too deep for stack.");
        }
    }

    @Override
    public GPSState getInitState() {
        Scanner s = new Scanner(System.in);
        int[][] map = new int[PuzzleState.LENGTH][PuzzleState.LENGTH];
        int index = 0;
        while (index < PuzzleState.LENGTH * PuzzleState.LENGTH) {
            map[index / PuzzleState.LENGTH][index % PuzzleState.LENGTH] = s.nextInt();
            index++;
        }
        s.close();
        return new PuzzleState(map);
    }

    @Override
    public boolean isGoal(GPSState state) {
        return state.equals(PuzzleState.finalState());
    }

    @Override
    public List<GPSRule> getRules() {
        List<GPSRule> rules = new LinkedList<GPSRule>();
        for (Direction d : Direction.values()) {
            rules.add(new PuzzleRule(d));
        }
        return rules;
    }

    // Valor Heurística para A*
    @Override
    public Integer getHValue(GPSState state) {
        return 0;
    }

}
