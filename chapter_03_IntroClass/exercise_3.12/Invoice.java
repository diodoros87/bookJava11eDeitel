/* =====================================================================================
 *       Filename:  Invoice.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.12 - class of product to make invoice
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Invoice {

   private String productNumber;
   private String productDescription;
   private int    quantity;
   private double costPerItem;
   
   // only integer numbers greater than zero are correct numbers
   private static final String onlyIntegersGreaterThanZeroRegex = "^[1-9][0-9]*$";  
  
   public Invoice(String productNumber, String productDescription, int quantity, double costPerItem) {
      
      if (true == productNumber.matches(onlyIntegersGreaterThanZeroRegex)) {
         this.productNumber = productNumber;
      } 
      else {
         this.productNumber = "0"; // number 0 is indication of incorrect product
      } 
      
      this.productDescription = productDescription;

      if (quantity > 0) { 
         this.quantity = quantity;
      }

      if (costPerItem > 0.0) { 
         costPerItem = (int)(costPerItem * 1000); /* in result of casting to int remains
                                                only 3 numbers after dot (decimal separator) */
                                                
         int digitToDelete = (int)costPerItem % 10;
         costPerItem -= digitToDelete; /* in result of difference between costPerItem
                                             and (costPerItem % 10)
                                             third number after dot (equals costPerItem % 10) 
                                             has been deleted */
                                             
         if (digitToDelete > 4) {        // calculate of rounding      
            costPerItem += 10;
         }
                                                
         costPerItem /= 1000; // in result costPerItem has only 2 numbers after dot
         this.costPerItem = costPerItem;
      }
   }
   
   // method that calculate cost for quantity of products in invoice
   public double getInvoiceAmount() {  
      return quantity * costPerItem;
   }

   public void setProductNumber(String productNumber) {
      
      if (true == productNumber.matches(onlyIntegersGreaterThanZeroRegex)) {
         this.productNumber = productNumber;
      } 
      else {
         this.productNumber = "0"; // number 0 is indication of incorrect product
      } 
   } 

   public String getProductNumber() {
      return productNumber; 
   } 
   
   public void setProductDescription(String productDescription) {
      this.productDescription = productDescription; 
   } 

   public String getProductDescription() {
      return productDescription; 
   } 
   
   public void setQuantity(int quantity) {
      if (quantity > 0) { 
         this.quantity = quantity;
      } 
      else {
         this.quantity = 0;
      }
   } 

   public int getQuantity() {
      return quantity; 
   } 
   
   public void setCostPerItem(double costPerItem) {
      if (costPerItem > 0.0) { 
         costPerItem = (int)(costPerItem * 1000); /* in result of casting to int remains
                                                only 3 numbers after dot (decimal separator) */
                                                
         if (costPerItem % 10 > 4) {        // calculate of rounding      
            costPerItem += 10;
         }
         
         costPerItem = (int)costPerItem / 10; /* in result of mix:
                                                casting to int and division by 10
                                                third number after dot (decimal separator) 
                                                has been deleted */
         costPerItem /= 100; // in result costPerItem has only 2 numbers after dot
         this.costPerItem = costPerItem;
      }
      else {
         this.costPerItem = 0;
      }
   } 

   public double getCostPerItem() {
      return costPerItem; 
   } 
}
