/* =====================================================================================
 *       Filename:  Invoice.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.16 - Invoice class that implements Payable
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Invoice implements Payable {
   private final String partNumber; 
   private final String partDescription;
   private final int quantity;
   private final double pricePerItem;

   // constructor
   public Invoice(String partNumber, String partDescription, int quantity,
      double pricePerItem) {
      if (quantity < 0) { // validate quantity
         throw new IllegalArgumentException("Quantity must be >= 0");
      }
      
      if (pricePerItem < 0.0) { // validate pricePerItem
         throw new IllegalArgumentException(
            "Price per item must be >= 0");
      }

      this.quantity = quantity;
      this.partNumber = partNumber;
      this.partDescription = partDescription;
      this.pricePerItem = pricePerItem;
   } 

   // get part number
   public String getPartNumber() {return partNumber;}

   // get description
   public String getPartDescription() {return partDescription;}

   // get quantity
   public int getQuantity() {return quantity;}

   // get price per item
   public double getPricePerItem() {return pricePerItem;}

   // return String representation of Invoice object
   @Override
   public String toString() {
      return String.format("%s: %n%s: %s (%s) %n%s: %d %n%s: $%,.2f", 
         "invoice", "part number", getPartNumber(), getPartDescription(), 
         "quantity", getQuantity(), "price per item", getPricePerItem());
   } 

   // method required to carry out contract with interface Payable     
   @Override                                                           
   public double getPaymentAmount() {                                  
      return getQuantity() * getPricePerItem(); // calculate total cost
   }                                                                   
} 
