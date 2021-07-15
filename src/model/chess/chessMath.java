package model.chess;

import model.bordergame.Board;
import model.bordergame.Piece;
import model.bordergame.Position;
import model.chess.pieces.King;
import model.chess.pieces.Rook;

public class ChessMath {

	private Board board;

	public ChessMath() {
		board = new Board(8, 8);
		initialSetup();
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] math = new ChessPiece[board.getRows()][board.getColums()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColums(); j++) {
				math[i][j] = (ChessPiece) board.pieces(i, j);
			}
		}
		return math;
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		Piece capturePiece = makemove(source, target);
		return (ChessPiece) capturePiece;
	}

	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
	}
	
	private Piece makemove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturePiece = board.removePiece(target);
		board.placePiese(p, target);
		return capturePiece;
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiese(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}

}
