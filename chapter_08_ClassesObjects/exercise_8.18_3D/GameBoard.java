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
 
import pairPackage.ByteIntegersPair;
 
import java.util.ArrayList;

enum MoveStatus { AFTER_GAME_OVER, OUT_OF_BOARD_LEVEL, OUT_OF_BOARD_ROW, OUT_OF_BOARD_COLUMN, OCCUPIED_POSITION, CORRECT }; 

enum CellValue { O, X, EMPTY };

public class GameBoard {
   private static final byte SQUARE_SIZE = 4;
   private static final byte NUMBER_OF_CELLS = SQUARE_SIZE * SQUARE_SIZE * SQUARE_SIZE;
   
   private CellValue [][][] board = new CellValue [SQUARE_SIZE][SQUARE_SIZE][SQUARE_SIZE];
   
   public GameBoard() {
      GameBoard.restart(board);
   }
   
   public GameBoard(CellValue [][][] board) {
      GameBoard.validateBoard(board);
      
      GameBoard.setBoardCells(this.board, board);
   }
   
   public static void validateBoard(CellValue [][][] board) {
      if (null == board) {
         throw new NullPointerException("board can not be null");
      }
      if (board.length != SQUARE_SIZE) {
         throw new IllegalArgumentException("board.length != SQUARE_SIZE");
      }
      
      for (byte level = 0; level < SQUARE_SIZE; level++) {
         if (board[level].length != SQUARE_SIZE) {
            throw new IllegalArgumentException(String.format("board[%d].length != SQUARE_SIZE", level));
         }
         
         for (byte row = 0; row < SQUARE_SIZE; row++) {
            if (board[level][row].length != SQUARE_SIZE) {
               throw new IllegalArgumentException(String.format("board[%d][%d].length != SQUARE_SIZE", level, row));
            }
         }
      }
   }
   
   private static void setBoardCells(CellValue [][][] destinationBoard, CellValue [][][] sourceBoard) {
      for (byte level = 0; level < SQUARE_SIZE; level++) {
         for (byte row = 0; row < SQUARE_SIZE; row++) {
            for (byte column = 0; column < SQUARE_SIZE; column++) {
               destinationBoard[level][row][column] = sourceBoard[level][row][column];
            }
         }
      }
   }
   
   private static void restart(CellValue [][][] board) {
      for (byte level = 0; level < SQUARE_SIZE; level++) {
         for (byte row = 0; row < SQUARE_SIZE; row++) {
            for (byte column = 0; column < SQUARE_SIZE; column++) {
               board[level][row][column] = CellValue.EMPTY;
            }
         }
      }
   }
   
   CellValue[][][] getBoard() {
      return board;
   }
   
   public void restart() {
      GameBoard.restart(board);
   }
   
   public static byte getNUMBER_OF_CELLS() {
      return NUMBER_OF_CELLS;
   }
   
   public static byte getSQUARE_SIZE() {
      return SQUARE_SIZE;
   }
   
   public static boolean validateLevelOrRowOrColumn(byte number) {
      // public methods in class GameBoard assume that first index of array is 1
      if (number <= 0 || number > SQUARE_SIZE) {
         return false;
      }
      
      return true;
   }
   
   public MoveStatus validatePosition(byte level, byte row, byte column) {
      // public methods in class GameBoard assume that first index of array is 1
      if (false == validateLevelOrRowOrColumn(level)) {
         return MoveStatus.OUT_OF_BOARD_LEVEL;
      }
      if (false == validateLevelOrRowOrColumn(row)) {
         return MoveStatus.OUT_OF_BOARD_ROW;
      }
      if (false == validateLevelOrRowOrColumn(column)) {
         return MoveStatus.OUT_OF_BOARD_COLUMN;
      }
       
      // otherwise first index of board 2Darray is 0
      if (CellValue.EMPTY != board[level - 1][row - 1][column - 1]) {
         return MoveStatus.OCCUPIED_POSITION;
      }
       
      return MoveStatus.CORRECT;
   }
   
   void markBoardCell(byte level, byte row, byte column, CellValue cellValue) {
      board[level][row][column] = cellValue;
   }
 
   void markBoardEmptyCell(CellValue cellValue) {
      for (byte level = 0; level < SQUARE_SIZE; level++) {
         for (byte row = 0; row < SQUARE_SIZE; row++) {
            for (byte column = 0; column < SQUARE_SIZE; column++) {
               
               if (CellValue.EMPTY == board[level][row][column]) {
                  markBoardCell(level, row, column, cellValue);
               }
            }
         }
      }
   }
   /*
   public ArrayList<ByteIntegersPair> getAllowedCellsCoordinations() {
      ArrayList<ByteIntegersPair> allowedCellsCoordinations = new ArrayList<ByteIntegersPair>();
      
      for (byte level = 0; level < SQUARE_SIZE; level++) {
         for (byte row = 0; row < SQUARE_SIZE; row++) {
            for (byte column = 0; column < SQUARE_SIZE; column++) {
               if (CellValue.EMPTY == board[row][column]) {
                  ByteIntegersPair coordination = new ByteIntegersPair(row, column);
                  
                  allowedCellsCoordinations.add(coordination);
               }
            }
         }
      }
      
      return allowedCellsCoordinations;
   }*/
   

