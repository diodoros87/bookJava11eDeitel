/* =====================================================================================
 *       Filename:  FuelUsage.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.17 - class of fuel usage in travel
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class FuelUsage {

   private int drivingKilometers;    
   private int refueledLitres;   
   
   public FuelUsage(int drivingKilometers, int refueledLitres) {
      if (false == (0 == refueledLitres && 0 < drivingKilometers))
      {
         if (0 <= drivingKilometers) {
            this.drivingKilometers = drivingKilometers;
         }
         
         if (0 <= refueledLitres) {
            this.refueledLitres = refueledLitres;
         }
      }   
   }
   
   public double getFuelUsagePer100Kilometres() {
      double fuelUsage = 0;
      if (drivingKilometers > 0 && refueledLitres >= 0) {
         fuelUsage = refueledLitres / (drivingKilometers / 100.0);
      }
      return fuelUsage;
   }

   public void setDrivingKilometers(int drivingKilometers) {
      if (false == (0 == refueledLitres && 0 < drivingKilometers))
      {
         if (0 <= drivingKilometers) {
            this.drivingKilometers = drivingKilometers;
         }
      }
   } 

   public int getDrivingKilometers() {
      return drivingKilometers; 
   } 
   
   public void setRefueledLitres(int refueledLitres) {
      if (false == (0 == refueledLitres && 0 < drivingKilometers))
      {
         if (0 <= refueledLitres) {
            this.refueledLitres = refueledLitres; 
         }
      }
   } 

   public int getRefueledLitres() {
      return refueledLitres; 
   } 
   
}
