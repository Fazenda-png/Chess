package model.chess.pieces;

import model.bordergame.Board;
import model.chess.Color;
import model.chess.chessPiece;

public class King extends chessPiece{

	public King(Board board, Color color) {
		super(board, color);
	}
	
	public String toString() {
		return "k";
	}
}
