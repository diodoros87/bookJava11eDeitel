/* =====================================================================================
 *       Filename:  BarChartTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.16 - Printing bar chart with lengths according
                                 to integers entered by User 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class BarChartTest {
   
   private static final String prompt = String.format("To quit enter sequence other than integers in range from %d to %d%n", Short.MAX_VALUE, Short.MAX_VALUE) +
                                String.format(" Enter integer, only first entered integer will be accepted: ");

   public static void main(String[] args) {
      System.out.println("****** This program print bar chart with lengths according to 5 correctly validated integers entered by User.");
      System.out.printf ("   - maximum value of entered number can not be more than %d %n", Short.MAX_VALUE);
      System.out.printf ("   - minimum value of entered number can not be less than %d %n", Short.MIN_VALUE);
      
      short number1 = getNumberFromInput();
      short number2 = getNumberFromInput();
      short number3 = getNumberFromInput();
      short number4 = getNumberFromInput();
      short number5 = getNumberFromInput();
      
      BarChart barChart = new BarChart(number1, number2, number3, number4, number5);
      barChart.printBarChart();
      
   } 
   
   public static short getNumberFromInput() {
      short number;
      
      do {
         number = GettingDataFromStandardInput.getShortInteger(String.format("%s", prompt));
      } while (false == BarChart.validateNumber(number));
      
      return number;
   }
   
} 
