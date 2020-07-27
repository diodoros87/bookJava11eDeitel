/* =====================================================================================
 *       Filename:  HourlyEmployee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.15 - HourlyEmployee class inherits from 
                                Employee
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class HourlyEmployee extends Employee {
   private static final int MIN_HOURS = 0;
   private static final int MAX_HOURS = 7 * 24; 
   private static final int STANDARD_HOURS = 5 * 8;
   
   private static final double MIN_WAGE = 0;
              
   private double hours = MIN_HOURS;      
   private double wage  = MIN_WAGE;

   public HourlyEmployee(String firstName, String lastName, String socialSecurityNumber,
                           double hours, double wage) {
      super(firstName, lastName, socialSecurityNumber);
      
      validateHours(hours);
      validateWage(wage);
       
      this.hours = hours;
      this.wage = wage;
   } 
   
   public static void validateHours(double hours) {     
      if (hours < MIN_HOURS || hours > MAX_HOURS) {
         throw new IllegalArgumentException(String.format(
            "Requirement: %d <= 'hours per week in work' <= %d ", MIN_HOURS, MAX_HOURS));
      }
   }
   
   public static void validateWage(double wage) {     
      if (wage < MIN_WAGE) {
         throw new IllegalArgumentException(String.format(
            "Requirement: %.2f <= 'wage rate'", MIN_WAGE));
      }
   }

   public void setHours(double hours) {
      validateHours(hours);   
      this.hours = hours;
   } 

   public double getHours() {return hours;}

   public void setWage(double wage) {
      validateWage(wage);
      this.wage = wage;
   } 

   public double getWage() {return wage;}

   public double earnings() {
      double earnings = getHours() * getWage();
      
      if (getHours() > STANDARD_HOURS) {
         earnings += overtimeAdditionalEarnings(getHours() - STANDARD_HOURS);
      }
         
      return earnings;
   } 
   
   private double overtimeAdditionalEarnings(double overtimeHours) {
      final double OVERTIME_FACTOR = 1.5 - 1.0;
      
      return overtimeHours * getWage() * OVERTIME_FACTOR;
   }

   @Override 
   public String toString() {
      return String.format("%s %s%n%s: %.2f%n%s: %.2f", 
         "hourly", super.toString(),
         "hours per week in work", getHours(), 
         "wage rate", getWage());
   } 
} 
