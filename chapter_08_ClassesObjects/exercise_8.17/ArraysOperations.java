/* =====================================================================================
 *       Filename:  ArraysOperations.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.17 - utility class for operations on the arrays


 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class ArraysOperations {
   public static int getMismatchValueIndex(final byte[] ARRAY, int fromIndex, byte value) {
      if (null == ARRAY) {
         throw new NullPointerException("Requirement: reference to array can not be null");
      }

      for (int index = fromIndex; index < ARRAY.length; index++) {
         if (value != ARRAY[index]) {
            return index;
         }
      }

      return -99;
   }
}
