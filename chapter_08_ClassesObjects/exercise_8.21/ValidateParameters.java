/* =====================================================================================
 *       Filename:  ValidateParameters.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.21 - class to validate parameters
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class ValidateParameters {
   
   public static void checkNullPointer(Object... objectArray) {
      for (Object object : objectArray) {
         if (object == null) {
            throw new NullPointerException();
         }
      }
   }
   
   public static void checkBlankString(String... stringArray) {
      Object[] objects = stringArray;
      checkNullPointer(objects);
      
      for (String string : stringArray) {
         if (string.isBlank() == true) {
            throw new IllegalArgumentException("blank string");
         }
      }
   }
}
