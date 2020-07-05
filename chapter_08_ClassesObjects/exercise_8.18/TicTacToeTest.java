/* =====================================================================================
 *       Filename:  TicTacToeTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.18 - test of tic tac toe game
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
import standardInputDataPackage.GettingDataFromStandardInput;
import java.io.PrintStream;

public class TicTacToeTest {
   private static final String  QUIT = "q";
   private static final PrintStream PRINT_STREAM = System.out;
   private static final byte SQUARE_SIZE = TicTacToeController.getSQUARE_SIZE();

   public static void main(String[] args) {
      TicTacToe     model = new TicTacToe();
      TicTacToeView view  = new TicTacToeView(PRINT_STREAM);
      TicTacToeController controller = new TicTacToeController(model, view);
      
      do {
         //printMenu();
         runGame(controller);
      } while(true == isProcessContinue(controller));
      
   }   
   
   private static boolean isProcessContinue(TicTacToeController controller) {
      GettingDataFromStandardInput.clearNextLine();
      String processContinue = GettingDataFromStandardInput.getString(String.format
                              ("%n %s %s to quit %n", "***** To restart game press ENTER or only", QUIT));

      if (null == processContinue || QUIT.equals(processContinue.toLowerCase())) {
         return false;
      }
      
      controller.restart();
      return true;
   }
   
   private static void runGame(TicTacToeController controller) {
      controller.printStartInfo(); 
      
      int turn = 1;
      String prompt;
      
      do {
         controller.printBoard();
         controller.printGameStatus();
         prompt = getPrompt(turn);
         markPositionOnBoard(controller, prompt);
         turn++;  
      } while (false == controller.isGameOver());
      
      controller.printBoard();
      controller.printGameStatus();   
   }
   
   private static void markPositionOnBoard(TicTacToeController controller, final String PROMT) {
      Byte row;
      Byte column;
      boolean correctMove = false;
      
      do {
         row    = getRow(PROMT);
         if (null == row) { 
            abnormalTermination("End-of-transmission character was detected");
         }
         column = getColumn();
         if (null == column) { 
            abnormalTermination("End-of-transmission character was detected");
         }
         
         try {
            correctMove = controller.move(row, column);
         } 
         catch (Exception exception) {
            PRINT_STREAM.printf("%n%s%n", exception.getMessage());
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
   
   public static void abnormalTermination(final String INFO) {
      System.out.println("****** Program is interrupted ");
      System.out.println(INFO);
      System.exit(1);
   }
} 
