/* =====================================================================================
 *       Filename:  TrafficLightTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.10 - Testing enum type TrafficLight
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.util.EnumSet;

public class TrafficLightTest {
   public static void main(String[] args) {
      System.out.println("All traffic lights:");

      // print all trafficLights in enum TrafficLight 
      for (TrafficLight trafficLight : TrafficLight.values()) {                       
         System.out.printf("%-10s light's duration: %d%n", trafficLight, trafficLight.getLightDuration());
      }

      System.out.printf("%nDisplay a range of enum constants:%n");
    
      // print last two trafficLights                                 
      for (TrafficLight trafficLight : EnumSet.range(TrafficLight.GREEN, TrafficLight.YELLOW)) {
         System.out.printf("traffic light: %10s   light's duration: %d%n", trafficLight, trafficLight.getLightDuration());
      }
      
      for (TrafficLight trafficLight : EnumSet.range(TrafficLight.GREEN, TrafficLight.GREEN)) {
         trafficLight.setLightDuration(345);
         System.out.printf("traffic light: %10s   light's duration: %d%n", trafficLight, trafficLight.getLightDuration());
      }
      
      for (TrafficLight trafficLight : EnumSet.range(TrafficLight.YELLOW, TrafficLight.YELLOW)) {
         trafficLight.setLightDuration(-9);
         System.out.printf("traffic light: %10s   light's duration: %d%n", trafficLight, trafficLight.getLightDuration());
      }
      
      System.out.printf("%nAll traffic lights:%n");
      // print all trafficLights in enum TrafficLight 
      for (TrafficLight trafficLight : TrafficLight.values()) {                       
         System.out.printf("%-10s light's duration: %d%n", trafficLight, trafficLight.getLightDuration());
      }
      
      changeLightDuration(TrafficLight.GREEN, 67);
      
      TrafficLight.RED.setLightDuration(111);
      System.out.printf("%-10s light's duration: %d%n", TrafficLight.RED, TrafficLight.RED.getLightDuration());
   }
   
   public static void changeLightDuration(TrafficLight trafficLight, int lightDuration) {
      trafficLight.setLightDuration(lightDuration);
      System.out.printf("%-10s light's duration: %d%n", trafficLight, trafficLight.getLightDuration());
   }
} 

