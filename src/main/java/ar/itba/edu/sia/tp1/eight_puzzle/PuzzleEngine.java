package ar.itba.edu.sia.tp1.eight_puzzle;

import ar.itba.edu.sia.tp1.gps.GPSEngine;
import ar.itba.edu.sia.tp1.gps.SearchStrategy;
import ar.itba.edu.sia.tp1.gps.api.GPSProblem;

import java.util.PriorityQueue;

class PuzzleEngine extends GPSEngine {
    public PuzzleEngine(GPSProblem problem, final SearchStrategy strategy) {
        super(problem, strategy);
        open = new PriorityQueue<>((o1, o2) -> {
            switch (strategy) {
                case BFS:
                    return Integer.compare(o1.getCost(), o2.getCost());
                case DFS:
                    return Integer.compare(o2.getCost(), o1.getCost());
                case IDDFS:
                    // IDDFS Condition
                    return 0;
                case GREEDY:
                    // Greedy Condition
                    return 0;
                case A_STAR:
                    // AStar Condition
                    return 0;
                default:
                    return 0;
            }
        });
    }
}
