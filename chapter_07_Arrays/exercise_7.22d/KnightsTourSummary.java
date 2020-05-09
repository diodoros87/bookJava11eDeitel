/* =====================================================================================
 *       Filename:  KnightsTourSummary.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.22c - summary of finding knight's
                                tour on virtual chessboard by
                                   heuristic accessibility algorithm
                                
                           
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
   
   KnightsTourSummary (byte startingPositionRow, byte startingPositionColumn,
               byte finalPositionRow, byte finalPositionColumn, byte visitedPositions) {
      this.startingPositionRow = startingPositionRow;
      this.startingPositionColumn = startingPositionColumn;
      this.finalPositionRow = finalPositionRow;
      this.finalPositionColumn = finalPositionColumn;
      this.visitedPositions = visitedPositions;
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

   String getKnightsTourSummary() {
      String summary = KnightsTourDescription.generateStartingPositionsString(startingPositionRow, startingPositionColumn);
      summary += "\n" + "Last position in row: " + finalPositionRow;
      summary += "\n" + "Last position in column: " + finalPositionColumn;
      summary += "\n" + "Number of visited positions: " + visitedPositions;
      
      return summary;
   }
} 
