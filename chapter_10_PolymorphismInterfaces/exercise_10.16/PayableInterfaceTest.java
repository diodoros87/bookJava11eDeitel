/* =====================================================================================
 *       Filename:  PayableInterfaceTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.16 - Payable interface test program processing
                                Invoices and Employees polymorphically
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class PayableInterfaceTest {
   public static void main(String[] args) {
      // create four-element Payable array
      Payable[] payableObjects = new Payable[] {
         new Invoice("01234", "seat", 2, 375.00),
         new Invoice("56789", "tire", 4, 79.95),
         new SalariedEmployee("John", "Smith", "111-11-1111", 800.00),                                    
         new HourlyEmployee("Karen", "Price", "222-22-2222", 16.75, 40),                            
         new CommissionEmployee("Sue", "Jones", "333-33-3333", 10000, .06),                      
         new BasePlusCommissionEmployee("Bob", "Lewis", "444-44-4444", 5000, .04, 300)
      };

      System.out.println(
         "Invoices and Employees processed polymorphically:"); 
      
      // generically process each element in array payableObjects
      for (Payable currentPayable : payableObjects) {
         System.out.printf("%n%s %n", 
            currentPayable.toString());
         
         // determine whether element is a BasePlusCommissionEmployee
         if (currentPayable instanceof BasePlusCommissionEmployee) {
            // downcast Payable reference to 
            // BasePlusCommissionEmployee reference
            BasePlusCommissionEmployee employee = 
               (BasePlusCommissionEmployee) currentPayable;

            employee.setBaseSalary(1.10 * employee.getBaseSalary());

            System.out.printf(
               "new base salary with 10%% increase is: $%,.2f%n",
               employee.getBaseSalary());
         } 
         // output currentPayable and its appropriate payment amount
         System.out.printf("payment due: $%,.2f%n", 
            currentPayable.getPaymentAmount()); 
      } 
   } 
} 
