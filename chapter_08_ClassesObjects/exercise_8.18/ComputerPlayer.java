/* =====================================================================================
 *       Filename:  ComputerPlayer.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.18 - class of computer player in
                                 tic tac toe game
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import pairPackage.ByteIntegersPair;

import java.util.ArrayList;

public class ComputerPlayer {
   
   private CellValue cellMarker;
   private String name;
   
   TicTacToeView view = new TicTacToeView();
   
   public ComputerPlayer() {
      cellMarker = CellValue.EMPTY;
      name = "No name";
   }  
   
   public ComputerPlayer(String name, CellValue cellMarker) {
      validateCellMarker(cellMarker);
      this.cellMarker = cellMarker;
      this.name = name;
   }
   
   public void setCellMarker(CellValue cellMarker) {
      validateCellMarker(cellMarker);
      this.cellMarker = cellMarker;
   }
   
   public CellValue getCellMarker() {
      return cellMarker;
   }
   
   public static void validateCellMarker(CellValue cellMarker) {
      if (cellMarker == CellValue.EMPTY) {
         throw new IllegalArgumentException("Can not set cell's marker as empty");
      }
   }
   /*
   private CellValue opponentCellMarker;
   private final byte SQUARE_SIZE;
   private final byte[][] GAME_OVER_COMBINATIONS;
   private byte[][] victoryCombinations;
   private byte[][] defeatCombinations;
   private byte[][] defeatChance  = getGameOverByPositionArray();
   
   public ComputerPlayer(CellValue cellMarker, byte squareSize) {
      validateCellMarker(cellMarker);
      this.cellMarker = cellMarker;
      this.opponentCellMarker = TicTacToe.getOtherPlayerCellValue(this.cellMarker);
      
      validateSquareSize(squareSize);
      this.SQUARE_SIZE = squareSize;
      GAME_OVER_COMBINATIONS = getGameOverCombinationsByPositionArray();
      
      this.victoryChance = new byte [SQUARE_SIZE][SQUARE_SIZE];
      this.defeatChance  = new byte [SQUARE_SIZE][SQUARE_SIZE];
   }
   
   public void setCellMarker(CellValue cellMarker) {
      validateCellMarker(cellMarker);
      this.cellMarker = cellMarker;
      this.opponentCellMarker = TicTacToe.getOtherPlayerCellValue(this.cellMarker);
   }
   
   public CellValue getCellMarker() {
      return cellMarker;
   }
   
   public static void validateCellMarker(CellValue cellMarker) {
      if (cellMarker == CellValue.EMPTY) {
         throw new IllegalArgumentException("Can not set cell's marker as empty");
      }
   }
   
   public static byte[][] getGameOverCombinationsByPositionArray() {
      byte[][] gameOverCombinationsArray = new byte [SQUARE_SIZE][SQUARE_SIZE];
      
      for (byte row = 0; row < SQUARE_SIZE; row++) {
         for (byte column = 0; column < SQUARE_SIZE; column++) {
            gameOverCombinationsArray[row][column] = getGameOverCombinationsByPosition(row, column);
         }
      }
      
      return gameOverCombinationsArray;
   }
   
   public static byte getGameOverCombinationsByPosition(int row, int column) {
      byte combinations = 2;
            
      boolean existOnDiagonal = GameBoard.existInUpperLeftDiagonal(row, column);
      if (true == existOnDiagonal) {
         combinations++;
      }
      existOnDiagonal = GameBoard.existInLowerLeftDiagonal(row, column);
      if (true == existOnDiagonal) {
         combinations++;
      }
      
      return combinations;
   }
   
   public byte getSquareSize() {
      return SQUARE_SIZE;
   }
   
   public static void validateSquareSize(byte SQUARE_SIZE) {
      if (0 >= SQUARE_SIZE) {
         throw new IllegalArgumentException("Requirement: 0 < SQUARE_SIZE");
      }
   }
   
   public move(final TicTacToe TIC_TAC_TOE) {
      if (SQUARE_SIZE != TicTacToe.getSQUARE_SIZE()) {
         throw new IllegalArgumentException("Input parameter Square size != instance's square size");
      }
      
      final GameBoard BOARD = TIC_TAC_TOE.getBoard();
      updateVictoryChance(BOARD);
      updateDefeatChance(BOARD);
   }
   
   private void updateChances(final GameBoard BOARD) {
      CellValue cell;
      for (byte row = 0; row < SQUARE_SIZE; row++) {
         for (byte column = 0; column < SQUARE_SIZE; column++) {
            cell = board[row][column];
            updateVictoryChance(row, column, cell);
            updateDefeatChance(row, column, cell);
         }
      }
   }
   
   private void updateVictoryChance(final byte ROW, final byte COLUMN, final CellValue cellMarker) {
      int victoryChance = 0;
      
      if (this.cellMarker == cellMarker) {
         victoryChance++;
      }
      
      
      for (int row = 0; row < SQUARE_SIZE; row++) {
         for (int column = 0; column < SQUARE_SIZE; column++) {
            
            switch(board[row][column]){
               
                = CellValue.EMPTY;
            }
         }
      }
   }
   
   private void updateDefeatChance(final GameBoard BOARD) {
      
   }
   
   
   public static ByteIntegersPair getBoardCellCoordination(final TicTacToe TIC_TAC_TOE) {
      final GameBoard BOARD = TIC_TAC_TOE.getBoard();

   }*/
   
   public ByteIntegersPair move(final TicTacToe TIC_TAC_TOE) {
      
      byte row    = -1;
      byte column = -1;
      ArrayList<ByteIntegersPair> allowedCellsCoordinations = TIC_TAC_TOE.getAllowedCellsCoordinations();
      int maxDepth = TicTacToe.NUMBER_OF_CELLS - TIC_TAC_TOE.getTurn();
      int maximum = Integer.MIN_VALUE;
      int maxValueIndex = Integer.MIN_VALUE;
      ByteIntegersPair coordinations = null;
      
      for (int index = 0; index < allowedCellsCoordinations.size(); index++) {
         coordinations = allowedCellsCoordinations.get(index);
         //TicTacToe ticTacToe = new TicTacToe(inputGameBoard);
         row    = coordinations.getFirstNumber();
         column = coordinations.getSecondNumber();
         //System.out.println();
         //System.out.println(" row = " + row + " column = " + column);
         TicTacToe ticTacToe = new TicTacToe(TIC_TAC_TOE);
         
         ticTacToe.move(++row, ++column);
         //view.printBoard(ticTacToe);
         int resultAfterMove = minMax(ticTacToe, false, maxDepth - 1);
         if (maximum < resultAfterMove) {
            maxValueIndex = index;
            maximum       = resultAfterMove;
         }
      }
      
      if (maxValueIndex != Integer.MIN_VALUE) {
         coordinations = allowedCellsCoordinations.get(maxValueIndex);
         row    = coordinations.getFirstNumber();
         column = coordinations.getSecondNumber();
         TIC_TAC_TOE.move(++row, ++column);
         System.out.println("\n MOVE to row = " + row + " column = " + column + " maxValueIndex = " + maxValueIndex);
         view.printBoard(TIC_TAC_TOE);
      }
      
      return coordinations;
   }
   
   public int minMax(final TicTacToe TIC_TAC_TOE, boolean maximizing, int depth) {
      if (true == TIC_TAC_TOE.isGameOver() || 0 == depth) {
         return evaluate(TIC_TAC_TOE);
      }
      
      //GameBoard inputGameBoard = TIC_TAC_TOE.getBoard();
      ArrayList<ByteIntegersPair> allowedCellsCoordinations = TIC_TAC_TOE.getAllowedCellsCoordinations();
      int extremum;
      
      if (true == maximizing) {
         extremum = Integer.MIN_VALUE;
         for (ByteIntegersPair coordinations : allowedCellsCoordinations) {
            //TicTacToe ticTacToe = new TicTacToe(inputGameBoard);
            byte row    = coordinations.getFirstNumber();
            byte column = coordinations.getSecondNumber();
            TicTacToe ticTacToe = new TicTacToe(TIC_TAC_TOE);
            ticTacToe.move(++row, ++column);
            /*
            System.out.println("\n maxi MOVE to row = " + row + " column = " + column);
            
            view.printBoard(ticTacToe);*/
            int resultAfterMove = minMax(ticTacToe, false, depth - 1);
            
            extremum = Math.max(resultAfterMove, extremum);
            if (resultAfterMove != 0)
               System.out.println("\n resultAfterMove = " + resultAfterMove + " MAX = " + extremum);
         }
      }
      else {
         extremum = Integer.MAX_VALUE;
         for (ByteIntegersPair coordinations : allowedCellsCoordinations) {
            //TicTacToe ticTacToe = new TicTacToe(inputGameBoard);
            byte row    = coordinations.getFirstNumber();
            byte column = coordinations.getSecondNumber();
            TicTacToe ticTacToe = new TicTacToe(TIC_TAC_TOE);
            ticTacToe.move(++row, ++column);
            /*
            System.out.println("\n mini MOVE to row = " + row + " column = " + column);
            
            view.printBoard(ticTacToe);
            */
            int resultAfterMove = minMax(ticTacToe, true, depth - 1);
            extremum = Math.min(resultAfterMove, extremum);
            if (resultAfterMove != 0)
               System.out.println("\n resultAfterMove = " + resultAfterMove + " MIN = " + extremum);
         }
         
      }
      
      return extremum;
   }
   
   private int evaluate(final TicTacToe TIC_TAC_TOE) {
      GameStatus status = TIC_TAC_TOE.getGameStatus();
      int evaluation    = evaluate(status);
      
      return evaluation;
   }
   
   private int evaluate(GameStatus status) {   
      if (GameStatus.FIRST_PLAYER_WIN == status && cellMarker == CellValue.X) {
         return +1;
      }
      else if (GameStatus.FIRST_PLAYER_WIN == status && cellMarker == CellValue.O) {
         return -1;
      }
      else if (GameStatus.SECOND_PLAYER_WIN == status && cellMarker == CellValue.X) {
         return -1;
      }
      else if (GameStatus.SECOND_PLAYER_WIN == status && cellMarker == CellValue.O) {
         return +1;
      }
      else {
         return 0;
      }
   }
   
   public String toString() {
      return name;
   }
   
}
