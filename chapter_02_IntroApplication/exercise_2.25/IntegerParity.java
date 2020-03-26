/* =====================================================================================
 *       Filename:  IntegerParity.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.25 - check parity of integer
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Scanner;

public class IntegerParity {   

   public static void main(String[] args) {
   
      System.out.println("*** Tasks of program:");
      System.out.println("1. Get integer from User in purpose of check parity of integer.");
      System.out.printf ("2. Check parity of integer and display result of checking.%n%n"); 
      
      System.out.println("*** Conditions for correct entering input data of integer accept only:");
      System.out.println("   - characters of digits ");
      System.out.println("   - eventually for numbers less than zero before number only one character \'-\'");
      System.out.println("   - eventually for numbers greater than zero before number only one character \'+\'");
      System.out.println("   - eventually for zero before number only one character: \'+\' otherwise \'-\' ");
      System.out.printf ("   - maximum value of entered number can not be more than %d %n", Long.MAX_VALUE);
      System.out.printf ("   - minimum value of entered number can not be less than %d %n", Long.MIN_VALUE);
      System.out.println("   Examples of accepted number formats are in quotes: ");
      System.out.println("   \'+1\' \'17\' \'-51\' \'+0\' \'-00\' \'-023\' \'+0005\'");
      
      System.out.println("   space, tabulator, new line character are skipped");
      System.out.println("   number of arguments entered by User can not be more than 1"); 
           
      System.out.print  ("Enter integer: ");
      
      Scanner input              = new Scanner(System.in);
      boolean isCorrectInputData = input.hasNextLong();   // to check input data
      
      if (false == isCorrectInputData) {
            System.err.println("Value entered by User is incorrect"); 
      }
      if (true == isCorrectInputData) {
         long number = input.nextLong();
         
         String nonWhitespaceRegex = "[^\\s]"; 
         String inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
         
         if (null != inputData) {
               System.err.println("Too many arguments entered by User."); 
         }
         if (null == inputData) {
         
             String parity = "odd";
             
             if (0 == number % 2) {
                parity = "even";
             }
             
             System.out.printf("%n %d is %s number. %n", number, parity);            
         }
      }
 
      input.close();
   }
   
}
