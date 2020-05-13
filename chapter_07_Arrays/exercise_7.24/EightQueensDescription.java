/* =====================================================================================
 *       Filename:  EightQueensDescription.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.24 - utility class to description of finding
                                queen's tour on virtual chessboard by
                                   heuristic accessibility algorithm
                                
                           
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class EightQueensDescription {
   public static final char queenCharacter   = 'Q';
   public static final char attackedCharacter  = '#';
   public static final char emptyPlaceCharacter = '-';
   
   static String generateBoardString(EightQueens eightQueens, boolean numbersAppending) {
      String boardString = "";
      byte [][] board = eightQueens.getBoard();
      
      if (true == numbersAppending) {
         boardString = appendNumbersRow();
      }
      
      for (int row = 0, queenColumn = Integer.MIN_VALUE; row < EightQueens.ROWS; row++, queenColumn = Integer.MIN_VALUE) {
         if (true == numbersAppending) {
            boardString += row;
         }
            
         for (int column = 0; column < EightQueens.COLUMNS; column++) {
         
            if (board[row][column] == EightQueens.QUEEN) {
               boardString += String.format("%2c", queenCharacter); 
               queenColumn = column;
            }
            else if (board[row][column] == EightQueens.ATTACKED) {
               boardString += String.format("%c%c", attackedCharacter, attackedCharacter); 
            }
            else {
               boardString += String.format("%c%c", emptyPlaceCharacter, emptyPlaceCharacter); 
            }
            
         }
         
         if (true == numbersAppending) {
            boardString += row;
         }
         if (queenColumn != Integer.MIN_VALUE) {   // if true, queen is in column
            boardString = boardString.concat(generateQueensPositions(row, queenColumn));
         }
         
         boardString = boardString.concat("\n"); 
      }
      
      if (true == numbersAppending) {
         boardString = boardString.concat(appendNumbersRow());
      }
      
      return boardString;
   }
   
   static String appendNumbersRow() {
      String numbersRowString = " ";
      
      for (int column = 0; column < EightQueens.ROWS; column++) {
         numbersRowString += " " + column; 
      }
      
      numbersRowString += "\n"; 
      
      return numbersRowString;
   }
   
   static String generateQueensPositions(int row, int column) {
      String queensData = String.format("%10s ", " ");
      queensData += String.format("Queen is in row %d and column %d", row, column);
      
      return queensData;
   }
   
   static String generatePositionsElimination(EightQueens eightQueens, String title) {
      System.out.println(title);
      
      String result = "";
      byte [][] positionsElimination = eightQueens.getPositionsElimination();
      byte [][] board = eightQueens.getBoard();
      
      for (int row = 0; row < EightQueens.ROWS; row++) {
            
         for (int column = 0; column < EightQueens.COLUMNS; column++) {
         
            switch (board[row][column]) {
               case EightQueens.QUEEN:
                  result += String.format("%5c", queenCharacter);
                  break;
               case EightQueens.ATTACKED:
                  result += String.format("%5c", attackedCharacter);
                  break;
               default:
                  result += String.format("%5d", positionsElimination[row][column]); // number of eliminated positions
            }
            
         }
         
         result = result.concat("\n"); 
      }
      
      return result;
   }
   
} 
