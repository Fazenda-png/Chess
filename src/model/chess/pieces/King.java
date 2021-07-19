package model.chess.pieces;

import model.bordergame.Board;
import model.chess.Color;
import model.chess.ChessPiece;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "k";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColums()];

		return mat;
	}
}
