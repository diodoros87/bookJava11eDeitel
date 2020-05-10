/* =====================================================================================
 *       Filename:  KnightsTour.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.22c - class of finding knight's tour on virtual
                                chessboard by heuristic accessibility algorithm
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class KnightsTour {
   
   public static final byte ROWS = 8;
   public static final byte COLUMNS = 8;
   private byte [][] board = new byte[ROWS][COLUMNS];   // chessboard
   
   public static final byte POSITIONS = ROWS * COLUMNS;
   
   public static final byte MAX_MOVES = 8;     
                                           //  0   1   2   3   4   5  6  7   ---  indexes in arrays of moves
   public static final byte [] vertical   = { -1, -2, -2, -1,  1,  2, 2, 1 };  // available change current row caused by knight's move on chessboard
   public static final byte [] horizontal = {  2,  1, -1, -2, -2, -1, 1, 2 };  // change current column caused by knight's move on chessboard 
   
   public static final byte VISITED   = 1;  // value means that position on board table was visited by knight 
   public static final byte NO_TRACED = 0;  // value means that position on board table was not visited by knight 
   
   private byte currentRow    = 0;    // row number on the board table where knight is currently  
   private byte currentColumn = 0;    // column number on the board table where knight is currently  
   
   private byte visitedPositionsCounter = 1;   // starting position initialized counter
   
   private String errorMessage = "";
   
   private byte [][] accessibility = new byte[ROWS][COLUMNS];   // to heuristic
   
   KnightsTour(byte startingRow, byte startingColumn) {
      resetTour(startingRow, startingColumn);
   }
   
   byte [][] getAccessibility () {
      return accessibility;
   }
   
   byte [][] getBoard () {
      return board;
   }
   
   static void validateRow(byte row) {
      if (row < 0 || row >= ROWS) {
         throw new IllegalArgumentException("row < 0 || row >= " + ROWS);
      }
   }
   
   static void validateColumn(byte column) {
      if (column < 0 || column >= COLUMNS) {
         throw new IllegalArgumentException("column < 0 || column >= " + COLUMNS);
      }
   }
   
   boolean validateMoveNumber(byte moveNumber) {
      if (moveNumber < 0 || moveNumber >= MAX_MOVES) {
         errorMessage = String.format("unrecognized move's number %d", moveNumber);
         return false;
      }
      
      return true;
   }
   
   boolean isMoveAvailable(byte moveNumber, byte row, byte column) {
      validateRow(row);
      validateColumn(column);
      
      if (false == validateMoveNumber(moveNumber)) {
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
         errorMessage = String.format("move to previous visited position: %d, %d", nextRow, nextColumn);
         return false;
      }
      
      return true;
   }
   
   byte getNumberOfPerformedMove() {
      updateAccessibility();
      
      byte result = -99;                        // incorrect data
      byte minAccessibility = Byte.MAX_VALUE;   // incorrect data
      int nextRow           = -1;               // incorrect data
      int nextColumn        = -1;               // incorrect data
      int checkedRow;
      int checkedColumn;
      
      for (byte move = 0; move < MAX_MOVES; move++) {
         if (true == isMoveAvailable(move, currentRow, currentColumn)) {
            checkedRow    = currentRow + vertical[move];
            checkedColumn = currentColumn + horizontal[move];
            if (accessibility[checkedRow][checkedColumn] < minAccessibility) {
               minAccessibility = accessibility[checkedRow][checkedColumn];
               nextRow    = checkedRow;
               nextColumn = checkedColumn;
               result = move;
            }
         }
      }
      
      if (minAccessibility != Byte.MAX_VALUE) {
         move((byte)nextRow, (byte)nextColumn);
      }
      
      return result;
   }
   
   private void move (byte row, byte column) {
      currentRow    = row;
      currentColumn = column;
      board[currentRow][currentColumn] = VISITED;
      visitedPositionsCounter++;
   }
   
   boolean move (byte moveNumber) {
      if (false == isMoveAvailable(moveNumber, currentRow, currentColumn)) {
         return false;
      }
      
      currentRow += vertical[moveNumber];
      currentColumn += horizontal[moveNumber];
      board[currentRow][currentColumn] = VISITED;
      visitedPositionsCounter++;
      
      return true;
   }
   
   public byte getCurrentRow() {
      return currentRow;
   }
   
   public byte getCurrentColumn() {
      return currentColumn;
   }
   
   public byte getVisitedPositionsCounter() {
      return visitedPositionsCounter;
   }
   
   public String getErrorMessage() {
      return errorMessage;
   }
   
   void clearErrorMessage() {
      errorMessage = "";
   }
   
   void resetTour(byte startingRow, byte startingColumn) {
      setValuesInArray(board, NO_TRACED);
      currentRow    = startingRow;
      currentColumn = startingColumn;
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
      
      for (byte row = 0; row < ROWS; row++) {
         for (byte column = 0; column < COLUMNS; column++) {
            value = 0;
            for (byte move = 0; move < MAX_MOVES; move++) {
               if (true == isMoveAvailable(move, row, column)) {
                  value++;
               }
            }
            accessibility[row][column] = value;
         }
      }
   }
   
} 
