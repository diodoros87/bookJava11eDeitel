/* =====================================================================================
 *       Filename:  SalariedEmployee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.13 - class of SalariedEmployee
                                extends Employee
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class SalariedEmployee extends Employee {
   private double weeklySalary;

   // constructor
   public SalariedEmployee(String firstName, String lastName, 
      String socialSecurityNumber, Date birthDate, double weeklySalary) {
      super(firstName, lastName, socialSecurityNumber, birthDate); 

      if (weeklySalary < 0.0) {
         throw new IllegalArgumentException(
            "Weekly salary must be >= 0.0");
      }

      this.weeklySalary = weeklySalary;
   } 

   // set salary
   public void setWeeklySalary(double weeklySalary) {
      if (weeklySalary < 0.0) {
         throw new IllegalArgumentException(
            "Weekly salary must be >= 0.0");
      }

      this.weeklySalary = weeklySalary;
   } 

   // return salary
   public double getWeeklySalary() {return weeklySalary;}

   @Override                                                           
   public double earnings() {
      return getWeeklySalary() + super.earnings();
   }                

   // return String representation of SalariedEmployee object  
   @Override                                                   
   public String toString() {                                  
      return String.format("salaried employee: %s%n%s: $%,.2f",
         super.toString(), "weekly salary", getWeeklySalary());
   }                                                           
} 
