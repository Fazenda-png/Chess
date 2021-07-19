package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.chess.ChessException;
import model.chess.ChessMath;
import model.chess.ChessPiece;
import model.chess.ChessPosition;

public class program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMath chessmath = new ChessMath();

		while (true) {
			try {
				UI.clearScreen();
				UI.printBoard(chessmath.getPieces());
				System.out.println();
				System.out.print("Source: ");
				ChessPosition source = UI.readChessPosition(sc);

				System.out.println();
				System.out.print("target: ");
				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturePiece = chessmath.performChessMove(source, target);
			} catch (ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

}
