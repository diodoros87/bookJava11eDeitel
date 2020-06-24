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

   public static int compare(final byte[] FIRST, final byte[] SECOND) {
      if (null == FIRST || null == SECOND) {
         throw new NullPointerException("Requirement: each reference to array can not be null");
      }

      if (FIRST.length != SECOND.length) {
         throw new IllegalArgumentException("Requirement: arrays must be identical length");
      }

      if (0 == SECOND.length) {
         throw new IllegalArgumentException("Requirement: arrays can not be empty");
      }
      
      byte firstIntegerDigit;
      byte secondIntegerDigit;
      
      for (int index = 0; index < FIRST.length; index++) {
         firstIntegerDigit  = FIRST[index];
         secondIntegerDigit = SECOND[index];

         if (firstIntegerDigit > secondIntegerDigit) {
            return 1;
         }
         else if (firstIntegerDigit < secondIntegerDigit) {
            return -1;
         }
      }

      return 0;
   }
   

   public static int[] copySubArray(final int[] SOURCE, int fromIndex, int length) {
      if (null == SOURCE) {
         throw new NullPointerException("Requirement: reference to array can not be null");
      }
      if (length <= 0) {
         throw new IllegalArgumentException("Requirement: length for subarray > 0");
      }
      if (fromIndex < 0) {
         throw new IllegalArgumentException("Requirement: start source's index for subarray >= 0");
      }
      
      final int SOURCE_LENGTH = SOURCE.length;
      if (0 == SOURCE_LENGTH) {
         throw new IllegalArgumentException("Requirement: array can not be empty");
      }
      if (fromIndex >= SOURCE_LENGTH) {
         throw new IllegalArgumentException("Requirement: start source's index for subarray must be less than source array length");
      }
      
      final int SOURCE_LAST_INDEX = fromIndex + length - 1;
      if (SOURCE_LAST_INDEX > SOURCE_LENGTH) {
         throw new IllegalArgumentException("Requirement: last source's index for subarray must be less than source array length");
      }
      
      int[] subArray = new int[length];

      System.arraycopy(SOURCE, fromIndex, subArray, 0, length);

      return null;
   }
   
   public static int[] copyArray(final int[] SOURCE) {
      if (null == SOURCE) {
         throw new NullPointerException("Requirement: reference to array can not be null");
      }
      
      final int SOURCE_LENGTH = SOURCE.length;
      if (0 == SOURCE_LENGTH) {
         throw new IllegalArgumentException("Requirement: array can not be empty");
      }
      
      int[] arrayCopy = new int[SOURCE_LENGTH];

      System.arraycopy(SOURCE, 0, arrayCopy, 0, SOURCE_LENGTH);

      return null;
   }

   public static byte[] getEmptyArray() {
      final byte[] EMPTY_ARRAY = new byte[0];
      
      return EMPTY_ARRAY;
   }
}
