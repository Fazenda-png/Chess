package model.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.bordergame.Board;
import model.bordergame.Piece;
import model.bordergame.Position;
import model.chess.pieces.King;
import model.chess.pieces.Rook;

public class ChessMath {

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	private boolean checkMate;

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

	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
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

		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturePiece);
			throw new ChessException("You can't put yorself in check");
		}

		check = (testCheck(opponent(currentPlayer))) ? true : false;
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
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
		if (capturePiece != null) {
			piecesOnTheBoard.remove(capturePiece);
			capturedPieces.add(capturePiece);
		}
		return capturePiece;
	}

	private void undoMove(Position source, Position target, Piece capturePiece) {
		Piece p = board.removePiece(target);
		board.placePiese(p, source);

		if (capturePiece != null) {
			board.placePiese(capturePiece, target);
			capturedPieces.remove(capturePiece);
			piecesOnTheBoard.add(capturePiece);
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiese(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}

	private void initialSetup() {
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));

		placeNewPiece('b', 7, new Rook(board, Color.BLACK));
		placeNewPiece('a', 8, new Rook(board, Color.BLACK));

	}

	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		throw new IllegalStateException("There is no" + color + "king on the board");
	}

	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPositon().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}

	private boolean testCheckMate(Color color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for (int i = 0; i < board.getRows(); i++) {
				for (int j = 0; j < board.getColums(); j++) {
					if (mat[i][j]) {
						Position source = ((ChessPiece) p).getChessPositon().toPosition();
						Position target = new Position(i, j);
						Piece capturePiece = makemove(source, target);
						boolean testeCheck = testCheck(color);
						undoMove(source, target, capturePiece);
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
