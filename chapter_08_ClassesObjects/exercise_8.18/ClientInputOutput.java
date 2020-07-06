/* =====================================================================================
 *       Filename:  ClientInputOutput.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.18 - class of client's input-output
                                to tic tac toe game
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class ClientInputOutput {
   
   private static final String  QUIT = "q";
   private static final String  MENU = getMenu();
   public static final int  TWO_HUMAN_PLAYERS    = 1;
   public static final int  HUMAN_VS_COMPUTER    = 2;
   public static final int  TWO_COMPUTER_PLAYERS = 3;
   
   static Byte getRow(final String PROMT) throws Exception {
      boolean promptDisplaying     = true;
      boolean acceptInfoDisplaying = false;

      Byte row = GettingDataFromStandardInput.getByteRejectOthersData(PROMT, 
                                                            promptDisplaying, acceptInfoDisplaying);
      ExceptionChecker.checkNullPointerException(row, "End-of-transmission character was detected");
         
      return row;
   }
   
   static Byte getColumn() throws Exception {
      boolean promptDisplaying     = false;
      boolean acceptInfoDisplaying = false;
      final String EMPTY_PROMT = "";

      Byte column = GettingDataFromStandardInput.getByteRejectOthersData(EMPTY_PROMT, 
                                                            promptDisplaying, acceptInfoDisplaying);
      ExceptionChecker.checkNullPointerException(column, "End-of-transmission character was detected");
      
      return column;
   }
   
   private static String getMenu() {
      String menu =        ("\t \t \t *** MENU *** \n");
      menu += ("Select option for game \n");
      menu += String.format("%d. Human vs Human    - enter %d %n", TWO_HUMAN_PLAYERS, TWO_HUMAN_PLAYERS);
      menu += String.format("%d. Human vs Computer - enter %d %n", HUMAN_VS_COMPUTER, HUMAN_VS_COMPUTER);
      menu += String.format("%d. Computer vs Computer - enter %d %n", TWO_COMPUTER_PLAYERS, TWO_COMPUTER_PLAYERS);
      
      return menu;
   }
   
   static boolean isProcessContinue() {
      GettingDataFromStandardInput.clearNextLine();
      String processContinue = GettingDataFromStandardInput.getString(String.format
                              ("%n %s %s to quit %n", "***** To restart game press ENTER or only", QUIT));

      if (null == processContinue || QUIT.equals(processContinue.toLowerCase())) {
         return false;
      }
      
      return true;
   }
   
   static boolean isCorrectGameOption(int gameOption) {
      
      switch (gameOption) {
         case ClientInputOutput.TWO_HUMAN_PLAYERS:
         case ClientInputOutput.HUMAN_VS_COMPUTER:
         case ClientInputOutput.TWO_COMPUTER_PLAYERS:
            return true;
      }
         
      return false;
   }
   
   static int getGameOption() throws Exception {
      Integer gameOption = GettingDataFromStandardInput.getInteger(MENU);
      ExceptionChecker.checkNullPointerException(gameOption, "End-of-transmission character was detected");
      
      while (false == isCorrectGameOption(gameOption)) {
         System.err.printf("ERROR: %d is incorrect game option. Try again %n", gameOption);
         gameOption = GettingDataFromStandardInput.getInteger(MENU);
         ExceptionChecker.checkNullPointerException(gameOption, "End-of-transmission character was detected");
      }
         
      return gameOption;
   }
}

class ExceptionChecker {
   static void checkNullPointerException(Object object, String message) throws Exception {
      if (null == object) { 
         throw new Exception(message);
      }
   }
}

class StringMaker {
   private static final byte SQUARE_SIZE = TicTacToeController.getSQUARE_SIZE();
   
   static String getPrompt(int turn) {
      final String TURN_INFO = getTurnInfo(turn);
      final String RANGE = String.format("Row and column must be from %d to %d", 1, SQUARE_SIZE);
      
      String prompt = "Enter number of row then after whitespace number of column";
      prompt = String.format("%s %n %s. %s %n", TURN_INFO, prompt, RANGE);
      
      return prompt;
   }
   
   static String getTurnInfo(int turn) {
      boolean oddTurnNumber = turn % 2 == 1;
      char playerNumber = (oddTurnNumber) ? 
                             TicTacToeView.getFirstPlayerMarker() : TicTacToeView.getSecondPlayerMarker();
      
      String turnInfo = String.format("--- Turn %d. Move for %s", turn, String.valueOf(playerNumber));
      
      return turnInfo;
   }
} 
