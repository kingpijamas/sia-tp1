package ar.itba.edu.sia.tp1.calcudoku;

import ar.itba.edu.sia.tp1.calcudoku.domain.Position;
import ar.itba.edu.sia.tp1.eight_puzzle.PuzzleState;
import ar.itba.edu.sia.tp1.gps.api.GPSRule;
import ar.itba.edu.sia.tp1.gps.api.GPSState;

import java.util.Optional;

/**
 * Created by scamisay on 02/04/16.
 */
public class CalcudokuRule implements GPSRule{

    private final Position position;
    private final int value;

    public CalcudokuRule(Position position, int value) {
        this.position = position;
        this.value = value;
    }

    @Override
    public Integer getCost() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }


    public Position getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }
}
