/* =====================================================================================
 *       Filename:  TwoDimensionalShape.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.14 - TwoDimensionalShape abstract class 
                                inherits from Shape 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public abstract class TwoDimensionalShape extends Shape {
   
   protected TwoDimensionalShape(String lengthName) {
      super(lengthName);
   }
   
   @Override
   public String toString() {
      return String.format("%20s 2-dimensional", super.toString());
   }
   
   public abstract double calculateArea();
} 
