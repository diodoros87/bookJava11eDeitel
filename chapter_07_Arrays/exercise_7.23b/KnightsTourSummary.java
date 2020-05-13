/* =====================================================================================
 *       Filename:  KnightsTourSummary.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.23b - summary of finding knight's
                                tour on virtual chessboard
                                
                           
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class KnightsTourSummary {

   private final byte startingPositionRow;    // knight's tour starting position in row
   private final byte startingPositionColumn; // knight's tour starting position in column
   private final byte finalPositionRow;       // knight's tour final position in row
   private final byte finalPositionColumn;    // knight's tour final position in column
   private final byte visitedPositions;       // number of all visited positions in knight's tour
   private final boolean tourClosed;
   
   KnightsTourSummary (byte startingPositionRow, byte startingPositionColumn,
               byte finalPositionRow, byte finalPositionColumn, byte visitedPositions, boolean tourClosed) {
      this.startingPositionRow = startingPositionRow;
      this.startingPositionColumn = startingPositionColumn;
      this.finalPositionRow = finalPositionRow;
      this.finalPositionColumn = finalPositionColumn;
      this.visitedPositions = visitedPositions;
      this.tourClosed = visitedPositions == KnightsTour.POSITIONS ? tourClosed : false;
   }
   
   byte getStartingPositionRow () {
      return startingPositionRow;
   }
   
   byte getStartingPositionColumn () {
      return startingPositionColumn;
   }
   
   byte getFinalPositionRow () {
      return finalPositionRow;
   }
   
   byte getFinalPositionColumn () {
      return finalPositionColumn;
   }
   
   byte getVisitedPositions () {
      return visitedPositions;
   }
   
   boolean isTourClosed () {
      return tourClosed;
   }

   String getKnightsTourSummary() {
      String summary = KnightsTourDescription.generateStartingPositionsString(startingPositionRow, startingPositionColumn);
      summary += "\n" + "Last position in row: " + finalPositionRow;
      summary += "\n" + "Last position in column: " + finalPositionColumn;
      summary += "\n" + "Number of visited positions: " + visitedPositions;
      summary += "\n" + "Tour was closed?: " + String.format("%B", tourClosed);
      
      return summary;
   }
   
   static void insertSortByVisitedPositions(KnightsTourSummary[] array, KnightsTourSummary newSummary) {
      byte newSummaryPositions = newSummary.getVisitedPositions();
      byte indexSummaryPositions;
      
      for (int index = 0; index < array.length; index++) {
         if (array[index] == null) {
            array[index] = newSummary;
            break;
         }
         else {
            indexSummaryPositions = array[index].getVisitedPositions();
            if (indexSummaryPositions > newSummaryPositions) {
               moveForwardIndexes(array, index, newSummary);
               break;
            }
         }
      }
   }
   
   static void moveForwardIndexes(KnightsTourSummary[] array, int fromIndex, KnightsTourSummary summary) {
      KnightsTourSummary temp;
      for (int index = fromIndex; index < array.length; index++) {
         temp = array[index];
         array[index] = summary;
         summary      = temp;
      }
   }
} 
