/* =====================================================================================
 *       Filename:  KnightsTour.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.22b - class of control knight's tour on virtual
                                chessboard
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class KnightsTour {
   
   public static final int ROWS = 8;
   public static final int COLUMNS = 8;
   private byte [][] board = new byte[ROWS][COLUMNS];   // chessboard
   
   public static final int POSITIONS = ROWS * COLUMNS;
   
   public static final int MAX_MOVES = 8;     
                                           //  0   1   2   3   4   5  6  7   ---  indexes in arrays of moves
   public static final byte [] vertical   = { -1, -2, -2, -1,  1,  2, 2, 1 };  // available change current row caused by knight's move on chessboard
   public static final byte [] horizontal = {  2,  1, -1, -2, -2, -1, 1, 2 };  // change current column caused by knight's move on chessboard 
   
   private static final byte VISITED = 1;  // value means that position on board table was visited by knight
   private static final byte NO_TRACE = 0;  // value means that position on board table was not visited by knight
   
   private int currentRow    = 0;    // row number on the board table where knight is currently  
   private int currentColumn = 0;    // column number on the board table where knight is currently  
   
   private int previousRow    = 0;    // row number on the board table where knight was previously  
   private int previousColumn = 0;    // column number on the board table where knight was previously  
   
   private int visitedPositionsCounter = 1;   // starting position initialized counter

   private char knightCharacter = 'K';
   private char visitedCharacter = '#';
   private char noTracedCharacter = '-';
   
   private String errorMessage = "";
   
   private byte [][] accessibility = new byte[ROWS][COLUMNS];   // to heuristic
   
   KnightsTour() {
      resetTour();
   }
   
   byte [][] getAccessibility () {
      return accessibility;
   }
   
   boolean isMoveAvailable(int moveNumber, int row, int column) {
      if (moveNumber < 0 || moveNumber >= MAX_MOVES) {
         errorMessage = String.format("unrecognized move's number %d", moveNumber);
         return false;
      }
      
      int nextRow = row + vertical[moveNumber];
      if (0 > nextRow) {
         errorMessage = String.format("move out of bounds: 0 > %d + %d", row, vertical[moveNumber]);
         return false;
      }
      if (ROWS - 1 < nextRow) {
         errorMessage = String.format("move out of bounds: %d < %d + %d", ROWS - 1, row, vertical[moveNumber]);
         return false;
      }
      
      int nextColumn = column + horizontal[moveNumber];
      if (0 > nextColumn) {
         errorMessage = String.format("move out of bounds: 0 > %d + %d", column, horizontal[moveNumber]);
         return false;
      }
      if (COLUMNS - 1 < nextColumn) {
         errorMessage = String.format("move out of bounds: %d < %d + %d", COLUMNS - 1, column, horizontal[moveNumber]);
         return false;
      }
      
      if (VISITED == board[nextRow][nextColumn]) {
         errorMessage = String.format("move to previous visited position: %d, %d", row, column);
         return false;
      }
      
      return true;
   }
   
   boolean move (int moveNumber) {
      if (false == isMoveAvailable(moveNumber, currentRow, currentColumn)) {
         return false;
      }
      
      previousRow = currentRow;
      previousColumn = currentColumn;
      
      currentRow += vertical[moveNumber];
      currentColumn += horizontal[moveNumber];
      board[currentRow][currentColumn] = VISITED;
      visitedPositionsCounter++;
      
      return true;
   }
   
   void undoLastMove () {
      board[currentRow][currentColumn] = NO_TRACE;
      currentRow = previousRow;
      currentColumn = previousColumn;
      visitedPositionsCounter--;
   }
   
   public int getCurrentRow() {
      return currentRow;
   }
   
   public int getCurrentColumn() {
      return currentColumn;
   }
   
   public int getVisitedPositionsCounter() {
      return visitedPositionsCounter;
   }
   
   public String getErrorMessage() {
      return errorMessage;
   }
   
   void clearErrorMessage() {
      errorMessage = "";
   }
   
   void resetTour() {
      setValuesInArray(board, NO_TRACE);
      currentRow = 0;
      currentColumn = 0;
      board[currentRow][currentColumn] = VISITED;  // marked as visited knight's starting position on board
      visitedPositionsCounter = 1;
      updateAccessibility();
   }
   
   public static void setValuesInArray(byte[][] array, byte value) {
      for (int row = 0; row < array.length; row++) {
         for (int column = 0; column < array[row].length; column++) {
            array[row][column] = value;
         }
      }
   }
   
   void updateAccessibility() {
      byte value;
      
      for (int row = 0; row < ROWS; row++) {
         for (int column = 0; column < COLUMNS; column++) {
            value = 0;
            for (int move = 0; move < MAX_MOVES; move++) {
               if (true == isMoveAvailable(move, row, column)) {
                  value++;
               }
            }
            accessibility[row][column] = value;
         }
      }
   }
   
   public String generateBoardString(boolean numbersAppending) {
      String boardString = "";
      
      if (true == numbersAppending) {
         boardString = appendNumbersRow();
      }
      
      for (int row = 0; row < ROWS; row++) {
         if (true == numbersAppending) {
            boardString += row;
         }
            
         for (int column = 0; column < COLUMNS; column++) {
            if (currentRow == row && currentColumn == column) {
               boardString += String.valueOf(knightCharacter); 
            }
            else if (this.board[row][column] == VISITED) {
               boardString += String.valueOf(visitedCharacter); 
            }
            else {
               boardString += String.valueOf(noTracedCharacter); 
            }
            
         }
         
         if (true == numbersAppending) {
               boardString += row;
         }
         
         boardString = boardString.concat(generateCurrentKnightsData(row));
         boardString = boardString.concat("\n"); 
      }
      
      if (true == numbersAppending) {
         boardString = boardString.concat(appendNumbersRow());
      }
      
      return boardString;
   }
   
   private String appendNumbersRow() {
      String numbersRowString = " ";
      
      for (int column = 0; column < ROWS; column++) {
         numbersRowString += column; 
      }
      
      numbersRowString += "\n"; 
      
      return numbersRowString;
   }
   
   private String generateCurrentKnightsData(int row) {
      String currentKnightsDataRow = String.format("%10s", " ");
      
      switch (row) {
         case 1:
            currentKnightsDataRow += "Knight:               " + String.valueOf(knightCharacter);
               break;
         case 2:
            currentKnightsDataRow += "Visited positions:    " + String.valueOf(visitedCharacter);
               break;
         case 3:
            currentKnightsDataRow += "No visited positions: " + String.valueOf(noTracedCharacter);
            break;
         case 4:
            currentKnightsDataRow += "Number of knight's position in row:    " + currentRow;
               break;
         case 5:
            currentKnightsDataRow += "Number of knight's position in column: " + currentColumn;
               break;
         case 6:
            currentKnightsDataRow += "Number of visited positions:           " + visitedPositionsCounter;
               break;
         default:
            return "";
      }
      
      return currentKnightsDataRow;
   }
   
} 
