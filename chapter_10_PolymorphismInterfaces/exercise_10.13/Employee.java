/* =====================================================================================
 *       Filename:  Employee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.13 - Employee abstract superclass
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public abstract class Employee {
   private final String firstName;
   private final String lastName;
   private final String socialSecurityNumber;
   private final Date   birthDate;
   
   private double premium = 0;

   // constructor
   public Employee(String firstName, String lastName, 
      String socialSecurityNumber, Date birthDate) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.socialSecurityNumber = socialSecurityNumber;
      this.birthDate = birthDate;
      this.premium = 0;
   } 

   // return first name
   public String getFirstName() {return firstName;}

   // return last name
   public String getLastName() {return lastName;}

   // return social security number
   public String getSocialSecurityNumber() {return socialSecurityNumber;}
   
   // return birth date
   public Date getBirthDate() {return birthDate;}
   
   public void setPremium(double premium) {
      if (premium < 0) {
         throw new IllegalArgumentException("premium must be >= 0.0");
      }
      
      this.premium = premium;
   }
   
   public double getPremium() {
      return premium;
   }

   // return String representation of Employee object
   @Override
   public String toString() {
      return String.format("%s %s%nsocial security number: %s%n birth date: %s", 
         getFirstName(), getLastName(), getSocialSecurityNumber(), getBirthDate());
   }

   public double earnings() {
      return premium;
   }
} 
