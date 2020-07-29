/* =====================================================================================
 *       Filename:  Employee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.16 - Employee class abstract superclass that
                                implements Payable
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public abstract class Employee implements Payable {
   private final String firstName;
   private final String lastName;
   private final String socialSecurityNumber;

   // constructor
   public Employee(String firstName, String lastName, 
      String socialSecurityNumber) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.socialSecurityNumber = socialSecurityNumber;
   } 

   // return first name
   public String getFirstName() {return firstName;}

   // return last name
   public String getLastName() {return lastName;}

   // return social security number
   public String getSocialSecurityNumber() {return socialSecurityNumber;}

   // return String representation of Employee object
   @Override
   public String toString() {
      return String.format("%s %s%nsocial security number: %s", 
         getFirstName(), getLastName(), getSocialSecurityNumber());
   }

   // abstract method must be overridden by concrete subclasses
   public abstract double earnings(); // no implementation here

   // implementing getPaymentAmount here enables the entire Employee
   // class hierarchy to be used in an app that processes Payables  
   public double getPaymentAmount() {return earnings();}            
} 
