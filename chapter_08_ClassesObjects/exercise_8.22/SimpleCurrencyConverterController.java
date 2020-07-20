/* =====================================================================================
 *       Filename:  SimpleCurrencyConverterController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.22 - controller's class of 
                                simple currency converter
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
import java.io.PrintStream;
import java.io.IOException;
import java.math.BigDecimal;

public class SimpleCurrencyConverterController {

   private SimpleCurrencyConverter     model;
   private SimpleCurrencyConverterView view;
   
   public SimpleCurrencyConverterController(SimpleCurrencyConverter model, SimpleCurrencyConverterView view) {
      ValidateParameters.checkNullPointer(model, view);
      
      this.model = model;
      this.view = view;
   }
   
   public SimpleCurrencyConverter getModel() {
      return model;
   }
   
   public SimpleCurrencyConverterView getView() {
      return view;
   }
   
   public void setPrintStream(PrintStream printStream) {
      view.setPrintStream(printStream);
   }
   
   public PrintStream getPrintStream() {
      return view.getPrintStream();
   }
   
   public void printAvailableCurrencies() {
      view.printAvailableCurrencies();
   }
   
   public void printAmountFirstCurrency() throws IOException {
      view.printAmountFirstCurrency(model);
   }
   
   public void printAmountSecondCurrency() {
      view.printAmountSecondCurrency(model);
   }
   
   public void printFirstToSecondConversion() {
      view.printFirstToSecondConversion(model);
   }
   
   public void printSecondToFirstConversion() {
      view.printSecondToFirstConversion(model);
   }
   
   public void setFirstCurrencyCode(int index) {
      model.setFirstCurrencyCode(index);
   }
   
   public void setSecondCurrencyCode(int index) {
      model.setSecondCurrencyCode(index);
   }
   
   public void setAmount(BigDecimal amount) {
      model.setAmount(amount); 
   }
   
   public void printStartInfo() {
      view.printStartInfo();
   }
 
} 
