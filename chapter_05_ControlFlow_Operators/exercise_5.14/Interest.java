/* =====================================================================================
 *       Filename:  Interest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.14 - Compound-interest calculations using
                                 for loop
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class Interest {

   static final double principal = 1000.0; // initial amount before interest
   
   public static void main(String[] args) {
      
      System.out.printf("Initial amount before interest = %,.2f%n", principal); 
      
      for (int rate = 5 /* interest rate*/ ; rate <= 10 ; rate++) {
         printDepositByYears(rate, 1, 10);
      }
                                      
   } 
   
   public static void printDepositByYears(int rate, int first, int last) {
      // display headers
      System.out.printf("%nRate of interest %d%% %n", rate);
      System.out.printf("%s%20s%n", "Year", "Amount on deposit");
      
      double rateFactor = 1.0 + rate / 100.0;
      double amount = 0;

      // calculate amount on deposit for each of ten years
      for (int year = first; year <= last; ++year) {                  
         // calculate new amount on deposit for specified year  
         amount = principal * Math.pow(rateFactor, year);
                                                                
         // display the year and the amount                     
         System.out.printf("%4d%,20.2f%n", year, amount);       
      }                         
   }
   
} 
