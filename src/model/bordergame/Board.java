package model.bordergame;

public class Board {

	private int rows;
	private int colums;
	private Piece[][] pieces;

	public Board() {
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColums() {
		return colums;
	}

	public void setColums(int colums) {
		this.colums = colums;
	}

}
