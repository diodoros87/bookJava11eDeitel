/* =====================================================================================
 *       Filename:  Minimum.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.23 - Finding the smallest number in 3 numbers
                                entered by User 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class Minimum {
   
   public static double minimum3(double first, double second, double third) {
      return Math.min(Math.min(first, second), third);
   }
   
   public static double getNumberFromUser() {
      final String QUIT_INFO = "To quit enter sequence other than number";
      final String PROMPT = "Only first entered number will be accepted. Enter number: ";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s", QUIT_INFO, PROMPT);
      
      return GettingDataFromStandardInput.getDouble(QUIT_INFO_PROMPT);
   }
   
   public static void main(String[] args) {
      System.out.println("****** This program find minimum in 3 numbers entered by User.");
      
      do {
         System.out.printf("%nMinimum is %f%n", minimum3
                                 (getNumberFromUser(), getNumberFromUser(), getNumberFromUser()));
      } while (true);

   } 
   
} 
