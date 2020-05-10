/* =====================================================================================
 *       Filename:  RandomMoves.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.23b - class of finding knight's tour on virtual
                                chessboard by random moves
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
import java.security.SecureRandom;
import java.util.Arrays;
 
public class RandomMoves {
   public static final SecureRandom randomNumbers = new SecureRandom();
   
   public static byte generateRandomMove() {
      int moveNumber = randomNumbers.nextInt(KnightsTour.MAX_MOVES);

      return (byte)moveNumber; 
   }
   
   static byte getNumberOfRandomPerformedMove(KnightsTour knightsTour) {
      boolean [] movesToCheck = new boolean [KnightsTour.MAX_MOVES];
      byte moveNumber;
      
      Arrays.fill(movesToCheck, false);
      while (true == isValue(movesToCheck, false)) {  // while all moves to check has not checked yet
         moveNumber = generateRandomMove();
         
         if (false == movesToCheck[moveNumber]) { // move with moveNumber has not checked yet
            if (true == knightsTour.move(moveNumber)) {  // when move was performed
               return moveNumber;
            }
            
            movesToCheck[moveNumber] = true;      // move with moveNumber was checked
         }
      }
      
      return Byte.MIN_VALUE;   // return incorrect data when move was not performed
   }
   
   public static boolean isValue(boolean [] array, boolean value) {
      for (boolean element : array) {
         if (element == value) {
            return true;
         }
      }
      
      return false;
   }
   
} 
