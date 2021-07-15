package model.chess;

import model.bordergame.Board;
import model.bordergame.Piece;

public class chessPiece extends Piece {

	private Color color;

	public chessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}
