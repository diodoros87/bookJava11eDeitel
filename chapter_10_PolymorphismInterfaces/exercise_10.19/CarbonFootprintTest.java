/* =====================================================================================
 *       Filename:  CarbonFootprintTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.19 - CarbonFootprint interface test program 
                             processing Cars, Bikes and Buildings polymorphically
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import validateParametersPackage.ValidateParameters;
 
import java.util.ArrayList;
import java.util.Collections;

public class CarbonFootprintTest {
   public static void main(String[] args) {
      ArrayList<CarbonFootprint> carbonFootprintArrayList = getCarbonFootprintArrayList();
      
      processCarbonFootprintObjects("Cars, Bikes and Buildings processed polymorphically:", carbonFootprintArrayList); 
   }
   
   public static void processCarbonFootprintObjects (String title, ArrayList<CarbonFootprint> carbonFootprintArray) {
      ValidateParameters.checkNullPointer(title);
      ValidateParameters.checkNullPointer(carbonFootprintArray);
      
      System.out.printf("%s %n%n", title);
      for (CarbonFootprint currentCarbonFootprint : carbonFootprintArray) {
         ValidateParameters.checkNullPointer(currentCarbonFootprint);
         
         System.out.printf("%n%s %n", currentCarbonFootprint.toString());
      } 
   }
   
   public static void fillCarbonFootprintArray (CarbonFootprint[] carbonFootprintArray, Vehicle[] vehicleArray, Building[] buildingArray) {
      ValidateParameters.checkNullPointersInArrays(vehicleArray, buildingArray);
      ValidateParameters.checkNullPointerOnlyArray(carbonFootprintArray);
      
      try {
         int startIndex = 0;
         fillArray(carbonFootprintArray, startIndex, vehicleArray);
         
         if (buildingArray.length > 0 && carbonFootprintArray.length > vehicleArray.length) {
            startIndex = vehicleArray.length;
            fillArray(carbonFootprintArray, startIndex, buildingArray);
            
            if (vehicleArray.length > 0) {
               vehicleArray[0].moveTo(new GeographicCoordination(-99, -9));  //to test clone() method of class Vehicle
            }
         }
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
         System.exit(1);
      }
   }
   
   public static void fillArray (Object[] destination, int startIndex, Object[] SOURCE)
                                                throws CloneNotSupportedException {
      if (0 > startIndex) {
         throw new ArrayIndexOutOfBoundsException(String.format("Requirement: startIndex (%d) >= 0", startIndex));
      }
      
      ValidateParameters.checkNullPointer(SOURCE);
      ValidateParameters.checkNullPointerOnlyArray(destination);
      
      if (startIndex >= destination.length) {
         throw new ArrayIndexOutOfBoundsException(String.format(
                  "Requirement: startIndex (%d) < destination.length (%d)", startIndex, destination.length));
      }
      
      final int INDEX_LIMIT = Math.min(SOURCE.length, destination.length - startIndex);
      
      for (int index = 0; index < INDEX_LIMIT; index++) {
         if (true == SOURCE[index] instanceof Vehicle) {
            Vehicle source                  = (Vehicle)SOURCE[index];
            destination[startIndex + index] = (Vehicle)source.clone();
         }
         else if (true == SOURCE[index] instanceof Building) {
            
            Building sourceBuilding         = (Building)SOURCE[index];
            destination[startIndex + index] = (Building)sourceBuilding.clone();
         }
         else {
            destination[startIndex + index] = SOURCE[index];
         }
         
         //destination[startIndex + index] = SOURCE[index];  //to test clone() method of classes Building and Vehicle
      }
   }
   
   public static ArrayList<CarbonFootprint> getCarbonFootprintArrayList() {
      CarbonFootprint[] carbonFootprintArray              = createCarbonFootprintArray();
      ArrayList<CarbonFootprint> carbonFootprintArrayList = new ArrayList<>();
      
      Collections.addAll(carbonFootprintArrayList, carbonFootprintArray);
      
      return carbonFootprintArrayList;
   }
   
   public static CarbonFootprint[] createCarbonFootprintArray() {
      Building[]          buildingArray          = createBuildingArray();
      Vehicle[]           vehicleArray           = createVehicleArray();
      
      CarbonFootprint[] carbonFootprintArray = new CarbonFootprint[buildingArray.length + vehicleArray.length];
      
      fillCarbonFootprintArray(carbonFootprintArray, vehicleArray, buildingArray);
      
      return carbonFootprintArray;
   }
   
   public static Vehicle[] createVehicleArray() {
      Vehicle[] vehicleArray = new Vehicle[] {
         new Car(new GeographicCoordination(-60, 50), 776645.5, 78.6, "Honda", "diesel"),
         new Car(new GeographicCoordination(0, 0), 645.5, 58.6, "Skoda", "gas"),
         new Bike(new GeographicCoordination(-145, -78), 645.5, 78.6, "Romet"),
         new Bike(new GeographicCoordination(144, 30), 6945.5, 58.6, "Delta")  
      };
      
      return vehicleArray;
   }
   
   public static Building[] createBuildingArray() {
      Building[] buildingArray = new Building[] {
         new Building(new GeographicCoordination(-60, 30), 45.5, 78.6),
         new Building(new GeographicCoordination(-170, -30), 745.5, 578.6),
         new Building(new GeographicCoordination(166, 56), 4445.5, 7438.6), 
         new Building(new GeographicCoordination(70, -88), 32.534, 123.2),   
      };
      
      return buildingArray;   
   } 
} 
