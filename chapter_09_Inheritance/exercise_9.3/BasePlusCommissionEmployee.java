/* =====================================================================================
 *       Filename:  BasePlusCommissionEmployee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.3 - BasePlusCommissionEmployee class uses
                                composition with reference to CommissionEmployee's
                                object instead of CommissionEmployee class inheritance
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class BasePlusCommissionEmployee {
   private double baseSalary; // base salary per week
   private CommissionEmployee commissionEmployee;

   // six-argument constructor
   public BasePlusCommissionEmployee(String firstName, String lastName, String socialSecurityNumber,
                     double grossSales, double commissionRate, double baseSalary) {
         
      commissionEmployee = new CommissionEmployee(firstName, lastName, socialSecurityNumber, 
         grossSales, commissionRate);                      

      // if baseSalary is invalid throw exception
      if (baseSalary < 0.0) {                  
         throw new IllegalArgumentException("Base salary must be >= 0.0");
      }       

      this.baseSalary = baseSalary;
   }
   
   // return first name
   public String getFirstName() {
      return commissionEmployee.getFirstName();
   }

   // return last name
   public String getLastName() {
      return commissionEmployee.getLastName();
   }

   // return social security number 
   public String getSocialSecurityNumber() {
      return commissionEmployee.getSocialSecurityNumber();
   }

   // set gross sales amount
   public void setGrossSales(double grossSales) {
      commissionEmployee.setGrossSales(grossSales);
   } 

   // return gross sales amount
   public double getGrossSales() {
      return commissionEmployee.getGrossSales();
   }

   // set commission rate
   public void setCommissionRate(double commissionRate) {
      commissionEmployee.setCommissionRate(commissionRate);
   } 

   // return commission rate
   public double getCommissionRate() {
      return commissionEmployee.getCommissionRate();
   }

   
   // set base salary
   public void setBaseSalary(double baseSalary) {
      if (baseSalary < 0.0) {                  
         throw new IllegalArgumentException("Base salary must be >= 0.0");
      }       

      this.baseSalary = baseSalary;               
   } 

   // return base salary
   public double getBaseSalary() {return baseSalary;}

   // calculate earnings
   public double earnings() {
      return getBaseSalary() + commissionEmployee.earnings();
   }

   // return String representation of BasePlusCommissionEmployee
   @Override
   public String toString() {
      return String.format("%s %s%n%s: %.2f", "base-salaried",
         commissionEmployee.toString(), "base salary", getBaseSalary());   
   } 
} 
