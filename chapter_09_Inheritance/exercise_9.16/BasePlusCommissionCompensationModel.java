/* =====================================================================================
 *       Filename:  BasePlusCommissionCompensationModel.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.16 - BasePlusCommissionCompensationModel class 
                                inherits from CommissionCompensationModel - instead of
                                   using BasePlusCommissionEmployee class
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class BasePlusCommissionCompensationModel extends CommissionCompensationModel {
   private double baseSalary;

   public BasePlusCommissionCompensationModel(double grossSales, double commissionRate, double baseSalary) {
      super(grossSales, commissionRate);                      

      validateBaseSalary(baseSalary);      

      this.baseSalary = baseSalary;
   }
   
   public static void validateBaseSalary(double baseSalary) {
      if (baseSalary < 0.0) {                  
         throw new IllegalArgumentException("Base salary must be >= 0.0");
      }                     
   } 
   
   public void setBaseSalary(double baseSalary) {
      validateBaseSalary(baseSalary);      

      this.baseSalary = baseSalary;                
   } 

   public double getBaseSalary() {return baseSalary;}

   @Override 
   public double earnings() {return getBaseSalary() + super.earnings();}

   @Override
   public String toString() {
      return String.format("%s %s%n%s: %.2f", "base-salaried",
         super.toString(), "base salary", getBaseSalary());   
   } 
} 
