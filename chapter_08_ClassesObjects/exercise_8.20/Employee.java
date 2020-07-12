/* =====================================================================================
 *       Filename:  Employee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.20 - Employee class with references to
                                other objects
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Employee {
   private final String firstName;
   private final String lastName;
   private final Date birthDate;
   private final Date hireDate; 

   // constructor to initialize name, birth date and hire date
   public Employee(String firstName, String lastName, Date birthDate, 
      Date hireDate) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.birthDate = birthDate;
      this.hireDate = hireDate;
   } 

   // convert Employee to String format
   public String toString() {
      return String.format("%s, %s  Hired: %s  Birthday: %s", 
         lastName, firstName, hireDate, birthDate);
   } 
} 
