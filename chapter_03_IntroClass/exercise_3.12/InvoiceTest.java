/* =====================================================================================
 *       Filename:  InvoiceTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.12 - Inputting and outputting 
                                floating-point numbers with Invoice objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class InvoiceTest {

   public static void main(String[] args) {
      manageCorrectData();
      
      createIncorrectData();
      
      Invoice createdInvoiceByUser = UserInterface.createProductByUser();
      UserInterface.printInvoiceOfProductSold(createdInvoiceByUser);
      
      UserInterface.modifyProductDataByUser(createdInvoiceByUser);
      UserInterface.printInvoiceOfProductSold(createdInvoiceByUser);
         
   } 
   
   private static void manageCorrectData() {
      // examples of correct data of products:
      System.out.printf("+++	 Display correct data of products:%n");
      
      Invoice bread = testingDisplayInvoice(new Invoice("1", "bread", 4, 50.004),
                                                "1", "bread", 4, 50);
      Invoice laptop = testingDisplayInvoice(new Invoice("101", "laptop", 1, 7.537),
                                                "101", "laptop", 1, 7.54);
      Invoice calculator = testingDisplayInvoice(new Invoice
                     ("57", "calculator", 22, 87.997), "57", "calculator", 22, 88);
      
      // trying to set only one of instance's variable 
      modifyProductNumber(bread, "6", "6");
      modifyProductNumber(bread, "t", "0");
      modifyQuantity(laptop, -2, 0);
      modifyQuantity(laptop, 3, 3);
      modifyCostPerItem(calculator, 200.2349, 200.23);
      modifyCostPerItem(calculator, 34.4551, 34.46);
      modifyCostPerItem(calculator, -34.4551, 0);
   }
   
   private static void createIncorrectData() {
      // examples of incorrect data of products:
      System.out.printf("%n+++	 Display incorrect data of products:%n");
      
      Invoice notNumber = testingDisplayInvoice(new Invoice
      ("notNumber", "notNumber", 5, 5.88), "0", "notNumber", 5, 5.88);
      
      Invoice mixDigitsLetters = testingDisplayInvoice(new Invoice
      ("2mixDigitsLetters", "mixDigitsLetters", 8, 5.80), "0", "mixDigitsLetters", 8, 5.8);
      
      Invoice mixDigitsLetters9 = testingDisplayInvoice(new Invoice
      ("mixDigitsLetters9", "mixDigitsLetters", 8, -5.80), "0", "mixDigitsLetters", 8, 0);
      
      Invoice mixDigitsLetters7 = testingDisplayInvoice(new Invoice
      ("mix7DigitsLetters", "mixDigitsLetters", -8, 5.8), "0", "mixDigitsLetters", 0, 5.8);
      
      Invoice empty = testingDisplayInvoice(new Invoice("", "", -2, -5.804), "0", "", 0, 0);
      Invoice minusNumber = testingDisplayInvoice(new Invoice
                        ("-1", "minusNumber", 4, 50.0048), "0", "minusNumber", 4, 50);
      Invoice zeroNumber = testingDisplayInvoice(new Invoice("0", "zero", 4, 0.1248),
                                                               "0", "zero", 4, 0.12);
      Invoice spaceNumber = testingDisplayInvoice(new Invoice
      (" 6", "spaceNumber", -4, 0.1248), "0", "spaceNumber", 0, 0.12);
   }
   
   private static Invoice testingDisplayInvoice(Invoice invoice, String expectedProductNumber, 
               String expectedProductDescription, int expectedQuantity, double expectedCostPerItem) {
                              
      AssertTesting.expectedResults(invoice, expectedProductNumber, expectedProductDescription, 
                                    expectedQuantity, expectedCostPerItem);
      UserInterface.printInvoiceOfProductSold(invoice);
      
      return invoice;
   }
   
   private static void modifyProductNumber(Invoice invoice, String productNumber, String expectedValue) {
      System.out.printf("%n###	 Try to set new product's number \'%s\'%n", productNumber);
      invoice.setProductNumber(productNumber);
      AssertTesting.expectedProductNumber(invoice, expectedValue);
      UserInterface.printInvoiceOfProductSold(invoice);
   }
   
   private static void modifyQuantity(Invoice invoice, int quantity, int expectedValue) {
      System.out.printf("%n###	 Try to set new product's quantity %d%n", quantity);
      invoice.setQuantity(quantity);
      AssertTesting.expectedQuantity(invoice, expectedValue);
      UserInterface.printInvoiceOfProductSold(invoice);
   }
   
   private static void modifyCostPerItem(Invoice invoice, double costPerItem, double expectedValue) {
      System.out.printf("%n###	 Try to set new product's cost per item %f%n", costPerItem);
      invoice.setCostPerItem(costPerItem);
      AssertTesting.expectedCostPerItem(invoice, expectedValue);
      UserInterface.printInvoiceOfProductSold(invoice);
   }
   
} 
