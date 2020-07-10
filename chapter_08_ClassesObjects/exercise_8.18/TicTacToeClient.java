/* =====================================================================================
 *       Filename:  TicTacToeClient.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.18 - client application of tic tac toe game
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import pairPackage.ByteIntegersPair;

import java.io.PrintStream;

enum Player { HUMAN, COMPUTER };

public class TicTacToeClient {
   private final TicTacToe           MODEL;
   private final TicTacToeView       VIEW;
   private final TicTacToeController CONTROLLER;
   private int gameOption = ClientInputOutput.TWO_HUMAN_PLAYERS;  // default gameOption 
   
   private Player firstPlayer;
   private Player secondPlayer;
   private ComputerPlayer firstComputerPlayer  = new ComputerPlayer("first computer player", CellValue.X);
   private ComputerPlayer secondComputerPlayer = new ComputerPlayer("second computer player", CellValue.O);
   
   public TicTacToeClient() {
      MODEL         = new TicTacToe();
      VIEW          = new TicTacToeView(System.out);
      CONTROLLER    = new TicTacToeController(MODEL, VIEW);
   }
   
   public TicTacToeClient(PrintStream printStream) {
      this();

      this.CONTROLLER.setPrintStream(printStream);
   }

   public void run() throws Exception {
      int gameOption;
      
      do {
         CONTROLLER.printStartInfo(); 
         try {
            gameOption = ClientInputOutput.getCorrectGameOption();
            setPlayers(gameOption);
            runGame();
         }
         catch (IllegalArgumentException exception) {
            System.err.printf("%nERROR: %s%n", exception.getMessage());
            exception.printStackTrace();
         }

      } while (true == isPlayingAgain());
   }
   
   private void setPlayers(int gameOption) {
      switch (gameOption) {
         case ClientInputOutput.TWO_HUMAN_PLAYERS:
            firstPlayer  = Player.HUMAN;
            secondPlayer = Player.HUMAN;
            
            break;
         case ClientInputOutput.HUMAN_VS_COMPUTER:
            boolean humanFirstPlayer = ClientInputOutput.isHumanFirstPlayer();
            
            if (true == humanFirstPlayer) {
               firstPlayer  = Player.HUMAN;
               secondPlayer = Player.COMPUTER;
            }
            else {
               firstPlayer  = Player.COMPUTER;
               secondPlayer = Player.HUMAN;
            }
            
            break;
         case ClientInputOutput.TWO_COMPUTER_PLAYERS:
            firstPlayer  = Player.COMPUTER;
            secondPlayer = Player.COMPUTER;
      }
   }
   
   private boolean isPlayingAgain() { 
      boolean playingAgain = ClientInputOutput.isPlayingAgain();
      if (true == playingAgain) {
         CONTROLLER.restart();
      }
      
      return playingAgain;
   }
   
   private void runGame() throws Exception {
      int turn = 1;
      
      do {
         CONTROLLER.printBoard();
         CONTROLLER.printGameStatus();
         markPositionOnBoard(turn);
         turn++;  
      } while (false == CONTROLLER.isGameOver());
      
      CONTROLLER.printBoard();
      CONTROLLER.printGameStatus();  
   }
   
   private void markPositionOnBoard(final int TURN) throws Exception {
      Player player = (TURN % 2 == 1) ? firstPlayer : secondPlayer;
      
      if (player == Player.HUMAN) {
         markPositionOnBoardByHuman(TURN);
      }
      else {   // if (player == Player.COMPUTER) {
         markPositionOnBoardByComputer(TURN);
      } 
   }
   
   private void markPositionOnBoardByComputer(final int TURN) {
      ComputerPlayer computerPlayer = (TURN % 2 == 1) ? firstComputerPlayer : secondComputerPlayer;
      
      ByteIntegersPair moveCoordinations = computerPlayer.move(CONTROLLER.getModel());
      printMoveCoordinationsInfo(computerPlayer.toString(), moveCoordinations);
   }
   
   private void printMoveCoordinationsInfo(String computerPlayerName, ByteIntegersPair moveCoordinations) {
      if (null == moveCoordinations) { 
         throw new NullPointerException("moveCoordinations is null");
      }
      if (true == moveCoordinations.isNullInPair()) { 
         throw new NullPointerException("in pair of moveCoordinations is null");
      }
      
      Byte row    = moveCoordinations.getFirstNumber();
      Byte column = moveCoordinations.getSecondNumber();
         
      VIEW.printMoveCoordinationsInfo(computerPlayerName, ++row, ++column);
   }
   
   private void markPositionOnBoardByHuman(final int TURN) throws Exception {
      Byte row;
      Byte column;
      boolean correctMove = false;
      final String PROMT = StringMaker.getRowColumnPrompt(TURN);
      
      do {
         row    = ClientInputOutput.getRow(PROMT);
         column = ClientInputOutput.getColumn();
         
         try {
            correctMove = CONTROLLER.move(row, column);
         } 
         catch (Exception exception) {
            System.err.printf("%n%s%n", exception.getMessage());
            exception.printStackTrace();
         }
      } while (false == correctMove);
   }
}
