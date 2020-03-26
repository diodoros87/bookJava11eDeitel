/* =====================================================================================
 *       Filename:  AssertTesting.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.12 - class to testing by asserts program of 
                                inputting and outputting floating-point numbers with 
                                   Invoice objects 
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class AssertTesting {

   public static void expectedResults(Invoice invoice,
                      String productNumber, String productDescription, int quantity, double costPerItem) {
      
      expectedProductNumber(invoice, productNumber);
      expectedProductDescription(invoice, productDescription);
      expectedQuantity(invoice, quantity);
      expectedCostPerItem(invoice, costPerItem);
   } 
   
   public static void expectedProductNumber(Invoice invoice, String productNumber) {
      assert(invoice.getProductNumber().equals(productNumber)) : 
                     String.format("\'%s\' != \'%s\'%n", invoice.getProductNumber(), productNumber);
   } 
   
   public static void expectedProductDescription(Invoice invoice, String productDescription) {
      assert(invoice.getProductDescription().equals(productDescription)) : 
                     String.format("\'%s\' != \'%s\'%n", invoice.getProductDescription(), productDescription);
   } 
   
   public static void expectedQuantity(Invoice invoice, int quantity) {
      assert(invoice.getQuantity() == quantity) : 
                     String.format("%d != %d%n", invoice.getQuantity(), quantity);
   } 
   
   public static void expectedCostPerItem(Invoice invoice, double costPerItem) {
      assert(invoice.getCostPerItem() == costPerItem) : 
                     String.format("%f != %f%n", invoice.getCostPerItem(), costPerItem);
   } 
   
} 
