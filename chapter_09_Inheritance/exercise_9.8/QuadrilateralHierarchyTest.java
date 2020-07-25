/* =====================================================================================
 *       Filename:  QuadrilateralHierarchyTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.8 - quadrilateral's hierarchy test program
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
import java.math.BigDecimal;

import validateParametersPackage.ValidateParameters;

public class QuadrilateralHierarchyTest {
   
   public static void main(String[] args) {
      Point[] vertices = { new Point("A", -1, -1), new Point("B", -1, 1), new Point("C", 1, 1), new Point("D", 1, -1) };
      Trapezoid trapez = new Trapezoid(vertices);
      AreaCalculating.printArea(trapez);
      
      testAreaCalculating();
      testIncorrectAreaCalculating();
      //testIncorrectSettings();
   }  
   
   private static void testAreaCalculating() {
      AreaCalculating areaCalculating  = new AreaCalculating();
      
      double[][] verticesCoordinations = { {0, 0}, {0, 1}, {1, 1}, {1, 0} };
      areaCalculating.run("Trapezoid", verticesCoordinations);
      areaCalculating.run("Parallelogram", verticesCoordinations);
      areaCalculating.run("Rectangle", verticesCoordinations);
      areaCalculating.run("Square", verticesCoordinations);
      
      double[][] verticesCoordinations_02 = { {-2, -3}, {-2, 4}, {6, 4}, {6, -3} };
      areaCalculating.run("Trapezoid", verticesCoordinations_02);
      areaCalculating.run("Parallelogram", verticesCoordinations_02);
      areaCalculating.run("Rectangle", verticesCoordinations_02);
      areaCalculating.testIncorrectAreaCalculating("Square", verticesCoordinations_02);
      
      double[][] verticesCoordinations_01 = { {0, 0}, {1, 1}, {2, 1}, {1, 0} };
      areaCalculating.run("Trapezoid", verticesCoordinations_01);
      areaCalculating.run("Parallelogram", verticesCoordinations_01);
      areaCalculating.testIncorrectAreaCalculating("Rectangle", verticesCoordinations_01);
      areaCalculating.testIncorrectAreaCalculating("Square", verticesCoordinations_01);
      
      double[][] verticesCoordinations_03 = { {0, 0}, {1, 1}, {2, 1}, {2, 0} };
      areaCalculating.run("Trapezoid", verticesCoordinations_03);
      areaCalculating.testIncorrectAreaCalculating("Parallelogram", verticesCoordinations_03);
      areaCalculating.testIncorrectAreaCalculating("Rectangle", verticesCoordinations_03);
      areaCalculating.testIncorrectAreaCalculating("Square", verticesCoordinations_03);
   }
   
   
   
   private static void testIncorrectAreaCalculating() {
      AreaCalculating areaCalculating  = new AreaCalculating();
      double[][] verticesCoordinations_01 = { {2, 0}, {0, 1}, {1, 1}, {1, 0} };
      areaCalculating.run("Parallelogram", verticesCoordinations_01);
   }
   
   private static void testIncorrectSettings() {
      System.out.printf("%n%n             TESTS OF Quadrilateral INCORRECT SETTINGS:%n%n");
      QuadrilateralIncorrectAttempts.testIncorrectVerticesNumber();
      QuadrilateralIncorrectAttempts.testIdenticalVertices();
      QuadrilateralIncorrectAttempts.testCollinearVertices();
      QuadrilateralIncorrectAttempts.testForbiddenIntersections();
      QuadrilateralIncorrectAttempts.testUnmodifiedVertices();
      
      System.out.printf("%n%n             TESTS OF Trapezoid INCORRECT SETTINGS:%n%n");
      TrapezoidIncorrectAttempts.testParallelSidesAbsention();
      
      System.out.printf("%n%n             TESTS OF Point INCORRECT SETTINGS:%n%n");
      PointIncorrectAttempts.testEqualityPoint();
   }
   
} 

class AreaCalculating {
   Point[] vertices = { new Point("A", -1, -1), new Point("B", -1, 1), new Point("C", 1, 1), new Point("D", 1, -1) };
   
   public void run(String quadrilateralType, double[][] pointCoordinations) {
      modifyVertices(pointCoordinations);
      Quadrilateral createdQuadrilateral = createQuadrilateral(quadrilateralType);
      printArea(createdQuadrilateral);
   }
   
   public Quadrilateral createQuadrilateral(String quadrilateralType) {
      ValidateParameters.checkNullPointer(quadrilateralType);
      switch(quadrilateralType) {
         default:
            throw new IllegalArgumentException(String.format(
                        "\'%s\' is not implemented concret class of quadrilateral", quadrilateralType));
         case "Trapezoid":
            Trapezoid trapezoid = new Trapezoid(vertices);
            return trapezoid;
         case "Parallelogram":
            Parallelogram parallelogram = new Parallelogram(vertices);
            return parallelogram;
         case "Rectangle":
            Rectangle rectangle = new Rectangle(vertices);
            return rectangle;
         case "Square":
            Square square = new Square(vertices);
            return square;
      }
   }
   
   public void modifyVertices(double[][] pointCoordinations) {
      ValidateParameters.checkNullPointer(pointCoordinations);
      
      if (pointCoordinations.length != Quadrilateral.NUMBER_OF_VERTICES) {
         
         throw new IllegalArgumentException("To create any quadrilateral must be exactly " 
                                             + Quadrilateral.NUMBER_OF_VERTICES + " vertices");
      }
      
      for (int point = 0; point < Quadrilateral.NUMBER_OF_VERTICES; point++) {
         if (pointCoordinations[point].length != 2) {
         
            throw new IllegalArgumentException("Every point in 2D must have exactly 2 coordinates");
         }
         
         vertices[point].setX(pointCoordinations[point][0]);
         vertices[point].setY(pointCoordinations[point][1]);
      }
   }
   
   public static void printArea(Quadrilateral quadrilateral) {
      BigDecimal area = quadrilateral.calculateArea();

      System.out.printf("Area of %s is %s %n%n", quadrilateral, area);
   }
   
   public void testIncorrectAreaCalculating(String quadrilateral, double[][] verticesCoordinations) {
      try {  
         run(quadrilateral, verticesCoordinations);
         assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct %s: %s%n", quadrilateral, exception.getMessage());
         exception.printStackTrace();
      }
   }
}

class PointIncorrectAttempts {
   static void testEqualityPoint() {
      Point[] vertices = { new Point("A", -1, -1), new Point("B", -1, 1), new Point("C", 1, 1), new Point("D", 5.57, -1) };
      Trapezoid trapez = new Trapezoid(vertices);
      AreaCalculating.printArea(trapez);
      
      try {  
         vertices[0].equals(trapez);
         assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while call Point's' equals with Trapezoid: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
   }
   
}

class QuadrilateralIncorrectAttempts {
   static void testUnmodifiedVertices() {
      Point[] vertices = { new Point("A", -1, -1), new Point("B", -1, 1), new Point("C", 1, 1), new Point("D", 2, -1) };
      Trapezoid trapez = new Trapezoid(vertices);
      
      System.out.printf("Before trying unmodified vertices: %n %s %n", trapez);
      
      vertices[0] = new Point("E", 5, 6);
      vertices[1].setX(67);
      
      System.out.printf("After trying unmodified vertices: %n %s %n", trapez);
      AreaCalculating.printArea(trapez);
   }
   
   static void testForbiddenIntersections() {
      try {  
         Point[] vertices = { new Point("A", 5, 6), new Point("B", -5, -6), new Point("C", -5, 6), new Point("D", 5, -6) };
         Trapezoid trapez = new Trapezoid(vertices);
         assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct Trapezoid: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
      
      try {  
         Point[] vertices = { new Point("A", 5, 6), new Point("B", -5, -6), new Point("C", 55, 6), new Point("D", 5, -6) };
         Trapezoid trapez = new Trapezoid(vertices);
         assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct Trapezoid: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
      
   }
   
   static void testIncorrectVerticesNumber() {
      try {  
         Point[] vertices = { new Point("A", 5, 6), new Point("B", -5, -6), new Point("C", 75, 6) };
         Trapezoid trapez = new Trapezoid(vertices);
         assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct Trapezoid: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
   }
   
   static void testIdenticalVertices() {
      try {  
         Point[] vertices = { new Point("A", 5, 6), new Point("B", -5, -6), new Point("C", 5, 6), new Point("D", 5, -6) };
         Trapezoid trapez = new Trapezoid(vertices);
         assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct Trapezoid: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
   }
   
   static void testCollinearVertices() {
      try {  
         Point[] vertices = { new Point("A", 0, 0), new Point("B", 1, 1), new Point("C", 2, 2), new Point("D", 5, -6) };
         Trapezoid trapez = new Trapezoid(vertices);
         assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct Trapezoid: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
      
      try {  
         Point[] vertices = { new Point("A", 0, 0), new Point("B", 1.5, 3), new Point("C", 2, 2), new Point("D", -3, -6) };
         Trapezoid trapez = new Trapezoid(vertices);
         assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct Trapezoid: %s%n", exception.getMessage());
         exception.printStackTrace();
      }   
   }
}

class TrapezoidIncorrectAttempts {
   static void testParallelSidesAbsention() {
      try {  
         Point[] vertices = { new Point("A", -1, 0), new Point("B", 0, 2), new Point("C", 4, 0), new Point("D", 0, -1)};
         Trapezoid trapez = new Trapezoid(vertices);
         assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct Trapezoid: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
   }
}
