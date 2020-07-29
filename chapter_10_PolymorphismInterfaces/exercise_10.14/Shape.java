/* =====================================================================================
 *       Filename:  Shape.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.14 - Shape abstract class 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public abstract class Shape {
   private final String lengthName;
   
   protected Shape(String lengthName) {
      this.lengthName = lengthName;
   }
   
   protected final void validateLength(double... lengths) {
      for (double length : lengths) {
         if (length <= 0) {
            throw new IllegalArgumentException(String.format(
                                          "Requirement: %s of %s > 0", lengthName, this.getClass().getName()));
         }
      }
   }
   
   @Override
   public String toString() {
      return this.getClass().getName();
   }
} 
