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
import java.util.logging.Logger;

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
   private static final byte MIN_TURN_TO_VICTORY = 2 * SQUARE_SIZE - 1;  // numberOfPlayers = 2
   private static final byte MIN_TURN_TO_DRAW    = NUMBER_OF_CELLS - SQUARE_SIZE;
   
   private GameStatus gameStatus = GameStatus.CONTINUE;
   public static Logger globalLogger = Logger.getGlobal();
   
   private int NUMBER_OF_CELLS_TO_ALMOST_WIN = SQUARE_SIZE - 1;
   
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
                                       
         if (NUMBER_OF_CELLS - 1 == turn && gameStatus == GameStatus.CONTINUE) {
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
      boolean gameOver = isWinnerInRows();
      
      if (false == gameOver) {
         gameOver = isWinnerInColumns();
      } 
      if (false == gameOver) {
         gameOver = isWinnerInDiagonals();
      }
      if (false == gameOver && turn >= MIN_TURN_TO_DRAW) {
         if (NUMBER_OF_CELLS == turn) {
            this.gameStatus = GameStatus.DRAW;
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
      int secondPlayerVictoryChances = calculateVictoryChance(CellValue.O);
      
      globalLogger.info("Victory chances: firstPlayer  = " + firstPlayerVictoryChances
                     +  "                 secondPlayer = " + secondPlayerVictoryChances);
      
      if (firstPlayerVictoryChances == 0 && secondPlayerVictoryChances == 0) {
         if (MIN_TURN_TO_DRAW < turn || 
            (MIN_TURN_TO_DRAW == turn && false == areAdjacentCells(CellValue.EMPTY, 2))) {
         
            this.gameStatus = GameStatus.DRAW;
         }
      }
      else {
         checkEarlyGameVictory();
      }
   }
   
   private void checkEarlyGameVictory() {
      int firstPlayerVictoryChances  = calculateVictoryChance(CellValue.X);
      int firstPlayerRemainedMoves   = getRemainedMoves(CellValue.X);
      int secondPlayerVictoryChances = calculateVictoryChance(CellValue.O);
      int secondPlayerRemainedMoves  = getRemainedMoves(CellValue.O);
      
      globalLogger.info("Victory chances: firstPlayer  = " + firstPlayerVictoryChances
                     +  "                 secondPlayer = " + secondPlayerVictoryChances);
                     
      globalLogger.info("Remained moves:  firstPlayer  = " + firstPlayerRemainedMoves
                     +  "                 secondPlayer = " + secondPlayerRemainedMoves);
      
      boolean afterFirstPlayerMove = turn % 2 == 1;
      int remainedTurns = NUMBER_OF_CELLS - turn;
      if (true == afterFirstPlayerMove) {
         if (secondPlayerVictoryChances >= remainedTurns || 
            (secondPlayerVictoryChances > firstPlayerRemainedMoves && 0 == firstPlayerVictoryChances)) { // delete this if when SQUARE_SIZE > 3
               
            this.gameStatus = GameStatus.SECOND_PLAYER_WIN;
         }
         else if (firstPlayerVictoryChances >= remainedTurns && 0 == secondPlayerVictoryChances) {
            
            this.gameStatus = GameStatus.FIRST_PLAYER_WIN;
         }
      }
      else {   // if (false == afterFirstPlayerMove) {
         if (firstPlayerVictoryChances >= remainedTurns ||
            (firstPlayerVictoryChances > secondPlayerRemainedMoves && 0 == secondPlayerVictoryChances)) { // delete this if when SQUARE_SIZE > 3
         
            this.gameStatus = GameStatus.FIRST_PLAYER_WIN;
         }
         else if (secondPlayerVictoryChances >= remainedTurns && 0 == firstPlayerVictoryChances) {
            this.gameStatus = GameStatus.SECOND_PLAYER_WIN;
         }
         //assert (secondPlayerFactualVictoryMoves <= 0);
         /*
         if (false == afterFirstPlayerMove || (true == afterFirstPlayerMove && 0 == secondPlayerVictoryChances)) {
            this.gameStatus = GameStatus.FIRST_PLAYER_WIN;
         }*/
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
      for (int row = 0; row < SQUARE_SIZE; row++) {
         for (int column = 0; column < SQUARE_SIZE; column++) {
            if (CellValue.EMPTY == board[row][column]) {
               markBoardCell((byte)row, (byte)column);
            }
         }
      }
   }
   
   private boolean isWinnerInRows() {
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
            setWinner(cell);
            return true;
         }
      }
      
      return false;
   }
   
   private boolean isWinnerInColumns() {
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
            setWinner(cell);
            return true;
         }
      }
      
      return false;
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
         if (SQUARE_SIZE == diagonalIndex) {
            setWinner(cell);
            return true;
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
   
   // return number of possible victory chances on all empty positions in board
   // for player with cellValue marked on board
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
   

   private boolean areAdjacentCells(CellValue cellValue, final int NUMBER_OF_CELLS) {
      
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
      
      //CellValue otherPlayerCellValue = getOtherPlayerCellValue(cellValue);
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
      
      if (NUMBER_OF_CELLS_TO_ALMOST_WIN == numberOfCellsOccupiedByCellValue) {
         if (1 == calculateNumberOfCellValueInRow(ROW, CellValue.EMPTY)) {
            
            return true;
         }
      }
      
      return false;
   }
   
   private boolean isAlmostWinInColumn(final int COLUMN, CellValue cellValue) {
      int numberOfCellsOccupiedByCellValue = calculateNumberOfCellValueInColumn(COLUMN, cellValue);
      
      if (NUMBER_OF_CELLS_TO_ALMOST_WIN == numberOfCellsOccupiedByCellValue) {
         if (1 == calculateNumberOfCellValueInColumn(COLUMN, CellValue.EMPTY)) {
            
            return true;
         }
      }
      
      return false;
   }
   
   private boolean isAlmostWinInUpperLeftDiagonal(CellValue cellValue) {
      int numberOfCellsOccupiedByCellValue = calculateNumberOfCellValueInUpperLeftDiagonal(cellValue);
      
      if (NUMBER_OF_CELLS_TO_ALMOST_WIN == numberOfCellsOccupiedByCellValue) {
         if (1 == calculateNumberOfCellValueInUpperLeftDiagonal(CellValue.EMPTY)) {
            
            return true;
         }
      }
      
      return false;
   }
   
   private boolean isAlmostWinInLowerLeftDiagonal(CellValue cellValue) {
      int numberOfCellsOccupiedByCellValue = calculateNumberOfCellValueInLowerLeftDiagonal(cellValue);
      
      if (NUMBER_OF_CELLS_TO_ALMOST_WIN == numberOfCellsOccupiedByCellValue) {
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
            gameStatus = GameStatus.FIRST_PLAYER_WIN;
            return CellValue.O;
            
         case EMPTY:
         default:
            assert(false) : "CellValue's enum of' " + cell + " should not be here as argument";
            throw new IllegalArgumentException("CellValue's enum of' " + cell);
      }
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
