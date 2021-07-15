package model.bordergame;

public class Board {

	private int rows;
	private int colums;
	private Piece[][] pieces;

	public Board() {
	}

	public Board(int i, int j) {
		if (rows > 1 && colums > 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = i;
		this.colums = j;
		pieces = new Piece[i][j];
	}

	public int getRows() {
		return rows;
	}

	public int getColums() {
		return colums;
	}

	public Piece pieces(int row, int colums) {
		if (!positionExists(row, colums)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][colums];
	}

	public Piece pieces(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiese(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	public boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < colums;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces(position) != null;
	}
}
