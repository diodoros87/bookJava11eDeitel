/* =====================================================================================
 *       Filename:  TemperatureConversion.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.22 - temperature conversion
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class TemperatureConversion {
   
   public static double celsius(double fahrenheit) {
      return 5.0 / 9 * (fahrenheit - 32);
   }
   
   public static double fahrenheit(double celsius) {
      return 9.0 / 5 * celsius + 32;
   }
   
   public static boolean menu() {
      System.out.printf("********* This program calculate of temperature conversions: %n");
      System.out.printf("### 1. from celsius to fahrenheit%n");
      System.out.printf("### 2. from fahrenheit to celsius%n");
      System.out.printf("*** Enter 1 or 2 for select conversion, for quit enter other key/keys%n");
      
      String menuSelection = GettingDataFromStandardInput.getStringWaitingForInput("");
      
      if (null != menuSelection) {
         if (menuSelection.equals("1")) {
            System.out.printf("%nRESULT: In scale of Fahrenheit temperature is %f%n", 
                                          fahrenheit(getTemperatureFromUser("Celsius")));
            return true;
         }
         else if (menuSelection.equals("2")) {
            System.out.printf("%nRESULT: In scale of Celsius temperature is %f%n", 
                                          celsius(getTemperatureFromUser("Fahrenheit")));
            return true;
         }
      }
      
      return false;
   }
   
   public static double getTemperatureFromUser(String scale) {
      final String QUIT_INFO = "To quit enter sequence other than number";
      final String PROMPT = "Only first entered number will be accepted. Enter number as temperature in scale of";
      final String QUIT_INFO_PROMPT = String.format(" %s%n %s %s: ", QUIT_INFO, PROMPT, scale);
      
      return GettingDataFromStandardInput.getDouble(QUIT_INFO_PROMPT);
   }
   
   public static void main(String[] args) {
      
      do {
      } while (true == menu());

   } 
   
} 
