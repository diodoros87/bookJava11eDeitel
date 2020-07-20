/* =====================================================================================
 *       Filename:  SimpleCurrencyConverterView.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.22 - SimpleCurrencyConverter's view class
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
//import        org.javamoney.moneta.Money;
import static org.javamoney.moneta.format.AmountFormatParams.PATTERN;
import static org.javamoney.moneta.format.CurrencyStyle.SYMBOL;
 
//import javax.money.convert.MonetaryConversions;
//import javax.money.convert.ExchangeRateProvider;
//import javax.money.convert.CurrencyConversion;
import javax.money.Monetary;
//import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.AmountFormatQuery;

import java.io.PrintStream;
import java.io.IOException;
import java.util.Currency;
import java.util.Locale;
import java.math.BigDecimal;
 
public class SimpleCurrencyConverterView {
   private static final String START_INFO = "This program converts one currency amount to another.";
   
   private PrintStream printStream = System.out;
   private static final Locale LOCALE_FORMAT = Locale.getDefault(Locale.Category.FORMAT);
   private static final MonetaryAmountFormat MONETARY_AMOUNT_FORMAT = getMonetaryAmountFormat();
   
   public SimpleCurrencyConverterView(PrintStream printStream) {
      ValidateParameters.checkNullPointer(printStream);
      
      this.printStream = printStream;
   }
   
   public void setPrintStream (PrintStream printStream) {
      ValidateParameters.checkNullPointer(printStream);
      
      this.printStream = printStream;
   }

   public PrintStream getPrintStream() {
      return printStream;
   }
   
   public void printStartInfo() {
      printStream.println(START_INFO);
   }
   
   public void printAvailableCurrencies() {
      printStream.println();
      printStream.printf("%-6s %-40s %7s %7s %n", "Number", "Currency", "Code", "Symbol");
      
      int size = SimpleCurrencyConverter.CURRENCIES_LIST.size();
      for (int index = 0; index < size; index++) {
         Currency currency = SimpleCurrencyConverter.CURRENCIES_LIST.get(index);
         String name   = currency.getDisplayName();
         String code   = currency.getCurrencyCode();
         String symbol = currency.getSymbol();
         
         printStream.printf("%3d.   %-40s %7s %7s %n", index, name, code, symbol);
      }
   }
   
   public void printAmountFirstCurrency(SimpleCurrencyConverter simpleCurrencyConverter) throws IOException {
      BigDecimal amount = simpleCurrencyConverter.getAmount();
      String firstCurrencyCode = simpleCurrencyConverter.getFirstCurrencyCode();
      MonetaryAmount monetaryAmount = Monetary.getDefaultAmountFactory()
            .setCurrency(firstCurrencyCode)
            .setNumber(amount).create();

      printStream.print("First amount is ");
      MONETARY_AMOUNT_FORMAT.print(this.printStream, monetaryAmount);
      printStream.println();
   }
   
   public void printAmountSecondCurrency(SimpleCurrencyConverter simpleCurrencyConverter) {
      MonetaryAmount monetaryAmount = simpleCurrencyConverter.getSecondAmount();
      String formattedAmount = MONETARY_AMOUNT_FORMAT.format(monetaryAmount);
      printStream.println("Second amount is " + formattedAmount);
   }
   
   private static MonetaryAmountFormat getMonetaryAmountFormat() {
      AmountFormatQueryBuilder builder = AmountFormatQueryBuilder.of(LOCALE_FORMAT);
      builder.set(SYMBOL);
      builder.set(PATTERN, " ¤,##0.00");
      AmountFormatQuery query = builder.build();
      MonetaryAmountFormat monetaryAmountFormat = MonetaryFormats.getAmountFormat(query);
      
      return monetaryAmountFormat;
   }
   
   public void printFirstToSecondConversion(SimpleCurrencyConverter simpleCurrencyConverter) {
      MonetaryAmount monetaryAmount = simpleCurrencyConverter.getFirstToSecondWithCurrencyConversion();
      String formattedAmount = MONETARY_AMOUNT_FORMAT.format(monetaryAmount);
      printStream.println("First amount after conversion is " + formattedAmount);
   }
   
   public void printSecondToFirstConversion(SimpleCurrencyConverter simpleCurrencyConverter) {
      MonetaryAmount monetaryAmount = simpleCurrencyConverter.getSecondToFirstWithCurrencyConversion();
      String formattedAmount = MONETARY_AMOUNT_FORMAT.format(monetaryAmount);
      printStream.println("Second amount after conversion is " + formattedAmount);
   }

}
