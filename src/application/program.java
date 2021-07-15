package application;

import java.util.Scanner;

import model.chess.ChessMath;
import model.chess.ChessPiece;
import model.chess.ChessPosition;

public class program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessMath chessmath = new ChessMath();

		while (true) {
			UI.printBoard(chessmath.getPieces());
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UI.readChessPosition(sc);

			System.out.println();
			System.out.print("target: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			ChessPiece capturePiece = chessmath.performChessMove(source, target);
		}
	}

}
