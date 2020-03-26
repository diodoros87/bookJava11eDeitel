/* =====================================================================================
 *       Filename:  IntegersComparison.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.16 - comparison of integers
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Scanner;

public class IntegersComparison {   

   public static void main(String[] args) {
   
      System.out.println("*** Tasks of program:");
      System.out.println("1. Get two integers from User in purpose of comparison.");
      System.out.printf ("2. Comparison and display result of comparison.%n%n");     
      
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
      System.out.println("   number of arguments entered by User can not be more than 2"); 
       
      System.out.print  ("Enter first integer: ");
      
      Scanner input              = new Scanner(System.in);
      boolean isCorrectInputData = input.hasNextLong();   // to check input data
      
      if (false == isCorrectInputData) {
            System.err.println("First value entered by User is incorrect"); 
      }
      if (true == isCorrectInputData) {
         long firstNumber = input.nextLong();
         
         String nonWhitespaceRegex = "[^\\s]"; 
         String inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
         
         if (null != inputData) {
               System.err.println("Too many arguments entered by User"); 
         }
         if (null == inputData) {
            System.out.print  ("Enter second integer: ");
            
            isCorrectInputData = input.hasNextLong();
            if (false == isCorrectInputData) {
               System.err.println("Second value entered by User is incorrect"); 
            }
            if (true == isCorrectInputData) {
               long secondNumber = input.nextLong();
               
               inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
               if (null != inputData) {
                  System.err.println("Number of arguments entered by User can not be more than 2"); 
               }
               if (null == inputData) {
                  if (firstNumber > secondNumber) {
                     System.out.printf("%n %d > %d %n Greater number is %d %n", firstNumber, secondNumber, firstNumber);  
                  }
                  if (firstNumber < secondNumber) {
                     System.out.printf("%n %d < %d %n Greater number is %d %n", firstNumber, secondNumber, secondNumber);  
                  }	
                  if (firstNumber == secondNumber) {
                     System.out.printf("%n %d is equal to %d %n Numbers are equal. %n", firstNumber, secondNumber);  
                  }	
               }
               
            }
            
         }
      }
 
      input.close();
   }
   
}
