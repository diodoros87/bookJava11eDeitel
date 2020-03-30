/* =====================================================================================
 *       Filename:  UserInterface.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.17 - class of user inferface in
                                program with FuelUsage objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class UserInterface {

   private static final String drivingKilometersPrompt = "Enter integer of driving kilometers: ";
   private static final String refueledLitresPrompt    = "Enter integer of fuel usage in litres: ";

   public static FuelUsage createFuelUsageByUser() {
      FuelUsage newTravel   = null;
      int drivingKilometers = -1;
      int refueledLitres    = -1;
      
      System.out.printf("%n****** To summary of travels and quit enter integer less than zero.%n");
      System.out.println("****** To quit enter sequence other than integer.");
      System.out.printf("%n****** Enter data of travel to display fuel usage in litres per 100 kilometres.%n");
   
      drivingKilometers = GettingDataFromStandardInput.getInteger(drivingKilometersPrompt);
      if (0 <= drivingKilometers) {
         refueledLitres = GettingDataFromStandardInput.getInteger(refueledLitresPrompt);
      }
      if (0 <= drivingKilometers && 0 <= refueledLitres) {
         newTravel = new FuelUsage(drivingKilometers, refueledLitres);
      }
      return newTravel;
   }
      
   public static void displayFuelUsage(FuelUsage travel) {
      System.out.printf("-------Travel's fuel usage data: %n"); 
      System.out.printf("driving kilometers: %d\t refueled litres: %d%n", 
                        travel.getDrivingKilometers(), travel.getRefueledLitres());
      System.out.printf("Fuel usage in litres per 100 kilometres is %f %n", travel.getFuelUsagePer100Kilometres());
   }
   
   public static void displayTotalFuelUsage(int drivingKilometers, int refueledLitres, int travelCounter) {
         System.out.println("-------Total of travels fuel usage data: "); 
         System.out.printf("number of travels: %d\t driving kilometers: %d\t refueled litres: %d%n", 
                           travelCounter, drivingKilometers, refueledLitres);
         if (drivingKilometers > 0 && refueledLitres >= 0) {
            System.out.printf("Fuel usage in litres per 100 kilometres is %f %n", 
                                       refueledLitres * 100.0 / drivingKilometers);
         }
   }
      
}