   /*
   // return number of possible victory chances on all empty positions in board
   // for player with cellValue marked on board
   int calculateVictoryChance(CellValue cellValue) {
      if (cellValue == CellValue.EMPTY) {
         assert(false) : "cellValue can not be EMPTY";
         return 0;
      }
      
      int victoryChancesCounter = 0;
      for (byte row = 0; row < SQUARE_SIZE; row++) {
         for (byte column = 0; column < SQUARE_SIZE; column++) {
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
      
      for (byte row = 0; row < SQUARE_SIZE; row++) {
         for (byte column = 0; column < SQUARE_SIZE; column++) {
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
      byte identicalCellsCounter = 0;
      
      for (byte column = 0; column < SQUARE_SIZE; column++) {
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
      byte identicalCellsCounter = 0;
      
      for (byte row = 0; row < SQUARE_SIZE; row++) {
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
      byte identicalCellsCounter = 0;
      
      for (byte index = 0; index < SQUARE_SIZE; index++) {
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
      byte identicalCellsCounter = 0;
      
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
      byte numberOfCellsOccupiedByCellValue = calculateNumberOfCellValueInRow(ROW, cellValue);
      
      if (TicTacToe.NUMBER_OF_CELLS_TO_ALMOST_WIN == numberOfCellsOccupiedByCellValue) {
         if (1 == calculateNumberOfCellValueInRow(ROW, CellValue.EMPTY)) {
            
            return true;
         }
      }
      
      return false;
   }
   
   private boolean isAlmostWinInColumn(final byte COLUMN, CellValue cellValue) {
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
   
   private int calculateNumberOfCellValueInRow(final byte ROW, CellValue cellValue) {
      int counter = 0;
      for (int column = 0; column < SQUARE_SIZE; column++) {
         if (cellValue == board[ROW][column]) {
            counter++;
         }
      }
      
      return counter;
   }
   
   private int calculateNumberOfCellValueInColumn(final byte COLUMN, CellValue cellValue) {
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
   }*/
   
   CellValue getHorizontalWinner() {
      CellValue winner = HorizontalVictory.getWinnerInRows(this.board);
      
      if (CellValue.EMPTY == winner) {
         winner = HorizontalVictory.getWinnerInColumns(this.board);
      }
      if (CellValue.EMPTY == winner) {
         winner = HorizontalVictory.getWinnerInDiagonals(this.board);
      }
      
      return winner;
   }
   
   CellValue getVerticalWinner() {
      CellValue winner = VerticalVictory.getWinnerInLevels(this.board);
      
      if (CellValue.EMPTY == winner) {
         winner = VerticalVictory.getWinnerInDiagonalsByRows(this.board);
      }
      if (CellValue.EMPTY == winner) {
         winner = VerticalVictory.getWinnerInDiagonalsByColumns(this.board);
      }
      if (CellValue.EMPTY == winner) {
         winner = VerticalVictory.getWinnerInCubeDiagonals(this.board);
      }
      
      return winner;
   }
} 

class HorizontalVictory {
   private static final byte SQUARE_SIZE = GameBoard.getSQUARE_SIZE();
   
   static CellValue getWinnerInRows(CellValue[][][] board) {
      CellValue cell;
      
      for (byte level = 0; level < SQUARE_SIZE; level++) {
         for (byte row = 0; row < SQUARE_SIZE; row++) {
            
            cell = board[level][row][0];
            if (CellValue.EMPTY == cell) {
               continue;
            }

            byte column = 1;
            for ( ; column < SQUARE_SIZE; column++) {
               if (cell != board[level][row][column]) {
                  break;
               }
            }
            
            if (SQUARE_SIZE == column) {
               
               return cell;
            }
         }
      }
      
      return CellValue.EMPTY;  // no winner
   }
   
   static CellValue getWinnerInColumns(CellValue[][][] board) {
      CellValue cell;
      
      for (byte level = 0; level < SQUARE_SIZE; level++) {
         for (byte column = 0; column < SQUARE_SIZE; column++) {
            cell = board[level][0][column];
            if (CellValue.EMPTY == cell) {
               continue;
            }

            byte row = 1;
            for ( ; row < SQUARE_SIZE; row++) {
               if (cell != board[level][row][column]) {
                  break;
               }
            }
            
            if (SQUARE_SIZE == row) {
               
               return cell;
            }
         }
      }
      
      return CellValue.EMPTY;  // no winner
   }
   
