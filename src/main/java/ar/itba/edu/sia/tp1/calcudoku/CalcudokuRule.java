package ar.itba.edu.sia.tp1.calcudoku;

import static ar.itba.edu.sia.tp1.utils.ObjectUtils.toStringBuilder;

import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.gps.GPSRule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuRule implements GPSRule {
    private final Set<Position> positions = new HashSet<>();

    public CalcudokuRule(Position pos1, Position pos2) {
        positions.add(pos1);
        positions.add(pos2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalcudokuRule)) return false;

        CalcudokuRule that = (CalcudokuRule) o;

        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        return positions.hashCode();
    }

    @Override
    public Integer getCost() {
        return null; // TODO
    }

    @Override
    public String getName() {
        return null; // TODO
    }

    @Override
    public String toString() {
        return toStringBuilder(this).append("position", position)
                .append("value", value).toString();
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
