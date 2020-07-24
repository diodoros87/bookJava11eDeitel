/* =====================================================================================
 *       Filename:  Point.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - Point class
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import validateParametersPackage.ValidateParameters;

public class Point {
   private final String name;
   private double x;
   private double y;

   public Point(String name, double x, double y) {
      ValidateParameters.checkBlankString(name);
      Point.validateCoordination(x, y);    

      this.x    = x;
      this.y    = y;
      this.name = name;
   }
   
   public Point(Point point) {
      ValidateParameters.checkNullPointer(point);
      ValidateParameters.checkBlankString(point.getName());
      Point.validateCoordination(point.getX(), point.getY());    

      this.x    = point.getX();
      this.y    = point.getY();
      this.name = point.getName();
   }
   
   public void setX(double x) {
      Point.validateCoordination(x);
      
      this.x    = x;
   }
   
   public double getX() {
      return x;
   }
   
   public void setY(double y) {
      Point.validateCoordination(y);
      
      this.y    = y;
   }
   
   public double getY() {
      return y;
   }
   
   public String getName() {
      return name;
   }
   
   @Override
   public boolean equals(Object other) {
      if (null == other) {
         return false;
      }
      
      if (false == other instanceof Point) {
         throw new IllegalArgumentException("argument type is " + other.getClass().getName() + " Only type Point is allowed");
      }
      
      Point point = (Point)other;
      if ( getX() == point.getX() && getY() == point.getY() ) {
      
         return true;
      }
      
      return false;
   }
   
   @Override
   public String toString() {
      return String.format("\'%s\'(%f, %f) ", getName(), getX(), getY());  
   } 
   
   public static void validateCoordination(double... coordinationsArray) {
      ValidateParameters.checkNullPointer(coordinationsArray);
      if (coordinationsArray == null) {
         throw new NullPointerException("coordinations array is null");
      }
      
      for (double coordination : coordinationsArray) {
         if (false == Double.isFinite(coordination)) {
            throw new IllegalArgumentException("Coordination must be finite and can not be " + coordination);
         }
      }
      
   }
   
   public static Point getIdenticalPoint(Point... pointsArray) {
      //Object[] objects = pointsArray;
      ValidateParameters.checkNullPointer(pointsArray);
      
      for (int index = 0; index < pointsArray.length - 1; index++) {
         for (int nextIndex = index + 1; nextIndex < pointsArray.length; nextIndex++) {
            if (true == pointsArray[index].equals(pointsArray[nextIndex])) {
               
               return pointsArray[index];
            }
         }
      }
      
      return null;
   }
   
   public static void assignPoints(Point[] destination, final Point[] SOURCE, final int copies) {
      if (copies <= 0) {
         throw new IllegalArgumentException("Requirement: copies > 0");
      }
      ValidateParameters.checkNullPointerOnlyArray(destination);
      ValidateParameters.checkNullPointersInArrays(SOURCE);
      
      if (copies > destination.length) {
         throw new IllegalArgumentException("Requirement: copies <= destinations array's length");
      }
      if (copies > SOURCE.length) {
         throw new IllegalArgumentException("Requirement: copies <= source array's length");
      }
      
      for (int index = 0; index < copies; index++) {
         destination[index] = new Point(SOURCE[index]);   // Point has not final coordinations (its coordinations may change)
         //destination[index] = SOURCE[index];
      }
   }
} 
