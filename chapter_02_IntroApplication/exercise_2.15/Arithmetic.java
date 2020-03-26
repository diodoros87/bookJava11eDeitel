/* =====================================================================================
 *       Filename:  Arithmetic.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.15 - arithmetic operations
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Scanner;

public class Arithmetic {   

   public static void main(String[] args) {
   
      System.out.println("*** Tasks of program:");
      System.out.println("1. Get two integers from User in purpose of calculate.");
      System.out.printf ("2. Calculate and display addition, subtraction, multiplication and eventually divisions.%n%n"); 
      
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
           
      System.out.print  ("Enter two integers separated by space or tabulator or new line character: ");
      
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
            
            String nonWhitespaceRegex = "[^\\s]"; 
            String inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
            if (null != inputData) {
               System.err.println("Number of arguments entered by User can not be more than 2"); 
            }
            if (null == inputData) {     
               boolean isLongIntegerOverflow = false;
               
               {    // Addition -- for addition identity (neutral) element is 0
                  if (firstNumber > 0) 
                     if (secondNumber > 0) 
                        if (Long.MAX_VALUE - firstNumber < secondNumber)
                           isLongIntegerOverflow = true;
                           
                  if (firstNumber < 0) 
                     if (secondNumber < 0) 
                        if (Long.MIN_VALUE - firstNumber > secondNumber)
                           isLongIntegerOverflow = true;
                        
                  if (true == isLongIntegerOverflow) {
                     System.err.printf("%nAddition (sum) of %d and %d (%d + %d) is integer overflow", 
                                          firstNumber, secondNumber, firstNumber, secondNumber);
                  }
                  if (false == isLongIntegerOverflow) {
                     System.out.printf("%nAddition (sum) of %d and %d (%d + %d) is equal to %d", 
                                          firstNumber, secondNumber, firstNumber, secondNumber,
                                          firstNumber + secondNumber);
                  }
               }
               
               {  // Subtraction -- for subtraction identity (neutral) element is 0
                  isLongIntegerOverflow = false;
                  
                  // absolute value of Long.MIN_VALUE is greater than absolute value of Long.MAX_VALUE
                  if (Long.MIN_VALUE == secondNumber)
                     if (0 == firstNumber)
                        isLongIntegerOverflow = true;
                  
                  if (firstNumber > 0) 
                     if (secondNumber < 0) 
                        if (Long.MAX_VALUE + secondNumber < firstNumber)
                           isLongIntegerOverflow = true;
                           
                  if (firstNumber < 0) 
                     if (secondNumber > 0) 
                        if (Long.MIN_VALUE + secondNumber > firstNumber)
                           isLongIntegerOverflow = true;
                           
                  if (true == isLongIntegerOverflow) {
                     System.err.printf("%nSubtraction (difference) of %d and %d (%d - %d) is integer overflow",
                                          firstNumber, secondNumber, firstNumber, secondNumber);
                  }
                  if (false == isLongIntegerOverflow) {
                     System.out.printf("%nSubtraction (difference) of %d and %d (%d - %d) is equal to %d", 
                                          firstNumber, secondNumber, firstNumber,
                                          secondNumber, firstNumber - secondNumber);
                  }
                  
               }
               
               {  // Multiplication -- for multiplication identity (neutral) element is 1
                  isLongIntegerOverflow = false;
                  
                  // absolute value of Long.MIN_VALUE is greater than absolute value of Long.MAX_VALUE
                  if (Long.MIN_VALUE == firstNumber)
                     if (-1 == secondNumber)
                        isLongIntegerOverflow = true;
                  
                  if (Long.MIN_VALUE == secondNumber)
                     if (-1 == firstNumber)
                        isLongIntegerOverflow = true;
                  
                  // to detect integer overflow in multiplication:
                  // absolute value of ((Long.MAX_VALUE or Long.MIN_VALUE) divided by secondNumber) must be less than
                  // absolute value of firstNumber
                  if (firstNumber > 1) 
                     if (secondNumber > 1) 
                        if (Long.MAX_VALUE / secondNumber < firstNumber)
                           isLongIntegerOverflow = true;
                           
                  if (firstNumber < -1) 
                     if (secondNumber < -1) 
                        if (Long.MAX_VALUE / secondNumber > firstNumber)
                           isLongIntegerOverflow = true;
                           
                  if (firstNumber < -1)  
                     if (secondNumber > 1)
                        if (Long.MIN_VALUE / secondNumber > firstNumber)
                           isLongIntegerOverflow = true;
                           
                  if (firstNumber > 1)
                     if (secondNumber < -1)
                        if (Long.MIN_VALUE / secondNumber < firstNumber)
                           isLongIntegerOverflow = true;
                  
                  if (false == isLongIntegerOverflow) {
                     System.out.printf("%nMultiplication (product) of %d and %d (%d * %d) is equal to %d",
                                          firstNumber, secondNumber, firstNumber,
                                          secondNumber, firstNumber * secondNumber);
                  }
                  if (true == isLongIntegerOverflow) {
                     System.err.printf("%nMultiplication (product) of %d and %d (%d * %d) is integer overflow", 
                                          firstNumber, secondNumber, firstNumber, secondNumber);
                  }
                        
               }
                    
               {  // Division
                  if (0 != secondNumber) {   
                     {
                        isLongIntegerOverflow = false;
                        
                        // absolute value of Long.MIN_VALUE is greater than absolute value of Long.MAX_VALUE
                        if (Long.MIN_VALUE == firstNumber)
                           if (-1 == secondNumber)
                              isLongIntegerOverflow = true;
                              
                        if (true == isLongIntegerOverflow) {
                           System.err.printf("%nInteger division (integer quotient) of %d and %d (%d / %d) is integer overflow",
                                                firstNumber, secondNumber, firstNumber, secondNumber);
                        }      
                        if (false == isLongIntegerOverflow) {      
                           System.out.printf("%nInteger division (integer quotient) of %d and %d (%d / %d) is equal to %d",
                                                firstNumber, secondNumber, firstNumber,
                                                secondNumber, firstNumber / secondNumber);
                        }
                     }
                     
                     System.out.printf("%nReal number approximation of division (quotient) of %d and %d (%d / %d) is equal to %f",
                                          firstNumber, secondNumber, firstNumber, secondNumber, ((double) firstNumber) / secondNumber);
                  }
                  if (0 == secondNumber) {
                     System.err.printf("%nSecond number as divisor (quotient) can not be zero.");
                     System.err.printf(" Any dividend divided by zero is undefined.");
                  }
               }
               
               System.out.println("");
            }
            
         }
      }
 
      input.close();
   }
   
}
