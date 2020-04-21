/* =====================================================================================
 *       Filename:  RoundingNumbers.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.10 - rounding numbers 
                                                tested by User 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class RoundingNumbers {
   
   public static void main(String[] args) {
      
      final String QUIT_INFO = "To quit enter End-Of-Transmission (EOT) character";
      final String EOT_INFO = "Ctrl-D (in Linux or Mac OS X) or Ctrl-Z in Windows";
      final String NEGATIVE_INFINITY_QUIT_INFO = String.format("To quit enter %f%n", Double.NEGATIVE_INFINITY);
      final String PROMPT = "Enter number to rounding: ";
      final String QUIT_INFO_PROMPT = String.format("%s - %s%n%s%n%s", QUIT_INFO, EOT_INFO, NEGATIVE_INFINITY_QUIT_INFO, PROMPT);
      
      System.out.printf("*** This program calculate rounding according to number entered by User.%n");
      System.out.printf("*** %s %n", QUIT_INFO_PROMPT);
      
      double number = GettingDataFromStandardInput.getDoubleRejectOthersData("", false);
      
      while (GettingDataFromStandardInput.endOfDataValueOfDouble != number) {
         printRounding(number);
         System.out.printf("%n*** %s %n", QUIT_INFO_PROMPT);
         number = GettingDataFromStandardInput.getDoubleRejectOthersData("", false);
      }

   } 
   
   public static void printRounding(double number) {
      System.out.printf("%s: %f \t%s: %f %n", "number", number, "rounding to integer", roundToInteger(number));
      System.out.printf("%s: %f \t%s: %f \t%s: %f %n", "rounding to tenths", roundToTenths(number),
                              "rounding to hundredths", roundToHundredths(number), "rounding to thousandths", roundToThousandths(number));
   }
   
   public static double roundToInteger(double number) {
      return Math.floor(number + 0.5);
   }
   
   public static double roundToTenths(double number) {
      return Math.floor(10 * number + 0.5) / 10;
   }
   
   public static double roundToHundredths(double number) {
      return Math.floor(100 * number + 0.5) / 100;
   }
   
   public static double roundToThousandths(double number) {
      return Math.floor(1000 * number + 0.5) / 1000;
   }
   
} 
