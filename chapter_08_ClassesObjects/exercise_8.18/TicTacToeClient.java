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

import java.io.PrintStream;

enum Player { HUMAN, COMPUTER };

public class TicTacToeClient {
   private final TicTacToeController CONTROLLER;
   private Player firstPlayer;
   private Player secondPlayer;
   
   public TicTacToeClient() {
      TicTacToe model    = new TicTacToe();
      TicTacToeView view = new TicTacToeView(System.out);
      this.CONTROLLER    = new TicTacToeController(model, view);
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
            //break;
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
         markPositionOnBoardByComputer();
      } 
   }
   
   private void markPositionOnBoardByComputer() {
      
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
   
   private void getRow(Player player) {
      
   }
}
