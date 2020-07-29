/* =====================================================================================
 *       Filename:  Ball.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.14 - Ball concrete class inherits from 
                                ThreeDimensionalShape
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public final class Ball extends ThreeDimensionalShape {
   private double radius;
   
   public Ball(double radius) {
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
      return 4 * Math.PI * getRadius() * getRadius();
   }
   
   @Override
   public double calculateVolume() {
      return 4 * Math.PI * getRadius() * getRadius() * getRadius() / 3;
   }
   
   @Override
   public String toString() {
      return String.format("%40s radius = %e", super.toString(), getRadius());
   }
} 
