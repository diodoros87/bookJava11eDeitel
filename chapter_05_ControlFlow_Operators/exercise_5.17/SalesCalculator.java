/* =====================================================================================
 *       Filename:  SalesCalculator.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.17 - class of calculate money from sales
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class SalesCalculator {
   public static final String availableProductsNumbersInfo = "Available numbers for products: 1, 2, 3, 4, 5";
   
   private double totalMoney = 0;
   
   public double getTotalMoney () {
      return totalMoney;
   }
   
   public boolean addProductPrice (short productNumber, short productQuantity) {
      if (productQuantity <= 0) {
         return false;
      }
      
      switch (productNumber) {
         case 0:
            return false;
         case 1:
            totalMoney += productQuantity * 2.98;
            break;
         case 2:
            totalMoney += productQuantity * 4.5;
            break;
         case 3:
            totalMoney += productQuantity * 9.98;
            break;
         case 4:
            totalMoney += productQuantity * 4.49;
            break;
         case 5:
            totalMoney += productQuantity * 6.87;
            break;
         default:
            System.out.println("No product with number " + productNumber);
      }
      
      return true;
   }

}
