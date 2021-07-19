package model.chess;

import model.bordergame.Board;
import model.bordergame.Piece;
import model.bordergame.Position;

public abstract class ChessPiece extends Piece {

	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().pieces(position);
		return p != null && p.getColor() != color;
	}
}
