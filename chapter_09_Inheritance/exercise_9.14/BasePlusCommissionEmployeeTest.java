/* =====================================================================================
 *       Filename:  BasePlusCommissionEmployeeTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.14 - BasePlusCommissionEmployee class
                                test program
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class BasePlusCommissionEmployeeTest {
   public static void main(String[] args) {
      // instantiate BasePlusCommissionEmployee object
      BasePlusCommissionEmployee employee =             
         new BasePlusCommissionEmployee(                
         "Bob", "Lewis", "333-33-3333", 5000, .04, 300);
      
      // get base-salaried commission employee data
      System.out.println(
         "Employee information obtained by get methods:");
      System.out.printf("%s %s%n", "First name is",
         employee.getFirstName());
      System.out.printf("%s %s%n", "Last name is", 
         employee.getLastName());
      System.out.printf("%s %s%n", "Social security number is", 
         employee.getSocialSecurityNumber());
      System.out.printf("%s %.2f%n", "Gross sales is", 
         employee.getGrossSales());
      System.out.printf("%s %.2f%n", "Commission rate is",
         employee.getCommissionRate());
      System.out.printf("%s %.2f%n", "Base salary is",
         employee.getBaseSalary());

      employee.setBaseSalary(1000); 
      
      System.out.printf("%n%s:%n%n%s%n", 
         "Updated employee information obtained by toString", 
          employee.toString());
   } 
} 
