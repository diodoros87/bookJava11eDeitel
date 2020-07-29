/* =====================================================================================
 *       Filename:  Square.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.14 - Square concrete class inherits from 
                                TwoDimensionalShape
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public final class Square extends TwoDimensionalShape {
   private double side;
   
   public Square(double side) {
      super("side");
      validateLength(side);
      
      this.side = side;
   }
   
   public void setSide(double side) {
      validateLength(side);
      
      this.side = side;
   }
   
   public double getSide() {
      return side;
   }
   
   @Override
   public double calculateArea() {
      return getSide() * getSide();
   }
   
   @Override
   public String toString() {
      return String.format("%40s side = %e", super.toString(), getSide());
   }
} 
