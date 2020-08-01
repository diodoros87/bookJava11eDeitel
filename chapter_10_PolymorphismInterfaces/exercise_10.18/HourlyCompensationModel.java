/* =====================================================================================
 *       Filename:  HourlyCompensationModel.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.18 - HourlyCompensationModel class 
                                implements CompensationModel interface
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class HourlyCompensationModel implements CompensationModel {            
   private double wage; // wage per hour
   private double hours; // hours worked for week

   public HourlyCompensationModel(double wage, double hours) {
      validateWage(wage);     
      validateHours(hours);     
       
      this.wage = wage;
      this.hours = hours;
   } 
   
   public static void validateWage(double wage) {
      if (wage < 0.0) { // validate wage
         throw new IllegalArgumentException("Hourly wage must be >= 0.0");
      }    
   } 
   
   public static void validateHours(double hours) {
      if ((hours < 0.0) || (hours > 168.0)) { // validate hours
         throw new IllegalArgumentException(
            "Hours worked must be >= 0.0 and <= 168.0");
      }  
   }

   public void setWage(double wage) {
      validateWage(wage);

      this.wage = wage;
   } 

   public double getWage() {return wage;}

   public void setHours(double hours) {
      validateHours(hours);
      
      this.hours = hours;
   } 

   public double getHours() {return hours;}
   
   @Override
   public double earnings() {
      if (getHours() <= 40) { // no overtime                           
         return getWage() * getHours();                                
      }                                                                
      else {                                                           
         return 40 * getWage() + (getHours() - 40) * getWage() * 1.5;  
      }    
   } 

   @Override 
   public String toString() {
      return String.format("%s %n%s: %,.2f %n%s: %.2f%n%s: %.2f", 
         "hourly compensation model", 
         "earnings", earnings(),
         "hourly wage", getWage(), 
         "hours worked", getHours());
   } 
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      Object   clonedObject          = super.clone();
      HourlyCompensationModel cloned = (HourlyCompensationModel) clonedObject;

      return cloned;
   }
} 
