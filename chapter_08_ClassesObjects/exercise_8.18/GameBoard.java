/* =====================================================================================
 *       Filename:  GameBoard.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.18 - class of board to tic tac toe game
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

enum MoveStatus { AFTER_GAME_OVER, OUT_OF_BOARD_ROW, OUT_OF_BOARD_COLUMN, OCCUPIED_POSITION, CORRECT }; 

enum CellValue { O, X, EMPTY };

public class GameBoard {
   private static final byte SQUARE_SIZE = 3;
   private static final byte NUMBER_OF_CELLS = SQUARE_SIZE * SQUARE_SIZE;
   
   private CellValue [][] board = new CellValue [SQUARE_SIZE][SQUARE_SIZE];
   
   public GameBoard() {
      for (int row = 0; row < SQUARE_SIZE; row++) {
         for (int column = 0; column < SQUARE_SIZE; column++) {
            board[row][column] = CellValue.EMPTY;
         }
      }
   }
   
   CellValue[][] getBoard() {
      return board;
   }
   
   public void restart() {
      for (int row = 0; row < SQUARE_SIZE; row++) {
         for (int column = 0; column < SQUARE_SIZE; column++) {
            board[row][column] = CellValue.EMPTY;
         }
      }
   }
   
   public static byte getNUMBER_OF_CELLS() {
      return NUMBER_OF_CELLS;
   }
   
   public static byte getSQUARE_SIZE() {
      return SQUARE_SIZE;
   }
   
   public static boolean validateRowOrColumn(byte number) {
      // public methods in class GameBoard assume that first index of array is 1
      if (number <= 0 || number > SQUARE_SIZE) {
         return false;
      }
      
      return true;
   }
   
   public MoveStatus validatePosition(byte row, byte column) {
      // public methods in class GameBoard assume that first index of array is 1
      if (false == validateRowOrColumn(row)) {
         return MoveStatus.OUT_OF_BOARD_ROW;
      }
      if (false == validateRowOrColumn(column)) {
         return MoveStatus.OUT_OF_BOARD_COLUMN;
      }
       
      // otherwise first index of board 2Darray is 0
      if (CellValue.EMPTY != board[row - 1][column - 1]) {
         return MoveStatus.OCCUPIED_POSITION;
      }
       
      return MoveStatus.CORRECT;
   }
   
   void markBoardCell(byte row, byte column, CellValue cellValue) {
      board[row][column] = cellValue;
   }
 
   void markBoardEmptyCell(CellValue cellValue) {
      for (int row = 0; row < SQUARE_SIZE; row++) {
         for (int column = 0; column < SQUARE_SIZE; column++) {
            if (CellValue.EMPTY == board[row][column]) {
               markBoardCell((byte)row, (byte)column, cellValue);
            }
         }
      }
   }
   
   CellValue getWinnerInRows() {
      CellValue cell;
      
      for (int row = 0; row < SQUARE_SIZE; row++) {
         cell = board[row][0];
         if (CellValue.EMPTY == cell) {
            continue;
         }

         int column = 1;
         for ( ; column < SQUARE_SIZE; column++) {
            if (cell != board[row][column]) {
               break;
            }
         }
         
         if (SQUARE_SIZE == column) {
            
            return cell;
         }
      }
      
      return CellValue.EMPTY;  // no winner
   }
   
   CellValue getWinnerInColumns() {
      CellValue cell;
      
      for (int column = 0; column < SQUARE_SIZE; column++) {
         cell = board[0][column];
         if (CellValue.EMPTY == cell) {
            continue;
         }

         int row = 1;
         for ( ; row < SQUARE_SIZE; row++) {
            if (cell != board[row][column]) {
               break;
            }
         }
         
         if (SQUARE_SIZE == row) {
            
            return cell;
         }
      }
      
      return CellValue.EMPTY;  // no winner
   }
   
   CellValue getWinnerInDiagonals() {
      CellValue cell = board[0][0];
      if (CellValue.EMPTY != cell) {
         int diagonalIndex = 1;
         for ( ; diagonalIndex < SQUARE_SIZE; diagonalIndex++) {
            if (cell != board[diagonalIndex][diagonalIndex]) {
               break;
            }
         }
         if (SQUARE_SIZE == diagonalIndex) {
            
            return cell;
         }
      }
      
      cell = board[SQUARE_SIZE - 1][0];
      if (CellValue.EMPTY != cell) {
         int row = SQUARE_SIZE - 1;
         for (int column = 0; row > -1; row--, column++) {
            if (cell != board[row][column]) {
               break;
            }
         }
         if (-1 == row) {
            
            return cell;
         }
      } 
      
      return CellValue.EMPTY;  // no winner
   }
   
   // return number of possible victory chances on all empty positions in board
   // for player with cellValue marked on board
   int calculateVictoryChance(CellValue cellValue) {
      if (cellValue == CellValue.EMPTY) {
         assert(false) : "cellValue can not be EMPTY";
         return 0;
      }
      
      int victoryChancesCounter = 0;
      for (int row = 0; row < SQUARE_SIZE; row++) {
         for (int column = 0; column < SQUARE_SIZE; column++) {
            if (CellValue.EMPTY == board[row][column]) {
               if (true == isVictoryChanceOnPosition(row, column, cellValue)) {
                  victoryChancesCounter++;
               }
            }
         }
      }
      
      return victoryChancesCounter;
   }
   

   boolean areAdjacentCells(CellValue cellValue, final int NUMBER_OF_CELLS) {
      
      for (int row = 0; row < SQUARE_SIZE; row++) {
         for (int column = 0; column < SQUARE_SIZE; column++) {
            if (cellValue == board[row][column]) {
               
               if (NUMBER_OF_CELLS == calculateNumberOfCellValueInRow(row, CellValue.EMPTY) 
                   && true == areAdjacentCellsInRow(row, CellValue.EMPTY)) {
                     
                  return true;
               }
               if (NUMBER_OF_CELLS == calculateNumberOfCellValueInColumn(column, CellValue.EMPTY)
                   && true == areAdjacentCellsInColumn(column, CellValue.EMPTY)) {
                     
                  return true;
               }
               if (true == existInUpperLeftDiagonal(row, column)) {
                  if (NUMBER_OF_CELLS == calculateNumberOfCellValueInUpperLeftDiagonal(CellValue.EMPTY)
                      && true == areAdjacentCellsInUpperLeftDiagonal(CellValue.EMPTY)) {
                        
                     return true;
                  }
               }
               if (true == existInLowerLeftDiagonal(row, column)) {
                  if (NUMBER_OF_CELLS == calculateNumberOfCellValueInLowerLeftDiagonal(CellValue.EMPTY)
                      && true == areAdjacentCellsInLowerLeftDiagonal(CellValue.EMPTY)) {
                         
                     return true;
                  }
               }
            }
         }
      }
      
      return false;
   }
   
   private boolean areAdjacentCellsInRow(final int ROW, CellValue cellValue) {
      int identicalCellsCounter = 0;
      
      for (int column = 0; column < SQUARE_SIZE; column++) {
         if (cellValue == board[ROW][column]) {
            identicalCellsCounter++;
         }
         else if (identicalCellsCounter == 1) { 
            identicalCellsCounter = 0;
         }
      }
      
      if (identicalCellsCounter < 2) {
         return false;
      }
      
      return true;
   }
   
   private boolean areAdjacentCellsInColumn(final int COLUMN, CellValue cellValue) {
      int identicalCellsCounter = 0;
      
      for (int row = 0; row < SQUARE_SIZE; row++) {
         if (cellValue == board[row][COLUMN]) {
            identicalCellsCounter++;
         }
         else if (identicalCellsCounter == 1) { 
            identicalCellsCounter = 0;
         }
      }
      
      if (identicalCellsCounter < 2) {
         return false;
      }
      
      return true;
   }
   
   private boolean areAdjacentCellsInUpperLeftDiagonal(CellValue cellValue) {
      int identicalCellsCounter = 0;
      
      for (int index = 0; index < SQUARE_SIZE; index++) {
         if (cellValue == board[index][index]) {
            identicalCellsCounter++;
         }
         else if (identicalCellsCounter == 1) { 
            identicalCellsCounter = 0;
         }
      }
      
      if (identicalCellsCounter < 2) {
         return false;
      }
      
      return true;
   }
   
   private boolean areAdjacentCellsInLowerLeftDiagonal(CellValue cellValue) {
      int identicalCellsCounter = 0;
      
      for (int column = 0, row = SQUARE_SIZE - 1; column < SQUARE_SIZE; column++, row--) {
         if (cellValue == board[row][column]) {
            identicalCellsCounter++;
         }
         else if (identicalCellsCounter == 1) { 
            identicalCellsCounter = 0;
         }
      }
      
      if (identicalCellsCounter < 2) {
         return false;
      }
      
      return true;
   }
   
   private boolean isVictoryChanceOnPosition(final int ROW, final int COLUMN, CellValue cellValue) {
      if (cellValue == CellValue.EMPTY) {
         assert(false) : "cellValue can not be EMPTY";
         return false;
      }

      if (true == isAlmostWinInRow(ROW, cellValue)) {
         return true;
      }
      if (true == isAlmostWinInColumn(COLUMN, cellValue)) {
         return true;
      }
      if (true == existInUpperLeftDiagonal(ROW, COLUMN)) {
         if (true == isAlmostWinInUpperLeftDiagonal(cellValue)) {
            return true;
         }
      }
      if (true == existInLowerLeftDiagonal(ROW, COLUMN)) {
         if (true == isAlmostWinInLowerLeftDiagonal(cellValue)) {
            return true;
         }
      }
      
      return false;
   }
   
   private boolean isAlmostWinInRow(final int ROW, CellValue cellValue) {
      int numberOfCellsOccupiedByCellValue = calculateNumberOfCellValueInRow(ROW, cellValue);
      
      if (TicTacToe.NUMBER_OF_CELLS_TO_ALMOST_WIN == numberOfCellsOccupiedByCellValue) {
         if (1 == calculateNumberOfCellValueInRow(ROW, CellValue.EMPTY)) {
            
            return true;
         }
      }
      
      return false;
   }
   
   private boolean isAlmostWinInColumn(final int COLUMN, CellValue cellValue) {
      int numberOfCellsOccupiedByCellValue = calculateNumberOfCellValueInColumn(COLUMN, cellValue);
      
      if (TicTacToe.NUMBER_OF_CELLS_TO_ALMOST_WIN == numberOfCellsOccupiedByCellValue) {
         if (1 == calculateNumberOfCellValueInColumn(COLUMN, CellValue.EMPTY)) {
            
            return true;
         }
      }
      
      return false;
   }
   
   private boolean isAlmostWinInUpperLeftDiagonal(CellValue cellValue) {
      int numberOfCellsOccupiedByCellValue = calculateNumberOfCellValueInUpperLeftDiagonal(cellValue);
      
      if (TicTacToe.NUMBER_OF_CELLS_TO_ALMOST_WIN == numberOfCellsOccupiedByCellValue) {
         if (1 == calculateNumberOfCellValueInUpperLeftDiagonal(CellValue.EMPTY)) {
            
            return true;
         }
      }
      
      return false;
   }
   
   private boolean isAlmostWinInLowerLeftDiagonal(CellValue cellValue) {
      int numberOfCellsOccupiedByCellValue = calculateNumberOfCellValueInLowerLeftDiagonal(cellValue);
      
      if (TicTacToe.NUMBER_OF_CELLS_TO_ALMOST_WIN == numberOfCellsOccupiedByCellValue) {
         if (1 == calculateNumberOfCellValueInLowerLeftDiagonal(CellValue.EMPTY)) {
            
            return true;
         }
      }
      
      return false;
   }
   
   private CellValue getOtherPlayerCellValue(CellValue cell) {
      switch (cell) {
         case O:
            return CellValue.X;
         case X:
            return CellValue.O;
            
         case EMPTY:
         default:
            assert(false) : "CellValue's enum of' " + cell + " should not be here as argument";
            throw new IllegalArgumentException("CellValue's enum of' " + cell);
      }
   }
   
   private int calculateNumberOfCellValueInRow(final int ROW, CellValue cellValue) {
      int counter = 0;
      for (int column = 0; column < SQUARE_SIZE; column++) {
         if (cellValue == board[ROW][column]) {
            counter++;
         }
      }
      
      return counter;
   }
   
   private int calculateNumberOfCellValueInColumn(final int COLUMN, CellValue cellValue) {
      int counter = 0;
      for (int row = 0; row < SQUARE_SIZE; row++) {
         if (cellValue == board[row][COLUMN]) {
            counter++;
         }
      }
      
      return counter;
   }
   
   private int calculateNumberOfCellValueInUpperLeftDiagonal(CellValue cellValue) {
      int counter = 0;
      for (int index = 0; index < SQUARE_SIZE; index++) {
         if (cellValue == board[index][index]) {
            counter++;
         }
      }
      
      return counter;
   }
   
   private int calculateNumberOfCellValueInLowerLeftDiagonal(CellValue cellValue) {
      int counter = 0;
      for (int column = 0, row = SQUARE_SIZE - 1; column < SQUARE_SIZE; column++, row--) {
         if (cellValue == board[row][column]) {
            counter++;
         }
      }
      
      return counter;
   }
   
   private boolean existInUpperLeftDiagonal(int row, int column) {
      if (row == column) {
         return true;
      }
      
      return false;
   }
   
   private boolean existInLowerLeftDiagonal(int row, int column) {
      if (row == SQUARE_SIZE - 1 - column) {
         return true;
      }
      
      return false;
   }
} 
