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
   private final TicTacToeController CONTROLLER;
   
   private int gameOption = ClientInputOutput.TWO_HUMAN_PLAYERS;  // default gameOption 
   
   private Player firstPlayer;
   private Player secondPlayer;
   
   private ComputerPlayer FIRST_COMPUTER_PLAYER;
   private ComputerPlayer SECOND_COMPUTER_PLAYER;
   
   public TicTacToeClient() {
      final TicTacToe     MODEL = new TicTacToe();
      final TicTacToeView VIEW  = new TicTacToeView(System.out);
      CONTROLLER                = new TicTacToeController(MODEL, VIEW);
      
      FIRST_COMPUTER_PLAYER  = new ComputerPlayer("first computer player",  CellValue.X);
      SECOND_COMPUTER_PLAYER = new ComputerPlayer("second computer player", CellValue.O);
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
            setHumanVsComputerOrder();
            
            break;
         case ClientInputOutput.TWO_COMPUTER_PLAYERS:
            firstPlayer  = Player.COMPUTER;
            secondPlayer = Player.COMPUTER;
      }
   }
   
   private void setHumanVsComputerOrder() {
      boolean humanFirstPlayer = ClientInputOutput.isHumanFirstPlayer();
            
      if (true == humanFirstPlayer) {
         firstPlayer  = Player.HUMAN;
         secondPlayer = Player.COMPUTER;
      }
      else {
         firstPlayer  = Player.COMPUTER;
         secondPlayer = Player.HUMAN;
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
      ComputerPlayer computerPlayer = (TURN % 2 == 1) ? FIRST_COMPUTER_PLAYER : SECOND_COMPUTER_PLAYER;
      
      TicTacToe model = CONTROLLER.getModel();
      ByteIntegersPair moveCoordinations = computerPlayer.move(model);
      String computerPlayerName = computerPlayer.toString();
      
      printMoveCoordinationsInfo(computerPlayerName, moveCoordinations);
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
         
      CONTROLLER.printMoveCoordinationsInfo(computerPlayerName, ++row, ++column);// public methods in class TicTacToe assume that first index of array is 1
   }
   
   private void markPositionOnBoardByHuman(final int TURN) throws Exception {
      byte[] moveCoordinations = new byte[2];
      boolean correctMove = false;
      final String PROMT = StringMaker.getRowColumnPrompt(TURN);
      
      do {
         ClientInputOutput.setMoveCoordinations(PROMT, moveCoordinations);
         
         try {
            correctMove = CONTROLLER.move(moveCoordinations[0], moveCoordinations[1]);
         } 
         catch (IllegalArgumentException exception) {
            System.err.printf("%n%s%n", exception.getMessage());
            exception.printStackTrace();
         }
      } while (false == correctMove);
   }
}
