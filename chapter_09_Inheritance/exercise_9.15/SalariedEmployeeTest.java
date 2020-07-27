/* =====================================================================================
 *       Filename:  SalariedEmployeeTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.15 - SalariedEmployee class
                                test program
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class SalariedEmployeeTest {
   public static void main(String[] args) {
      SalariedEmployee employee =             
         new SalariedEmployee(                
         "Bob", "Lewis", "333-33-3333", 5000.98);
      
      System.out.println(
         "Salaried employee information obtained by get methods:");
      System.out.printf("%s %s%n", "First name is",
         employee.getFirstName());
      System.out.printf("%s %s%n", "Last name is", 
         employee.getLastName());
      System.out.printf("%s %s%n", "Social security number is", 
         employee.getSocialSecurityNumber());
      System.out.printf("%s %,.2f%n", "Salary is",
         employee.getSalary());
      System.out.printf("%s %,.2f%n", "Earnings is",
         employee.earnings());

      employee.setSalary(1000); 
      
      System.out.printf("%n%s:%n%n%s%n", 
         "Updated employee information obtained by toString", 
          employee.toString());
      System.out.printf("%s %,.2f%n", "Earnings is",
         employee.earnings());
   } 
} 
