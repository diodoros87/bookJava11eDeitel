/* =====================================================================================
 *       Filename:  KnightsTourControl.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.26 - class of tracking while finding knight's
                                tour on virtual chessboard by
                                   heuristic accessibility algorithm and checking tour
                                      was closed
                                
                           
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class KnightsTourControl {

   public static final String START_INFO = String.format("%s %n %s%n", "Program was written in purpose of control test of tracking while ",
                                "finding knight's tour on virtual chessboard by heuristic accessibility algorithm");
   
   public final static int NEXT_MOVE_KEY        = 1;
   public final static int ENDING_TOUR_KEY      = 3;
   public final static int ALL_TOURS_KEY        = 5;
   public final static int RESTART_PROGRAM_KEY  = 7;
   public final static int QUIT_KEY             = 9;
   
   public static final String QUIT_INFO = String.format("To quit enter End-Of-Transmission (EOT) character or %d ", QUIT_KEY);
   public static final String EOT_INFO = "End-Of-Transmission (EOT) character is Ctrl-D (in Linux or Mac OS X) or Ctrl-Z in Windows";
   
   private boolean quit = false;
   
   private KnightsTour knightsTour   = null;
   
   private byte [][] startingPositions = new byte[KnightsTour.ROWS][KnightsTour.COLUMNS]; // all available positions in chessboard
   private byte startingRow    = 0;                               // number of chessboard's row where knight starts tour
   private byte startingColumn = 0;                               // number of chessboard's column where knight starts tour
   public static final byte STARTED = 1;   /* set that value in array startingPositions[row][column] after knight started tour
                                           with starting row equals to row in startingPositions[row][column] 
                                           and starting column equals to column in startingPositions[row][column] */
   public static final byte WAIT = 0;   /*  value is in array startingPositions[row][column] before knight starts tour
                                        with starting row equals to row in startingPositions[row][column] 
                                           and starting column equals to column in startingPositions[row][column]  */
                                           
   private KnightsTourSummary[][] knightsTourSummaries = new KnightsTourSummary[KnightsTour.ROWS][KnightsTour.COLUMNS];
   
   KnightsTourControl () {
      quit = false;
      KnightsTour.setValuesInArray(startingPositions, WAIT);  // all values set to WAIT because in all starting positions tour has not started yet
      generateStartingPositions();    // select starting position for knight tour
      knightsTour = new KnightsTour(startingRow, startingColumn);
   }
   
   private boolean generateStartingPositions() {
      for (byte row = 0; row < KnightsTour.ROWS; row++) {
         for (byte column = 0; column < KnightsTour.COLUMNS; column++) {
            if (WAIT == startingPositions[row][column]) {
               startingRow    = row;
               startingColumn = column;
               startingPositions[row][column] = STARTED;
               return true;
            }
         }
      }
      
      return false;
   }
   
   private void restart() {
      KnightsTour.setValuesInArray(startingPositions, WAIT);  // all values set to WAIT because in all starting positions tour has not started yet
      generateStartingPositions();    // select starting position for knight tour
      knightsTour.resetTour(startingRow, startingColumn);
   }
   
   public boolean isQuit() {
      return quit;
   }
   
   void control(int command) {
      switch(command) {
         case NEXT_MOVE_KEY:
            performMove(true);
            break;
         case ENDING_TOUR_KEY:
            completeCurrentTourStartNextTour();
            break;
         case ALL_TOURS_KEY:
            makeToursStartingOnEachPositions();
            restart();
            break;
         case RESTART_PROGRAM_KEY:
            restart();
            break;
         case QUIT_KEY:
            quit = true;
            break;
         default:
            System.err.printf("****ERROR  Unrecognized command %d%n", command);
      }
   }
   
   private void makeToursStartingOnEachPositions() {
      KnightsTour.setValuesInArray(startingPositions, WAIT);
      
      while (true == generateStartingPositions()) {
         makeTour(true, false, false);
         knightsTourSummaries[startingRow][startingColumn] = generateKnightsTourSummary(true, true);
      }
      
      System.out.println("Summaries of all tours was printed below:");
      System.out.println(KnightsTourDescription.generateKnightsToursSummaries(knightsTourSummaries));
      System.out.println("Summaries of all tours was printed above");
   }
   
   private void completeCurrentTourStartNextTour() {
      makeTour(false, true, true);    // perform next available moves - if current tour has not finished yet
      if (true == generateStartingPositions()) {    // new tour with new starting position
         knightsTour.resetTour(startingRow, startingColumn);
      }
      else {
         System.out.printf("$$$:  Knight's tours starting with each positions were performed %n");
         System.out.printf("$$$:  To start new tour need restart program enter %d %n", RESTART_PROGRAM_KEY);
      }
   }
   
   private void makeTour(boolean newTour, boolean generatingSummary, boolean printingFinalBoard) {
      if (true == newTour) {
         knightsTour.resetTour(startingRow, startingColumn);
      }
      
      while (true == performMove(false)) {
         continue;
      }
      
      if (true == generatingSummary) {
         generateKnightsTourSummary(true, printingFinalBoard);
      }
   }   
   
   private boolean performMove(boolean printing) {
      byte moveNumber = knightsTour.getNumberOfPerformedMove();

      if (false == knightsTour.validateMoveNumber(moveNumber)) {
         if (true == printing) {
            System.err.printf("****ERROR:   Move is unavailable%n");
            generateKnightsTourSummary(true, false);
         }
         
         return false;
      }
      else {
         if (true == printing) {
            System.out.printf("$$$:   Move %d was performed%n", moveNumber);
         }
         
         return true;
      }
   }
   
   private KnightsTourSummary generateKnightsTourSummary(boolean printingSummary, boolean printingFinalBoard) {
      final byte finalPositionRow = knightsTour.getCurrentRow();
      final byte finalPositionColumn = knightsTour.getCurrentColumn();
      final byte visitedPositions = knightsTour.getVisitedPositionsCounter();
      final boolean tourClosed = KnightsTour.isMovePossible(finalPositionRow, finalPositionColumn, startingRow, startingColumn);

      KnightsTourSummary knightsTourSummary = new KnightsTourSummary (startingRow, startingColumn, 
                                          finalPositionRow, finalPositionColumn, visitedPositions, tourClosed);
      if (true == printingSummary) {                                    
         String summary = knightsTourSummary.getKnightsTourSummary();
         System.out.printf(" *** SUMMARY OF KNIGHT'S TOUR: %n %s %n", summary);
      }
      
      if (true == printingFinalBoard) {  
         System.out.println(KnightsTourDescription.generateBoardString(knightsTour, true));
      }
      
      return knightsTourSummary;
   }
   
   void printMenu() {
      System.out.printf("%n------------- STATUS OF MOVES: %n");
      
      for (byte move = 0; move < KnightsTour.MAX_MOVES; move++) {
         if (true == knightsTour.isMoveAvailable(move, knightsTour.getCurrentRow(), knightsTour.getCurrentColumn())) {
             System.out.printf("### move %d for %s %n", move, KnightsTourDescription.getMoveDescription(knightsTour, move));
         }
         else {
            System.out.printf("### unavailable move %d for %s %n", move, 
                                          KnightsTourDescription.getMoveDescription(knightsTour, move));
         }
      }
      
      System.out.printf("%n***** enter %d for next move of knight's tour  %n", NEXT_MOVE_KEY);
      System.out.printf("***** enter %d for ending tour and change starting tour's position  %n", ENDING_TOUR_KEY);
      System.out.printf("***** enter %d for try knight's tours starting with each positions %n", ALL_TOURS_KEY);
      System.out.printf("***** enter %d for RESTART program  %n", RESTART_PROGRAM_KEY);
      System.out.printf("***** enter %d for QUIT  %n", QUIT_KEY);
      
      displayArray(knightsTour.getAccessibility(), "^^^^   Accessibility array for chessboard:");
      System.out.printf("****** CHESSBOARD: %n");
      System.out.println(KnightsTourDescription.generateStartingPositionsString(startingRow, startingColumn));
      System.out.println(KnightsTourDescription.generateBoardString(knightsTour, true));
      System.out.print(">>> Enter COMMAND:     ");
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
