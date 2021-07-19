package model.chess;

import model.bordergame.Board;
import model.bordergame.Piece;
import model.bordergame.Position;
import model.chess.pieces.King;
import model.chess.pieces.Rook;

public class ChessMath {

	private int turn;
	private Color currentPlayer;
	private Board board;

	public ChessMath() {
		board = new Board(8, 8);
		initialSetup();
		turn = 1;
		currentPlayer = Color.WHITE;
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

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean[][] possibleMoves(ChessPosition sorcePosition) {
		Position position = sorcePosition.toPosition();
		validateSourcePosition(position);
		return board.pieces(position).possibleMoves();
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturePiece = makemove(source, target);
		nextTurn();
		return (ChessPiece) capturePiece;
	}

	public void validateTargetPosition(Position source, Position target) {
		if (!board.pieces(source).possibleMove(target)) {
			throw new ChessException("The chose piece can't move to target position");
		}
	}

	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		if (currentPlayer != ((ChessPiece) board.pieces(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours");
		}
		if (board.pieces(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chose piece");
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

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

}
