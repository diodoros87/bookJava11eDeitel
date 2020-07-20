/* =====================================================================================
 *       Filename:  ConverterConsoleClient.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.22 - console's client application of 
                                currency's converter
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class ConverterConsoleClient {

   public static void main(String[] args) {
      ConverterClient     consoleClient = new ConverterClient(System.out);
      
      try {
         consoleClient.run();
      } catch (Exception exception) {
         exception.printStackTrace();
         abnormalTermination(exception.getMessage());
      }
   }   
   
   public static void abnormalTermination(final String INFO) {
      System.err.println("****** Program is interrupted ");
      System.err.println(INFO);
      System.exit(1);
   }
} 
