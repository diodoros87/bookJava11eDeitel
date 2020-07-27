/* =====================================================================================
 *       Filename:  CompensationModel.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.16 - CompensationModel class to calculate
                                earnings
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class CompensationModel {            

   public double earnings() {
      return -Math.PI;  // incorrect result
   }
   
   @Override 
   public String toString() {
      return String.format("%s%n%s: %,.2f", "compensation model",
         "earnings", earnings());
   }   
} 

