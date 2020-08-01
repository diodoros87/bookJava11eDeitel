/* =====================================================================================
 *       Filename:  SalariedCompensationModel.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.17 - SalariedCompensationModel class 
                                implements CompensationModel interface
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class SalariedCompensationModel implements CompensationModel {            
   private double weeklySalary;

   public SalariedCompensationModel(double weeklySalary) {
      validateWeeklySalary(weeklySalary);        
       
      this.weeklySalary = weeklySalary;
   } 
   
   public static void validateWeeklySalary(double weeklySalary) {
      if (weeklySalary < 0.0) {
         throw new IllegalArgumentException(
            "Weekly salary must be >= 0.0");
      }     
   } 

   public void setWeeklySalary(double weeklySalary) {
      validateWeeklySalary(weeklySalary);

      this.weeklySalary = weeklySalary;
   } 

   public double getWeeklySalary() {return weeklySalary;}
   
   @Override
   public double earnings() {
      return getWeeklySalary();
   } 

   @Override 
   public String toString() {
      return String.format("%s %n%s: %,.2f %n%s: %.2f", 
         "salaried compensation model", 
         "earnings", earnings(),
         "weekly salary", getWeeklySalary());
   } 
} 
