/* =====================================================================================
 *       Filename:  SalariedEmployee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.15 - SalariedEmployee class 
                                inherits from Employee 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class SalariedEmployee extends Employee {
   private static final double MIN_SALARY  = 0;
   private double salary                   = MIN_SALARY;

   public SalariedEmployee(String firstName, String lastName, String socialSecurityNumber, double salary) {
      super(firstName, lastName, socialSecurityNumber);                      

      validateSalary(salary);   

      this.salary = salary;
   }
   
   public static void validateSalary(double salary) {     
      if (salary < MIN_SALARY) {
         throw new IllegalArgumentException(String.format(
            "Requirement: %,.2f <= salary", MIN_SALARY));
      }
   }
   
   public void setSalary(double salary) {
      validateSalary(salary);      

      this.salary = salary;                
   } 

   public double getSalary() {return salary;}

   public double earnings() {return getSalary();}

   @Override
   public String toString() {
      return String.format("%s %s%n%s: %,.2f", "salaried",
         super.toString(), "salary", getSalary());   
   } 
} 
