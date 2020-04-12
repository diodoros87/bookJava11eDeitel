/* =====================================================================================
 *       Filename:  Interest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.18 - Compound-interest calculations using
                                 only integers
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class Interest {

   public static void main(String[] args) {
   
      int amount = 1000_00; // initial amount in cents before interest
      
      System.out.printf("Initial amount before interest = %,d dollars and %d cents %n",
                           amount / 100, amount % 100);
      int rate = 5; // interest rate

      // display headers
      System.out.printf("%nRate of interest %d%% %n", rate);
      System.out.printf("%s%20s%n", "Year", "Amount on deposit");
      
      int rateFactor = 100 + rate;

      // calculate amount on deposit for each of ten years
      for (int year = 1; year <= 10; ++year) {                  

         amount = amount * rateFactor / 100;   // no amount *= rateFactor / 100; because (rateFactor / 100) 
         // is calculated before multiplying by amount
                                                                
         // display the year and the amount                     
         System.out.printf("%4d%,20d.%02d %n", year, amount / 100, amount % 100);       
      }                                                         
   } 
} 
