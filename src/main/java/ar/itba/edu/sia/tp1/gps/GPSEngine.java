package ar.itba.edu.sia.tp1.gps;

import ar.itba.edu.sia.tp1.gps.api.GPSProblem;
import ar.itba.edu.sia.tp1.gps.api.GPSRule;
import ar.itba.edu.sia.tp1.gps.api.GPSState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

public abstract class GPSEngine {

    protected Queue<GPSNode> open;
    protected Map<GPSState, Integer> bestCosts = new HashMap<GPSState, Integer>();

    protected GPSProblem problem;

    // Use this variable in open set order.
    protected SearchStrategy strategy;


    /*an instance cannot exist unless all its dependencies exists*/
    private GPSEngine() {
    }

    public GPSEngine(GPSProblem problem, SearchStrategy strategy) {
        this.problem = problem;
        this.strategy = strategy;
    }

    public void solve() {
        GPSNode rootNode = new GPSNode(problem.getInitState(), 0);
        boolean finished = false;
        boolean failed = false;
        long explosionCounter = 0;
        open.add(rootNode);
        while (!failed && !finished) {
            if (open.isEmpty()) {
                failed = true;
            } else {
                GPSNode currentNode = open.remove();
                if (problem.isGoal(currentNode.getState())) {
                    finished = true;
                    System.out.println(currentNode.getSolution());
                    System.out.println("Expanded nodes: " + explosionCounter);
                    System.out.println("Solution cost: " + currentNode.getG());
                } else {
                    explosionCounter++;
                    explode(currentNode);
                }
            }
        }
        if (finished) {
            System.out.println("OK! solution found!");
        } else if (failed) {
            System.err.println("FAILED! solution not found!");
        }
    }

    private boolean explode(GPSNode node) {
        GPSState state = node.getState();
        int gValue = node.getG();
        if (bestCosts.containsKey(state) && bestCosts.get(state) <= gValue) {
            return false;
        }
        updateBest(node);
        if (problem.getRules() == null) {
            System.err.println("No rules!");
            return false;
        }
        for (GPSRule rule : problem.getRules()) {
            Optional<GPSState> newStateOpt = state.apply(rule);

            if (newStateOpt.isPresent()) {
                GPSState newState = newStateOpt.get();
                int newGValue = gValue + rule.getCost();

                if (isBest(newState, newGValue)) {
                    GPSNode newNode = new GPSNode(newState, newGValue);
                    newNode.setParent(node);
                    open.add(newNode);
                }
            }
        }
        return true;
    }

    private boolean isBest(GPSState state, Integer cost) {
        return !bestCosts.containsKey(state) || cost < bestCosts.get(state);
    }

    private void updateBest(GPSNode node) {
        bestCosts.put(node.getState(), node.getG());
    }

}
