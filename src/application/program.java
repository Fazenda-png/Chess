package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.chess.ChessException;
import model.chess.ChessMath;
import model.chess.ChessPiece;
import model.chess.ChessPosition;

public class program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMath chessmath = new ChessMath();

		List<ChessPiece> captured = new ArrayList<>();

		while (!chessmath.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessmath, captured);
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);

				boolean[][] possibleMoves = chessmath.possibleMoves(source);
				UI.printBoard(chessmath.getPieces(), possibleMoves);

				System.out.println();
				System.out.print("target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturePiece = chessmath.performChessMove(source, target);
				if (capturePiece != null) {
					captured.add(capturePiece);
				}

			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessmath, captured);
	}

}
