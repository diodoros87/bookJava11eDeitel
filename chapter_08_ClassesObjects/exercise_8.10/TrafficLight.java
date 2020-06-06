/* =====================================================================================
 *       Filename:  TrafficLight.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.10 - Declaring an enum type with a constructor
                                and explicit instance fields and
                                    accessors for these fields
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public enum TrafficLight {
   // declare constants of enum type                          
   RED(-99),                       
   GREEN(77),                          
   YELLOW(22);           
 
   private int lightDuration;

   TrafficLight(int lightDuration) {
      if (lightDuration >= 0)
         this.lightDuration = lightDuration;
   } 

   public int getLightDuration() {
      return lightDuration;
   } 
   
   public void setLightDuration(int lightDuration) {
      if (lightDuration >= 0)
         this.lightDuration = lightDuration;
   } 
} 

