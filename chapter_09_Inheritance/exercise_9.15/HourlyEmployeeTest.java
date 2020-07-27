/* =====================================================================================
 *       Filename:  HourlyEmployeeTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.15 - HourlyEmployee class test program
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class HourlyEmployeeTest {
   public static void main(String[] args) { 
      HourlyEmployee employee = new HourlyEmployee(
         "Philip", "August", "222-22-2222", 50, 30);       
      
      // get commission employee data
      System.out.println("Hourly employee information obtained by get methods:");
      System.out.printf("%n%s %s%n", "First name is",
         employee.getFirstName());
      System.out.printf("%s %s%n", "Last name is", 
         employee.getLastName());
      System.out.printf("%s %s%n", "Social security number is", 
         employee.getSocialSecurityNumber());
      System.out.printf("%s %.2f%n", "Hours per week in work are", 
         employee.getHours());
      System.out.printf("%s %.2f%n", "Wage rate is",
         employee.getWage());
      System.out.printf("%s %,.2f%n", "Earnings is",
         employee.earnings());

      employee.setHours(30.5);  
      employee.setWage(50);
      
      System.out.printf("%n%s:%n%n%s%n",                                
         "Updated employee information obtained by toString", employee);
      System.out.printf("%s %,.2f%n", "Earnings is",
         employee.earnings());
   } 
} 
