/* =====================================================================================
 *       Filename:  MinMaxInteger.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.24 - display minimum and maximum integers
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Scanner;

public class MinMaxInteger {   

   public static void main(String[] args) {
   
      System.out.println("*** Tasks of program:");
      System.out.println("1. Get five integers from User in purpose of find maximum and minimum elements.");
      System.out.printf ("2. Find and display maximum and minimum number.%n%n"); 
      
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
      System.out.println("   number of arguments entered by User can not be more than 5"); 
           
      System.out.print  ("Enter five integers separated by space or tabulator or new line character: ");
      
      Scanner input              = new Scanner(System.in);                                                                         
      boolean isCorrectInputData = input.hasNextLong();   // to check input data
      
      if (false == isCorrectInputData) {
            System.err.println("First value entered by User is incorrect"); 
      }
      if (true == isCorrectInputData) {
         long firstNumber = input.nextLong();
         
         isCorrectInputData = input.hasNextLong();
         if (false == isCorrectInputData) {
            System.err.println("Second value entered by User is incorrect"); 
         }
         if (true == isCorrectInputData) {
            long secondNumber = input.nextLong();
            
            isCorrectInputData = input.hasNextLong();
            if (false == isCorrectInputData) {
               System.err.println("Third value entered by User is incorrect"); 
            }
            if (true == isCorrectInputData) {
               long thirdNumber = input.nextLong();
               
               isCorrectInputData = input.hasNextLong();
               if (false == isCorrectInputData) {
                  System.err.println("Fourth value entered by User is incorrect"); 
               }
               if (true == isCorrectInputData) {
                  long fourthNumber = input.nextLong();
               
                  isCorrectInputData = input.hasNextLong();
                  if (false == isCorrectInputData) {
                     System.err.println("Fifth value entered by User is incorrect"); 
                  }
                  if (true == isCorrectInputData) {
                     long fifthNumber = input.nextLong();
                  
                     String nonWhitespaceRegex = "\\S"; 
                     String inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
                     if (null != inputData) {
                        System.err.println("Number of arguments entered by User can not be more than 5"); 
                     }
                     if (null == inputData) {                                                                                                                                                                              
                        {
                           long maximum = firstNumber;
                           if (maximum < secondNumber)
                              maximum = secondNumber;
                           if (maximum < thirdNumber)
                              maximum = thirdNumber;
                           if (maximum < fourthNumber)
                              maximum = fourthNumber;
                           if (maximum < fifthNumber)
                              maximum = fifthNumber;
                              
                           System.out.printf("%n Maximum number is %d", maximum);
                        }
                        {
                           long minimum = firstNumber;
                           if (minimum > secondNumber)
                              minimum = secondNumber;
                           if (minimum > thirdNumber)
                              minimum = thirdNumber;
                           if (minimum > fourthNumber)
                              minimum = fourthNumber;
                           if (minimum > fifthNumber)
                              minimum = fifthNumber;
                              
                           System.out.printf("%n Minimum number is %d %n", minimum); 
                        }     
                     }
                  }
               }
               
            }
            
         }
      }
 
      input.close();
   }
   
}
