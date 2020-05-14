/* =====================================================================================
 *       Filename:  EightQueens.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.25 - class of solving eight queens problem
                                on virtual chessboard by brute force algorithm
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class EightQueens {
   
   public static final byte ROWS = 8;
   public static final byte COLUMNS = 8;
   private byte [][] board = new byte[ROWS][COLUMNS];   // chessboard
   
   public static final byte MAX_QUEENS = 8;  
   
   public static final byte QUEEN = 89;  // value means that queen is in position on chessboard array
   public static final byte FREE = 77;  // value means that position on chessboard array is empty and is not attacked by queen
   public static final byte ATTACKED = 102;  /* value means that position on chessboard array is empty and is attacked by single or more queens
                           value (ATTACKED + queensCounter) means that position on chessboard array is empty and is attacked only by queen placed in order queensCounter
                           this distinction is for separate attacked position when queen was removed on chessboard (but other queens may remain) while brute force algorithm is 
                           running  */
   private byte queensCounter = 0;   // number of queens on chessboard
   
   private String errorMessage = "";
   
   EightQueens() {
      reset();
   }
   
   byte [][] getBoard () {
      return board;
   }
   
   byte getQueensCounter () {
      return queensCounter;
   }
   
   static void validateRow(byte row) {
      if (row < 0 || row >= ROWS) {
         throw new IllegalArgumentException(String.format(" row =  %d  ", row));
      }
   }
   
   static void validateColumn(byte column) {
      if (column < 0 || column >= COLUMNS) {
         throw new IllegalArgumentException(String.format(" column =  %d  ", column));
      }
   }
   
   private boolean isPlaceAvailable(byte row, byte column) {
      validateRow(row);
      validateColumn(column);
      
      if (QUEEN == board[row][column]) {
         errorMessage = String.format("move to other queen's position: %d, %d", row, column);
         return false;
      }
      
      if (FREE != board[row][column]) {
         errorMessage = String.format("move to other position attacked by other queen: %d, %d", row, column);
         return false;
      }
      
      return true;
   }
   
   public void place8Queens() { 
      byte startRow = 0;
      place8Queens(startRow);
   }
   
   private void place8Queens(byte row) { 
      validateRow(row);
      
      for (byte column = 0; column < COLUMNS; column++) {
         if (true == placeQueen(row, column)) {
            
            if (queensCounter == MAX_QUEENS) {
               System.out.println(EightQueensDescription.generateBoardString(this, true));
            }
            else {
               place8Queens((byte)(row + 1));   // attempt to place next queen in next row
            }
         
            removeQueen(row, column);
         }
         
      }

   }
   
   
   private boolean placeQueen (byte row, byte column) {
      if (false == isPlaceAvailable(row, column)) {
         return false;
      }
      
      board[row][column] = QUEEN;
      queensCounter++;
      setPositionsAttackedByQueen(row, column);
      
      return true;
   }
   
   private boolean removeQueen (byte quennRow, byte quennColumn) {
      if (board[quennRow][quennColumn] != QUEEN) {
         return false;
      }
      
      board[quennRow][quennColumn] = FREE;
      for (byte row = 0; row < ROWS; row++) {
         for (byte column = 0; column < COLUMNS; column++) {
            if (ATTACKED + queensCounter == board[row][column]) {  // remove positions attacked only by this queen
               board[row][column] = FREE;
            }
         }
      }
      queensCounter--;
      
      return true;
   }
   
   public String getErrorMessage() {
      return errorMessage;
   }
   
   void clearErrorMessage() {
      errorMessage = "";
   }
   
   void reset() {
      setValuesInArray(board, FREE);
      queensCounter = 0;
   }
   
   public static void setValuesInArray(byte[][] array, byte value) {
      for (int row = 0; row < array.length; row++) {
         for (int column = 0; column < array[row].length; column++) {
            array[row][column] = value;
         }
      }
   }
   
   private void setPositionsAttackedByQueen(byte queenRow, byte queenColumn) {
      validateRow(queenRow);
      validateColumn(queenColumn);
      
      for (byte rowCounter = 0; rowCounter < ROWS ; rowCounter++) {
         if (rowCounter != queenRow) {
            checkQueenOnPosition(rowCounter, queenColumn);  
            
            if (FREE == board[rowCounter][queenColumn]) {  // ATTACKED + queensCounter - set to attacked positions attacked only by this queen
               board[rowCounter][queenColumn] = (byte)(ATTACKED + queensCounter);
            }
         }
      }
      
      for (byte columnCounter = 0; columnCounter < COLUMNS ; columnCounter++) {
         if (columnCounter != queenColumn) {
            checkQueenOnPosition(queenRow, columnCounter); 
            
            if (FREE == board[queenRow][columnCounter]) {
               board[queenRow][columnCounter] = (byte)(ATTACKED + queensCounter);
            }
         }
      }

      for (int rowCounter = queenRow + 1, columnCounter = queenColumn + 1; 
                rowCounter < ROWS && columnCounter < COLUMNS; 
                rowCounter++, columnCounter++) {
         checkQueenOnPosition((byte)rowCounter, (byte)columnCounter);    
         
         if (FREE == board[rowCounter][columnCounter]) {
            board[rowCounter][columnCounter] = (byte)(ATTACKED + queensCounter);
         }
      }
      
      for (int rowCounter = queenRow - 1, columnCounter = queenColumn - 1; 
                rowCounter >= 0 && columnCounter >= 0; 
                rowCounter--, columnCounter--) {
         checkQueenOnPosition((byte)rowCounter, (byte)columnCounter); 
         
         if (FREE == board[rowCounter][columnCounter]) {
            board[rowCounter][columnCounter] = (byte)(ATTACKED + queensCounter);
         }
      }
      
      for (int rowCounter = queenRow + 1, columnCounter = queenColumn - 1; 
                rowCounter < ROWS && columnCounter >= 0; 
                rowCounter++, columnCounter--) {
         checkQueenOnPosition((byte)rowCounter, (byte)columnCounter); 
         
         if (FREE == board[rowCounter][columnCounter]) {
            board[rowCounter][columnCounter] = (byte)(ATTACKED + queensCounter);
         }
      }
      
      for (int rowCounter = queenRow - 1, columnCounter = queenColumn + 1; 
                rowCounter >= 0 && columnCounter < COLUMNS; 
                rowCounter--, columnCounter++) {
         checkQueenOnPosition((byte)rowCounter, (byte)columnCounter); 
         
         if (FREE == board[rowCounter][columnCounter]) {
            board[rowCounter][columnCounter] = (byte)(ATTACKED + queensCounter);
         }
      }
   }
   
   private void checkQueenOnPosition(byte row, byte column) {
      if (board[row][column] == QUEEN) {
         throw new IllegalArgumentException("Attempt to set queen's position as attacked by other queen");
      }
   }
   
} 
