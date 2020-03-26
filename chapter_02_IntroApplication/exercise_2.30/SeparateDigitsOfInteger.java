/* =====================================================================================
 *       Filename:  SeparateDigitsOfInteger.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.30 - print separate digits of integer
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Scanner;

public class SeparateDigitsOfInteger {   

   public static void main(String[] args) {
   
      System.out.println("*** Tasks of program:");
      System.out.println("1. Get integer from User in purpose of separate maximally last 5 digits of integer.");
      System.out.printf ("2. Separate maximally last 5 digits of integer and display result of separating.%n%n"); 
      
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
           
      System.out.print  ("Enter integer: ");
      
      Scanner input              = new Scanner(System.in);
      boolean isCorrectInputData = input.hasNextInt();   // to check input data
      
      if (false == isCorrectInputData) {
            System.err.println("Value entered by User is incorrect"); 
      }
      if (true == isCorrectInputData) {
         int number = input.nextInt();
         
         String nonWhitespaceRegex = "[^\\s]"; 
         String inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
         
         if (null != inputData) {
               System.err.println("Too many arguments entered by User."); 
         }
         if (null == inputData) {
            String spaces = "   ";
            
            if (number < 0) {
               System.out.printf("%c%s", '-', spaces);
               number = -number;
            }
         
            short factorOne = (short) (number % 10);
            short factorTen = (short) (number % 100 / 10);
            short factorHundred = (short) (number % 1000 / 100);
            short factorThousand = (short) (number % 10_000 / 1000);
            short factorTenThousands = (short) (number % 100_000 / 10_000);
            
            short howManyDigitsDisplay = 5;
            
            if (0 == factorTenThousands) {
               howManyDigitsDisplay--;
               if (0 == factorThousand) {
                  howManyDigitsDisplay--;   
                  if (0 == factorHundred) {
                     howManyDigitsDisplay--;
                     if (0 == factorTen) {
                        howManyDigitsDisplay--;                  
                     }
                  }
               }
            }
            
            if (5 == howManyDigitsDisplay) {
               System.out.printf("%d%s", factorTenThousands, spaces); 
               howManyDigitsDisplay--;
            }
            if (4 == howManyDigitsDisplay) {
               System.out.printf("%d%s", factorThousand, spaces); 
               howManyDigitsDisplay--;
            }
            if (3 == howManyDigitsDisplay) {
               System.out.printf("%d%s", factorHundred, spaces); 
               howManyDigitsDisplay--;
            }
            if (2 == howManyDigitsDisplay) {
               System.out.printf("%d%s", factorTen, spaces); 
               howManyDigitsDisplay--;
            }
            if (1 == howManyDigitsDisplay) {
               System.out.printf("%d%n", factorOne); 
            }
         }
      }
 
      input.close();
   }
   
}
