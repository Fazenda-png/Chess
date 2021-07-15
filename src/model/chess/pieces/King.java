package model.chess.pieces;

import model.bordergame.Board;
import model.chess.Color;
import model.chess.ChessPiece;

public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}
	
	public String toString() {
		return "k";
	}
}
