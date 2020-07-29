/* =====================================================================================
 *       Filename:  ThreeDimensionalShape.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.14 - ThreeDimensionalShape abstract class 
                                inherits from Shape 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public abstract class ThreeDimensionalShape extends Shape {
   
   protected ThreeDimensionalShape(String lengthName) {
      super(lengthName);
   }
   
   @Override
   public String toString() {
      return String.format("%20s 3-dimensional", super.toString());
   }
   
   public abstract double calculateArea();
   
   public abstract double calculateVolume();
} 
