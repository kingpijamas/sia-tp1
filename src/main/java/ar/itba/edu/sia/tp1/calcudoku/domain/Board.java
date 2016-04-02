package ar.itba.edu.sia.tp1.calcudoku.domain;

import ar.itba.edu.sia.tp1.calcudoku.CalcudokuRule;
import ar.itba.edu.sia.tp1.gps.api.GPSState;

import java.util.BitSet;
import java.util.List;
import java.util.Optional;

/**
 * Created by scamisay on 02/04/16.
 */
public class Board {
    private final BitSet data;

    public Board(int n) {
        data = new BitSet(n * n * n);
    }

    private Board(Board baseBoard) {
        this.data = baseBoard.data.get(0, baseBoard.data.size());
    }

    public Board deepCopy() {
        return new Board(this);
    }

    public void put(Position position, int value) {
        // TODO: Implement!
    }

    public boolean isValid() {
        return true; //todo: implementar
    }


/*
    public abstract void get(int row, int column);
*/
    //  int[][]a = {{1,2,3,4},{5,6,7,8}};
    // Arrays.deepToString(a).replace("],","]\n");

}

