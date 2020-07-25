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

public class LineSegment implements Cloneable {
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
   
   @Override
   protected Object clone() throws CloneNotSupportedException {
      super.clone();
      LineSegment lineSegment = new LineSegment(this.EXTREMITIES);
        
      return lineSegment;
   }
   
   public BigDecimal calculateLength() {
      BigDecimal extremity_0X = BigDecimal.valueOf(EXTREMITIES[0].getX());
      BigDecimal extremity_0Y = BigDecimal.valueOf(EXTREMITIES[0].getY());
      BigDecimal extremity_1X = BigDecimal.valueOf(EXTREMITIES[1].getX());
      BigDecimal extremity_1Y = BigDecimal.valueOf(EXTREMITIES[1].getY());
      
      BigDecimal horizontal   = extremity_0X.subtract(extremity_1X, Line.MATH_CONTEXT);
      BigDecimal vertical     = extremity_0Y.subtract(extremity_1Y, Line.MATH_CONTEXT);
      
      horizontal   = horizontal.abs(Line.MATH_CONTEXT);
      vertical     = vertical.abs(Line.MATH_CONTEXT);
      
      BigDecimal squareHorizontal = horizontal.multiply(horizontal, Line.MATH_CONTEXT);
      BigDecimal squareVertical   = vertical.multiply(vertical, Line.MATH_CONTEXT);
      
      BigDecimal length = squareHorizontal.add(squareVertical, Line.MATH_CONTEXT);
      length            = length.sqrt(Line.MATH_CONTEXT);
      
      return length;
   }
   
   protected Point[] getExtremities() {
      Point[] extremitiesCopy = Arrays.copyOf(EXTREMITIES, EXTREMITIES.length);
      
      return extremitiesCopy;
   }
   
   public Point getIntersectionPoint(LineSegment other) {
      ValidateParameters.checkNullPointer(other);
      
      final Line OTHER_LINE = other.getLine();
      Point intersectionPoint = LINE.calculateIntersectionPoint(OTHER_LINE);
      
      if (null != intersectionPoint) {
         return getIntersectionPoint(intersectionPoint);
      }
      
      return null;
   }
   
   private Point getIntersectionPoint(Point intersectionPoint) {
      double intersectionPoint_X = intersectionPoint.getX();
      double intersectionPoint_Y = intersectionPoint.getY();
      
      double minX = Math.min(EXTREMITIES[0].getX(), EXTREMITIES[1].getX());
      double minY = Math.min(EXTREMITIES[0].getY(), EXTREMITIES[1].getY());
      double maxX = Math.max(EXTREMITIES[0].getX(), EXTREMITIES[1].getX());
      double maxY = Math.max(EXTREMITIES[0].getY(), EXTREMITIES[1].getY());
      
      if (intersectionPoint_X >= minX && intersectionPoint_X <= maxX) {
         if (intersectionPoint_Y >= minY && intersectionPoint_Y <= maxY) {
            
            return new Point("I", intersectionPoint_X, intersectionPoint_Y);
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
