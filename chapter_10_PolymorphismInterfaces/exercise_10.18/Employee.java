/* =====================================================================================
 *       Filename:  Employee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.18 - Employee class that
                                implements Payable and uses
                                composition with reference to CompensationModel's
                                object to calculate earnings
                                 instead of using Employee class inheritance
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import validateParametersPackage.ValidateParameters;
 
public class Employee implements Payable, Cloneable {
   private final String firstName;                        
   private final String lastName;                         
   private final String socialSecurityNumber;
   
   private CompensationModel compensationModel;             

   public Employee(String firstName, String lastName, String socialSecurityNumber,
                           CompensationModel compensationModel) {
      ValidateParameters.checkNullPointer(compensationModel);   
      ValidateParameters.checkBlankString(firstName, lastName, socialSecurityNumber);
      
      this.firstName = firstName;                                    
      this.lastName = lastName;                                    
      this.socialSecurityNumber = socialSecurityNumber;    
      this.compensationModel = compensationModel;      
   } 

   public String getFirstName() {return firstName;}

   public String getLastName() {return lastName;}

   public String getSocialSecurityNumber() {return socialSecurityNumber;}
   
   public void setCompensationModel(CompensationModel compensationModel) {
      ValidateParameters.checkNullPointer(compensationModel);
      
      this.compensationModel = compensationModel;
   }
   
   public CompensationModel getCompensationModel() {return compensationModel;}
   
   public double earnings() {
      return compensationModel.earnings();
   }
   
   public double getPaymentAmount() {return earnings();}   

   @Override 
   public String toString() {
      return String.format("%s: %s %s%n%s: %s%n%s%n", 
         "employee", getFirstName(), getLastName(), 
         "social security number", getSocialSecurityNumber(),
         compensationModel.toString());
   } 
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      Object   clonedObject   = super.clone();
      Employee clonedEmployee = (Employee) clonedObject;

      Object            clonedCompensationModelObject = this.compensationModel.clone();
      CompensationModel clonedCompensationModel       = (CompensationModel)clonedCompensationModelObject;

      clonedEmployee.setCompensationModel(clonedCompensationModel);

      return clonedEmployee;
   }
} 
