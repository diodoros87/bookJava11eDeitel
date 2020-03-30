/* =====================================================================================
 *       Filename:  CreditLimitCalculatorTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.18 - Solving the calculate credit limit of many 
                                shop's customers using sentinel-controlled iteration
                                   with CreditLimitCalculator objects
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class CreditLimitCalculatorTest {

   public static void main(String[] args) {
      // initialization phase
      CreditLimitCalculator customerData = new CreditLimitCalculator();
      
      // processing phase
      // prompt for input and read data from user
      // loop until sentinel value read from user
      while (UserInterface.modifyCustomerDataByUser(customerData) == true) {
         UserInterface.displayCustomerData(customerData);
         customerData.resetDataForNextCustomer();
      }
   } 
   
} 
