package ar.itba.edu.sia.tp1.calcudoku.domain;

import java.util.List;

/**
 * Created by scamisay on 02/04/16.
 */
public class Group {
    private final List<Position> positions;
    private final Operator operator;
    private final int result;

    public Group(List<Position> positions, Operator operator, int result) {
        this.positions = positions;
        this.operator = operator;
        this.result = result;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public Operator getOperator() {
        return operator;
    }

    public int getResult() {
        return result;
    }
}
