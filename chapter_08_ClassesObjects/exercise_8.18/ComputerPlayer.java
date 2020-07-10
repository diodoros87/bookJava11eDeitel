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
         row    = coordinations.getFirstNumber();
         column = coordinations.getSecondNumber();
         TicTacToe ticTacToe = new TicTacToe(TIC_TAC_TOE);
         
         ticTacToe.move(++row, ++column);
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
      }
      
      return coordinations;
   }
   
   public int minMax(final TicTacToe TIC_TAC_TOE, boolean maximizing, int depth) {
      if (true == TIC_TAC_TOE.isGameOver() || 0 == depth) {
         return evaluate(TIC_TAC_TOE);
      }
      
      ArrayList<ByteIntegersPair> allowedCellsCoordinations = TIC_TAC_TOE.getAllowedCellsCoordinations();
      int extremum;
      
      if (true == maximizing) {
         extremum = Integer.MIN_VALUE;
         for (ByteIntegersPair coordinations : allowedCellsCoordinations) {

            byte row    = coordinations.getFirstNumber();
            byte column = coordinations.getSecondNumber();
            TicTacToe ticTacToe = new TicTacToe(TIC_TAC_TOE);
            ticTacToe.move(++row, ++column);

            int resultAfterMove = minMax(ticTacToe, false, depth - 1);
            
            extremum = Math.max(resultAfterMove, extremum);
         }
      }
      else {
         extremum = Integer.MAX_VALUE;
         for (ByteIntegersPair coordinations : allowedCellsCoordinations) {

            byte row    = coordinations.getFirstNumber();
            byte column = coordinations.getSecondNumber();
            TicTacToe ticTacToe = new TicTacToe(TIC_TAC_TOE);
            ticTacToe.move(++row, ++column);

            int resultAfterMove = minMax(ticTacToe, true, depth - 1);
            extremum = Math.min(resultAfterMove, extremum);
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
