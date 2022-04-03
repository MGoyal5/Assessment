package com.game.chess.service;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ChessService {


	//Define Constants
	private static final int[] CHESS_ROW= {1,2,3,4,5,6,7,8};
	private static final char[] CHESS_COLUMN= {'A','B','C','D','E','F','G','H'};
	private static final int MAX_ROW = CHESS_ROW.length;
	private static final char MAX_COL = 'H';
	private static final String[] POSSIBLE_CHESS_PIECE = {"Pawn", "King","Queen"};
	private Scanner scanner = new Scanner(System.in);
	//define static variables
	private static ChessService chessService=null;	
	
	//define global non-static variable
	
	
	private ChessService() {
		System.out.println("Welcome To The Chess Board");
	}
	private static void showChessBoard() {
		for(int i=CHESS_ROW.length-1;i>=0;i--) {
			if(i==MAX_ROW-1)
				System.out.println("\n\n");
			for(char c_col: CHESS_COLUMN) {
				if(c_col=='A')
					System.out.print("\t\t");
				System.out.print(c_col + "" + CHESS_ROW[i]);
				if(c_col!=MAX_COL) {
					System.out.print(" ");
				}
				else
					System.out.print("\t\t");
			}		
			System.out.println();
			if(i==0)
				System.out.println("\n");
		}
	
	}
	
	public static ChessService getChessServiceInstance() {
		if(null==chessService)
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
			}catch (Exception e) {
				System.out.println("You have provided wrong input. Please try again");
			}
			
		}while(!isExitGame);
	}
	private boolean startGame(int input) {
		switch(input) {
			case 1:
				ChessService.showChessBoard();
				return false;
			case 2:
				this.possibleMovement();
				return false;
			case 3:
				return true;
		}
		return false;
	}
	private void possibleMovement() {
		String pieceType;
		String position;
		System.out.println("Please provide Type of piece and position");
		pieceType = scanner.next();
		position = scanner.next();
		if(getPossibleChessPiece().contains(pieceType)) {
			if(position.length()==2)
			{
				char chess_col_pos = position.charAt(0);
				String chess_row_sub_string = position.substring(1);
				int chess_row_pos;
				if(StringUtils.isNumeric(chess_row_sub_string)) {
					chess_row_pos = Integer.parseInt(chess_row_sub_string);
				}
				else {
					System.out.println("Please provide valid position. For reference please check the chess board");
				}
			}
			else {
				System.out.println("Please provide valid position");
			}
		}
		else {
			System.out.println("Please provide valid Piece Type. Available Piece Types are: " + getPossibleChessPiece().toString());
		}
		
	}
	/**
	 * @return the possibleChessPiece
	 */
	public List<String> getPossibleChessPiece() {
		return Arrays.asList(POSSIBLE_CHESS_PIECE);
	}

}
