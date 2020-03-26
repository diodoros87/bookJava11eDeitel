/* =====================================================================================
 *       Filename:  SignumFunction.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.32 - counting integers by mathematical
                                signum function
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Scanner;

public class SignumFunction {   

   public static void main(String[] args) {
   
      System.out.println("*** Tasks of program:");
      System.out.println("1. Get five integers from User in purpose of counting integers by mathematical signum function.");
      System.out.printf ("2. Counting integers by mathematical signum function and display results of counting.%n%n"); 
      
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
                     
                        short plusCounter = 0;
                        short minusCounter = 0;
                        short zeroCounter = 0;
                        
                        if (firstNumber > 0)
                           ++plusCounter;
                        if (firstNumber == 0)
                           ++zeroCounter;
                        if (firstNumber < 0)
                           ++minusCounter;
                           
                        if (secondNumber > 0)
                           ++plusCounter;
                        if (secondNumber == 0)
                           ++zeroCounter;
                        if (secondNumber < 0)
                           ++minusCounter;
                           
                        if (thirdNumber > 0)
                           ++plusCounter;
                        if (thirdNumber == 0)
                           ++zeroCounter;
                        if (thirdNumber < 0)
                           ++minusCounter;
                           
                        if (fourthNumber > 0)
                           ++plusCounter;
                        if (fourthNumber == 0)
                           ++zeroCounter;
                        if (fourthNumber < 0)
                           ++minusCounter;
                           
                        if (fifthNumber > 0)
                           ++plusCounter;
                        if (fifthNumber == 0)
                           ++zeroCounter;
                        if (fifthNumber < 0)
                           ++minusCounter;
                           
                        System.out.printf ("In entered numbers counted: %n");                        
                        System.out.printf (" %d with plus sign %n", plusCounter);
                        System.out.printf (" %d without sign %n", zeroCounter);
                        System.out.printf (" %d with minus sign %n", minusCounter);
                     }
                  }
               }
               
            }
            
         }
      }
 
      input.close();
   }
   
}
