/* =====================================================================================
 *       Filename:  CommissionEmployee.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.14 - CommissionEmployee class inherits from 
                                Employee
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class CommissionEmployee extends Employee {            
   private double grossSales;      
   private double commissionRate;

   public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber,
                           double grossSales, double commissionRate) {
         
      super(firstName, lastName, socialSecurityNumber);

      if (grossSales < 0.0) {
         throw new IllegalArgumentException("Gross sales must be >= 0.0");
      }      

      if (commissionRate <= 0.0 || commissionRate >= 1.0) {
         throw new IllegalArgumentException(
            "Commission rate must be > 0.0 and < 1.0");
      }      
       
      this.grossSales = grossSales;
      this.commissionRate = commissionRate;
   } 

   public void setGrossSales(double grossSales) {
      if (grossSales < 0.0) {
         throw new IllegalArgumentException("Gross sales must be >= 0.0");
      }      

      this.grossSales = grossSales;
   } 

   public double getGrossSales() {return grossSales;}

   public void setCommissionRate(double commissionRate) {
      if (commissionRate <= 0.0 || commissionRate >= 1.0) {
         throw new IllegalArgumentException(
            "Commission rate must be > 0.0 and < 1.0");
      } 

      this.commissionRate = commissionRate;
   } 

   public double getCommissionRate() {return commissionRate;}

   public double earnings() {
      return getCommissionRate() * getGrossSales();
   } 

   @Override 
   public String toString() {
      return String.format("%s %s%n%s: %.2f%n%s: %.2f", 
         "commission", super.toString(),
         "gross sales", getGrossSales(), 
         "commission rate", getCommissionRate());
   } 
} 
