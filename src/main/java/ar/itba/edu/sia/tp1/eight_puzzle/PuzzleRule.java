package ar.itba.edu.sia.tp1.eight_puzzle;

import java.awt.Point;

import ar.itba.edu.sia.tp1.gps.api.GPSRule;
import ar.itba.edu.sia.tp1.gps.api.GPSState;
import ar.itba.edu.sia.tp1.gps.exception.NotAppliableException;
import ar.itba.edu.sia.tp1.utils.Copies;

public class PuzzleRule implements GPSRule {
    Direction direction;

    private Point destination;

    public PuzzleRule(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Integer getCost() {
        return 1;
    }

    @Override
    public String getName() {
        return "Move blank space " + direction.toString();
    }

    @Override
    public GPSState evalRule(GPSState state){
        PuzzleState puzzleState = ((PuzzleState) state);
        Point delta = direction.getDelta();
        Point blank = puzzleState.getBlankCoords();
        destination = (Point) blank.clone();
        destination.translate(delta.x, delta.y);
        if (!isValid()) {
            return new InvalidState();
        }

        int[][] newMap = Copies.deepCopy(puzzleState.map);
        newMap[blank.x][blank.y] = newMap[destination.x][destination.y];
        newMap[destination.x][destination.y] = PuzzleState.BLANK;
        return new PuzzleState(newMap);
    }

    @Override
    public boolean isValid() {
        return !(destination.getX() < 0 || destination.getX() >= PuzzleState.LENGTH || destination.getY() < 0
                || destination.getY() >= PuzzleState.LENGTH);
    }
}
