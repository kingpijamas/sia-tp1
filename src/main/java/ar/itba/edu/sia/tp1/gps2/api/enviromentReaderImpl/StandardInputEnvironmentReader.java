package ar.itba.edu.sia.tp1.gps2.api.enviromentReaderImpl;

import ar.itba.edu.sia.tp1.eight_puzzle.PuzzleState;
import ar.itba.edu.sia.tp1.gps.api.GPSState;
import ar.itba.edu.sia.tp1.gps2.api.EnvironmentReader;

import java.util.Scanner;

/**
 * Created by scamisay on 30/03/16.
 */
public class StandardInputEnvironmentReader implements EnvironmentReader {

    @Override
    public GPSState read() {
        Scanner s = new Scanner(System.in);
        int[][] map = new int[PuzzleState.LENGTH][PuzzleState.LENGTH];
        int index = 0;
        while (index < PuzzleState.LENGTH * PuzzleState.LENGTH) {
            map[index / PuzzleState.LENGTH][index % PuzzleState.LENGTH] = s.nextInt();
            index++;
        }
        s.close();
        return new PuzzleState(map);
    }
}
