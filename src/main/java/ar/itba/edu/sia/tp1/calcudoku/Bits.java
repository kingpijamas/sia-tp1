package ar.itba.edu.sia.tp1.calcudoku;

import java.util.BitSet;

/**
 * Created by scamisay on 02/04/16.
 */
public class Bits {
    public static void main() {
        //Stream<BitSet> list = Arrays.stream(new long[]{1,2,3}).map();

    }

    @FunctionalInterface
    public interface BitFunction {
        BitSet apply(long num);
    }

    public static BitSet convert(long value) {
        BitSet bits = new BitSet();
        int index = 0;
        while (value != 0L) {
            if (value % 2L != 0) {
                bits.set(index);
            }
            ++index;
            value = value >>> 1;
        }
        return bits;
    }

    public static long convert(BitSet bits) {
        long value = 0L;
        for (int i = 0; i < bits.length(); ++i) {
            value += bits.get(i) ? (1L << i) : 0L;
        }
        return value;
    }
}