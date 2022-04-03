package com.game.chess.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.game.chess.util.ChessConstants;
import com.game.chess.util.ErrorConstants;

/**
 * @author mangl Chess service provides the services to play chess game with
 *         below operations Show Chess Board Show Possible Movement as per the
 *         given problem statement User can check the possible movements only
 *         for Pawn, King and Queen
 * 
 */
public class ChessService {

	private static ChessService chessService = null;

	private Scanner scanner = new Scanner(System.in);

	//
	private List<Character> chessCols = ChessConstants.getChessColumn();
	private List<Integer> chessRows = ChessConstants.getChessRow();
	private char chess_col_pos;
	private int chess_row_pos;
	private List<String> possibleMovementsForPiece;

	private ChessService() {
	}

	/**
	 * @return ChessService Object
	 */
	public static ChessService getChessServiceInstance() {
		if (null == chessService)
			chessService = new ChessService();
		return chessService;
	}

	public void playChess() {
		boolean isExitGame = false;
		int input;
		do {
			System.out.println("Please select the option: ");
			System.out.println("Enter 1 for Show Chess Board");
			System.out.println("Enter 2 for Show Possible Movements");
			System.out.println("Enter 3 for Exit Game");
			try {
				input = scanner.nextInt();
				isExitGame = this.startGame(input);
			} catch (Exception e) {
				System.out.println(ErrorConstants.WRONG_INPUT);
			}

		} while (!isExitGame);
	}

	/**
	 * @param input
	 * @return Method will return boolean value if input entered is 3 then return
	 *         true else false
	 */
	private boolean startGame(int input) {
		switch (input) {
		case 1:
			ChessService.showChessBoard();
			return false;
		case 2:
			this.possibleMovement();
			return false;
		case 3:
			return true;
		default:
			System.out.println(ErrorConstants.WRONG_INPUT);
		}
		return false;
	}

	private static void showChessBoard() {
		for (int i = ChessConstants.getChessRow().size() - 1; i >= 0; i--) {
			if (i == ChessConstants.getMaxRow() - 1)
				System.out.println("\n\n");
			for (char c_col : ChessConstants.getChessColumn()) {
				if (c_col == 'A')
					System.out.print("\t\t");
				System.out.print(c_col + "" + ChessConstants.getChessRow().get(i));
				if (c_col != ChessConstants.getMaxCol()) {
					System.out.print(" ");
				} else
					System.out.print("\t\t");
			}
			System.out.println();
			if (i == 0)
				System.out.println("\n");
		}

	}

	private void possibleMovement() {
		possibleMovementsForPiece = new LinkedList<>();
		String pieceType;
		String position;
		System.out.println("Please provide Type of piece:");
		pieceType = scanner.next();
		boolean isPieceTypeMatch = (ChessConstants.getPossibleChessPiece().contains(pieceType.toUpperCase()));
		if (!isPieceTypeMatch)
			System.out.println(ErrorConstants.INVALID_PIECE_TYPE);
		else {
			System.out.println("Position Of " + pieceType + " :");
			position = scanner.next();

			if (position.length() == 2) {
				this.chess_col_pos = position.charAt(0);
				this.chess_row_pos = Integer.parseInt(position.replaceAll("[^0-9]", ""));
				List<String> positions = this.getPossibleMovements(pieceType, position);
				if (positions != null && positions.size() > 0) {
					System.out.println("Possible Movements for " + pieceType + " are: ");
					System.out.println(positions.toString());
					chess_col_pos = 'I';
					chess_row_pos = 0;
				}

			} else {
				System.out.println(ErrorConstants.INVAVLID_POSITION);
			}
		}
	}

	private List<String> getPossibleMovements(String pieceType, String position) {
		List<String> positions = new LinkedList<>();
		boolean isPositionCorrect = chessCols.contains(chess_col_pos) && chessRows.contains(chess_row_pos);
		if (isPositionCorrect) {
			switch (pieceType) {
			case "Pawn":
				positions = getPossibleMovesForPawn(pieceType, position);
				break;
			case "King":
				positions = getPossibleMovesForKing(pieceType, position);
				break;
			case "Queen":
				positions = getPossibleMovesForQueen(pieceType, position);
				break;
			default:
				position = null;
			}

		} else {
			System.out.println(ErrorConstants.INVAVLID_POSITION);
		}
		return positions;
	}

	private List<String> getPossibleMovesForQueen(String pieceType, String position) {
		// total 8 direction will be covered
		int currentRowIndex = chessRows.indexOf(chess_row_pos);
		int currentColIndex = chessCols.indexOf(chess_col_pos);

		int maxRowIndex = chessRows.size() - 1;
		int maxColIndex = chessCols.size() - 1;

		int minRowIndex = 0;
		int minColIndex = 0;
		
		//direct 1 --- forward in same col till max row index
		if(currentRowIndex!=maxRowIndex) {
			for(int i=currentRowIndex;i<=maxRowIndex;i++) {
				possibleMovementsForPiece
				.add(new String(Character.toString(chessCols.get(currentColIndex)).concat("" + (chessRows.get(i)))));
			}
		}
		
		//direction2 backward in same col till row_index 0;
		
		
		//direction3 -- forward in same row till max col index
		if(currentColIndex!=maxColIndex) {
			for(int i=currentColIndex;i<=maxRowIndex;i++) {
				possibleMovementsForPiece
				.add(new String(Character.toString(chessCols.get(i)).concat("" + (chessRows.get(currentRowIndex)))));
			}
		}
		
		//direction4 backward in same row till col_index 0
		
		
		

		// backward in same row till col_index 0;
		// backward in same col till row_index 0;

		// forward in right cross till max index(either row or col) -- increase both
		// colIndex and rowIndex
		// forward in left cross till max index of row or min index of col -- Increase
		// rowIndex and descrease colIndex

		// backward in right cross till min Index(either row or col) -- decrease both
		// col and row Index
		// backward in left cross till min index of row and max index of col ---
		// decrease rowIndex and increase colIndex

		return null;
	}

	private List<String> getPossibleMovesForKing(String pieceType, String position) {
		// TODO Auto-generated method stub
		int currentRowIndex = chessRows.indexOf(chess_row_pos);
		int currentColIndex = chessCols.indexOf(chess_col_pos);

		int prevRowIndex = currentRowIndex;
		int prevColIndex = currentColIndex;
		int nextRowIndex = currentRowIndex;
		int nextColIndex = currentColIndex;

		if (prevRowIndex > 0)
			prevRowIndex--;

		if (prevColIndex > 0)
			prevColIndex--;

		if (currentRowIndex < ChessConstants.getMaxRow() - 1)
			nextRowIndex++;
		if (currentColIndex < ChessConstants.getChessColumn().size() - 1)
			nextColIndex++;
		for (int j = prevColIndex; j <= nextColIndex; j++) {
			for (int i = prevRowIndex; i <= nextRowIndex; i++) {
				possibleMovementsForPiece
						.add(new String(Character.toString(chessCols.get(j)).concat("" + (chessRows.get(i)))));
			}
		}

		possibleMovementsForPiece.remove(position);
		return possibleMovementsForPiece;
	}

	private List<String> getPossibleMovesForPawn(String pieceType, String position) {

		// for pawn return the single position till the max
		if (chess_row_pos == chessRows.size()) {
			System.out.println("Pawn has already at max position");
		} else
			possibleMovementsForPiece
					.add(new String(Character.toString(chess_col_pos).concat("" + (chess_row_pos + 1))));
		return possibleMovementsForPiece;
	}

}
