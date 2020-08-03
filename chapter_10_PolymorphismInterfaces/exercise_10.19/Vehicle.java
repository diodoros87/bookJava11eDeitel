/* =====================================================================================
 *       Filename:  Vehicle.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.19 - Vehicle class 
                                implements CarbonFootprint interface
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Objects;
 
public abstract class Vehicle implements CarbonFootprint, Cloneable {  
   private GeographicCoordination location;
   private double                 drivenKilometers;
   private final double           CARBON_FOOTPRINT_PER_KM;

   public Vehicle(GeographicCoordination location, double drivenKilometers, double carbonFootprint) {
      validateDrivenKilometers(drivenKilometers);       
      validateCarbonFootprint(carbonFootprint);
      
      this.location                = Objects.requireNonNull(location, "location must not be null"); 
      this.drivenKilometers        = drivenKilometers;
      this.CARBON_FOOTPRINT_PER_KM = carbonFootprint;
   } 
   
   public static void validateDrivenKilometers(double drivenKilometers) {
      if (drivenKilometers < 0) { 
         throw new IllegalArgumentException("driven kilometers must be >= 0");
      }
   }
   
   public static void validateCarbonFootprint(double carbonFootprint) {
      if (carbonFootprint < 0) { 
         throw new IllegalArgumentException("average of carbon footprint must be >= 0");
      }
   }
   
   public GeographicCoordination getLocation() {
      return location;
   }
   
   public double getLatitude() {
      return location.getLatitude();
   }
   
   public double getLongitude() {
      return location.getLongitude();
   }
   
   public double getDrivenKilometers() {
      return drivenKilometers;
   }
   
   public void setDrivenKilometers(double drivenKilometers) {
      validateDrivenKilometers(drivenKilometers);
      
      this.drivenKilometers        = drivenKilometers;
   }
   
   public double getCarbonFootprintPerKm() {
      return CARBON_FOOTPRINT_PER_KM;
   }
   
   public void moveTo(double latitude, double longitude) {
      setLatitude(latitude); 
      setLongitude(longitude);
   }
   
   private void setLatitude(double latitude) {
      location.setLatitude(latitude); 
   }
   
   private void setLongitude(double longitude) {
      location.setLongitude(longitude);
   }

   @Override 
   public String toString() {
      return String.format("%s: %s%n%s: %,.2f %n%s: %.2f", 
         "location", getLocation(),
         "driven kilometers", getDrivenKilometers(),
         "carbon footprint per kilometer", getCarbonFootprintPerKm());
   } 
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      Object   clonedObject = super.clone();
      Vehicle  cloned       = (Vehicle) clonedObject;
      
      GeographicCoordination clonedLocation = (GeographicCoordination)location.clone();
      cloned.moveTo(clonedLocation.getLatitude(), clonedLocation.getLongitude());

      return cloned;
   }
} 
