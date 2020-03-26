/* =====================================================================================
 *       Filename:  UserInterface.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.12 - class of user inferface in
                                program with Invoice objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class UserInterface {

   private static final String productNumberPrompt      = "Enter product's number for invoice: ";
   private static final String productDescriptionPrompt = "Enter product's description for invoice: ";
   private static final String productQuantityPrompt    = "Enter product's quantity for invoice: ";
   private static final String productCostPerItemPrompt = "Enter product's cost per item for invoice: ";

   public static Invoice createProductByUser() {
   
      System.out.printf("%n****** Enter data of product (number, description, quantity, cost per item)" 
                         + " to create product.%n");
      String productNumber      = GettingDataFromStandardInput.getString(productNumberPrompt);
      String productDescription = GettingDataFromStandardInput.getString(productDescriptionPrompt);
      int quantity              = GettingDataFromStandardInput.getInteger(productQuantityPrompt);
      double costPerItem        = GettingDataFromStandardInput.getDouble(productCostPerItemPrompt);
      
      Invoice newProduct        = new Invoice(productNumber, productDescription,
                                       quantity, costPerItem);
      return newProduct;
   }
   
   public static Invoice modifyProductDataByUser(Invoice modifyInvoice) {
   
      System.out.printf("%n****** Enter data of product (number, description, quantity, cost per item)" 
                + String.format(" to modify product with number %s%n", modifyInvoice.getProductNumber()));
      
      modifyProductNumber(modifyInvoice);
      modifyProductDescription(modifyInvoice);
      modifyQuantity(modifyInvoice);
      modifyCostPerItem(modifyInvoice);
      
      return modifyInvoice;
   }
   
   public static Invoice modifyProductNumber(Invoice modifyInvoice) {
   
      String productNumber = GettingDataFromStandardInput.getString(productNumberPrompt);
      modifyInvoice.setProductNumber(productNumber);
    
      return modifyInvoice;
   }
   
   public static Invoice modifyProductDescription(Invoice modifyInvoice) {
   
      String productDescription = GettingDataFromStandardInput.getString(productDescriptionPrompt);
      modifyInvoice.setProductDescription(productDescription);
    
      return modifyInvoice;
   }
   
   public static Invoice modifyQuantity(Invoice modifyInvoice) {
   
      int quantity = GettingDataFromStandardInput.getInteger(productQuantityPrompt);
      modifyInvoice.setQuantity(quantity);
    
      return modifyInvoice;
   }
   
   public static Invoice modifyCostPerItem(Invoice modifyInvoice) {
   
      double costPerItem = GettingDataFromStandardInput.getDouble(productCostPerItemPrompt);
      modifyInvoice.setCostPerItem(costPerItem);
    
      return modifyInvoice;
   }
   
   public static void displayProductData(Invoice invoiceToDisplay) {
      System.out.printf("-------Product's data: %n"); 
      System.out.printf("number: \'%s\'\t description: \'%s\'\t quantity: %d\t cost per item: %f%n", 
         invoiceToDisplay.getProductNumber(), invoiceToDisplay.getProductDescription(),
         invoiceToDisplay.getQuantity(), invoiceToDisplay.getCostPerItem());
   }
   
   public static void printInvoiceOfProductSold(Invoice invoiceToDisplay) {
      displayProductData(invoiceToDisplay);
      System.out.printf("Cost of product is: %.2f%n", invoiceToDisplay.getInvoiceAmount()); 
   }
   
}
