/* =====================================================================================
 *       Filename:  CommissionCompensationModel.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.18 - CommissionCompensationModel class 
                                implements CompensationModel interface
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class CommissionCompensationModel implements CompensationModel {            
   private double grossSales;      
   private double commissionRate;

   public CommissionCompensationModel(double grossSales, double commissionRate) {
      validateGrossSales(grossSales);     
      validateCommissionRate(commissionRate);     
       
      this.grossSales = grossSales;
      this.commissionRate = commissionRate;
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
   
   @Override
   public double earnings() {
      return getCommissionRate() * getGrossSales();
   } 

   @Override 
   public String toString() {
      return String.format("%s %n%s: %,.2f %n%s: %.2f%n%s: %.2f", 
         "commission compensation model", 
         "earnings", earnings(),
         "gross sales", getGrossSales(), 
         "commission rate", getCommissionRate());
   } 
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      Object   clonedObject              = super.clone();
      CommissionCompensationModel cloned = (CommissionCompensationModel) clonedObject;

      return cloned;
   }
} 
