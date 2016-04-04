package ar.itba.edu.sia.tp1.calcudoku.domain;

import java.util.BitSet;

/**
 * Created by scamisay on 02/04/16.
 *
 * Tablero Alto Nivel
 *
 * 2	1	3
 * 1	3	2
 * 3	2	1
 *
 *
 * Tablero en Binario
 *
 * 010 100 001
 * 100 001 010
 * 001 010 100
 *
 *
 * Tablero en BitSet
 *
 * 010100001100001010001010100
 *
 *
 */
public class Board {

	public static void main(String []args){
		System.out.print(1);
		int n = 3;
		Board board = new Board(n);

		board.put(new Position(0,0),2);
		board.put(new Position(0,1),1);
		board.put(new Position(0,2),3);

		board.put(new Position(1,0),1);
		board.put(new Position(1,1),3);
		board.put(new Position(1,2),2);

		board.put(new Position(2,0),3);
		board.put(new Position(2,1),2);
		board.put(new Position(2,2),1);

		board.isValid();
		board.toString();
	}

	private final BitSet data;

	private final int n;

	public Board(int n) {
		this.n = n;
		data = new BitSet(n * n * n);
	}

	private Board(Board baseBoard) {
		this.n = baseBoard.getN();
		this.data = baseBoard.data.get(0, baseBoard.data.size());
	}

	public Board deepCopy() {
		return new Board(this);
	}

	/**
	 * Precondicion: n >= 1 && 0 < value <= n
	 * @param position
	 * @param value
     */
	public void put(Position position, int value) {
		// representation of value in n bits
		BitSet bitValue = new BitSet(n);

		//index starts at 0
		bitValue.set(value-1);

		putBitSetValue(position, bitValue);

	}

	public boolean isValid() {
		if(!areRowsValid()){
			return false;
		}

		if(!areColsValid()){
			return false;
		}

		/*todo: implement this
		if(!areGroupsValid()){
			return false;
		}*/
		return true;
	}

	/**
	 * The binary OR between all elements of each col must be equal to n 1s
	 * @return
	 */
	private boolean areColsValid() {
		BitSet op = new BitSet(n);
		for(int j = 0 ; j < n; j++){
			for(int i = 0; i < n; i++){
				op.or(getCell(i,j));
			}

			//cardinality counts the number of 1s in the BitSet
			if(op.cardinality() < n){
				return false;
			}
			op.clear();
		}
		return true;
	}

	/**
	 * The binary OR between all elements of each row must be equal to n 1s
	 * @return
     */
	private boolean areRowsValid() {
		BitSet op = new BitSet(n);
		for(int i = 0 ; i < n; i++){
			for(int j = 0; j < n; j++){
				op.or(getCell(i,j));
			}

			//cardinality counts the number of 1s in the BitSet
			if(op.cardinality() < n){
				return false;
			}
			op.clear();
		}
		return true;
	}

	private BitSet getCell(int i, int j) {
		int beginning = getBeginningOfCell(new Position(i,j));
		return data.get(beginning, beginning + n);
	}

	public int getN() {
		return n;
	}

	/**
	 * put the 'bitvalue' at the index given by 'position' in data
	 * @param position
	 * @param bitValue
     */
	private void putBitSetValue(Position position, BitSet bitValue) {
		int beginning = getBeginningOfCell(position);

		int dataIndex;
		int i;
		for(dataIndex = beginning, i=0 ; dataIndex < beginning + n; dataIndex++, i++){
			data.set(dataIndex, bitValue.get(i));
		}
	}

	/**
	 * devuelve index en el bitset para el inicio del dato en el casillero
	 */
	private int getBeginningOfCell(Position position){
		return position.getCol()*n + position.getRow()*n*n;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(getCellValue(i,j) + "\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private Integer getCellValue(int i, int j) {
		BitSet cell = getCell(i,j);
		for(int index = 0; index < n ; index++){
			if(cell.get(index)){
				return index + 1;
			}
		}
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Board board = (Board) o;

		return data.equals(board.data);

	}

	@Override
	public int hashCode() {
		return data.hashCode();
	}
}
