/* =====================================================================================
 *       Filename:  ParkingCharges.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.8 - parking charges calculator 
                                                tested by User 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class ParkingCharges {
   
   public static void main(String[] args) {
   
      final String QUIT_INFO = "To quit enter sequence other than number or enter number less than zero.";
      final String PROMPT = "Enter number of parking hours: ";
      final String QUIT_INFO_PROMPT = String.format("%s%n%s", QUIT_INFO, PROMPT);
      
      System.out.printf("*** This program calculate parking charges according to number of parking hours.%n");
      
      double clientCharges;
      double totalClientCharges = 0;
      double hours = GettingDataFromStandardInput.getDouble(QUIT_INFO_PROMPT);
      
      while (hours >= 0) {
         clientCharges = calculateCharges(hours);
         totalClientCharges += clientCharges;
         System.out.printf("*** Parking charges for client is %.2f %n", clientCharges);
         System.out.printf("*** Total parking charges for all clients is %.2f %n", totalClientCharges);
         hours = GettingDataFromStandardInput.getDouble(QUIT_INFO_PROMPT);
      }

   } 
   
   public static final int    HOURS_PER_DAY  = 24;
   public static final double MAX_CHARGES_PER_DAY = 10;
   
   public static double calculateCharges (double hours) {
      if (hours < 0) {
         System.err.println("Parking hours can not be less than zero ");
         return Double.NEGATIVE_INFINITY;
      }
      
      int    days = (int)hours / HOURS_PER_DAY;       // calculate parking whole days
      double charges = days * MAX_CHARGES_PER_DAY; // calculate parking charges for whole days
      
      hours -= days * HOURS_PER_DAY;               // calculate parking hours for last day
      
      return charges += calculateChargesForOneDay(hours);
   }
   
   public static double calculateChargesForOneDay (double hours) {
      if (hours < 0) {
         System.err.println("Parking hours can not be less than zero ");
         return Double.NEGATIVE_INFINITY;
      }
      
      if (hours > HOURS_PER_DAY) {
         System.err.println("Parking hours per day can not be greater than " + HOURS_PER_DAY);
         return Double.POSITIVE_INFINITY;
      }
      
      if (0 == hours) {
         return 0;
      }
      
      final double STANDARD_CHARGES = 2;
      double                charges = STANDARD_CHARGES;
      final double STANDARD_HOURS   = 3;
      
      if (hours > STANDARD_HOURS) {
         final double EXTRA_CHARGES    = 0.5;
         double extraCharges = Math.ceil(hours - STANDARD_HOURS) * EXTRA_CHARGES;
         charges += Math.min(extraCharges, MAX_CHARGES_PER_DAY - STANDARD_CHARGES);
      }
      
      return charges;
   }
   
} 
