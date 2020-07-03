/* =====================================================================================
 *       Filename:  TicTacToe.java
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

enum MoveStatus { AFTER_GAME_OVER, OUT_OF_BOARD_ROW, OUT_OF_BOARD_COLUMN, OCCUPIED_POSITION, CORRECT };

enum GameStatus {
   CONTINUE("indecisive"),
   FIRST_PLAYER_WIN("first player win"),
   SECOND_PLAYER_WIN("second player win"),
   DRAW("draw");
   
   private String name;
   
   private GameStatus(String name) {
      this.name = name;
   }
   
   public String toString() {
      return name;
   }
} 

public class TicTacToe {
   enum CellValue {O, X, EMPTY};
   private static final byte SQUARE_SIZE = 3;
   private static final byte NUMBER_OF_CELLS = SQUARE_SIZE * SQUARE_SIZE;
   
   private CellValue [][] board = new CellValue [SQUARE_SIZE][SQUARE_SIZE];
   private byte turn = 0;
   private static final byte MIN_TURN_TO_VICTORY = 2 * SQUARE_SIZE - 1;
   private static final byte MIN_TURN_TO_DRAW    = NUMBER_OF_CELLS - SQUARE_SIZE;
   
   private GameStatus gameStatus = GameStatus.CONTINUE;
   
   public TicTacToe() {
      for (int row = 0; row < SQUARE_SIZE; row++) {
         for (int column = 0; column < SQUARE_SIZE; column++) {
            board[row][column] = CellValue.EMPTY;
         }
      }
      
      turn = 0;
      gameStatus = GameStatus.CONTINUE;
   }
   
   public GameStatus getGameStatus() {
      return gameStatus;
   }
   
   public boolean isGameOver() {
      return gameStatus != GameStatus.CONTINUE;
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
      
      turn = 0;
      gameStatus = GameStatus.CONTINUE;
   }
   
   public static byte getSQUARE_SIZE() {
      return SQUARE_SIZE;
   }
   
   public static boolean validateRowOrColumn(byte number) {
      // public methods in class TicTacToe assume that first index of array is 1
      if (number <= 0 || number > SQUARE_SIZE) {
         return false;
      }
      
      return true;
   }
   
   public MoveStatus validatePosition(byte row, byte column) {
      // public methods in class TicTacToe assume that first index of array is 1
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
   
   public MoveStatus move(byte row, byte column) {
      if (true == isGameOver()) {
         return MoveStatus.AFTER_GAME_OVER;
      }
      
      // public methods in class TicTacToe assume that first index of array is 1
      MoveStatus moveStatus = validatePosition(row, column);
      if (MoveStatus.CORRECT == moveStatus) {
         turn++;
         // otherwise private methods in class TicTacToe assume that first index of array is 0
         markBoardCell(--row, --column);  
                                       
         if (NUMBER_OF_CELLS - 1 == turn) {
            performLastMoveAutomatically();
         }
      } 
    
      return moveStatus;
   }
   
   private void markBoardCell(byte row, byte column) {
      if (1 == turn % 2) {
         board[row][column] = CellValue.X;
      } 
      else {
         board[row][column] = CellValue.O;
      }
      if (turn >= MIN_TURN_TO_VICTORY) {
         checkGameStatus();
      }
   }
   
   private void checkGameStatus() {
      boolean continueSearching = isWinnerInRows();
      
      if (true == continueSearching) {
         continueSearching = isWinnerInColumns();
      } 
      if (true == continueSearching) {
         continueSearching = isWinnerInDiagonals();
      }
      if (true == continueSearching && turn >= MIN_TURN_TO_DRAW) {
         if (NUMBER_OF_CELLS == turn) {
            gameStatus = GameStatus.DRAW;
         }
         else {
            checkEarlyGameTermination();
         }
      }
      
   }
   
   private void checkEarlyGameTermination() {
      if (MIN_TURN_TO_DRAW > turn) {
         return;
      }
      
      int firstPlayerVictoryChances  = calculateVictoryChance(CellValue.X);
      int firstPlayerRemainedMoves   = getRemainedMoves(CellValue.X);
      int secondPlayerVictoryChances = calculateVictoryChance(CellValue.O);
      int secondPlayerRemainedMoves  = getRemainedMoves(CellValue.O);
      
      if (firstPlayerVictoryChances == 0 && secondPlayerVictoryChances == 0) {
         assert (secondPlayerRemainedMoves <= 1);
         this.gameStatus = GameStatus.DRAW;
      }
      
      int firstPlayerFactualVictoryMoves  = firstPlayerVictoryChances  - secondPlayerRemainedMoves;
      int secondPlayerFactualVictoryMoves = secondPlayerVictoryChances - firstPlayerRemainedMoves;
      
      if (firstPlayerFactualVictoryMoves > 0) {
         assert (secondPlayerFactualVictoryMoves == 0);
         this.gameStatus = GameStatus.FIRST_PLAYER_WIN;
      }
      else if (secondPlayerFactualVictoryMoves > 0) {
         this.gameStatus = GameStatus.SECOND_PLAYER_WIN;
      }
      
   }
   /*
   private void checkDrawConditions() {
      int emptyCellsToFind = NUMBER_OF_CELLS - turn;
      
      for (int row = 0; row < SQUARE_SIZE; row++) {
         for (int column = 0; column < SQUARE_SIZE; column++) {
            if (CellValue.EMPTY == board[row][column]) {
               
               emptyCellsToFind--;
            }
         }
      }
   }*/
   
   private void performLastMoveAutomatically() {
      turn++;
      for (int row = 1; row <= SQUARE_SIZE; row++) {
         for (int column = 1; column <= SQUARE_SIZE; column++) {
            if (CellValue.EMPTY == board[row][column]) {
               markBoardCell((byte)row, (byte)column);
            }
         }
      }
   }
   
   private boolean isWinnerInRows() {
      CellValue cell;
      boolean continueSearching = true;
      
      for (int row = 0; row < SQUARE_SIZE && true == continueSearching; row++) {
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
            setWinner(cell);
            continueSearching = false;
         }
      }
      
      return continueSearching;
   }
   
   private boolean isWinnerInColumns() {
      CellValue cell;
      boolean continueSearching = true;
      
      for (int column = 0; column < SQUARE_SIZE && true == continueSearching; column++) {
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
            setWinner(cell);
            continueSearching = false;
         }
      }
      
      return continueSearching;
   }
   
   private boolean isWinnerInDiagonals() {
      CellValue cell = board[0][0];
      if (CellValue.EMPTY != cell) {
         int diagonalIndex = 1;
         for ( ; diagonalIndex < SQUARE_SIZE; diagonalIndex++) {
            if (cell != board[diagonalIndex][diagonalIndex]) {
               break;
            }
         }
         if (SQUARE_SIZE > diagonalIndex) {
            setWinner(cell);
            return true;
         }
      }
      
      cell = board[SQUARE_SIZE - 1][0];
      if (CellValue.EMPTY != cell) {
         int row = SQUARE_SIZE - 1;
         for (int column = 0; column < SQUARE_SIZE; row--, column++) {
            if (cell != board[row][column]) {
               break;
            }
         }
         if (0 == row) {
            setWinner(cell);
            return true;
         }
      } 
      
      return false;
   }
   /*
   private boolean isVictoryChanceInEmptyPosition(int row, int column) {
      CellValue cell;
      int winningChancesCounter = 0;
      int drawsCounter = 0;
      boolean winningChance = true;
      boolean isX = false;
      boolean is0 = false;
      
      for (int row = 0; row < SQUARE_SIZE; row++) {
         isX = isCellValueInRow(row, CellValue.X);
         is0 = isCellValueInRow(row, CellValue.O);
         
         if (true == isX && true == is0) {
            return false;
         }
         
         result = CONTINUE;
         for (int column = 1; column < SQUARE_SIZE; column++) {
            if (cell != board[row][column]) {
               if (EMPTY != board[row][column]) {
                  draws++;
                  result = DRAW;
               }
               break;
            }
         }
      }
      
      if (CONTINUE != result) {
         setWinner(cell);
      } 
      
      return true;
   }*/ 
   /*
   private int calculateNumberOfAvailableVictoryMoves (CellValue cellValue) {
      int availableVictoryMoves = 0;
      
      for (int rowOrColumn = 0; rowOrColumn < SQUARE_SIZE; rowOrColumn++) {
         if (SQUARE_SIZE - 1 == calculateNumberOfCellValueInRow(rowOrColumn, cellValue)) {
            if (1 == calculateNumberOfCellValueInRow(rowOrColumn, EMPTY)) {
               availableVictoryMoves++;
            }
         }
         
         if (SQUARE_SIZE - 1 == calculateNumberOfCellValueInColumn(rowOrColumn, cellValue)) {
            if (1 == calculateNumberOfCellValueInColumn(rowOrColumn, EMPTY)) {
               availableVictoryMoves++;
            }
         }
      }
      
      if (SQUARE_SIZE - 1 == calculateNumberOfCellValueInUpperLeftDiagonal(cellValue)) {
         if (1 == calculateNumberOfCellValueInUpperLeftDiagonal(EMPTY)) {
            availableVictoryMoves++;
         }
      }
      if (SQUARE_SIZE - 1 == calculateNumberOfCellValueInLowerLeftDiagonal(cellValue)) {
         if (1 == calculateNumberOfCellValueInLowerLeftDiagonal(EMPTY)) {
            availableVictoryMoves++;
         }
      }
      
      return availableVictoryMoves;
   }*/
   
   private int getRemainedMoves(CellValue cellValue) {
      if (cellValue == CellValue.EMPTY) {
         throw new IllegalArgumentException("cellValue " + cellValue);
      }
      
      int remainedMoves = (NUMBER_OF_CELLS - turn) / 2;
      
      if (NUMBER_OF_CELLS % 2 == 1 && CellValue.X == cellValue && turn % 2 == 0 ||
          NUMBER_OF_CELLS % 2 == 0 && CellValue.O == cellValue && turn % 2 == 1)  {
             
         remainedMoves++;
      } 
      
      return remainedMoves;
   }
   
   private int calculateVictoryChance(CellValue cellValue) {
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
   
   private boolean isVictoryChanceOnPosition(final int ROW, final int COLUMN, CellValue cellValue) {
      if (cellValue == CellValue.EMPTY) {
         assert(false) : "cellValue can not be EMPTY";
         return false;
      }
      
      if (SQUARE_SIZE - 1 == calculateNumberOfCellValueInRow(ROW, cellValue)) {
         return true;
      }
      if (SQUARE_SIZE - 1 == calculateNumberOfCellValueInColumn(COLUMN, cellValue)) {
         return true;
      }
      if (true == existInUpperLeftDiagonal(ROW, COLUMN)) {
         if (SQUARE_SIZE - 1 == calculateNumberOfCellValueInUpperLeftDiagonal(cellValue)) {
            return true;
         }
      }
      if (true == existInLowerLeftDiagonal(ROW, COLUMN)) {
         if (SQUARE_SIZE - 1 == calculateNumberOfCellValueInLowerLeftDiagonal(cellValue)) {
            return true;
         }
      }
      
      return false;
   }
   /*
   private boolean isVictoryChanceInEmptyPosition() {
      CellValue cell;
      int winningChancesCounter = 0;
      int drawsCounter = 0;
      boolean winningChance = true;
      boolean isX = false;
      boolean is0 = false;
      
      for (int row = 0; row < SQUARE_SIZE; row++) {
         isX = isCellValueInRow(row, CellValue.X);
         is0 = isCellValueInRow(row, CellValue.O);
         
         if (true == isX && true == is0) {
            return false;
         }
         
         result = CONTINUE;
         for (int column = 1; column < SQUARE_SIZE; column++) {
            if (cell != board[row][column]) {
               if (EMPTY != board[row][column]) {
                  draws++;
                  result = DRAW;
               }
               break;
            }
         }
      }
      
      return true;
   } */
   
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
   /*
   private boolean isCellValueInRow(final int ROW, CellValue cellValue) {
      for (int column = 0; column < SQUARE_SIZE; column++) {
         if (cellValue == board[ROW][column]) {
            return true;
         }
      }
      
      return false;
   }
   
   private boolean isCellValueInColumn(final int COLUMN, CellValue cellValue) {
      for (int row = 0; row < SQUARE_SIZE; row++) {
         if (cellValue == board[row][COLUMN]) {
            return true;
         }
      }
      
      return false;
   }*/
   /*
   private GameStatus checkDrawInColumns() {
      CellValue cell;
      boolean gameContinuation = true;
      
      for (int column = 0; column < SQUARE_SIZE && true == gameContinuation; column++) {
         cell = board[0][column];
         gameContinuation = false;
         
         for (int row = 1; row < SQUARE_SIZE; row++) {
            if (cell != board[row][column]) {
               gameContinuation = true;
               break;
            }
         }
      }
      
      if (false == gameContinuation) {
         setWinner(cell);
      } 
      
      return gameContinuation;
   }
   
   private GameStatus checkDrawInDiagonals() {
      CellValue cell;
      boolean gameContinuation = true;
      
      for (int column = 0; column < SQUARE_SIZE && true == gameContinuation; column++) {
         cell = board[0][column];
         gameContinuation = false;
         
         for (int row = 1; row < SQUARE_SIZE; row++) {
            if (cell != board[row][column]) {
               gameContinuation = true;
               break;
            }
         }
      }
      
      if (false == gameContinuation) {
         setWinner(cell);
      } 
      
      return gameContinuation;
   }*/
   
   private void setWinner(CellValue cell) {
      switch (cell) {
         case O:
            gameStatus = GameStatus.SECOND_PLAYER_WIN;
            break;
         case X:
            gameStatus = GameStatus.FIRST_PLAYER_WIN;
            break;
            
         case EMPTY:
         default:
            assert(false) : "CellValue's enum of' " + cell + " should not be here as argument";
            throw new IllegalArgumentException("CellValue's enum of' " + cell);
      }
   }
} 
