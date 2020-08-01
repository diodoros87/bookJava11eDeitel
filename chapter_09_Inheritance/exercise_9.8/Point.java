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

public class Point implements Cloneable {
   private final String name;
   private double x;
   private double y;

   public Point(String name, double x, double y) {
      ValidateParameters.checkBlankString(name);
      Point.validateCoordinations(x, y);    

      this.x    = x;
      this.y    = y;
      this.name = name;
   }
   
   public Point(Point point) {
      ValidateParameters.checkNullPointer(point);
      ValidateParameters.checkBlankString(point.getName());
      Point.validateCoordinations(point.getX(), point.getY());    

      this.x    = point.getX();
      this.y    = point.getY();
      this.name = point.getName();
   }
   
   public void setX(double x) {
      Point.validateCoordinations(x);
      
      this.x    = x;
   }
   
   public double getX() {
      return x;
   }
   
   public void setY(double y) {
      Point.validateCoordinations(y);
      
      this.y    = y;
   }
   
   public double getY() {
      return y;
   }
   
   public String getName() {
      return name;
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      Point point = new Point(this);
      //Point point = (Point)super.clone();  // other method to achieve identical result
     
      return point;
   }
   
   public boolean isPointLieBetweenCoordinations(Point first, Point second) {
      ValidateParameters.checkNullPointer(first, second);
      
      double minX = Math.min(first.getX(), second.getX());
      double minY = Math.min(first.getY(), second.getY());
      double maxX = Math.max(first.getX(), second.getX());
      double maxY = Math.max(first.getY(), second.getY());
      
      if (this.x >= minX && this.x <= maxX) {
         if (this.y >= minY && this.y <= maxY) {
            
            return true;
         }
      }
      
      return false;
   }
   
   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      }
      if (null == other) {
         return false;
      }
      
      if (getClass() != other.getClass()) {
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
   
   public static void validateCoordinations(double... coordinationsArray) {
      ValidateParameters.checkNullPointer(coordinationsArray);
      
      for (double coordination : coordinationsArray) {
         if (false == Double.isFinite(coordination)) {
            throw new IllegalArgumentException("Coordination must be finite and can not be " + coordination);
         }
      }
      
   }
   
   public static Point getIdenticalPoint(Point... pointsArray) {
      ValidateParameters.checkNullPointer((Object[])pointsArray);
      
      for (int index = 0; index < pointsArray.length - 1; index++) {
         for (int nextIndex = index + 1; nextIndex < pointsArray.length; nextIndex++) {
            if (true == pointsArray[index].equals(pointsArray[nextIndex])) {
               
               return pointsArray[index];
            }
         }
      }
      
      return null;
   }
   
   public static void assignPoints(Point[] destination, final Point[] SOURCE, final int COPIES) {
      if (COPIES <= 0) {
         throw new IllegalArgumentException("Requirement: copies > 0");
      }
      ValidateParameters.checkNullPointerOnlyArray(destination);
      ValidateParameters.checkNullPointer((Object[])SOURCE);
      
      if (COPIES > destination.length) {
         throw new IllegalArgumentException("Requirement: copies <= destinations array's length");
      }
      if (COPIES > SOURCE.length) {
         throw new IllegalArgumentException("Requirement: copies <= source array's length");
      }
      
      for (int index = 0; index < COPIES; index++) {
         destination[index] = new Point(SOURCE[index]);   // Point has not final coordinations (its coordinations may change)
         //destination[index] = SOURCE[index];
      }
   }
} 
