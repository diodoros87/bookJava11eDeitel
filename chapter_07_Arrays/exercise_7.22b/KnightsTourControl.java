/* =====================================================================================
 *       Filename:  KnightsTourControl.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.22b - class of control knight's tour on virtual
                                chessboard in manual test on keyboard
                                
                           
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class KnightsTourControl {

   public static final String START_INFO = String.format("%s %n %s%n", "Program was written in purpose of manual control test on ", 
                              "keyboard for User to trying knight's tour on virtual chessboard");
   
   public final static int MOVE_0_KEY = 0;  
   public final static int MOVE_1_KEY = 1;                              
   public final static int MOVE_2_KEY = 2;  
   public final static int MOVE_3_KEY = 3;  
   public final static int MOVE_4_KEY = 4;                              
   public final static int MOVE_5_KEY = 5;  
   public final static int MOVE_6_KEY = 6; 
   public final static int MOVE_7_KEY = 7;
   public final static int RESET_TOUR_KEY = 8;
   public final static int QUIT_KEY = 9;
   public final static int UNDO_MOVE_KEY = 10;
   
   public static final String QUIT_INFO = String.format("To quit enter End-Of-Transmission (EOT) character or %d ", QUIT_KEY);
   public static final String EOT_INFO = "End-Of-Transmission (EOT) character is Ctrl-D (in Linux or Mac OS X) or Ctrl-Z in Windows";
   
   private KnightsTour knightsTour   = null;
   private String currentKnightsData = null;
   
   private boolean quit = false;
   private boolean undoLastMoveAvailable = false;
   
   KnightsTourControl () {
      knightsTour = new KnightsTour();
      currentKnightsData = generateCurrentKnightsData();
      quit = false;
      undoLastMoveAvailable = false;
   }
   
   public boolean isQuit() {
      return quit;
   }
   
   void control(int command) {
      if (command >= MOVE_0_KEY && command <= MOVE_7_KEY) {
         if (true == knightsTour.move(command)) {
            undoLastMoveAvailable = true;
         }
         else {
            System.err.printf("****ERROR:   Move is unavailable%n");
         }
      }
      else if (RESET_TOUR_KEY == command) {
         knightsTour.resetTour();
         undoLastMoveAvailable = false;
      }
      else if (QUIT_KEY == command) {
         quit = true;
      }
      else if (UNDO_MOVE_KEY == command) {
         if (true == undoLastMoveAvailable) {
            knightsTour.undoLastMove();
            undoLastMoveAvailable = false;
         }
         else {
            System.err.printf("****ERROR:   Option undo move currently is unavailable%n");
         }
      }
      else {
         System.err.printf("****ERROR  Unrecognized command %d%n", command);
      }
   }
   
   void updateData() {
      knightsTour.updateAccessibility();
   }
   
   void printMenu() {
      System.out.printf("%n------------- MENU %n");
      
      for (int command = 0; command < KnightsTour.MAX_MOVES; command++) {
         if (true == knightsTour.isMoveAvailable(command, knightsTour.getCurrentRow(), knightsTour.getCurrentColumn())) {
             System.out.printf("### enter %d for move %s %n", command, getMoveDescription(command));
         }
         else {
            System.out.printf("### unavailable move %d for %s %n", command, getMoveDescription(command));
         }
      }
      
      System.out.printf("### enter %d for reset tour and set to starting position  %n", RESET_TOUR_KEY);
      System.out.printf("### enter %d for QUIT  %n", QUIT_KEY);
      if (true == undoLastMoveAvailable) {
         System.out.printf("### enter %d for undo last move  %n", UNDO_MOVE_KEY);
      }
      
      displayArray(knightsTour.getAccessibility(), "^^^^   Accessibility array for chessboard:");
      System.out.printf("****** CHESSBOARD: %n");
      System.out.println(knightsTour.generateBoardString(true));
   }
   
   String getMoveDescription(int moveNumber) {
      if (moveNumber < 0 || moveNumber >= KnightsTour.MAX_MOVES) {
         throw new IllegalArgumentException(String.format("moveNumber %d", moveNumber));
      }
      
      return String.format("rows += %d     columns += %d", 
                        KnightsTour.vertical[moveNumber], KnightsTour.horizontal[moveNumber]);
   }
   
   String generateCurrentKnightsData() {
      this.currentKnightsData = "Position in row: " + knightsTour.getCurrentRow();
      this.currentKnightsData += "\n" + "Position in column: " + knightsTour.getCurrentColumn();
      this.currentKnightsData += "\n" + "Number of visited positions: " + knightsTour.getVisitedPositionsCounter();
      
      return this.currentKnightsData;
   }
   
   public static void displayArray(byte[][] array, String title) {
      System.out.println(title);
      for (byte[] row : array) {
         for (byte value : row) {
            System.out.printf("%d ", value);
         }
         System.out.println();
      }
   }
   
} 
