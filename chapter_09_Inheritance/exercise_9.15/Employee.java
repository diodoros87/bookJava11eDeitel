/* =====================================================================================
 *       Filename:  Employee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.15 - Employee class 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import validateParametersPackage.ValidateParameters;
 
public class Employee {
   private final String firstName;                        
   private final String lastName;                         
   private final String socialSecurityNumber;             

   public Employee(String firstName, String lastName, String socialSecurityNumber) {   
      ValidateParameters.checkBlankString(firstName, lastName, socialSecurityNumber);
      
      this.firstName = firstName;                                    
      this.lastName  = lastName;                                    
      this.socialSecurityNumber = socialSecurityNumber;         
   } 

   public String getFirstName() {return firstName;}

   public String getLastName() {return lastName;}

   public String getSocialSecurityNumber() {return socialSecurityNumber;}

   @Override 
   public String toString() {
      return String.format("%s: %s %s%n%s: %s", 
         "employee", getFirstName(), getLastName(), 
         "social security number", getSocialSecurityNumber());
   } 
} 
