/* =====================================================================================
 *       Filename:  FuelUsageTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.17 - Solving the calculate fuel usage in many 
                                travels using sentinel-controlled iteration
                                   with FuelUsage objects
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class FuelUsageTest {

   public static void main(String[] args) {
   
      // initialization phase
      int totalRefueledLitres = 0; // initialize sum of every travel's refueled litres
      int totalDrivingKilometers = 0; // initialize sum of every travel's driving kilometers
      int travelCounter = 0; // initialize counter of travels entered so far
      
      // processing phase
      // prompt for input and read data from user
      FuelUsage travel = UserInterface.createFuelUsageByUser();
      // loop until sentinel value read from user
      while (travel != null) {
         totalRefueledLitres = totalRefueledLitres + travel.getRefueledLitres();
         if (travel.getDrivingKilometers() > 0) {
            UserInterface.displayFuelUsage(travel);
            totalDrivingKilometers = totalDrivingKilometers + travel.getDrivingKilometers();
            travelCounter = travelCounter + 1; // increment counter 
         }
         travel = null;
         System.gc();
         // prompt for input and read data from user
         travel = UserInterface.createFuelUsageByUser();
      }

      // termination phase
      // if user entered at least one correct data of travel
      if (travelCounter != 0) {
         UserInterface.displayTotalFuelUsage(totalDrivingKilometers, totalRefueledLitres, travelCounter);
      } 
      else { // no correct data of travel were entered, so output appropriate message
         System.out.println("No correct data of travel were entered"); 
      }
   
   } 
   
} 
