/* =====================================================================================
 *       Filename:  TriangleHypotenuse.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.15 - calculate of triangle's hypotenuse
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class TriangleHypotenuse {
   
   public static void main(String[] args) {
      System.out.printf("*** This program calculate triangle's hypotenuse according to lengths of two other sides %n");
      int triangleCounter = 0;
      
      printTriangleSideLengths(++triangleCounter, 3, 4);
      printTriangleSideLengths(++triangleCounter, 5, 12);
      printTriangleSideLengths(++triangleCounter, 8, 15);
   } 
   
   public static void printTriangleSideLengths(int triangleCounter, double side1, double side2) {
      System.out.printf("%d. Triangle: \t  side1: %f \tside2: %f %n", triangleCounter, side1, side2);
      System.out.printf(" hypotenuse: %f \t  hypotenuse after call Math.hypot(): %f%n%n",
                                       hypotenuse(side1, side2), Math.hypot(side1, side2));
   }
   
   public static double hypotenuse(double side1, double side2) {
      return Math.sqrt(Math.pow(side1, 2) + Math.pow(side2, 2));
   }
   
} 
