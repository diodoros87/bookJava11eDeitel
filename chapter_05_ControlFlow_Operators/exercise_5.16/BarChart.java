/* =====================================================================================
 *       Filename:  BarChart.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.16 - Bar chart class 
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class BarChart {
   
   private static final short min = 1;
   private static final short max = 30;
   private static final char asterisk = '*';
   private static final String numberInfo = "Entered integer must be in range between " + min + " and " + max;

   private short number1;
   private short number2;
   private short number3;
   private short number4;
   private short number5;
   
   public BarChart (short number1, short number2, short number3, short number4, short number5) {
      if (true == validateNumber(number1))
         this.number1 = number1;
         
      if (true == validateNumber(number2))
         this.number2 = number2;
         
      if (true == validateNumber(number3))  
         this.number3 = number3;
         
      if (true == validateNumber(number4))
         this.number4 = number4;
         
      if (true == validateNumber(number5))
         this.number5 = number5;
   }
   
   public static boolean validateNumber (short number) {
      if (number >= min && number <= max) {
         return true;
      }
      else {
         System.err.println("++++++ERROR: " + numberInfo);
         return false;
      }
   }

   public void printBarChart() {
      System.out.println("        Bar chart printing:");
      
      printCharactersLine(asterisk, number1);
      printCharactersLine(asterisk, number2);
      printCharactersLine(asterisk, number3);
      printCharactersLine(asterisk, number4);
      printCharactersLine(asterisk, number5);
   } 
   
   public static void printCharactersLine(char character, int width) {
   
      for (int counter = 1; counter <= width; ++counter) {                                     
         System.out.print(character);       
      }     
      
      System.out.println();
   }
}
