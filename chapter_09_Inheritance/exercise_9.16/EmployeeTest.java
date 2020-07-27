/* =====================================================================================
 *       Filename:  EmployeeTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.16 - Employee class test program
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class EmployeeTest {
   public static void main(String[] args) {
      CommissionCompensationModel commissionModel = new CommissionCompensationModel(1000, 0.25);
      Employee firstEmployee = new Employee("Bob", "Lewis", "333-33-3333", commissionModel);
      System.out.printf("%s %n%n", firstEmployee);

      BasePlusCommissionCompensationModel basePlusCommissionModel =             
         new BasePlusCommissionCompensationModel(5000, .04, 300);
      Employee secondEmployee = new Employee("Tom", "Jerry", "888-11-3333", basePlusCommissionModel);
      System.out.printf("%s %n%n", secondEmployee);
      
      System.out.println("After changing of compensation model:");
      
      BasePlusCommissionCompensationModel otherBasePlusCommissionModel =             
         new BasePlusCommissionCompensationModel(750, .03, 200);
      firstEmployee.setCompensationModel(otherBasePlusCommissionModel);
      System.out.printf("%s %n%n", firstEmployee);
      
      CommissionCompensationModel otherCommissionModel = new CommissionCompensationModel(50_000, 0.05);
      secondEmployee.setCompensationModel(otherCommissionModel);
      System.out.printf("%s %n%n", secondEmployee);
      
   }
} 
