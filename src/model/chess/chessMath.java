package model.chess;

import model.bordergame.Board;
import model.bordergame.Position;
import model.chess.pieces.King;
import model.chess.pieces.Rook;

public class chessMath {

	private Board board;

	public chessMath() {
		board = new Board(8, 8);
		initialSetup();
	}

	public chessPiece[][] getPieces() {
		chessPiece[][] math = new chessPiece[board.getRows()][board.getColums()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColums(); j++) {
				math[i][j] = (chessPiece) board.pieces(i, j);
			}
		}
		return math;
	}
	
	private void initialSetup() {
		board.placePiese(new Rook(board, Color.WHITE), new Position(2,1));
		board.placePiese(new King(board, Color.BLACK), new Position(0,4));
	}
}
