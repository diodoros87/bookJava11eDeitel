/* =====================================================================================
 *       Filename:  Circle.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.28 - calculate diameter, circumference, area 
                                of circle
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Scanner;

public class Circle {   

   public static void main(String[] args) {
   
      System.out.println("*** Tasks of program:");
      System.out.println("1. Get integer greater than zero from User as length of radius of circle.");
      System.out.printf ("2. Calculate diameter, circumference, area of circle and display result.%n%n"); 
      
      System.out.println("*** Conditions for correct entering input data of integer accept only:");
      System.out.println("   - characters of digits ");
      System.out.println("   - eventually for numbers less than zero before number only one character \'-\'");
      System.out.println("   - eventually for numbers greater than zero before number only one character \'+\'");
      System.out.println("   - eventually for zero before number only one character: \'+\' otherwise \'-\' ");
      System.out.printf ("   - maximum value of entered number can not be more than %d %n", Integer.MAX_VALUE);
      System.out.printf ("   - minimum value of entered number can not be less than %d %n", Integer.MIN_VALUE);
      System.out.println("   Examples of accepted number formats are in quotes: ");
      System.out.println("   \'+1\' \'17\' \'-51\' \'+0\' \'-00\' \'-023\' \'+0005\'");
      
      System.out.println("   space, tabulator, new line character are skipped");
      System.out.println("   number of arguments entered by User can not be more than 1"); 
           
      System.out.print  ("Enter integer as length of radius of circle: ");
      
      Scanner input              = new Scanner(System.in);
      boolean isCorrectInputData = input.hasNextInt();   // to check input data
      
      if (false == isCorrectInputData) {
            System.err.println("Value entered by User is incorrect"); 
      }
      if (true == isCorrectInputData) {
         int radius = input.nextInt();
         
         if (radius <= 0) {
            System.err.println("Length of radius of circle can not be less than zero or equal zero");
         }
         if (radius > 0) {
            String nonWhitespaceRegex = "[^\\s]"; 
            String inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
            
            if (null != inputData) {
                  System.err.println("Too many arguments entered by User."); 
            }
            if (null == inputData) {
               System.out.printf("%n Diameter of circle is %d %n", 2 * radius);  
               System.out.printf("%n Circumference of circle is %f %n", 2 * Math.PI * radius);
               System.out.printf("%n Area of circle is %f %n", Math.PI * radius * radius);
            }
         }
         
      }
 
      input.close();
   }
   
}
