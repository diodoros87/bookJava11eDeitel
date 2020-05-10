/* =====================================================================================
 *       Filename:  KnightsTour.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.26 - class of finding knight's tour on virtual
                                chessboard by heuristic accessibility algorithm and
                                   checking tour was closed
                           
                             
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
      byte nextRow           = -1;               // incorrect data
      byte nextColumn        = -1;               // incorrect data
      byte checkedRow;
      byte checkedColumn;
      
      for (byte move = 0; move < MAX_MOVES; move++) {
         if (true == isMoveAvailable(move, this.currentRow, this.currentColumn)) {
            checkedRow    = (byte)(this.currentRow + vertical[move]);
            checkedColumn = (byte)(this.currentColumn + horizontal[move]);
            if (accessibility[checkedRow][checkedColumn] < minAccessibility) {
               minAccessibility = accessibility[checkedRow][checkedColumn];
               nextRow    = checkedRow;
               nextColumn = checkedColumn;
               result     = move;
            }
            else if (accessibility[checkedRow][checkedColumn] == minAccessibility) {
               if (getMinimalAccessibility(nextRow, nextColumn) > getMinimalAccessibility(checkedRow, checkedColumn)) {
                  nextRow    = checkedRow;
                  nextColumn = checkedColumn;
                  result     = move;
               }
            }
         }
      }
      
      if (minAccessibility != Byte.MAX_VALUE) {
         move(nextRow, nextColumn);
      }
      
      return result;
   }
   
   private byte getMinimalAccessibility(byte row, byte column) {
      byte minAccessibility = Byte.MAX_VALUE;   // incorrect data
      int checkedRow;
      int checkedColumn;
      
      for (byte move = 0; move < MAX_MOVES; move++) {
         if (true == isMoveAvailable(move, row, column)) {
            checkedRow    = row + vertical[move];
            checkedColumn = column + horizontal[move];
            if (accessibility[checkedRow][checkedColumn] < minAccessibility) {
               minAccessibility = accessibility[checkedRow][checkedColumn];
            }
         }
      }
      
      return minAccessibility;
   }
   
   
   private void move (byte row, byte column) {
      currentRow    = row;
      currentColumn = column;
      board[currentRow][currentColumn] = VISITED;
      visitedPositionsCounter++;
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
   
   public static boolean isMovePossible(byte fromRow, byte fromColumn, byte toRow, byte toColumn) {
      if (false == isRowPossible(fromRow) || false == isColumnPossible(fromColumn)) {
         return false;
      }
      if (false == isRowPossible(toRow) || false == isColumnPossible(toColumn)) {
         return false;
      }
      
      int nextRow;
      int nextColumn;
      
      for (byte moveNumber = 0; moveNumber < MAX_MOVES; moveNumber++) {
         nextRow    = fromRow + vertical[moveNumber];
         nextColumn = fromColumn + horizontal[moveNumber];
         if (true == isRowPossible((byte)nextRow) && true == isColumnPossible((byte)nextColumn)) {
            if (nextRow == toRow && nextColumn == toColumn) {
               return true;
            }
         }
      }
      
      return false;
   }
   
   public static boolean isRowPossible(byte row) {
      if (row >= 0 && row < ROWS) {
         return true;
      }
      
      return false;
   }
   
   public static boolean isColumnPossible(byte column) {
      if (column >= 0 && column < COLUMNS) {
         return true;
      }
      
      return false;
   }
   
} 
