/* =====================================================================================
 *       Filename:  KnightsTourControl.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.22c - class of tracking while finding knight's
                                tour on virtual chessboard by
                                   heuristic accessibility algorithm
                                
                           
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class KnightsTourControl {

   public static final String START_INFO = String.format("%s %n %s%n", "Program was written in purpose of manual control test on ", 
                              "keyboard for User to trying knight's tour on virtual chessboard");
   
   public final static int NEXT_MOVE_KEY   = 1;
   public final static int ENDING_TOUR_KEY = 4;
   public final static int ALL_TOURS_KEY   = 7;
   public final static int QUIT_KEY        = 0;
   
   public static final String QUIT_INFO = String.format("To quit enter End-Of-Transmission (EOT) character or %d ", QUIT_KEY);
   public static final String EOT_INFO = "End-Of-Transmission (EOT) character is Ctrl-D (in Linux or Mac OS X) or Ctrl-Z in Windows";
   
   private KnightsTour knightsTour   = null;
   
   private boolean quit = false;
   
   
   private byte [][] startingPositions = new byte[KnightsTour.ROWS][KnightsTour.COLUMNS]; // all available positions in chessboard
   private byte startingRow    = 0;                               // number of row where knight starts tour
   private byte startingColumn = 0;                               // number of column where knight starts tour
   public static final byte STARTED = 1;   /* set that value in array startingPositions[row][column] after knight started tour
                                           with starting row equals to row in startingPositions[row][column] 
                                           and starting column equals to column in startingPositions[row][column] */
   public static final byte WAIT = 0;   /*  value is in array startingPositions[row][column] before knight starts tour
                                        with starting row equals to row in startingPositions[row][column] 
                                           and starting column equals to column in startingPositions[row][column]  */
   
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
   
   public boolean isQuit() {
      return quit;
   }
   
   void control(int command) {
      if (command == NEXT_MOVE_KEY) {
         byte moveNumber = knightsTour.getNumberOfPerformedMove();

         if (false == knightsTour.validateMoveNumber(moveNumber)) {
            System.err.printf("****ERROR:   Move is unavailable%n");
         }
         else {
            System.out.printf("$$$:   Move %d was performed%n", moveNumber);
         }
      }
      else if (ENDING_TOUR_KEY == command) {
         if (true == generateStartingPositions()) {
            knightsTour.resetTour(startingRow, startingColumn);
         }
         else {
            System.out.printf("$$$:  Knight's tours with each positions were tried %n");
         }
      }
      else if (ALL_TOURS_KEY == command) {
         allPositionsStartingTour();
      }
      else if (QUIT_KEY == command) {
         quit = true;
      }
      else {
         System.err.printf("****ERROR  Unrecognized command %d%n", command);
      }
   }
   
   void allPositionsStartingTour() {
      KnightsTour.setValuesInArray(startingPositions, WAIT);
      
      while (true == generateStartingPositions()) {
         knightsTour.resetTour(startingRow, startingColumn);
         byte moveNumber;
         
         do {
            moveNumber = knightsTour.getNumberOfPerformedMove();
         } while (true == knightsTour.validateMoveNumber(moveNumber));
         
         System.out.printf(" *** SUMMARY: %n %s %n", generateKnightsTourSummary());
      }
   }
   
   private String generateKnightsTourSummary() {
      String summary = generateStartingPositionsString();
      summary += "\n" + "Last position in row: " + knightsTour.getCurrentRow();
      summary += "\n" + "Last position in column: " + knightsTour.getCurrentColumn();
      summary += "\n" + "Number of visited positions: " + knightsTour.getVisitedPositionsCounter();
      
      return summary;
   }
   
   void updateData() {
      knightsTour.updateAccessibility();
   }
   
   void printMenu() {
      System.out.printf("%n------------- STATUS OF MOVES: %n");
      
      for (byte move = 0; move < KnightsTour.MAX_MOVES; move++) {
         if (true == knightsTour.isMoveAvailable(move, knightsTour.getCurrentRow(), knightsTour.getCurrentColumn())) {
             System.out.printf("### move %d for %s %n", move, getMoveDescription(move));
         }
         else {
            System.out.printf("### unavailable move %d for %s %n", move, getMoveDescription(move));
         }
      }
      
      System.out.printf("%n***** enter %d for next move of knight's tour  %n", NEXT_MOVE_KEY);
      System.out.printf("***** enter %d for ending tour and change starting tour's position  %n", ENDING_TOUR_KEY);
       System.out.printf("***** enter %d for try knight's tours starting with each positions %n", ALL_TOURS_KEY);
      System.out.printf("***** enter %d for QUIT  %n", QUIT_KEY);
      
      displayArray(knightsTour.getAccessibility(), "^^^^   Accessibility array for chessboard:");
      System.out.printf("****** CHESSBOARD: %n");
      System.out.println(generateStartingPositionsString());
      System.out.println(generateBoardString(true));
   }
   
   String getMoveDescription(byte moveNumber) {
      if (moveNumber < 0 || moveNumber >= KnightsTour.MAX_MOVES) {
         throw new IllegalArgumentException(String.format("moveNumber %d", moveNumber));
      }
      
      return String.format("rows += %d     columns += %d", 
                        KnightsTour.vertical[moveNumber], KnightsTour.horizontal[moveNumber]);
   }
   
   String generateStartingPositionsString() {
      return "Tour's starting position in row: " + startingRow + "  in column: " + startingColumn;
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
   
   public String generateBoardString(boolean numbersAppending) {
      String boardString = "";
      byte currentRow = knightsTour.getCurrentRow();
      byte currentColumn = knightsTour.getCurrentColumn();
      byte [][] board = knightsTour.getBoard();
      
      if (true == numbersAppending) {
         boardString = appendNumbersRow();
      }
      
      for (int row = 0; row < KnightsTour.ROWS; row++) {
         if (true == numbersAppending) {
            boardString += row;
         }
            
         for (int column = 0; column < KnightsTour.COLUMNS; column++) {
            if (currentRow == row && currentColumn == column) {
               boardString += String.valueOf(KnightsTour.knightCharacter); 
            }
            else if (board[row][column] == KnightsTour.VISITED) {
               boardString += String.valueOf(KnightsTour.visitedCharacter); 
            }
            else {
               boardString += String.valueOf(KnightsTour.noTracedCharacter); 
            }
            
         }
         
         if (true == numbersAppending) {
               boardString += row;
         }
         
         boardString = boardString.concat(generateCurrentKnightsData(row));
         boardString = boardString.concat("\n"); 
      }
      
      if (true == numbersAppending) {
         boardString = boardString.concat(appendNumbersRow());
      }
      
      return boardString;
   }
   
   public String appendNumbersRow() {
      String numbersRowString = " ";
      
      for (int column = 0; column < KnightsTour.ROWS; column++) {
         numbersRowString += column; 
      }
      
      numbersRowString += "\n"; 
      
      return numbersRowString;
   }
   
   private String generateCurrentKnightsData(int row) {
      String currentKnightsDataRow = String.format("%10s", " ");
      
      switch (row) {
         case 1:
            currentKnightsDataRow += "Knight:               " + String.valueOf(KnightsTour.knightCharacter);
               break;
         case 2:
            currentKnightsDataRow += "Visited positions:    " + String.valueOf(KnightsTour.visitedCharacter);
               break;
         case 3:
            currentKnightsDataRow += "No visited positions: " + String.valueOf(KnightsTour.noTracedCharacter);
            break;
         case 4:
            currentKnightsDataRow += "Number of knight's position in row:    " + knightsTour.getCurrentRow();
               break;
         case 5:
            currentKnightsDataRow += "Number of knight's position in column: " + knightsTour.getCurrentColumn();
               break;
         case 6:
            currentKnightsDataRow += "Number of visited positions:           " + knightsTour.getVisitedPositionsCounter();
               break;
         default:
            return "";
      }
      
      return currentKnightsDataRow;
   }
   
} 
