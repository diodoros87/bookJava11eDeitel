/* =====================================================================================
 *       Filename:  TicTacToeView.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.18 - class of tic tac toe game
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.io.PrintStream;

public class TicTacToeView {
   private static final String START_INFO = "This program is tic-tac-toe game's simulation for 2 human players.";
                                                   
   private static final char FIRST_PLAYER_MARKER  = 'X';
   private static final char SECOND_PLAYER_MARKER = 'O';
   private static final char EMPTY_CELL_MARKER    = ':';
   private static final char HORIZONTAL_BORDER_MARKER  = '=';
   private static final char VERTICAL_BORDER_MARKER    = '|';
   
   private static final String BORDER_HORIZONTAL_LINE = getHorizontalLine(1 + 4 * TicTacToe.getSQUARE_SIZE());
   
   private PrintStream printStream = System.out;
   
   public TicTacToeView() {
      printStream = System.out;
   }
   
   public TicTacToeView(PrintStream printStream) {
      if (null == printStream) {
         throw new NullPointerException("Null reference to " + printStream);
      }
      
      this.printStream = printStream;
   }
   
   public static char getFirstPlayerMarker() {
      return FIRST_PLAYER_MARKER;
   }
   
   public static char getSecondPlayerMarker() {
      return SECOND_PLAYER_MARKER;
   }
   
   public void printStartInfo() {
      printStream.println(START_INFO);
   }
   
   public void printGameOver() {
      printStream.println("\t Game is over");
   }
   
   public void printOccupiedPositionInfo(byte row, byte column) {
      printStream.printf("ERROR: Cell in (%d, %d) is not empty %n", row, column);
   }
   
   public void printGameStatus(GameStatus gameStatus) {
      printStream.println("\t Game's status: " + gameStatus);
   }
   
   public void printBoard(TicTacToe ticTacToe) {
      printStream.println("----------------------");
      printStream.println("########  Board: ");
      printStream.println();
      
      String result = String.format("TicTacToeView's data: ");
      String boardString = generateBoardString(ticTacToe, true);
      printStream.println(boardString);
   } 
   
   private String generateBoardString(TicTacToe ticTacToe, boolean numbersAppending) {
      final TicTacToe.CellValue[][] BOARD = ticTacToe.getBoard();
      final byte SQUARE_SIZE = TicTacToe.getSQUARE_SIZE();
      String boardString = "";
      String numbersRow = appendNumbersRow(SQUARE_SIZE);
      
      if (true == numbersAppending) {
         boardString = numbersRow;
      }
      
      boardString += BORDER_HORIZONTAL_LINE;
      for (int row = 0; row < SQUARE_SIZE; row++) {
         if (true == numbersAppending) {
            boardString += row + 1;
         }
         
         boardString += String.valueOf(VERTICAL_BORDER_MARKER);
         for (int column = 0; column < SQUARE_SIZE; column++) {
            boardString += getCellMarker(BOARD[row][column]);
         }
         
         if (true == numbersAppending) {
            boardString += row + 1;
         }
         
         boardString = boardString.concat(String.format("%n")); 
         boardString += BORDER_HORIZONTAL_LINE;
      }
      
      //boardString += BORDER_HORIZONTAL_LINE;
      
      if (true == numbersAppending) {
         boardString = boardString.concat(numbersRow);
      }
      
      return boardString;
   }
   
   private static String getHorizontalLine(int length) {
      String line = " ";
      
      for (int counter = 0; counter < length; counter++) {
         line += HORIZONTAL_BORDER_MARKER;
      }
      
      line = line.concat(String.format("%n")); 
      
      return line;
   }
   /*
   private String getUnborderedCell(TicTacToe.CellValue cellValue, int cellWidth) {
      String positionMarker = "";
      
      switch (cellValue) {
         case X:
            positionMarker = String.format("%c", FIRST_PLAYER_MARKER); 
            break;
         case O:
            positionMarker = String.format("%c", SECOND_PLAYER_MARKER); 
            break;
         default:
            positionMarker = String.format("%c", EMPTY_CELL_MARKER); 
      }
      
      return positionMarker;
   }*/
   
   private String getCellMarker(TicTacToe.CellValue cellValue) {
      String positionMarker = "";
      
      switch (cellValue) {
         case X:
            positionMarker = String.format(" %c %c", FIRST_PLAYER_MARKER, VERTICAL_BORDER_MARKER); 
            break;
         case O:
            positionMarker = String.format(" %c %c", SECOND_PLAYER_MARKER, VERTICAL_BORDER_MARKER); 
            break;
         default:
            positionMarker = String.format(" %c %c", EMPTY_CELL_MARKER, VERTICAL_BORDER_MARKER); 
      }
      
      return positionMarker;
   }
   
   private String appendNumbersRow(final byte SQUARE_SIZE) {
      String numbersRowString = "";
      
      for (int column = 1; column <= SQUARE_SIZE; column++) {
         numbersRowString += String.format("   %d", column);
      }
      
      numbersRowString += String.format("%n");
      
      return numbersRowString;
   }
} 
