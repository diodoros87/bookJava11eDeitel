/* =====================================================================================
 *       Filename:  UserInterface.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.19 - class of user inferface in
                                program with CommissionCalculator objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class UserInterface {
   
   public static String promptFormatting(String productNumber) {
      return String.format("Enter quantity (integer not less than zero) of %s product: ", productNumber);
   }
   
   public static void modifySalesmanDataByUser(CommissionCalculator salesmanData) {
      System.out.printf("%n****** To quit enter sequence other than integer.%n");
      System.out.printf("%n****** Enter data of salesman to display salesman's salary.%n");
   
      long product1stQuantity = GettingDataFromStandardInput.getLongInteger(promptFormatting("1st"));
      long product2ndQuantity = GettingDataFromStandardInput.getLongInteger(promptFormatting("2nd"));
      long product3rdQuantity = GettingDataFromStandardInput.getLongInteger(promptFormatting("3rd"));
      long product4thQuantity = GettingDataFromStandardInput.getLongInteger(promptFormatting("4th"));
      
      salesmanData.setProduct1stQuantity(product1stQuantity);
      salesmanData.setProduct2ndQuantity(product2ndQuantity);
      salesmanData.setProduct3rdQuantity(product3rdQuantity);
      salesmanData.setProduct4thQuantity(product4thQuantity);
   }
      
   public static void displaySalesmanSalary(CommissionCalculator salesmanData) {
      System.out.printf("-------Salesman's salary is %.2f %n", salesmanData.getTotalSalary()); 
   }
   
}
