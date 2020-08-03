/* =====================================================================================
 *       Filename:  Bike.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.19 - Bike class 
                                implements CarbonFootprint interface
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Objects;
 
public class Bike extends Vehicle {  
   private final String MODEL;
   
   public Bike(GeographicCoordination location, double drivenKilometers, double carbonFootprint,
                              String model) {
      super(location, drivenKilometers, carbonFootprint);
      this.MODEL                = Objects.requireNonNull(model, "model of bike must not be null"); 
   } 
   
   public String getModel() {
      return MODEL;
   }
   
   @Override
   public double getCarbonFootprint() {
      return getCarbonFootprintPerKm() * getDrivenKilometers();
   } 

   @Override 
   public String toString() {
      return String.format("%s %s%n%s%n%s: %,.2f", 
         "bike", getModel(),
         super.toString(),
         "total carbon footprint", getCarbonFootprint());
   } 
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      Object   clonedObject            = super.clone();
      Bike cloned = (Bike) clonedObject;

      return cloned;
   }
} 
