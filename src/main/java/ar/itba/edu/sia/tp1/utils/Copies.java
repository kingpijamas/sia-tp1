package ar.itba.edu.sia.tp1.utils;

public class Copies {
	public static int[][] deepCopy(int[][] param) {
		int baseLength = param.length;
		int[][] ret = new int[baseLength][];
		for (int i = 0; i < baseLength; i++) {
			ret[i] = new int[param[i].length];
			for (int j = 0; j < baseLength; j++) {
				ret[i][j] = param[i][j];
			}
		}
		return ret;
	}
}
