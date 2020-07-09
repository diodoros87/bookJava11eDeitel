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
   static final String MENU               = StringMaker.getMenu();
   static final int  TWO_HUMAN_PLAYERS    = 1;
   static final int  HUMAN_VS_COMPUTER    = 2;
   static final int  TWO_COMPUTER_PLAYERS = 3;
   static final String REJECT_ANSWER      = "y";
   
   static boolean inputWhitespaces = false;
   
   static Byte getRow(final String PROMT) throws Exception {
      inputWhitespaces = true;   // GettingDataFromStandardInput.getInteger() leaves whitespaces in input
      
      boolean promptDisplaying     = true;
      boolean acceptInfoDisplaying = false;

      Byte row = GettingDataFromStandardInput.getByteRejectOthersData(PROMT, 
                                                            promptDisplaying, acceptInfoDisplaying);
      ExceptionChecker.checkNullPointerException(row, "End-of-transmission character was detected");
         
      return row;
   }
   
   static Byte getColumn() throws Exception {
      inputWhitespaces = true;   // GettingDataFromStandardInput.getInteger() leaves whitespaces in input
      
      boolean promptDisplaying     = false;
      boolean acceptInfoDisplaying = false;
      final String EMPTY_PROMT = "";

      Byte column = GettingDataFromStandardInput.getByteRejectOthersData(EMPTY_PROMT, 
                                                            promptDisplaying, acceptInfoDisplaying);
      ExceptionChecker.checkNullPointerException(column, "End-of-transmission character was detected");
      
      return column;
   }
   
   static boolean isPlayingAgain() {
      String question = StringMaker.getPlayingAgainQuestion();
      
      boolean answer = answerToQuestion(question);
      return answer;
   }
   
   static boolean isHumanFirstPlayer() {
      String question = StringMaker.getHumanFirstPlayerQuestion();
      
      boolean answer = answerToQuestion(question);
      return answer;
   }
   
   static boolean isCorrectGameOption(int gameOption) {
      
      switch (gameOption) {
         case TWO_HUMAN_PLAYERS:
         case HUMAN_VS_COMPUTER:
         case TWO_COMPUTER_PLAYERS:
            return true;
      }
         
      return false;
   }
   
   static boolean answerToQuestion(final String QUESTION) {
      if (true == inputWhitespaces) {
         clearNextLine();
      }
      
      String answer = GettingDataFromStandardInput.getString(QUESTION);

      if (null == answer || REJECT_ANSWER.equals(answer.toLowerCase())) {
         return false;
      }
      
      return true;
   }
   
   static int getCorrectGameOption() throws Exception {
      int gameOption = getGameOption();
      
      while (false == isCorrectGameOption(gameOption)) {
         System.err.printf("ERROR: %d is incorrect game option. Try again %n", gameOption);
         gameOption = getGameOption();
      }
         
      return gameOption;
   }
   
   static int getGameOption() throws Exception {
      try {
         Integer gameOption = GettingDataFromStandardInput.getInteger(MENU);
         ExceptionChecker.checkNullPointerException(gameOption, "End-of-transmission character was detected");
         
         return gameOption;
      } 
      catch (IllegalArgumentException exception) {
         clearNextLine();
   
         throw exception;
      }
   }
   
   static void clearNextLine() {
      GettingDataFromStandardInput.clearNextLine();
      inputWhitespaces = false;
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
   static final byte SQUARE_SIZE = TicTacToeController.getSQUARE_SIZE();
   
   static String getPlayingAgainQuestion() {
      String question = String.format("***** To exit program press \'%s\' - to restart game press other key %n",
                                       ClientInputOutput.REJECT_ANSWER);
                                       
      return question;
   }
   
   static String getHumanFirstPlayerQuestion() {
      String question = String.format(
            "***** If first turn for computer player press \'%s\' - otherwise press other key %n", 
                                       ClientInputOutput.REJECT_ANSWER);
            
      return question;
   }
   
   static String getRowColumnPrompt(int turn) {
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
   
   static String getMenu() {
      String menu =        ("\t \t \t *** MENU *** \n");
      menu += ("Select option for game \n");
      menu += String.format("%d. Human vs Human    - enter %d %n", 
                     ClientInputOutput.TWO_HUMAN_PLAYERS, ClientInputOutput.TWO_HUMAN_PLAYERS);
      menu += String.format("%d. Human vs Computer - enter %d %n", 
                     ClientInputOutput.HUMAN_VS_COMPUTER, ClientInputOutput.HUMAN_VS_COMPUTER);
      menu += String.format("%d. Computer vs Computer - enter %d %n", 
                     ClientInputOutput.TWO_COMPUTER_PLAYERS, ClientInputOutput.TWO_COMPUTER_PLAYERS);
      
      return menu;
   }
} 
