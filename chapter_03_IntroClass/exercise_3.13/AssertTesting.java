/* =====================================================================================
 *       Filename:  AssertTesting.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.13 - class to testing by asserts program of 
                                inputting and outputting floating-point numbers with 
                                   Employee objects 
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class AssertTesting {

   public static void expectedResults(Employee employee,
                      String firstName, String lastName, double salaryPerMonth) {
      
      expectedFirstName(employee, firstName);
      expectedLastName(employee, lastName);
      expectedSalaryPerMonth(employee, salaryPerMonth);
   } 
   
   public static void expectedFirstName(Employee employee, String firstName) {
      assert(employee.getFirstName().equals(firstName)) : 
                     String.format("\'%s\' != \'%s\'%n", employee.getFirstName(), firstName);
   } 
   
   public static void expectedLastName(Employee employee, String lastName) {
      assert(employee.getLastName().equals(lastName)) : 
                     String.format("\'%s\' != \'%s\'%n", employee.getLastName(), lastName);
   } 
   
   public static void expectedSalaryPerMonth(Employee employee, double salaryPerMonth) {
      assert(employee.getSalaryPerMonth() == salaryPerMonth) : 
                     String.format("%f != %f%n", employee.getSalaryPerMonth(), salaryPerMonth);
   } 
   
} 
