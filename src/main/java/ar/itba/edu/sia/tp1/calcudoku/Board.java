package ar.itba.edu.sia.tp1.calcudoku;

import java.util.List;

/**
 * Created by scamisay on 02/04/16.
 */
public abstract class Board {
    //  int[][]a = {{1,2,3,4},{5,6,7,8}};
    // Arrays.deepToString(a).replace("],","]\n");
    public Board(int n) {
    }

    public Board(List<Integer> values) {
    }

    public abstract boolean isValid();



/*
    public abstract void get(int row, int column);
*/

}

