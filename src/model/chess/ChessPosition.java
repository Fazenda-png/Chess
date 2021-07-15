package model.chess;

import model.bordergame.Position;

public class ChessPosition {

	private char columns;
	private int rows;

	public ChessPosition(char columns, int rows) {
		if (columns < 'a' || columns > 'h' || rows < 1 || rows > 8) {
			throw new ChessException("Error instantiating ChessPosition. valid values are from a1 to h8.");
		}
		this.columns = columns;
		this.rows = rows;
	}

	public char getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}

	protected Position toPosition() {
		return new Position(8 - rows, columns - 'a');
	}

	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char) ('a' - position.getColumn()), 8 - position.getRow());
	}

	@Override
	public String toString() {
		return "" + columns + rows;
	}

}
