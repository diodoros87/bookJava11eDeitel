/* =====================================================================================
 *       Filename:  FindMinimumInteger.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.11 - Finding the smallest integer in integers
                                entered by User using counter-controlled iteration
                                number of integers is set by User in first of integer
                                (but this integer is not subject to the process of 
                                finding minimum)
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class FindMinimumInteger {

   static final String numberOfIntegersInfo = "Number of entered integers is set by User in first of entered by User integer.";
   static final String excludeFirstIntegersInfo = "But this integer is not subject to the process of finding minimum";
   
   static final String prompt = String.format("To quit enter sequence other than integers in range from %d to %d%n",
                                                Integer.MIN_VALUE, Integer.MAX_VALUE) +
                                             "Enter integer, only first entered integer will be accepted: ";
                                             

   public static void main(String[] args) {
      System.out.println("****** This program find the smallest integer in integers entered by User.");
      System.out.printf ("   - maximum value of entered number can not be more than %d %n", Integer.MAX_VALUE);
      System.out.printf ("   - minimum value of entered number can not be less than %d %n", Integer.MIN_VALUE);
      
      int howManyIntegers = getNumberOfIntegersToEnter();
      
      if (0 < howManyIntegers) {
         System.out.printf("*** The smallest integer is %d %n", getMinimum(howManyIntegers));
      }
   } 
   
   public static int getNumberOfIntegersToEnter() {
      int howManyIntegers;
      
      do {
         howManyIntegers = GettingDataFromStandardInput.getInteger(String.format("%s%n%s%n%s", 
                                             numberOfIntegersInfo, excludeFirstIntegersInfo, prompt));
         if (howManyIntegers < 0) {
            System.err.println("Number of entered integers can not be less than zero. Try again.");
         }
         else {
            break;
         }
      } while (true);
      
      return howManyIntegers;
   }
   
   public static Integer getMinimum (int howManyIntegers) {
      Integer minimum;
      
      if (howManyIntegers > 0) {
         minimum = Integer.MAX_VALUE;
      }
      else {
         minimum = null;
      }
      
      int integer;
      
      for (int counter = 1; counter <= howManyIntegers; counter++) { 
         integer = GettingDataFromStandardInput.getInteger(String.format("prompt %d%n%s", counter, prompt));
         if (minimum > integer) {
            minimum = integer;
         }
      }
      
      return minimum;
   }
} 
