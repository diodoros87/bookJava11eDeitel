/* =====================================================================================
 *       Filename:  MeasurementSystem.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 12.11 - Declaring an enum type describing
                                various measurement systems 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public enum MeasurementSystem {
   METRIC  ("metres", "kilograms"), 
   IMPERIAL("inches", "pounds");
   
   private final String lengthUnit;
   private final String massUnit;
   
   MeasurementSystem(String lengthUnit, String massUnit) {
      this.lengthUnit = lengthUnit;
      this.massUnit   = massUnit;
   }
   
   public String getLengthUnit() {return lengthUnit;}
   
   public String getMassUnit() {return massUnit;}
}
