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

public class QuadrilateralHierarchyTest {
   public static void main(String[] args) {

      Point[] vertices = { new Point("A", 5, 6), new Point("B", -5, -6), new Point("C", -5, 6), new Point("D", 5, -6) };
      
      Trapezoid trapez = new Trapezoid(vertices);
      System.out.println(trapez);
      
      //Quadrilateral quadrilateral = new Quadrilateral();
      //System.out.println(quadrilateral);
      
      vertices[0] = new Point("E", 5, 6);
      vertices[1].setX(67);
      
      System.out.println(trapez);
      
      testIncorrectSettings();
   } 
   
   private static void testIncorrectSettings() {
      try {  
         Point[] vertices = { new Point("A", 5, 6), new Point("B", -5, -6), new Point("C", 5, 6), new Point("D", 5, -6) };
         Trapezoid trapez = new Trapezoid(vertices);
         assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct Trapezoid: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
      
      try {  
         Point[] vertices = { new Point("A", 5, 6), new Point("B", -5, -6), new Point("C", 75, 6) };
         Trapezoid trapez = new Trapezoid(vertices);
         assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while construct Trapezoid: %s%n", exception.getMessage());
         exception.printStackTrace();
      }
      
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
      
      Point[] vertices = { new Point("A", 5, 6), new Point("B", -5, -6), new Point("C", 55, 6), new Point("D", 5, -6) };
      Trapezoid trapez = new Trapezoid(vertices);
      try {  
        vertices[2].equals(trapez);
        assert(false);
      }
      catch (IllegalArgumentException exception) {
         System.out.printf("%nException while check equality: %s%n", exception.getMessage());
         exception.printStackTrace();
      }   
            
   }
} 
