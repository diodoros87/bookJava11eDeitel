/* =====================================================================================
 *       Filename:  EightQueens.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.24 - class of solving eight queens problem
                                on virtual chessboard by eliminate positions by
                                    heuristic algorithm
                           
                             
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
   
   public static final byte QUEEN = 101;  // value means that queen is in position on chessboard array
   public static final byte FREE = 77;  // value means that position on chessboard array is empty and is not attacked by queen
   public static final byte ATTACKED = 102;  // value means that position on chessboard array is empty and is attacked by queen
   
   public static final byte TEMPORARY_QUEEN = 99;  // value means that temporary queen is in position on chessboard array
   public static final byte TEMPORARY_ATTACKED = 98;  // value means that position on chessboard array is empty and is attacked by temporary queen
   
   private byte queensCounter = 0;   // number of queens on chessboard
   
   private String errorMessage = "";
   
   private byte [][] positionsElimination = new byte[ROWS][COLUMNS];   // to heuristic algorithm
   
   EightQueens() {
      reset();
   }
   
   byte [][] getPositionsElimination () {
      return positionsElimination;
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
      
      if (ATTACKED == board[row][column]) {
         errorMessage = String.format("move to other position attacked by other queen: %d, %d", row, column);
         return false;
      }
      
      if (TEMPORARY_QUEEN == board[row][column]) {
         errorMessage = String.format("move to other temporary queen's position: %d, %d", row, column);
         return false;
      }
      
      if (TEMPORARY_ATTACKED == board[row][column]) {
         errorMessage = String.format("move to other position attacked by other temporary queen: %d, %d", row, column);
         return false;
      }
      
      return true;
   }
    
   void selectPositionsByMinimalElimination() {
      byte minPositionsElimination  = Byte.MAX_VALUE;   // set to incorrect data at start
      byte placementRow    = Byte.MIN_VALUE;     // set to incorrect data at start
      byte placementColumn = Byte.MIN_VALUE;  // set to incorrect data at start
      
      for (byte row = 0; row < ROWS; row++) {
         for (byte column = 0; column < COLUMNS; column++) {
            if (FREE == board[row][column]) {
               
               if (positionsElimination[row][column] < minPositionsElimination) {
                  placementRow = row;
                  placementColumn = column;
                  minPositionsElimination = positionsElimination[row][column];
               }
               else if (positionsElimination[row][column] == minPositionsElimination) {
                  byte placementMin = getMinimalPositionsElimination(placementRow, placementColumn);
                  byte min = getMinimalPositionsElimination(row, column);
                  
                  if (placementMin > min) {
                     placementRow = row;
                     placementColumn = column;
                  }
                  else if (placementMin == min) {
                     placementMin = getMinimalPositionsSecondElimination(placementRow, placementColumn, false);
                     min = getMinimalPositionsSecondElimination(row, column, false);
                     if (placementMin >= min) {   // my implementation of algorithm does not work correctly for condition: if (placementMin > min)
                        placementRow = row;
                        placementColumn = column;
                     }
                  }
               }
                
            }
         }
      }
      
      placeQueen(placementRow, placementColumn);
   }

   void placeQueens() {
      for (byte row = 0; row < ROWS; row++) {
         selectPositionsByMinimalElimination();
      }
   }
   
   
   private boolean placeQueen (byte row, byte column) {
      if (false == isPlaceAvailable(row, column)) {
         return false;
      }
      
      board[row][column] = QUEEN;
      setPositionsAttackedByQueen(row, column, false);
      queensCounter++;
      calculatePositionsElimination();
      
      return true;
   }
   
   private boolean removeQueen (byte quennRow, byte quennColumn) {
      
      board[quennRow][quennColumn] = FREE;
      for (byte row = 0; row < ROWS; row++) {
         for (byte column = 0; column < COLUMNS; column++) {
            if (TEMPORARY_ATTACKED == board[row][column]) {
               board[row][column] = FREE;
            }
         }
      }
      queensCounter--;
      calculatePositionsElimination();
      
      return true;
   }
   
   private byte getMinimalPositionsElimination(byte quennRow, byte quennColumn) {
      if (false == placeQueenTemporary(quennRow, quennColumn)) {  //  place temporary queen
         return Byte.MAX_VALUE;  // return incorrect data
      }
      
      byte minPositionsElimination  = Byte.MAX_VALUE;   // set to incorrect data at start
      
      for (byte row = 0; row < ROWS; row++) {
         for (byte column = 0; column < COLUMNS; column++) {
            if (FREE == board[row][column]) {
               
               if (positionsElimination[row][column] < minPositionsElimination) {
                  minPositionsElimination = positionsElimination[row][column];
               }
                
            }
         }
      }
      
      if (false == removeQueen(quennRow, quennColumn)) {  // remove temporary queen
         return Byte.MAX_VALUE;  // return incorrect data
      }
      
      return minPositionsElimination;
   }
   
   private byte getMinimalPositionsSecondElimination(byte quennRow, byte quennColumn, boolean secondTemporaryQueen) {
      if (false == placeQueenTemporary(quennRow, quennColumn)) {  //  place (first or second) temporary queen
         return Byte.MAX_VALUE;  // return incorrect data
      }
      
      byte minPositionsElimination  = Byte.MAX_VALUE;   // set to incorrect data at start
      byte secondRow    = Byte.MIN_VALUE;               // set to incorrect data at start
      byte secondColumn = Byte.MIN_VALUE;               // set to incorrect data at start
      
      for (byte row = 0; row < ROWS; row++) {
         for (byte column = 0; column < COLUMNS; column++) {
            if (FREE == board[row][column]) {
               
               if (positionsElimination[row][column] < minPositionsElimination) {
                  minPositionsElimination = positionsElimination[row][column];
                  secondRow = row;
                  secondColumn = column;
               }
                
            }
         }
      }
      
      if (true == secondTemporaryQueen) {
         return minPositionsElimination;   // end of method for second temporary queen
      }
      
      minPositionsElimination = getMinimalPositionsSecondElimination(secondRow, secondColumn, true);
      
      if (false == removeQueen(secondRow, secondColumn)) {  // remove second temporary queen
         return Byte.MAX_VALUE;  // return incorrect data
      }
      if (false == removeQueen(quennRow, quennColumn)) {  // remove first temporary queen
         return Byte.MAX_VALUE;  // return incorrect data
      }
      
      return minPositionsElimination;
   }
   
   private boolean placeQueenTemporary (byte row, byte column) {
      if (false == isPlaceAvailable(row, column)) {
         return false;
      }
      
      board[row][column] = TEMPORARY_QUEEN;
      setPositionsAttackedByQueen(row, column, true);
      queensCounter++;
      calculatePositionsElimination();
      
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
      calculatePositionsElimination();
   }
   
   public static void setValuesInArray(byte[][] array, byte value) {
      for (int row = 0; row < array.length; row++) {
         for (int column = 0; column < array[row].length; column++) {
            array[row][column] = value;
         }
      }
   }
   
   void calculatePositionsElimination() {
      byte row;
      byte column;
      
      for (row = 0; row < ROWS; row++) {
         for (column = 0; column < COLUMNS; column++) {
            if (FREE == board[row][column]) {  // elimination values are calculated only for free positions
               positionsElimination[row][column] = getEliminationValue(row, column);
            }
         }
      }
   }
   
   private byte getEliminationValue(byte row, byte column) {
      byte value = -1;   // set to -1 to consume duplicate increment the same (free) position during incrementing in rows and columns
                         
      for (byte rowCounter = 0; rowCounter < ROWS ; rowCounter++) {
         if (FREE == board[rowCounter][column]) {
            value++;
         }
      }
      
      for (byte columnCounter = 0; columnCounter < COLUMNS ; columnCounter++) {
         if (FREE == board[row][columnCounter]) {
            value++;
         }
      }

      for (int rowCounter = row + 1, columnCounter = column + 1; 
                rowCounter < ROWS && columnCounter < COLUMNS; 
                rowCounter++, columnCounter++) {
         if (FREE == board[rowCounter][columnCounter]) {
            value++;
         }
      }
      
      for (int rowCounter = row - 1, columnCounter = column - 1; 
                rowCounter >= 0 && columnCounter >= 0; 
                rowCounter--, columnCounter--) {
         if (FREE == board[rowCounter][columnCounter]) {
            value++;
         }
      }
      
      for (int rowCounter = row + 1, columnCounter = column - 1; 
                rowCounter < ROWS && columnCounter >= 0; 
                rowCounter++, columnCounter--) {
         if (FREE == board[rowCounter][columnCounter]) {
            value++;
         }
      }
      
      for (int rowCounter = row - 1, columnCounter = column + 1; 
                rowCounter >= 0 && columnCounter < COLUMNS; 
                rowCounter--, columnCounter++) {
         if (FREE == board[rowCounter][columnCounter]) {
            value++;
         }
      }
      
      return value;
   }
   
   private void setPositionsAttackedByQueen(byte queenRow, byte queenColumn, boolean temporaryQueen) {
      validateRow(queenRow);
      validateColumn(queenColumn);
      
      byte value = true == temporaryQueen ? TEMPORARY_ATTACKED : ATTACKED;
      
      for (byte rowCounter = 0; rowCounter < ROWS ; rowCounter++) {
         if (rowCounter != queenRow) {
            checkQueenOnPosition(rowCounter, queenColumn);  
            
            if (FREE == board[rowCounter][queenColumn]) {
               board[rowCounter][queenColumn] = value;
            }
         }
      }
      
      for (byte columnCounter = 0; columnCounter < COLUMNS ; columnCounter++) {
         if (columnCounter != queenColumn) {
            checkQueenOnPosition(queenRow, columnCounter); 
            
            if (FREE == board[queenRow][columnCounter]) {
               board[queenRow][columnCounter] = value;
            }
         }
      }

      for (int rowCounter = queenRow + 1, columnCounter = queenColumn + 1; 
                rowCounter < ROWS && columnCounter < COLUMNS; 
                rowCounter++, columnCounter++) {
         checkQueenOnPosition((byte)rowCounter, (byte)columnCounter);    
         
         if (FREE == board[rowCounter][columnCounter]) {
            board[rowCounter][columnCounter] = value;
         }
      }
      
      for (int rowCounter = queenRow - 1, columnCounter = queenColumn - 1; 
                rowCounter >= 0 && columnCounter >= 0; 
                rowCounter--, columnCounter--) {
         checkQueenOnPosition((byte)rowCounter, (byte)columnCounter); 
         
         if (FREE == board[rowCounter][columnCounter]) {
            board[rowCounter][columnCounter] = value;
         }
      }
      
      for (int rowCounter = queenRow + 1, columnCounter = queenColumn - 1; 
                rowCounter < ROWS && columnCounter >= 0; 
                rowCounter++, columnCounter--) {
         checkQueenOnPosition((byte)rowCounter, (byte)columnCounter); 
         
         if (FREE == board[rowCounter][columnCounter]) {
            board[rowCounter][columnCounter] = value;
         }
      }
      
      for (int rowCounter = queenRow - 1, columnCounter = queenColumn + 1; 
                rowCounter >= 0 && columnCounter < COLUMNS; 
                rowCounter--, columnCounter++) {
         checkQueenOnPosition((byte)rowCounter, (byte)columnCounter); 
         
         if (FREE == board[rowCounter][columnCounter]) {
            board[rowCounter][columnCounter] = value;
         }
      }
   }
   
   private void checkQueenOnPosition(byte row, byte column) {
      if (board[row][column] == QUEEN) {
         throw new IllegalArgumentException("Attempt to set queen's position as attacked by other queen");
      }
   }
   
} 
