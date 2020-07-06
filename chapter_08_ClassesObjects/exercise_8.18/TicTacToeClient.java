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
import standardInputDataPackage.GettingDataFromStandardInput;

import java.io.PrintStream;

public class TicTacToeClient {
   private static final String  QUIT = "q";
   private static final byte SQUARE_SIZE = TicTacToeController.getSQUARE_SIZE();
   
   private final PrintStream printStream;
   
   private final TicTacToe     model = new TicTacToe();
   private final TicTacToeView view;
   private final TicTacToeController controller;
   
   public TicTacToeClient() {
      printStream = System.out;
      view        = new TicTacToeView(printStream);
      controller  = new TicTacToeController(model, view);
   }
   
   public TicTacToeClient(PrintStream printStream) {
      if (null == printStream) {
         throw new NullPointerException("Null reference to printStream");
      }
      
      this.printStream = printStream;
      view       = new TicTacToeView(printStream);
      controller = new TicTacToeController(model, view);
   }

   public void run() throws Exception {
      controller.printStartInfo(); 
      
      do {
         runGame();
      } while(true == isProcessContinue());
      
   }   
   
   private boolean isProcessContinue() {
      GettingDataFromStandardInput.clearNextLine();
      String processContinue = GettingDataFromStandardInput.getString(String.format
                              ("%n %s %s to quit %n", "***** To restart game press ENTER or only", QUIT));

      if (null == processContinue || QUIT.equals(processContinue.toLowerCase())) {
         return false;
      }
      
      controller.restart();
      return true;
   }
   
   private void runGame() throws Exception {
      int turn = 1;
      String prompt;
      
      do {
         controller.printBoard();
         controller.printGameStatus();
         prompt = getPrompt(turn);
         markPositionOnBoard(prompt);
         turn++;  
      } while (false == controller.isGameOver());
      
      controller.printBoard();
      controller.printGameStatus();  
   }
   
   private void markPositionOnBoard(final String PROMT) throws Exception {
      Byte row;
      Byte column;
      boolean correctMove = false;
      
      do {
         row    = getRow(PROMT);
         if (null == row) { 
            throw new Exception("End-of-transmission character was detected");
         }
         column = getColumn();
         if (null == column) { 
            throw new Exception("End-of-transmission character was detected");
         }
         
         try {
            correctMove = controller.move(row, column);
         } 
         catch (Exception exception) {
            printStream.printf("%n%s%n", exception.getMessage());
            exception.printStackTrace();
         }
      } while (false == correctMove);
   }
   
   private static String getPrompt(int turn) {
      final String TURN_INFO = getTurnInfo(turn);
      final String RANGE = String.format("Row and column must be from %d to %d", 1, SQUARE_SIZE);
      
      String prompt = "Enter number of row then after whitespace number of column";
      prompt = String.format("%s %n %s. %s %n", TURN_INFO, prompt, RANGE);
      
      return prompt;
   }
   
   private static String getTurnInfo(int turn) {
      boolean oddTurnNumber = turn % 2 == 1;
      char playerNumber = (oddTurnNumber) ? 
                             TicTacToeView.getFirstPlayerMarker() : TicTacToeView.getSecondPlayerMarker();
      
      String turnInfo = String.format("--- Turn %d. Move for %s", turn, String.valueOf(playerNumber));
      
      return turnInfo;
   }
   
   private static Byte getRow(final String PROMT) {
      boolean promptDisplaying     = true;
      boolean acceptInfoDisplaying = false;

      Byte row = GettingDataFromStandardInput.getByteRejectOthersData(PROMT, 
                                                            promptDisplaying, acceptInfoDisplaying);
      return row;
   }
   
   private static Byte getColumn() {
      boolean promptDisplaying     = false;
      boolean acceptInfoDisplaying = false;
      final String EMPTY_PROMT = "";

      Byte column = GettingDataFromStandardInput.getByteRejectOthersData(EMPTY_PROMT, 
                                                            promptDisplaying, acceptInfoDisplaying);
      return column;
   }
} 
