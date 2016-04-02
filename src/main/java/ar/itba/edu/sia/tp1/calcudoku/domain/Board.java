package ar.itba.edu.sia.tp1.calcudoku.domain;

import java.util.BitSet;
import java.util.List;

/**
 * Created by scamisay on 02/04/16.
 */
public class Board {
    private final BoardStructure boardInmutableStructure;
    private final BitSet data;

    public Board(int n, List<Group> groups) {
        this.boardInmutableStructure = new BoardStructure(n, groups);
        data = new BitSet(n * n * n);
    }

    private Board(Board previousBoard) {
        this.boardInmutableStructure = previousBoard.boardInmutableStructure;
        this.data = previousBoard.data.get(0, previousBoard.data.size());
    }

    public Board deepCopy() {
        return new Board(this);
    }

    public boolean isValid(){
        return true; //todo: implementar
    }

    private static class BoardStructure {
        final int n;
        final List<Group> groups;

        BoardStructure(int n, List<Group> groups) {
            this.n = n;
            this.groups = groups;
        }
    }


/*
    public abstract void get(int row, int column);
*/
    //  int[][]a = {{1,2,3,4},{5,6,7,8}};
    // Arrays.deepToString(a).replace("],","]\n");

}

