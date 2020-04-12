/* =====================================================================================
 *       Filename:  SalesCalculatorTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.17 - calculate money from sales according to
                                 numbers entered by User 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class SalesCalculatorTest {
   
   private static final String prompt = " Enter integer for product's";
   private static final String summaryInfo = 
                  "To print total money for sales enter 0 for product's number or integer not greater than 0 for product's quantity";

   public static void main(String[] args) {
      System.out.println("****** This program calculate money from sales according to integers entered by User.");
      System.out.printf ("   - maximum value of entered number can not be more than %d %n", Short.MAX_VALUE);
      System.out.printf ("   - minimum value of entered number can not be less than %d %n", Short.MIN_VALUE);
      System.out.printf ("  To quit enter sequence other than integers in range from %d to %d%n%n", Short.MIN_VALUE, Short.MAX_VALUE);
      System.out.println("****** " + SalesCalculator.availableProductsNumbersInfo);
      
      SalesCalculator salesCalculator = new SalesCalculator();
      buyProduct(salesCalculator);
      System.out.printf("Total money from sales is %.2f %n", salesCalculator.getTotalMoney());
   } 
   
   public static void buyProduct(SalesCalculator salesCalculator) {
      short productNumber;
      short productQuantity;
      
      do {
         System.out.printf("Total money from sales is %.2f %n", salesCalculator.getTotalMoney());
         System.out.println("****** " + summaryInfo);
         productNumber = GettingDataFromStandardInput.getShortInteger(String.format("%s %s: ", prompt, "number"));
         productQuantity = GettingDataFromStandardInput.getShortInteger(String.format("%s %s: ", prompt, "quantity"));
      } while (true == salesCalculator.addProductPrice(productNumber, productQuantity));
      
   }
   
} 
