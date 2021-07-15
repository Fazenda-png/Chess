package model.chess.pieces;

import model.bordergame.Board;
import model.chess.Color;
import model.chess.ChessPiece;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}
	@Override
	public String toString() {
		return "R";
	}
}
