/* =====================================================================================
 *       Filename:  KnightsTourDescription.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.22c - utility class to description of finding
                                knight's tour on virtual chessboard by
                                   heuristic accessibility algorithm
                                
                           
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class KnightsTourDescription {
   public static final char knightCharacter   = 'K';
   public static final char visitedCharacter  = '#';
   public static final char noTracedCharacter = '-';
   
   static String getMoveDescription(KnightsTour knightsTour, byte moveNumber) {
      if (false == knightsTour.validateMoveNumber(moveNumber)) {
         return String.format("unrecognized move's number %d", moveNumber);
      }
      
      return String.format("rows += %d     columns += %d", 
                        KnightsTour.vertical[moveNumber], KnightsTour.horizontal[moveNumber]);
   }
   
   static String generateStartingPositionsString(byte startingPositionRow, byte startingPositionColumn) {
      return "Tour's starting position in row: " + startingPositionRow + "  in column: " + startingPositionColumn;
   }
   
   static String generateBoardString(KnightsTour knightsTour, boolean numbersAppending) {
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
               boardString += String.valueOf(knightCharacter); 
            }
            else if (board[row][column] == KnightsTour.VISITED) {
               boardString += String.valueOf(visitedCharacter); 
            }
            else {
               boardString += String.valueOf(noTracedCharacter); 
            }
            
         }
         
         if (true == numbersAppending) {
            boardString += row;
         }
         
         boardString = boardString.concat(generateCurrentKnightsData(knightsTour, row));
         boardString = boardString.concat("\n"); 
      }
      
      if (true == numbersAppending) {
         boardString = boardString.concat(appendNumbersRow());
      }
      
      return boardString;
   }
   
   static String appendNumbersRow() {
      String numbersRowString = " ";
      
      for (int column = 0; column < KnightsTour.ROWS; column++) {
         numbersRowString += column; 
      }
      
      numbersRowString += "\n"; 
      
      return numbersRowString;
   }
   
   static String generateCurrentKnightsData(KnightsTour knightsTour, int row) {
      String currentKnightsDataRow = String.format("%10s", " ");
      
      switch (row) {
         case 1:
            currentKnightsDataRow += "Knight:               " + String.valueOf(knightCharacter);
               break;
         case 2:
            currentKnightsDataRow += "Visited positions:    " + String.valueOf(visitedCharacter);
               break;
         case 3:
            currentKnightsDataRow += "No visited positions: " + String.valueOf(noTracedCharacter);
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
   
   static String generateKnightsToursSummaries(KnightsTourSummary[][] knightsTourSummaries) {
      String summaries = String.format("%s %s %s %s %s %s %s %n", "Tour's number", "Start row", "Start column", "Final row", "Final column",
                                                "Visited positions", "Tour was complete?");
      
      for (byte row = 0, counter = 1; row < knightsTourSummaries.length; row++) {
         for (byte column = 0; column < knightsTourSummaries[row].length; column++, counter++) {
            KnightsTourSummary tourSummary = knightsTourSummaries[row][column];
            
            byte startingRow = tourSummary.getStartingPositionRow();
            byte startingColumn = tourSummary.getStartingPositionColumn();
            byte finalRow = tourSummary.getFinalPositionRow();
            byte finalColumn = tourSummary.getFinalPositionColumn();
            byte visitedPositions = tourSummary.getVisitedPositions();
            
            summaries += String.format("%-13d %9d %12d %9d %12d %17d %18B %n", counter, startingRow, startingColumn, 
                                       finalRow, finalColumn, visitedPositions, visitedPositions == KnightsTour.POSITIONS);
         }
      }
      
      return summaries;
   }
   
} 
