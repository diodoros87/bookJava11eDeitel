/* =====================================================================================
 *       Filename:  Employee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.13 - class of employee
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Employee {

   private String firstName = ""; // empty string is indication of incorrect names
   private String lastName = "";
   private double salaryPerMonth;
   
   private static final short numberMonthsPerYear = 12;
   // first letter must be [A-Z], second letter and eventually next letters must be [a-z]
   private static final String onlyAlphabeticCharacters = "\\p{Upper}\\p{Lower}+";  
   
   public Employee(String firstName, String lastName, double salaryPerMonth) {
      
      if (true == firstName.matches(onlyAlphabeticCharacters)) {
         this.firstName = firstName;
      } 
      
      if (true == lastName.matches(onlyAlphabeticCharacters)) {
         this.lastName = lastName;
      } 
      
      if (salaryPerMonth > 0.0) { 
         salaryPerMonth = (int)(salaryPerMonth * 1000); /* in result of casting to int remains
                                                only 3 numbers after dot (decimal separator) */
                                                
         if (salaryPerMonth % 10 > 4) {        // calculate of rounding      
            salaryPerMonth += 10;
         }
         
         salaryPerMonth = (int)salaryPerMonth / 10; /* in result of mix:
                                                casting to int and division by 10
                                                third number after dot (decimal separator) 
                                                has been deleted */
         salaryPerMonth /= 100; // in result salaryPerMonth has only 2 numbers after dot
         this.salaryPerMonth = salaryPerMonth;
      }
   }
   
   // method that calculate salary per year
   public double getSalaryPerYear() {  
      return numberMonthsPerYear * salaryPerMonth;
   }

   public void setFirstName(String firstName) {
      if (true == firstName.matches(onlyAlphabeticCharacters)) {
         this.firstName = firstName;
      } 
   } 

   public String getFirstName() {
      return firstName; 
   } 
   
   public void setLastName(String lastName) {
      if (true == lastName.matches(onlyAlphabeticCharacters)) {
         this.lastName = lastName;
      } 
   } 

   public String getLastName() {
      return lastName; 
   } 
   
   public void setSalaryPerMonth(double salaryPerMonth) {
      if (salaryPerMonth > 0.0) { 
         salaryPerMonth = (int)(salaryPerMonth * 1000); /* in result of casting to int remains
                                                only 3 numbers after dot (decimal separator) */
                                                
         int digitToDelete = (int)salaryPerMonth % 10;
         salaryPerMonth -= digitToDelete; /* in result of difference between salaryPerMonth
                                             and (salaryPerMonth % 10)
                                             third number after dot (equals salaryPerMonth % 10) 
                                             has been deleted */
                                             
         if (digitToDelete > 4) {        // calculate of rounding      
            salaryPerMonth += 10;
         }
                                                
         salaryPerMonth /= 1000; // in result salaryPerMonth has only 2 numbers after dot
         
         if (salaryPerMonth > 0.0)
            this.salaryPerMonth = salaryPerMonth;
      }
   } 

   public double getSalaryPerMonth() {
      return salaryPerMonth; 
   } 
}
