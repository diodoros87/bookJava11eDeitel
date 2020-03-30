/* =====================================================================================
 *       Filename:  CommissionCalculator.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.19 - class of calculate salesman's salary 
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class CommissionCalculator {

   private static final double salary     = 2000;
   private static final double commission = 0.09;
   
   private static final double product1stCost = 239.99;
   private static final double product2ndCost = 1129.75;
   private static final double product3rdCost = 99.95;
   private static final double product4thCost = 850.89;
   
   private long product1stQuantity;
   private long product2ndQuantity;
   private long product3rdQuantity;
   private long product4thQuantity;
   
   public void resetDataForNextSalesman() {
      product1stQuantity = 0;
      product2ndQuantity = 0;
      product3rdQuantity = 0;
      product4thQuantity = 0;
   }
   
   public double getTotalSalary() {
      return getCommissionSalary() + salary;
   }
   
   public double getCommissionSalary() {
      return commission * getSalesAmount();
   }
   
   public double getSalesAmount() {
      return product1stQuantity * product1stCost + product2ndQuantity * product2ndCost +
             product3rdQuantity * product3rdCost + product4thQuantity * product4thCost;
   }

   public void setProduct1stQuantity(long product1stQuantity) {
      if (product1stQuantity >= 0) {
         this.product1stQuantity = product1stQuantity;
      }
   } 

   public long getProduct1stQuantity() {
      return product1stQuantity; 
   } 
   
   public void setProduct2ndQuantity(long product2ndQuantity) {
      if (product2ndQuantity >= 0) {
         this.product2ndQuantity = product2ndQuantity;
      }
   } 

   public long getProduct2ndQuantity() {
      return product2ndQuantity; 
   } 
   
   public void setProduct3rdQuantity(long product3rdQuantity) {
      if (product3rdQuantity >= 0) {
         this.product3rdQuantity = product3rdQuantity;
      }
   } 

   public long getProduct3rdQuantity() {
      return product3rdQuantity; 
   } 
   
   public void setProduct4thQuantity(long product4thQuantity) {
      if (product4thQuantity >= 0) {
         this.product4thQuantity = product4thQuantity;
      }
   } 

   public long getProduct4thQuantity() {
      return product4thQuantity; 
   } 
   
}
