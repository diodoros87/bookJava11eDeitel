/* =====================================================================================
 *       Filename:  CircleArea.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.20 - calculate circle's area
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class CircleArea {
   
   public static void main(String[] args) {
   
      System.out.printf("*** This program calculate circle's area according to radius as number entered by User.%n");
      
      final String QUIT_INFO = "To quit enter sequence other than number";
      final String PROMPT = "Enter number as length of radius of circle: ";
      final String QUIT_INFO_PROMPT = String.format("%s%n%s", QUIT_INFO, PROMPT);
      
      double radius;
      
      do {
         radius = GettingDataFromStandardInput.getDouble(QUIT_INFO_PROMPT);
         
         if (radius <= 0) {
            System.err.println("Length of radius of circle can not be less than zero or equal zero");
         }
         else {
            System.out.printf("%n Area of circle is %f %n", circleArea(radius));
         }
         
      } while (true);

   } 
   
   public static double circleArea(double radius) {
      return Math.PI * radius * radius;
   }
   
} 
