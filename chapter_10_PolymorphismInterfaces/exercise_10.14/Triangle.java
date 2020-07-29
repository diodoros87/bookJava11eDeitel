/* =====================================================================================
 *       Filename:  Triangle.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.14 - Triangle concrete class inherits from 
                                TwoDimensionalShape
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public final class Triangle extends TwoDimensionalShape {
   private double[] sides = new double[3];
   
   public Triangle(double side0, double side1, double side2) {
      super("side");
      validateLength(side1, side2, side0);
      checkTriangleInequality(side1, side2, side0);
      
      assignSides(side0, side1, side2);
   }
   
   public static final void checkTriangleInequality(double side1, double side2, double side0) {
      checkOneOfTriangleInequality(side1, side2, side0);
      checkOneOfTriangleInequality(side1, side0, side2);
      checkOneOfTriangleInequality(side0, side2, side1);
   }
   
   public static final void checkOneOfTriangleInequality(double side1, double side2, double side0) {
      if (side1 + side2 <= side0) {
         throw new IllegalArgumentException(String.format(
                   "Triangle inequality not passed: %e + %e <= %e", side1, side2, side0));
      }
   }
   
   public void setSides(double side1, double side2, double side0) {
      validateLength(side1, side2, side0);
      checkTriangleInequality(side1, side2, side0);
      
      assignSides(side1, side2, side0);
   }
   
   private final void assignSides(double... lengths) {
      for (int index = 0; index < this.sides.length; index++) {
         this.sides[index] = lengths[index];
      }
   }
   
   public double getSide(int index) {
      try {
         
         return sides[index];
      } catch (ArrayIndexOutOfBoundsException exception) {
         throw new ArrayIndexOutOfBoundsException("Requirement: triangle's correct sides indexes are: 0, 1, 2");
      }
   }
   
   @Override
   public double calculateArea() {
      double semiperimeter = (getSide(0) + getSide(1) + getSide(2)) / 2;
      
      double squareOfArea = semiperimeter;
      for (double side : sides) {
         squareOfArea *= semiperimeter - side;
      }
      
      return Math.sqrt(squareOfArea);
   }
   
   @Override
   public String toString() {
      String string = String.format("%40s", super.toString());

      for (int index = 0; index < this.sides.length; index++) {
         string += String.format("   side = %e", getSide(index));
      }
      
      return string;
   }
} 
