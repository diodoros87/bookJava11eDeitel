/* =====================================================================================
 *       Filename:  SimpleCurrencyConverter.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.22 - simple currency converter class using
                                JavaMoney 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import org.javamoney.moneta.Money;

import javax.money.Monetary;
import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import javax.money.MonetaryException;
import javax.money.UnknownCurrencyException;
import javax.money.convert.MonetaryConversions;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.CurrencyConversionException;

import java.util.Currency;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.math.BigDecimal;
 
public class SimpleCurrencyConverter {
   public  static final ArrayList<Currency> CURRENCIES_LIST = SimpleCurrencyConverter.getAvailableCurrencyCodes();
   private static final String EXCHANGE_RATE_PROVIDER_STRING = "IMF";
   private static final ExchangeRateProvider EXCHANGE_RATE_PROVIDER = 
                                       MonetaryConversions.getExchangeRateProvider(EXCHANGE_RATE_PROVIDER_STRING);
   
   private String firstCurrencyCode  = "PLN";
   private String secondCurrencyCode = "USD";
   
   private BigDecimal amount = BigDecimal.ZERO;
   
   public SimpleCurrencyConverter() {
      this.firstCurrencyCode  = "PLN";
      this.secondCurrencyCode = "USD";
      this.amount = BigDecimal.ZERO;
   }
   
   public SimpleCurrencyConverter(String firstCurrencyCode, BigDecimal amount, String secondCurrencyCode) {
      SimpleCurrencyConverter.checkCurrencyAvailability(firstCurrencyCode, secondCurrencyCode);                              
      SimpleCurrencyConverter.checkAmount(amount);
      
      this.firstCurrencyCode  = firstCurrencyCode;
      this.secondCurrencyCode = secondCurrencyCode;
      this.amount = amount;
   }
   
   public String getFirstCurrencyCode() {
      return firstCurrencyCode;
   }
   
   public void setFirstCurrencyCode(String firstCurrencyCode) {
      SimpleCurrencyConverter.checkCurrencyAvailability(firstCurrencyCode); 
      
      this.firstCurrencyCode  = firstCurrencyCode;
   }
   
   public void setFirstCurrencyCode(int index) {
      this.firstCurrencyCode  = SimpleCurrencyConverter.getCurrencyCodeFromAvailableCurrencies(index);
   }
   
   public String getSecondCurrencyCode() {
      return secondCurrencyCode;
   }
   
   public void setSecondCurrencyCode(String secondCurrencyCode) {
      SimpleCurrencyConverter.checkCurrencyAvailability(secondCurrencyCode); 
      
      this.secondCurrencyCode  = secondCurrencyCode;
   }
   
   public void setSecondCurrencyCode(int index) {
      this.secondCurrencyCode  = SimpleCurrencyConverter.getCurrencyCodeFromAvailableCurrencies(index);
   }
   
   public BigDecimal getAmount() {
      return amount;
   }
   
   public void setAmount(BigDecimal amount) {
      SimpleCurrencyConverter.checkAmount(amount); 
      
      this.amount  = amount;
   }
   
   public static String getCurrencyCodeFromAvailableCurrencies(int index) {
      try {
         Currency currency = CURRENCIES_LIST.get(index);
         String code = currency.getCurrencyCode();
         
         return code;
      }
      catch (IndexOutOfBoundsException exception) {
         //throw new IndexOutOfBoundsException(exception.getMessage());
         throw new IndexOutOfBoundsException(index);
      }
   }
   
   public MonetaryAmount getFirstAmount() {
      MonetaryAmount amount = Money.of(this.amount, firstCurrencyCode);
      
      return amount;
   }
   
   public MonetaryAmount getSecondAmount() {
      MonetaryAmount amount = Money.of(this.amount, secondCurrencyCode);
      
      return amount;
   }
   
   private static ArrayList<Currency> getAvailableCurrencyCodes() {
      Set<Currency> setOfCurrencies = Currency.getAvailableCurrencies();
      Currency[] currenciesArray = setOfCurrencies.toArray(new Currency[0]);
      List<Currency> currenciesList = List.of(currenciesArray);
      ArrayList<Currency> availableCurrenciesList = new ArrayList<Currency>(currenciesList);
      
      for (int index = 0; index < currenciesList.size(); index++) {
         Currency currency = currenciesArray[index];
         String code       = currency.getCurrencyCode();
         
         boolean currencyAvailability = Monetary.isCurrencyAvailable(code);
         if (false == currencyAvailability) {
            availableCurrenciesList.remove(index);
         }
      }
      
      return availableCurrenciesList;
   }
   
   public MonetaryAmount getFirstToSecondWithCurrencyConversion() {
      try {
         CurrencyUnit firstCurrencyUnit  = Monetary.getCurrency(firstCurrencyCode);
         CurrencyUnit secondCurrencyUnit = Monetary.getCurrency(secondCurrencyCode);
         MonetaryAmount amount           = getFirstToSecondWithCurrencyConversion(firstCurrencyUnit, secondCurrencyUnit);
         
         return amount;
      }
      catch (UnknownCurrencyException exception) {
         throw exception;
      }
   }
   
   private MonetaryAmount getFirstToSecondWithCurrencyConversion(CurrencyUnit firstCurrencyUnit, CurrencyUnit secondCurrencyUnit) {
      CurrencyConversion convertion = EXCHANGE_RATE_PROVIDER.getCurrencyConversion(secondCurrencyUnit);
      MonetaryAmount amount         = Money.of(this.amount, firstCurrencyUnit);
      
      try {
         MonetaryAmount amountAfterConvertion = amount.with(convertion);

         return amountAfterConvertion;
      }
      catch (CurrencyConversionException exception) {
         throw exception;
      }
   }
   
   public MonetaryAmount getSecondToFirstWithCurrencyConversion() {
      try {
         CurrencyConversion convertion = MonetaryConversions.getConversion(firstCurrencyCode, EXCHANGE_RATE_PROVIDER_STRING);
         MonetaryAmount amount                = Money.of(this.amount, secondCurrencyCode);
         
         try {
            MonetaryAmount amountAfterConvertion = amount.with(convertion);

            return amountAfterConvertion;
         }
         catch (CurrencyConversionException exception) {
            throw exception;
         }
      }
      catch (MonetaryException exception) {
         throw exception;
      }

   }
   
   public static void checkCurrencyAvailability(String... currencyCodeStringArray) {
      Object[] objects = currencyCodeStringArray;
      ValidateParameters.checkNullPointer(objects);
      
      for (String code : currencyCodeStringArray) {
         if (false == SimpleCurrencyConverter.isCurrencyCodeAvailable(code)) {
            throw new IllegalArgumentException("currency's code " + code + " is not available");
         }
      }
   }
   
   public static boolean isCurrencyCodeAvailable(String currencyCodeString) {
      ValidateParameters.checkNullPointer(currencyCodeString);
      
      Iterator<Currency> iterator = CURRENCIES_LIST.iterator();
      while (true == iterator.hasNext()) {
         Currency currency = iterator.next();
         String currencyCode = currency.getCurrencyCode();
         System.out.println(" currency's code = " + currencyCode);
         if (true == currencyCodeString.equals(currencyCode)) {
            
            return true;
         }
      }
      
      return false;
   }
   
   public static void checkAmount(BigDecimal amount) {
      ValidateParameters.checkNullPointer(amount);
      
      if (-1 == amount.compareTo(BigDecimal.ZERO)) {
         throw new IllegalArgumentException("amount can not be less than zero");
      }
   }
}
