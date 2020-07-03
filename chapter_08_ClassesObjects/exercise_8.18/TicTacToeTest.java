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
      do {
         controller.printBoard();
         controller.printGameStatus();
         markPositionOnBoard(controller, turn);
         turn++;  
      } while (false == controller.isGameOver());
      
      controller.printBoard();
      controller.printGameStatus();   
   }
   
   private static void markPositionOnBoard(TicTacToeController controller, int turn) {
      Byte row;
      Byte column;
      boolean correctMove = false;
      final byte SQUARE_SIZE = TicTacToeController.getSQUARE_SIZE();
      final String TURN_INFO = getTurnInfo(turn);
      final String PROMT = "Enter number of row then after whitespace number of column";
      final String RANGE = String.format("Row and column must be from %d to %d", 1, SQUARE_SIZE);
      final String TURN_INFO_PROMT_RANGE = String.format("%s %n %s. %s %n", TURN_INFO, PROMT, RANGE);
      
      do {
         row    = getRow(TURN_INFO_PROMT_RANGE);
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
   
   private static String getTurnInfo(int turn) {
      boolean oddTurnNumber = turn % 2 == 1;
      char playerNumber = (oddTurnNumber) ? 
                             TicTacToeView.getFirstPlayerMarker() : TicTacToeView.getSecondPlayerMarker();
      
      String turnInfo = String.format("Turn %d. Move for %s", turn, String.valueOf(playerNumber));
      
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

      Byte column = GettingDataFromStandardInput.getByteRejectOthersData("", 
                                                            promptDisplaying, acceptInfoDisplaying);
      return column;
   }
   
   public static void abnormalTermination(final String INFO) {
      System.out.println("****** Program is interrupted ");
      System.out.println(INFO);
      System.exit(1);
   }
} 
