/* =====================================================================================
 *       Filename:  SidesOfRightTriangle.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.37 - checking that 3 entered integers by User
                                can create lengths of sides of right triangle
                  
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */ 
 
import standardInputDataPackage.GettingDataFromStandardInput;
import trianglePackage.Triangle;

public class SidesOfRightTriangle {
   
   public static void main(String[] args) {
   
      final String prompt = "To quit enter sequence other than integer. Enter integer as length of right triangle's side number";
      final short numberOfTriangleSides = Triangle.getNumberOfTriangleSides();
      
      System.out.printf("*** This program checks that %d integers can representented lengths of sides of right triangle%n", numberOfTriangleSides);

      int integer;
      int triangleSidesCounter = 0;
      Triangle triangle = new Triangle();

      while (true) {
         triangleSidesCounter = 1;
         while (triangleSidesCounter <= numberOfTriangleSides) {
            integer = GettingDataFromStandardInput.getInteger(String.format("%s %d: ", prompt, triangleSidesCounter));
            
            if (1 == triangleSidesCounter) {
               triangle.setFirstTriangleSideLength(integer);
            }
            else if (2 == triangleSidesCounter) {
               triangle.setSecondTriangleSideLength(integer);
            }
            else {
               triangle.setThirdTriangleSideLength(integer);
            }
            
            triangleSidesCounter++;
         }
         
         boolean result = triangle.isRightTriangleSidesLengths();
         triangle.printErrorsInfo();
         System.out.printf("*** Entered integers can%s representented lengths of sides of right triangle%n", 
                                 result ? "" : " NOT");
         triangle.clearErrorsFlag();
      }
      
   }
    
}
