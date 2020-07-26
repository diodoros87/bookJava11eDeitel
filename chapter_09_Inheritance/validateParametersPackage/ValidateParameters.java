/* =====================================================================================
 *       Filename:  ValidateParameters.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercises to Chapter 9 - utility class using in 
                                programs in exercises to chapter 9 to 
                                validate parameters
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
package validateParametersPackage;

public class ValidateParameters {
   
   public static void checkNullPointer(Object... objectArray) {
      checkNullPointerOnlyArray(objectArray);
      
      for (Object object : objectArray) {
         if (object == null) {
            throw new NullPointerException();
         }
      }
   }
   
   public static void checkNullPointerOnlyArray(Object[] objectArray) {
      if (objectArray == null) {
         throw new NullPointerException();
      }
   }
   
   public static void checkNullPointersInArrays(Object[]... objectArrays) {
      checkNullPointer(objectArrays); //checkNullPointerOnlyArray(objectArrays);
      
      for (Object[] array : objectArrays) {
         checkNullPointer(array);
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
