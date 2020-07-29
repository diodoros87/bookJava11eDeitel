/* =====================================================================================
 *       Filename:  ShapeHierarchyTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.14 - Shape hierarchy test program
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import validateParametersPackage.ValidateParameters;

public class ShapeHierarchyTest {
   public static void main(String[] args) {
      Shape[] shapes = new Shape[6];
      
      createShapes2D(shapes, 0);
      createShapes3D(shapes, 3);               

      printShapesCalculations(shapes);             
   } 
   
   private static void createShapes2D(Shape[] shapes, int fromIndex) {
      Circle circle = new Circle(100); 
      Square square = new Square(44);  
      Triangle triangle = new Triangle(5, 3, 4); 

      assignShapes(shapes, fromIndex, circle, square, triangle);     
   }
   
   private static void createShapes3D(Shape[] shapes, int fromIndex) {
      Ball ball = new Ball(7); 
      Cube cube = new Cube(5);  
      RegularTetrahedron tetrahedron = new RegularTetrahedron(6); 
      
      assignShapes(shapes, fromIndex, ball, cube, tetrahedron);     
   }
   
   public static void assignShapes(Shape[] array, int fromIndex, Shape... shapes) {
      if (0 > fromIndex) {
         throw new IllegalArgumentException("fromIndex (" + fromIndex + ") must be >= 0");
      }

      ValidateParameters.checkNullPointerOnlyArray((Object[])array);
      ValidateParameters.checkNullPointer((Object[])shapes);
      if (fromIndex >= array.length) {
         throw new IllegalArgumentException("fromIndex (" + fromIndex + ") must be < array.length (" + array.length + ") ");
      }
      if (array.length - fromIndex < shapes.length) {
         throw new IllegalArgumentException(String.format(
                  "Requirement: array.length(%d) - fromIndex(%d) >= shapes.length(%d)))",
                  array.length, fromIndex, shapes.length));
      }
      
      for (int index = 0; index < shapes.length; index++) {
         array[fromIndex + index] = shapes[index];
      }
   }
   
   public static void printShapesCalculations(Shape... shapes) {
      ValidateParameters.checkNullPointer((Object)shapes);
      for (Shape currentShape : shapes) {
         System.out.println(currentShape); 
         
         printCalculations(currentShape);
      } 
   }   
   
   public static void printCalculations(Shape shape) {
      ValidateParameters.checkNullPointer(shape);
      
      if (shape instanceof TwoDimensionalShape) {
         TwoDimensionalShape shape2D = (TwoDimensionalShape) shape;
         
         System.out.printf("Area : %e %n", shape2D.calculateArea());
      }
      else if (shape instanceof ThreeDimensionalShape) {
         ThreeDimensionalShape shape3D = (ThreeDimensionalShape) shape;
         
         System.out.printf("Area   : %e %n", shape3D.calculateArea());
         System.out.printf("Volume : %e %n", shape3D.calculateVolume());
      }
      else {
         throw new IllegalArgumentException("shape is unknown type " + shape.getClass().getName());    
      } 
   }                    
} 