   static CellValue getWinnerInDiagonals(CellValue[][][] board) {
      for (byte level = 0; level < SQUARE_SIZE; level++) {
         
         CellValue cell = board[level][0][0];
         if (CellValue.EMPTY != cell) {
            byte row = 1;
            for (byte column = 1 ; row < SQUARE_SIZE; row++, column++) {
               if (cell != board[level][row][column]) {
                  break;
               }
            }
            if (SQUARE_SIZE == row) {
               
               return cell;
            }
         }
         
         cell = board[level][SQUARE_SIZE - 1][0];
         if (CellValue.EMPTY != cell) {
            int row = SQUARE_SIZE - 2;
            for (byte column = 1 ; row > -1; row--, column++) {
               if (cell != board[level][row][column]) {
                  break;
               }
            }
            if (-1 == row) {
               
               return cell;
            }
         } 
      }
      
      return CellValue.EMPTY;  // no winner
   }
}

class VerticalVictory {
   private static final byte SQUARE_SIZE = GameBoard.getSQUARE_SIZE();
   
   static CellValue getWinnerInLevels(CellValue[][][] board) {
      CellValue cell;
      
      for (byte row = 0; row < SQUARE_SIZE; row++) {
         for (byte column = 0; column < SQUARE_SIZE; column++) {
            
            cell = board[0][row][column];
            if (CellValue.EMPTY == cell) {
               continue;
            }

            byte level = 1;
            for ( ; level < SQUARE_SIZE; level++) {
               if (cell != board[level][row][column]) {
                  break;
               }
            }
            
            if (SQUARE_SIZE == level) {
               
               return cell;
            }
         }
      }
      
      return CellValue.EMPTY;  // no winner
   }
   
   static CellValue getWinnerInDiagonalsByRows(CellValue[][][] board) {
      for (byte row = 0; row < SQUARE_SIZE; row++) {
         
         CellValue cell = board[0][row][0];
         if (CellValue.EMPTY != cell) {
            byte level = 1;
            for (byte column = 1 ; level < SQUARE_SIZE; level++, column++) {
               if (cell != board[level][row][column]) {
                  break;
               }
            }
            if (SQUARE_SIZE == level) {
               
               return cell;
            }
         }
         
         cell = board[SQUARE_SIZE - 1][row][0];
         if (CellValue.EMPTY != cell) {
            int level = SQUARE_SIZE - 2;
            for (byte column = 1; level > -1; level--, column++) {
               if (cell != board[level][row][column]) {
                  break;
               }
            }
            if (-1 == level) {
               
               return cell;
            }
         } 
      }
      
      return CellValue.EMPTY;  // no winner
   }
   
   static CellValue getWinnerInDiagonalsByColumns(CellValue[][][] board) {
      for (byte column = 0; column < SQUARE_SIZE; column++) {
         
         CellValue cell = board[0][0][column];
         if (CellValue.EMPTY != cell) {
            byte level = 1;
            for (byte row = 1 ; level < SQUARE_SIZE; level++, row++) {
               if (cell != board[level][row][column]) {
                  break;
               }
            }
            if (SQUARE_SIZE == level) {
               
               return cell;
            }
         }
         
         cell = board[SQUARE_SIZE - 1][0][column];
         if (CellValue.EMPTY != cell) {
            int level = SQUARE_SIZE - 2;
            for (byte row = 1; level > -1; level--, row++) {
               if (cell != board[level][row][column]) {
                  break;
               }
            }
            if (-1 == level) {
               
               return cell;
            }
         } 
      }
      
      return CellValue.EMPTY;  // no winner
   }
   
   static CellValue getWinnerInCubeDiagonals(CellValue[][][] board) {   
      CellValue cell = board[0][0][0];
      if (CellValue.EMPTY != cell) {
         byte diagonalIndex = 1;
         for ( ; diagonalIndex < SQUARE_SIZE; diagonalIndex++) {
            if (cell != board[diagonalIndex][diagonalIndex][diagonalIndex]) {
               break;
            }
         }
         if (SQUARE_SIZE == diagonalIndex) {
            
            return cell;
         }
      }
      
      cell = board[0][SQUARE_SIZE - 1][0];
      if (CellValue.EMPTY != cell) {
         int row = SQUARE_SIZE - 2;
         for (int level = 1, column = 1; row > -1; level++, row--, column++) {
            if (cell != board[level][row][column]) {
               break;
            }
         }
         if (-1 == row) {
            
            return cell;
         }
      } 
      
      cell = board[0][0][SQUARE_SIZE - 1];
      if (CellValue.EMPTY != cell) {
         int column = SQUARE_SIZE - 2;
         for (int level = 1, row = 1; column > -1; level++, row++, column--) {
            if (cell != board[level][row][column]) {
               break;
            }
         }
         if (-1 == column) {
            
            return cell;
         }
      } 
      
      cell = board[0][SQUARE_SIZE - 1][SQUARE_SIZE - 1];
      if (CellValue.EMPTY != cell) {
         int column = SQUARE_SIZE - 2;
         int row = SQUARE_SIZE - 2;
         for (int level = 1; row > -1; level++, row--, column--) {
            if (cell != board[level][row][column]) {
               break;
            }
         }
         if (-1 == row) {
            
            return cell;
         }
      } 
      
      return CellValue.EMPTY;  // no winner
   }
}
