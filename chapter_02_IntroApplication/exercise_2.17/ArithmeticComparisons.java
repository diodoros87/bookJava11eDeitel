/* =====================================================================================
 *       Filename:  ArithmeticComparisons.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.17 - arithmetic operations and comparisons
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Scanner;

public class ArithmeticComparisons {   

   public static void main(String[] args) {
   
      System.out.println("*** Tasks of program:");
      System.out.println("1. Get three integers from User in purpose of calculate and comparisons.");
      System.out.printf ("2. Calculate and display addition, arithmetic mean (average), multiplication, maximum and minimum number.%n%n");
      
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
      System.out.println("   number of arguments entered by User can not be more than 3"); 
            
      System.out.print  ("Enter three integers separated by space or tabulator or new line character: ");
      
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
            
               String nonWhitespaceRegex = "\\S"; 
               String inputData = input.findInLine(nonWhitespaceRegex); // search eventual arguments entered by User
               if (null != inputData) {
                  System.err.println("Number of arguments entered by User can not be more than 3"); 
               }
               if (null == inputData) { 
                  boolean isOverflow = isOverflowIntegerAddition(firstNumber, secondNumber, thirdNumber);
                  
                  if (false == isOverflow) {
                     System.out.printf("%nAddition (sum) of (%d + %d + %d) is equal to %d",
                                          firstNumber, secondNumber, thirdNumber,
                                          firstNumber + secondNumber + thirdNumber);
                     System.out.printf("%nInteger of arithmetic mean (average) of ");
                     System.out.printf("%d and %d and %d ((%d + %d + %d) / 3) is equal to %d",
                                          firstNumber, secondNumber, thirdNumber, 
                                          firstNumber, secondNumber, thirdNumber,
                                          (firstNumber + secondNumber + thirdNumber) / 3);
                     System.out.printf("%nReal number approximation of arithmetic mean (average) of ");
                     System.out.printf("%d and %d and %d ((%d + %d + %d) / 3.0) is equal to %f",
                                          firstNumber, secondNumber, thirdNumber, 
                                          firstNumber, secondNumber, thirdNumber,
                                          (firstNumber + secondNumber + thirdNumber) / 3.0);
                  }
                  if (true == isOverflow) {
                     System.err.printf("%nAddition (sum) of (%d + %d + %d) is integer overflow",
                                          firstNumber, secondNumber, thirdNumber);
                  }
                  
                  isOverflow = isOverflowIntegerMultiplication(firstNumber, secondNumber, thirdNumber);
                  if (false == isOverflow) {
                     System.out.printf("%nMultiplication (product) of %d and %d and %d (%d * %d * %d) is equal to %d",
                                          firstNumber, secondNumber, thirdNumber, 
                                          firstNumber, secondNumber, thirdNumber,
                                          firstNumber * secondNumber * thirdNumber);
                  }
                  if (true == isOverflow) {
                     System.err.printf("%nMultiplication (product) of %d and %d and %d (%d * %d * %d) is integer overflow",
                                          firstNumber, secondNumber, thirdNumber,
                                          firstNumber, secondNumber, thirdNumber);
                  }
                                       
                  {
                     long maximum = firstNumber;
                     if (maximum < secondNumber)
                        maximum = secondNumber;
                     if (maximum < thirdNumber)
                        maximum = thirdNumber;
                        
                     System.out.printf("%n Maximum number is %d", maximum);
                  }
                  {
                     long minimum = firstNumber;
                     if (minimum > secondNumber)
                        minimum = secondNumber;
                     if (minimum > thirdNumber)
                        minimum = thirdNumber;   
                        
                     System.out.printf("%n Minimum number is %d %n", minimum);    
                  }
                  
               }
               
            }
            
         }
      }
 
      input.close();
   }
   
   public static boolean isOverflowIntegerAddition(long firstNumber, long secondNumber, long thirdNumber) {
   
      boolean isOverflow = isOverflowIntegerAddition(firstNumber, secondNumber);
               
      if (false == isOverflow) {
         isOverflow = isOverflowIntegerAddition(firstNumber + secondNumber, thirdNumber); 
      }
      
      if (true == isOverflow) {
         isOverflow = isOverflowIntegerAddition(firstNumber, thirdNumber);
         
         if (false == isOverflow) {
            isOverflow = isOverflowIntegerAddition(firstNumber + thirdNumber, secondNumber); 
         }
      }
      
      if (true == isOverflow) {
         isOverflow = isOverflowIntegerAddition(secondNumber, thirdNumber);
         
         if (false == isOverflow) {
            isOverflow = isOverflowIntegerAddition(secondNumber + thirdNumber, firstNumber); 
         }
      }
         
      return isOverflow;
   }
   
   public static boolean isOverflowIntegerAddition(long firstNumber, long secondNumber) {
   
      boolean isOverflow = false;               
      // for addition identity (neutral) element is 0
      
      if (firstNumber > 0) 
         if (secondNumber > 0) 
            if (Long.MAX_VALUE - firstNumber < secondNumber)
               isOverflow = true;
               
      if (firstNumber < 0) 
         if (secondNumber < 0) 
            if (Long.MIN_VALUE - firstNumber > secondNumber)
               isOverflow = true;
            
      return isOverflow;
   }
   
   public static boolean isOverflowIntegerMultiplication(long firstNumber, long secondNumber, long thirdNumber) {
   
      boolean isOverflow = isOverflowIntegerMultiplication(firstNumber, secondNumber);
               
      if (false == isOverflow) {
         isOverflow = isOverflowIntegerMultiplication(firstNumber * secondNumber, thirdNumber); 
      }
      
      if (true == isOverflow) {
         isOverflow = isOverflowIntegerMultiplication(firstNumber, thirdNumber);
         
         if (false == isOverflow) {
            isOverflow = isOverflowIntegerMultiplication(firstNumber * thirdNumber, secondNumber); 
         }
      }
      
      if (true == isOverflow) {
         isOverflow = isOverflowIntegerMultiplication(secondNumber, thirdNumber);
         
         if (false == isOverflow) {
            isOverflow = isOverflowIntegerMultiplication(secondNumber * thirdNumber, firstNumber); 
         }
      }
         
      return isOverflow;
   }
   
   public static boolean isOverflowIntegerMultiplication(long firstNumber, long secondNumber) {
   
      boolean isOverflow = false;               
      // for multiplication identity (neutral) element is 1
      
      // absolute value of Long.MIN_VALUE is greater than absolute value of Long.MAX_VALUE
      if (Long.MIN_VALUE == firstNumber)
         if (-1 == secondNumber)
            isOverflow = true;
      
      if (Long.MIN_VALUE == secondNumber)
         if (-1 == firstNumber)
            isOverflow = true;
      
      // to detect integer overflow in multiplication:
      // absolute value of ((Long.MAX_VALUE or Long.MIN_VALUE) divided by secondNumber) must be less than
      // absolute value of firstNumber
      if (firstNumber > 1) 
         if (secondNumber > 1) 
            if (Long.MAX_VALUE / secondNumber < firstNumber)
               isOverflow = true;
               
      if (firstNumber < -1) 
         if (secondNumber < -1) 
            if (Long.MAX_VALUE / secondNumber > firstNumber)
               isOverflow = true;
               
      if (firstNumber < -1)  
         if (secondNumber > 1)
            if (Long.MIN_VALUE / secondNumber > firstNumber)
               isOverflow = true;
               
      if (firstNumber > 1)
         if (secondNumber < -1)
            if (Long.MIN_VALUE / secondNumber < firstNumber)
               isOverflow = true;
         
      return isOverflow;             
   }
   
}
