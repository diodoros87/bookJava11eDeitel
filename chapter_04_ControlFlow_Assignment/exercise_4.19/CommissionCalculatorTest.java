/* =====================================================================================
 *       Filename:  CommissionCalculatorTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.19 - Solving the calculate salary of many 
                                salesmans using sentinel-controlled iteration
                                   with CommissionCalculator objects
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class CommissionCalculatorTest {

   public static void main(String[] args) {
      CommissionCalculator salesmanData = new CommissionCalculator();
      
      UserInterface.modifySalesmanDataByUser(salesmanData);
      while (true) {
         UserInterface.displaySalesmanSalary(salesmanData);
         salesmanData.resetDataForNextSalesman();
         UserInterface.modifySalesmanDataByUser(salesmanData);
      }
   } 
   
} 
