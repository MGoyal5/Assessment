package com.game.chess.util;

import java.util.Arrays;
import java.util.List;

public class ChessConstants {

	private static final Integer[] CHESS_ROW = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private static final Character[] CHESS_COLUMN = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
	private static final int MAX_ROW = CHESS_ROW.length;
	private static final char MAX_COL = 'H';
	private static final String[] POSSIBLE_CHESS_PIECE = { "PAWN", "KING", "QUEEN" };
	/**
	 * @return the chessRow
	 */
	public static List<Integer> getChessRow() {
		return Arrays.asList(CHESS_ROW);
	}
	/**
	 * @return the chessColumn
	 */
	public static List<Character> getChessColumn() {
		return Arrays.asList(CHESS_COLUMN);
	}
	/**
	 * @return the maxRow
	 */
	public static int getMaxRow() {
		return MAX_ROW;
	}
	/**
	 * @return the maxCol
	 */
	public static char getMaxCol() {
		return MAX_COL;
	}
	/**
	 * @return the possibleChessPiece
	 */
	public static List<String> getPossibleChessPiece() {
		return Arrays.asList(POSSIBLE_CHESS_PIECE);
	}
	
	
}
