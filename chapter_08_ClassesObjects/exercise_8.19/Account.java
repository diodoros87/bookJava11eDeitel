/* =====================================================================================
 *       Filename:  Account.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.19 - Account class with using BigDecimal 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.math.MathContext;
 
enum WithdrawStatus { MINUS, ZERO, CORRECT, EXCEEDED };

public class Account {
   public  static final NumberFormat CURRENCY_INSTANCE = NumberFormat.getCurrencyInstance();
   
   private String name = "";
   private BigDecimal balance = BigDecimal.ZERO;
   
   private static final int PRECISION = 30;
   private static final MathContext MATH_CONTEXT = new MathContext(PRECISION, RoundingMode.HALF_EVEN);
   public  static final String PAYMENT_EXCEEDED = "The payment would exceed the available balance ";
   
   public Account(String name, String balanceString) {
      Account.validateName(name);
      
      BigDecimal balance = Account.parseBigDecimal(balanceString);
      Account.validateAmount(balance);
      
      this.name = name;
      this.balance = balance;
   }
   
   public static BigDecimal parseBigDecimal(String string) {
      try {
         BigDecimal value = new BigDecimal(string, MATH_CONTEXT);
         
         return value;
      }
      catch (NumberFormatException exception) {
         throw exception;
      }
   }
   
   public static void validateAmount(BigDecimal amount) {
      if (null == amount) {
         throw new NullPointerException(" amount "); 
      }
      if (-1 == amount.compareTo(BigDecimal.ZERO)) {
         throw new IllegalArgumentException(" amount < 0 "); 
      }
   }
   
   public static void validateName(String name) {
      if (null == name) {
         throw new NullPointerException(" name "); 
      }
      if (true == name.isBlank()) {
         throw new IllegalArgumentException(" name is blank "); 
      }
   }

   public void deposit(String depositAmountString) { 
      BigDecimal depositAmount = Account.parseBigDecimal(depositAmountString);
      Account.validateAmount(depositAmount);
      
      if (+1 == depositAmount.compareTo(BigDecimal.ZERO)) { 
         balance = balance.add(depositAmount, MATH_CONTEXT);  
      }
   }
   
   public WithdrawStatus withdraw(String withdrawAmountString) {  
      BigDecimal withdrawAmount = Account.parseBigDecimal(withdrawAmountString);
      Account.validateAmount(withdrawAmount);
      
      if (+1 == withdrawAmount.compareTo(BigDecimal.ZERO)) {
         
         if (balance == balance.max(withdrawAmount)) {   
            balance = balance.subtract(withdrawAmount, MATH_CONTEXT); 
            
            return WithdrawStatus.CORRECT;
         }
         else { 
            return WithdrawStatus.EXCEEDED;
         }
      }
      
      return WithdrawStatus.ZERO;
   }

   public BigDecimal getBalance() {
      return balance; 
   } 

   public void setName(String name) {
      Account.validateName(name);
      this.name = name; 
   } 

   public String getName() {
      return name; 
   } 
   
   @Override
   public String toString() {
      return String.format(" Account's owner: %s   balance: %-20s %n", getName(), 
                                    CURRENCY_INSTANCE.format(getBalance()));
   }
}
