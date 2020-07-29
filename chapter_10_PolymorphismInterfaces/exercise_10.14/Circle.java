/* =====================================================================================
 *       Filename:  Circle.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.14 - Circle concrete class inherits from 
                                TwoDimensionalShape
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public final class Circle extends TwoDimensionalShape {
   private double radius;
   
   public Circle(double radius) {
      super("radius");
      validateLength(radius);
      
      this.radius = radius;
   }
   
   public void setRadius(double radius) {
      validateLength(radius);
      
      this.radius = radius;
   }
   
   public double getRadius() {
      return radius;
   }
   
   @Override
   public double calculateArea() {
      return Math.PI * getRadius() * getRadius();
   }
   
   @Override
   public String toString() {
      return String.format("%40s radius = %e", super.toString(), getRadius());
   }
} 
