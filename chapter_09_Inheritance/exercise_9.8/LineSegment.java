/* =====================================================================================
 *       Filename:  LineSegment.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - class about line segment
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Arrays;
import java.math.BigDecimal;

import validateParametersPackage.ValidateParameters;

public class LineSegment {
   public  static final int NUMBER_OF_EXTREMITIES = 2;

   private final Point[] EXTREMITIES = new Point[NUMBER_OF_EXTREMITIES]; 
   private final Line LINE;
   
   public LineSegment(Point... pointsArray) {
      LineSegment.validatePoints(pointsArray);
      LINE = new Line(pointsArray[0], pointsArray[1]);
      Point.assignPoints(this.EXTREMITIES, pointsArray, NUMBER_OF_EXTREMITIES);
   }
   /*
   public BigDecimal getA() {
      return LINE.getA();
   }
   
   public BigDecimal getB() {
      return LINE.getB();
   }
   
   public BigDecimal getC() {
      return LINE.getC();
   }
   */
   public Line getLine() {
      return LINE;
   }
   
   public BigDecimal calculateLength() {
      BigDecimal extremity_0X = BigDecimal.valueOf(EXTREMITIES[0].getX());
      BigDecimal extremity_0Y = BigDecimal.valueOf(EXTREMITIES[0].getY());
      BigDecimal extremity_1X = BigDecimal.valueOf(EXTREMITIES[1].getX());
      BigDecimal extremity_1Y = BigDecimal.valueOf(EXTREMITIES[1].getY());
      
      BigDecimal horizontal   = extremity_0X.subtract(extremity_1X, Line.MATH_CONTEXT);
      BigDecimal vertical     = extremity_0Y.subtract(extremity_1Y, Line.MATH_CONTEXT);
      
      BigDecimal squareHorizontal = horizontal.multiply(horizontal, Line.MATH_CONTEXT);
      BigDecimal squareVertical   = vertical.multiply(vertical, Line.MATH_CONTEXT);
      
      BigDecimal length = squareHorizontal.add(squareVertical, Line.MATH_CONTEXT);
      length            = length.sqrt(Line.MATH_CONTEXT);
      
      return length;
   }
   
   protected Point getExtremity(int index) {
      try {
         return EXTREMITIES[index];
      }
      catch (ArrayIndexOutOfBoundsException exception) {
         throw exception;
      }
   }
   
   public Point getCloneOfExtremity(int index) throws CloneNotSupportedException {
      try {
         return (Point)EXTREMITIES[index].clone();
      }
      catch (ArrayIndexOutOfBoundsException exception) {
         throw exception;
      }
   }
   
   public Point getIntersectionPoint(LineSegment other) {
      ValidateParameters.checkNullPointer(other);
      
      final Line OTHER_LINE   = other.getLine();
      Point intersectionPoint = LINE.calculateIntersectionPoint(OTHER_LINE);
      
      if (null != intersectionPoint) {
         boolean containsPoint = intersectionPoint.isPointLieBetweenCoordinations
                                                (getExtremity(0), getExtremity(1));
         boolean otherContainsPoint = intersectionPoint.isPointLieBetweenCoordinations
                                                   (other.getExtremity(0), other.getExtremity(1));
         //System.out.printf("\nOTHER_LINE %s", OTHER_LINE);
         //System.out.printf("\nLINE %s\n", LINE);
         if (true == containsPoint && true == otherContainsPoint) {
            
            return intersectionPoint;
         }
      }
      
      return null;
   }
   
   @Override 
   public String toString() {
      return String.format(" Line segment %s %s", EXTREMITIES[0], EXTREMITIES[1]);
   } 
   
   public static void validatePoints(Point... pointsArray) {
      Point identicalPoint = Point.getIdenticalPoint(pointsArray);
      if (null != identicalPoint) {
         String message = String.format("Identical extremities %s was detected. %n", identicalPoint)
                        + " Each extremities in line segment must be unique";
                        
         throw new IllegalArgumentException(message);
      }
      
      if (NUMBER_OF_EXTREMITIES != pointsArray.length) {
         throw new IllegalArgumentException("Line segment must contain " + NUMBER_OF_EXTREMITIES + " extremities");
      }
   }
}
