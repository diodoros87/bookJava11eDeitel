/* =====================================================================================
 *       Filename:  ConverterClient.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.22 - client application of 
                                currency's converter
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.InputMismatchException;

import javax.money.MonetaryException;
import javax.money.UnknownCurrencyException;
import javax.money.convert.CurrencyConversionException;

public class ConverterClient {
   private final SimpleCurrencyConverterController CONTROLLER;
   
   public ConverterClient() {
      final SimpleCurrencyConverter     MODEL = new SimpleCurrencyConverter();
      final SimpleCurrencyConverterView VIEW  = new SimpleCurrencyConverterView(System.out);
      CONTROLLER                = new SimpleCurrencyConverterController(MODEL, VIEW);
   }
   
   public ConverterClient(PrintStream printStream) {
      this();

      this.CONTROLLER.setPrintStream(printStream);
   }

   public void run() throws Exception {
      do {
         CONTROLLER.printAvailableCurrencies();
         CONTROLLER.printStartInfo(); 
          
         selectCurrencies();
         setAmount();
         
         try {
            CONTROLLER.printAmountFirstCurrency();
            CONTROLLER.printAmountSecondCurrency();
            CONTROLLER.printFirstToSecondConversion();
            CONTROLLER.printSecondToFirstConversion();
         }
         catch (CurrencyConversionException exception) {
            System.err.printf("%n%s%n", exception.getMessage());
            exception.printStackTrace();
         }
         catch (UnknownCurrencyException exception) {
            System.err.printf("%n%s%n", exception.getMessage());
            exception.printStackTrace();
         }
         catch (MonetaryException exception) {
            System.err.printf("%n%s%n", exception.getMessage());
            exception.printStackTrace();
         }
         catch (Exception exception) {
            System.err.printf("%n%s%n", exception.getMessage());
            exception.printStackTrace();
         }

      } while (true == ClientInputOutput.isConvertAgain());
   }
   
   private void setAmount() {
      BigDecimal amount;
      boolean correctAmount = false;
      
      do {
         
         try {
            amount = ClientInputOutput.getAmount();
            CONTROLLER.setAmount(amount);
            correctAmount = true;
            
         } catch (InputMismatchException exception) {
            System.err.printf("%n%s%n", exception.getMessage());
            exception.printStackTrace();
         } catch (IllegalArgumentException exception) {
            System.err.printf("%n%s%n", exception.getMessage());
            exception.printStackTrace();
         }
         
      } while (false == correctAmount);
   }
   
   private void selectCurrencies() throws Exception {
      int[] currencies = new int[2];
      boolean correctCurrencies = false;
      
      do {
         ClientInputOutput.setCurrencies(currencies);
         
         try {
            CONTROLLER.setFirstCurrencyCode(currencies[0]);
            CONTROLLER.setSecondCurrencyCode(currencies[1]);
            correctCurrencies = true;
         } 
         catch (IndexOutOfBoundsException exception) {
            System.err.printf("%n%s%n", exception.getMessage());
            exception.printStackTrace();
         }
      } while (false == correctCurrencies);
   }
}
