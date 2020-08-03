/* =====================================================================================
 *       Filename:  Car.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.19 - Car class that implements CarbonFootprint
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
import java.util.Objects;

public class Car extends Vehicle implements CarbonFootprint {
   private String engine; 
   private final String MODEL;

   public Car(GeographicCoordination location, double drivenKilometers, double carbonFootprint,
                              String model, String engine) {
   
      super(location, drivenKilometers, carbonFootprint);
      this.MODEL  = Objects.requireNonNull(model, "model of car must not be null"); 
      this.engine = Objects.requireNonNull(engine, "engine of car must not be null"); 
   }  
   
   public String getEngine() {
      return engine;
   }
   
   public void setEngine(String engine) {
      this.engine = Objects.requireNonNull(engine, "engine of car must not be null"); 
   }
   
   public String getModel() {
      return MODEL;
   }

   @Override 
   public String toString() {
      return String.format("%s %s%n %s%n %s: %s%n %s: %,.2f", 
         "car", getModel(),
         super.toString(),
         "engine", getEngine(),
         "total carbon footprint", getCarbonFootprint());
   } 

  @Override
   public double getCarbonFootprint() {
      return getCarbonFootprintPerKm() * getDrivenKilometers();
   }  
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      Object   clonedObject            = super.clone();
      Car cloned = (Car) clonedObject;

      return cloned;
   }                                                             
} 
