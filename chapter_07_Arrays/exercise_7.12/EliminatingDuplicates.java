/* =====================================================================================
 *       Filename:  EliminatingDuplicates.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.12 - detection duplicates of integers in range
                                from 10 to 100 entered by User 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class EliminatingDuplicates {

   public static final String INTEGER_MAX_VALUE_STRING = String.format
                                       ("Maximum value of entered number can not be more than %d %n", Integer.MAX_VALUE);
   public static final String INTEGER_MIN_VALUE_STRING = String.format
                                       ("Minimum value of entered number can not be less than %d %n", Integer.MIN_VALUE);
   
   public static void main(String[] args) { 
      final int MIN = 10;
      final int MAX = 100;
      final int ARRAY_LENGTH = 5;
      int[] array = new int[ARRAY_LENGTH];
      final String PROMPT = String.format("Enter integer in range from %d to %d: ", MIN, MAX);
      int enteredNumberIndex = 0;
      
      printStartingInfo(ARRAY_LENGTH);
   
      for (int counterEnteredNumbers = 0; counterEnteredNumbers < ARRAY_LENGTH; counterEnteredNumbers++) {
         System.out.printf("### Number of integers in range from %d to %d entered by User: %d %n", MIN, MAX, counterEnteredNumbers);
         
         array[enteredNumberIndex] = GettingDataFromStandardInput.getInteger(PROMPT);
         while (array[enteredNumberIndex] < MIN || array[enteredNumberIndex] > MAX) {
            System.err.printf("***ERROR: Entered integer must be in range from %d to %d - try again %n", MIN, MAX);
            array[enteredNumberIndex] = GettingDataFromStandardInput.getInteger(PROMPT);
         }
         
         int arrayIndex = 0;
         for (; arrayIndex < enteredNumberIndex; arrayIndex++) {
            if (array[arrayIndex] == array[enteredNumberIndex]) {
               System.err.printf("***ERROR: Entered value of integer %d already exists in array%n", array[enteredNumberIndex]);
               System.err.println("***Each of values in array must be unique");
               break;
            }
         }
         
         if (arrayIndex == enteredNumberIndex) { // duplicate value has not been found in case of: 
            enteredNumberIndex++;                // arrayIndex == enteredNumberIndex
            System.out.println("$$$ Integer's value of " + array[arrayIndex] + " is unique in array");
         } 
         
         printArray(array, "Unique values entered by User has been printed below: ", enteredNumberIndex);
      }
         
   }

   
   private static void printStartingInfo(final int ARRAY_LENGTH) {
      System.out.printf("*** This program prints up to %d unique numbers as integers entered by User%n", ARRAY_LENGTH);
      
      final String QUIT_INFO = "To quit enter sequence other than integer from below range:";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %s%n", QUIT_INFO, INTEGER_MAX_VALUE_STRING, INTEGER_MIN_VALUE_STRING);
      
      System.out.printf(QUIT_INFO_PROMPT);
   }
   
   public static void printArray(final int[] array, final String info, final int endIndex) {
      System.out.println(info);
      final int ARRAY_LENGTH = array.length;
      
      for (int index = 0; index < ARRAY_LENGTH && index < endIndex; index++) {
         System.out.print(array[index] + "   ");
      }
      
      System.out.println();
   }
   
} 
