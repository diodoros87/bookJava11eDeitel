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
                                                   
   private static final char firstPlayerMarker  = 'X';
   private static final char secondPlayerMarker = 'O';
   private static final char EmptyCellMarker    = ':';
   private static final char horizontalBorderMarker  = '=';
   private static final char verticalBorderMarker    = '|';
   
   private static final String BORDER_HORIZONTAL_LINE = getHorizontalLine(3 * TicTacToe.getSQUARE_SIZE());
   
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
      return firstPlayerMarker;
   }
   
   public static char getSecondPlayerMarker() {
      return secondPlayerMarker;
   }
   
   public void printStartInfo() {
      printStream.println(START_INFO);
   }
   
   public void printGameOver() {
      printStream.println("Game is over");
   }
   
   public void printOccupiedPositionInfo(byte row, byte column) {
      printStream.printf("Cell in (%d, %d) is not empty %n", row, column);
   }
   
   public void printGameStatus(GameStatus gameStatus) {
      printStream.println("Game's status: " + gameStatus);
   }
   
   public void printBoard(TicTacToe ticTacToe) {
      printStream.println("----------------------");
      printStream.println("########  Board: ");
      printStream.println();
      
      String result = String.format("TicTacToeView's data: ");
      int cellWidth = 2;
      String boardString = generateBoardString(ticTacToe, true);
      printStream.println(boardString);
   } 
   
   private String generateBoardString(TicTacToe ticTacToe, boolean numbersAppending) {
      final TicTacToe.CellValue[][] BOARD = ticTacToe.getBoard();
      final byte SQUARE_SIZE = TicTacToe.getSQUARE_SIZE();
      String boardString = "";
      int cellWidth = 1;
      String numbersRow = appendNumbersRow(SQUARE_SIZE, cellWidth);
      
      if (true == numbersAppending) {
         boardString = numbersRow;
      }
      
      boardString += BORDER_HORIZONTAL_LINE;
      
      for (int row = 0; row < SQUARE_SIZE; ) {
         if (true == numbersAppending) {
            boardString += row + 1;
         }
            
         for (int column = 0; column < SQUARE_SIZE; column++) {
            boardString += getCellMarker(BOARD[row][column]);
         }
         
         if (true == numbersAppending) {
            boardString += ++row;
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
      String line = "";
      
      for (int counter = 0; counter < length; counter++) {
         line += horizontalBorderMarker;
      }
      
      line = line.concat(String.format("%n")); 
      
      return line;
   }
   /*
   private String getUnborderedCell(TicTacToe.CellValue cellValue, int cellWidth) {
      String positionMarker = "";
      
      switch (cellValue) {
         case X:
            positionMarker = String.format("%c", firstPlayerMarker); 
            break;
         case O:
            positionMarker = String.format("%c", secondPlayerMarker); 
            break;
         default:
            positionMarker = String.format("%c", EmptyCellMarker); 
      }
      
      return positionMarker;
   }*/
   
   private String getCellMarker(TicTacToe.CellValue cellValue) {
      String positionMarker = "";
      
      switch (cellValue) {
         case X:
            positionMarker = String.format("%c", firstPlayerMarker); 
            break;
         case O:
            positionMarker = String.format("%c", secondPlayerMarker); 
            break;
         default:
            positionMarker = String.format("%c", EmptyCellMarker); 
      }
      
      return positionMarker;
   }
   
   private String appendNumbersRow(final byte SQUARE_SIZE, int cellWidth) {
      String numbersRowString = " ";
      
      for (int column = 1; column <= SQUARE_SIZE; column++) {
         numbersRowString += String.format("%" + cellWidth + "d", column);
      }
      
      numbersRowString += String.format("%n");
      
      return numbersRowString;
   }
} 
