/* =====================================================================================
 *       Filename:  ValidateParameters.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.22 - utility class to validate parameters 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
*/
 
public class ValidateParameters {
   
   public static void checkNullPointer(Object... objectArray) {
      if (objectArray == null) {
         throw new NullPointerException();
      }
      
      for (Object object : objectArray) {
         if (object == null) {
            throw new NullPointerException();
         }
      }
   }
}
