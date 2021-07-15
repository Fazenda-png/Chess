package application;

import model.chess.chessMath;

public class program {

	public static void main(String[] args) {
		chessMath chessmath = new chessMath();
		UI.printBoard(chessmath.getPieces());
	}

}
