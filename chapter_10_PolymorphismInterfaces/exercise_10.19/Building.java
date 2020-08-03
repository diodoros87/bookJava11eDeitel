/* =====================================================================================
 *       Filename:  Building.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.19 - Building class that
                                implements CarbonFootprint 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Objects;

import java.lang.reflect.Field;
 
public class Building implements CarbonFootprint, Cloneable {
   private double electricityUsage;   
   private double coalUsage;
   private final GeographicCoordination LOCATION;
   
   private static final double ELECTRICITY_CARBON_FOOTPRINT_COEFFICIENT = 33;
   private static final double COAL_CARBON_FOOTPRINT_COEFFICIENT        = 99;        

   public Building(GeographicCoordination location, double coalUsage, double electricityUsage) {
      validateUsage(coalUsage, "coal");
      validateUsage(electricityUsage, "electricity");
      
      this.LOCATION         = Objects.requireNonNull(location, "location must not be null"); 
      this.coalUsage        = coalUsage;
      this.electricityUsage = electricityUsage;   
   } 
   
   public Building(Building building) throws CloneNotSupportedException {
      Objects.requireNonNull(building, "building must not be null");
      
      this.LOCATION         = Objects.requireNonNull(building.getLocation(), "location must not be null"); 
      this.coalUsage        = building.getCoalUsage();
      this.electricityUsage = building.getElectricityUsage(); 
   }

   public GeographicCoordination getLocation() throws CloneNotSupportedException {
      Object clonedObject = LOCATION.clone();
      GeographicCoordination clonedLocation = (GeographicCoordination)clonedObject;
      
      return clonedLocation;
   }
   
   public double getLatitude() {
      return LOCATION.getLatitude();
   }
   
   public double getLongitude() {
      return LOCATION.getLongitude();
   }

   private static void validateUsage(double usage, String type) {
      if (usage < 0) { 
         throw new IllegalArgumentException(String.format("usage of %s must be >= 0", type));
      }
   }
   
   public double getElectricityUsage() {
      return electricityUsage;
   }
   
   public void setElectricityUsage(double electricityUsage) {
      validateUsage(electricityUsage, "electricity");
      
      this.electricityUsage        = electricityUsage;
   }
   
   public double getCoalUsage() {
      return coalUsage;
   }
   
   public void setCoalUsage(double coalUsage) {
      validateUsage(coalUsage, "coal");
      
      this.coalUsage        = coalUsage;
   }
   
   @Override
   public double getCarbonFootprint() {
      return ELECTRICITY_CARBON_FOOTPRINT_COEFFICIENT * getElectricityUsage()
               + COAL_CARBON_FOOTPRINT_COEFFICIENT * getCoalUsage();
   } 

   @Override 
   public String toString() {
      return String.format("%s %n%s: %s%n %s: %,.2f%n %s: %,.2f%n %s: %,.2f", 
         "building",
         "location", LOCATION,
         "electricity usage", getElectricityUsage(),
         "coal usage", getCoalUsage(),
         "total carbon footprint", getCarbonFootprint());
   } 
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      Building clonedBuilding = new Building (this);

      return clonedBuilding;
   }
   /*
   @Override
   public Object clone() {
     try {
         Building clone      = (Building)super.clone();
         Field locationField = Building.class.getDeclaredField("LOCATION");
         
         locationField.setAccessible(true);
         locationField.set(clone, LOCATION.clone());
         
         return clone;
      } catch (CloneNotSupportedException|ReflectiveOperationException ex) {
         
         throw new AssertionError(ex);
      }
   }*/
} 
