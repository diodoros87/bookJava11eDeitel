/* =====================================================================================
 *       Filename:  ClientInputOutput.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.22 - class of client's input-output
                                to currency's converter
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

import java.math.BigDecimal;

public class ClientInputOutput {
   static final String ACCEPT_ANSWER      = "y";
   
   public static BigDecimal getAmount() {
      final String PROMPT = "Enter non-negative amount of money without currency: ";

      BigDecimal amount = GettingDataFromStandardInput.getBigDecimal(PROMPT);
         
      return amount;
   }
   
   public static int getCurrenciesListIndex(boolean promptDisplaying) throws Exception {
      boolean acceptInfoDisplaying = false;
      String prompt = "";
      if (true == promptDisplaying) {
         prompt = StringMaker.getCurrenciesListIndexPrompt();
      }
      
      Integer index = GettingDataFromStandardInput.getIntegerRejectOthersData(prompt, 
                                                promptDisplaying, acceptInfoDisplaying);
      
      ExceptionChecker.checkNullPointerException(index, "End-of-transmission character was detected");
         
      return index;
   }
   
   public static void setCurrencies(int[] currencies) throws Exception {
      if (null == currencies) { 
         throw new NullPointerException();
      }
      if (2 != currencies.length) { 
         throw new IllegalArgumentException("currencies must be 2 elements array");
      }
      
      currencies[0] = getCurrenciesListIndex(true);
      currencies[1] = getCurrenciesListIndex(false);
      
      GettingDataFromStandardInput.clearNextLine(); // GettingDataFromStandardInput.getIntegerRejectOthersData() leaves 
                                                    // whitespaces in input and may leaves other characters
   }
   
   public static boolean isConvertAgain() {
      String question = StringMaker.convertAgainQuestion();
      boolean answer = answerToQuestion(question);
      
      return answer;
   }
   
   public static boolean answerToQuestion(final String QUESTION) {
      String answer = GettingDataFromStandardInput.getString(QUESTION);

      if (null == answer || ACCEPT_ANSWER.equals(answer.toLowerCase())) {
         return false;
      }
      
      return true;
   }
}

class ExceptionChecker {
   static void checkNullPointerException(Object object, String message) throws Exception {
      if (null == object) { 
         throw new Exception(message);
      }
   }
}

class StringMaker {
   static final int CURRENCIES_LIST_LAST_ELEMENT = SimpleCurrencyConverter.CURRENCIES_LIST.size() - 1;
   
   static String convertAgainQuestion() {
      String question = String.format("***** To exit program press \'%s\' - to convert again press other key %n",
                                       ClientInputOutput.ACCEPT_ANSWER);
                                       
      return question;
   }
   
   static String getCurrenciesListIndexPrompt() {
      final String RANGE = String.format("Indexes must be from %d to %d", 0, CURRENCIES_LIST_LAST_ELEMENT);
      
      String prompt = String.format("To select 2 currencies enter 2 integers separated by whitespaces %n") 
                                       + "as indexes of currency";
      prompt = String.format("%s. %s %n ", prompt, RANGE);
      
      return prompt;
   }
} 
