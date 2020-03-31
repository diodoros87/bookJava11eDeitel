/* =====================================================================================
 *       Filename:  SalaryCalculator.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.20 - class of calculate employee's weekly salary 
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class SalaryCalculator {

   private static final int standardWorkingHoursPerWeek = 40;
   private static final double overtimeSalaryRate  = 1.5;
   
   private String lastName = "";
   private double salaryPerHour;
   private double workingHours;
   
   public void resetDataForNextEmployee() {
      lastName = "";
      salaryPerHour = 0;
      workingHours = 0;
   }
   
   public double getTotalSalary() {
      double salary = getStandardSalary();
      if (workingHours > standardWorkingHoursPerWeek) {
         salary += overtimeSalaryRate * salaryPerHour * (workingHours - standardWorkingHoursPerWeek);
      }
      return salary;
   }
   
   public double getStandardSalary() {
      if (workingHours <= standardWorkingHoursPerWeek) {
         return salaryPerHour * workingHours;
      }
      else {
         return salaryPerHour * standardWorkingHoursPerWeek;
      }
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   } 

   public String getLastName() {
      return lastName; 
   } 
   
   public void setSalaryPerHour(double salaryPerHour) {
      if (salaryPerHour >= 0) {
         this.salaryPerHour = salaryPerHour;
      }
   } 

   public double getSalaryPerHour() {
      return salaryPerHour; 
   } 
   
   public void setWorkingHours(double workingHours) {
      if (workingHours >= 0) {
         this.workingHours = workingHours;
      }
   } 

   public double getWorkingHours() {
      return workingHours; 
   } 
}
