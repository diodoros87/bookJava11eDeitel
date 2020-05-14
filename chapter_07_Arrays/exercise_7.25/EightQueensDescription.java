/* =====================================================================================
 *       Filename:  EightQueensDescription.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.25 - utility class to description of solving
                                 eight queens problem
                                on virtual chessboard by brute force algorithm
                                
                           
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class EightQueensDescription {
   public static final String INFO = String.format("%s %n %s%n", "Program was written in purpose of test of tracking while ",
                                "solving eight queens problem on virtual chessboard by brute force algorithm");
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
               boardString += String.format("%5c", queenCharacter); 
               queenColumn = column;
            }
            else if (board[row][column] >= EightQueens.ATTACKED) {
               boardString += String.format("%5c", attackedCharacter); 
            }
            else {
               boardString += String.format("%5c", emptyPlaceCharacter); 
            }
            
         }
         
         if (true == numbersAppending) {
            boardString += String.format("%5d", row);
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
         numbersRowString += String.format("%5d", column);  
      }
      
      numbersRowString += "\n"; 
      
      return numbersRowString;
   }
   
   static String generateQueensPositions(int row, int column) {
      String queensData = String.format("%10s ", " ");
      queensData += String.format("Queen is in row %d and column %d", row, column);
      
      return queensData;
   }
   
} 
