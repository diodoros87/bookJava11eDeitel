/* =====================================================================================
 *       Filename:  EightQueensControl.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.24 - class of tracking while solving
                                 eight queens problem on virtual chessboard by
                                    eliminate positions by
                                    heuristic algorithm
                                
                           
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class EightQueensControl {

   public static final String START_INFO = String.format("%s %n %s%n", "Program was written in purpose of control test of tracking while ",
                                "solving eight queens problem on virtual chessboard by eliminate positions by heuristic algorithm");
   
   public final static int PLACE_SINGLE_QUEEN_KEY = 1;
   public final static int PLACE_EIGHT_QUEENS_KEY = 4;
   public final static int RESTART_PROGRAM_KEY    = 7;
   public final static int QUIT_KEY               = 0;
   
   public static final String QUIT_INFO = String.format("To quit enter End-Of-Transmission (EOT) character or %d ", QUIT_KEY);
   public static final String EOT_INFO = "End-Of-Transmission (EOT) character is Ctrl-D (in Linux or Mac OS X) or Ctrl-Z in Windows";
   
   private boolean quit = false;
   
   private EightQueens eightQueens   = null;
   
   EightQueensControl () {
      quit = false;
      eightQueens = new EightQueens();
   }
   
   public boolean isQuit() {
      return quit;
   }
   
   void control(int command) {
      switch(command) {
         case PLACE_SINGLE_QUEEN_KEY:
            putQueenOnChessboard(true);
            break;
         case PLACE_EIGHT_QUEENS_KEY:
            eightQueens.reset();   // can not place more than 8 queens on chessboard 8x8
            eightQueens.placeQueens();
            break;
         case RESTART_PROGRAM_KEY:
            eightQueens.reset();
            break;
         case QUIT_KEY:
            quit = true;
            break;
         default:
            System.err.printf("****ERROR  Unrecognized command %d%n", command);
      }
   }
   
   private void putQueenOnChessboard(boolean printing) {
      if (eightQueens.getQueensCounter() == EightQueens.MAX_QUEENS) { // can not place more than 8 queens on chessboard 8x8
         eightQueens.reset();
      }
      
      eightQueens.selectPositionsByMinimalElimination();

      if (true == printing && eightQueens.getErrorMessage() != "") {
         System.err.printf("****ERROR:   %s %n", eightQueens.getErrorMessage());
         eightQueens.clearErrorMessage();
      }
      
      
   }
   
   void printMenu() {
      System.out.printf("%n------------- MENU: %n");
      
      System.out.printf("***** enter %d for place one queen  %n", PLACE_SINGLE_QUEEN_KEY);
      System.out.printf("***** enter %d for place eight queens %n", PLACE_EIGHT_QUEENS_KEY);
      System.out.printf("***** enter %d for RESTART program  %n", RESTART_PROGRAM_KEY);
      System.out.printf("***** enter %d for QUIT  %n", QUIT_KEY);
      
      System.out.println(EightQueensDescription.generatePositionsElimination(eightQueens , "^^^^   positions elimination array for chessboard:"));
      System.out.printf("****** CHESSBOARD: %n");
      System.out.println(EightQueensDescription.generateBoardString(eightQueens, true));
      System.out.print(">>> Enter COMMAND:     ");
   }
   
} 
