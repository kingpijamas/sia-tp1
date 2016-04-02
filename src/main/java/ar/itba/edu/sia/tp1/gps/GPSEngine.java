package ar.itba.edu.sia.tp1.gps;

import ar.itba.edu.sia.tp1.gps.api.GPSProblem;
import ar.itba.edu.sia.tp1.gps.api.GPSRule;
import ar.itba.edu.sia.tp1.gps.api.GPSState;

import java.util.HashMap;
import java.util.Map;
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
            if (open.size() <= 0) {
                failed = true;
            } else {
                GPSNode currentNode = open.remove();
                if (problem.isGoal(currentNode.getState())) {
                    finished = true;
                    System.out.println(currentNode.getSolution());
                    System.out.println("Expanded nodes: " + explosionCounter);
                    System.out.println("Solution cost: " + currentNode.getCost());
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
        if (bestCosts.containsKey(node.getState()) && bestCosts.get(node.getState()) <= node.getCost()) {
            return false;
        }
        updateBest(node);
        if (problem.getRules() == null) {
            System.err.println("No rules!");
            return false;
        }
        for (GPSRule rule : problem.getRules()) {
            GPSState newState = rule.evalRule(node.getState());
            if (newState.isValid()) {
                int newCost = node.getCost() + rule.getCost();
                if (isBest(newState, newCost)) {
                    GPSNode newNode = new GPSNode(newState, newCost);
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
        bestCosts.put(node.getState(), node.getCost());
    }

}
