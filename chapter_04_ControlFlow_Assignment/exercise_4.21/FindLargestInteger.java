/* =====================================================================================
 *       Filename:  FindLargestInteger.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.21 - Finding the largest integer of 10 integers 
                                entered by User using counter-controlled iteration
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class FindLargestInteger {

   static final short numberOfIntegers = 10;
   static final String prompt = "***Enter exactly one integer, only first entered integer will be accepted: ";

   public static void main(String[] args) {
      System.out.printf("****** This program find the largest integer of %d integers entered by User.%n", numberOfIntegers);
      System.out.printf ("   - maximum value of entered number can not be more than %d %n", Long.MAX_VALUE);
      System.out.printf ("   - minimum value of entered number can not be less than %d %n", Long.MIN_VALUE);
      
      short       counter = 1;
      long        integer = GettingDataFromStandardInput.getLongInteger(String.format("prompt %d%s", counter, prompt));
      long        largest = integer;
      
      while (counter < numberOfIntegers) { 
         counter++;
         integer = GettingDataFromStandardInput.getLongInteger(String.format("prompt %d%s", counter, prompt));
         if (largest < integer) {
            largest = integer;
         }
      }
      
      System.out.printf("*** The largest integer is %d %n", largest);
   } 
} 
