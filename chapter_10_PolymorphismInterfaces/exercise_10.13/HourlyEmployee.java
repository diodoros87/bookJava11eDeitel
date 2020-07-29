/* =====================================================================================
 *       Filename:  HourlyEmployee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.13 - class of HourlyEmployee
                                extends Employee
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class HourlyEmployee extends Employee {
   private double wage; // wage per hour
   private double hours; // hours worked for week

   // constructor
   public HourlyEmployee(String firstName, String lastName,
      String socialSecurityNumber, Date birthDate, double wage, double hours) {
      super(firstName, lastName, socialSecurityNumber, birthDate);

      if (wage < 0.0) { // validate wage
         throw new IllegalArgumentException("Hourly wage must be >= 0.0");
      }

      if ((hours < 0.0) || (hours > 168.0)) { // validate hours
         throw new IllegalArgumentException(
            "Hours worked must be >= 0.0 and <= 168.0");
      }

      this.wage = wage;
      this.hours = hours;
   } 

   // set wage
   public void setWage(double wage) {
      if (wage < 0.0) { // validate wage
         throw new IllegalArgumentException("Hourly wage must be >= 0.0");
      }

      this.wage = wage;
   } 

   // return wage
   public double getWage() {return wage;}

   // set hours worked
   public void setHours(double hours) {
      if ((hours < 0.0) || (hours > 168.0)) { // validate hours
         throw new IllegalArgumentException(
            "Hours worked must be >= 0.0 and <= 168.0");
      }

      this.hours = hours;
   } 

   // return hours worked
   public double getHours() {return hours;}

   @Override                                                           
   public double earnings() {     
      double earnings = super.earnings();
      
      if (getHours() <= 40) { // no overtime                           
         earnings += getWage() * getHours();                                
      }                                                                
      else {                                                           
         earnings += 40 * getWage() + (getHours() - 40) * getWage() * 1.5;  
      }
      
      return earnings;                                                                
   }                                                                   

   // return String representation of HourlyEmployee object              
   @Override                                                             
   public String toString() {                                            
      return String.format("hourly employee: %s%n%s: $%,.2f; %s: %,.2f", 
         super.toString(), "hourly wage", getWage(),                     
         "hours worked", getHours());                                    
   }                                                                     
} 
