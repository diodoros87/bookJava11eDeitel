/* =====================================================================================
 *       Filename:  BasePlusCommissionCompensationModel.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.18 - BasePlusCommissionCompensationModel class  
                                implements directly CompensationModel interface - 
                                instead of
                                inherits from CommissionCompensationModel
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class BasePlusCommissionCompensationModel implements CompensationModel {
   private double grossSales;      
   private double commissionRate;
   private double baseSalary;

   public BasePlusCommissionCompensationModel(double grossSales, double commissionRate, double baseSalary) {
      validateGrossSales(grossSales);     
      validateCommissionRate(commissionRate);  
      validateBaseSalary(baseSalary);     
       
      this.grossSales = grossSales;
      this.commissionRate = commissionRate;                      
      this.baseSalary = baseSalary;
   }
   
   public static void validateGrossSales(double grossSales) {
      if (grossSales < 0.0) {
         throw new IllegalArgumentException("Gross sales must be >= 0.0");
      }      
   } 
   
   public static void validateCommissionRate(double commissionRate) {
      if (commissionRate <= 0.0 || commissionRate >= 1.0) {
         throw new IllegalArgumentException(
            "Commission rate must be > 0.0 and < 1.0");
      }     
   }

   public void setGrossSales(double grossSales) {
      validateGrossSales(grossSales);

      this.grossSales = grossSales;
   } 

   public double getGrossSales() {return grossSales;}

   public void setCommissionRate(double commissionRate) {
      validateCommissionRate(commissionRate);
      
      this.commissionRate = commissionRate;
   } 

   public double getCommissionRate() {return commissionRate;}
   
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
   public double earnings() {
      return getBaseSalary() + getCommissionRate() * getGrossSales();
   }

   @Override
   public String toString() {
      String commisionString = String.format("%s %n%s: %,.2f %n%s: %.2f%n%s: %.2f", 
         "commission compensation model", 
         "earnings", earnings(),
         "gross sales", getGrossSales(), 
         "commission rate", getCommissionRate());
         
      return String.format("%s %s%n%s: %.2f", "base-salaried",
         commisionString, "base salary", getBaseSalary());   
   } 
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      Object   clonedObject                      = super.clone();
      BasePlusCommissionCompensationModel cloned = (BasePlusCommissionCompensationModel) clonedObject;

      return cloned;
   }
} 
