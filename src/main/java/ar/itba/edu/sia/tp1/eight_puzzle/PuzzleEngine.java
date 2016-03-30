package ar.itba.edu.sia.tp1.eight_puzzle;

import java.util.Comparator;
import java.util.PriorityQueue;

import ar.itba.edu.sia.tp1.gps.GPSNode;
import ar.itba.edu.sia.tp1.gps.SearchStrategy;
import ar.itba.edu.sia.tp1.gps2.GPSEngine;
import ar.itba.edu.sia.tp1.gps2.api.GPSProblem;

class PuzzleEngine extends GPSEngine {


    public PuzzleEngine(GPSProblem problem, final SearchStrategy strategy) {
        super(problem, strategy);
        open = new PriorityQueue<GPSNode>(new Comparator<GPSNode>() {
            @Override
            public int compare(GPSNode o1, GPSNode o2) {
                switch (strategy) {
                    case BFS:
                        return o1.getCost() - o2.getCost();
                    case DFS:
                        return o2.getCost() - o1.getCost();
                    case IDDFS:
                        // IDDFS Condition
                        return 0;
                    case GREEDY:
                        // Greedy Condition
                        return 0;
                    case ASTAR:
                        // AStar Condition
                        return 0;
                    default:
                        return 0;
                }
            }
        });
    }
}
