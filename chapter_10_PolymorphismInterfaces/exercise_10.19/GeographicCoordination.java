/* =====================================================================================
 *       Filename:  GeographicCoordination.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.19 - Geographic coordinations class 
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class GeographicCoordination implements Cloneable {  
   private static final int NORTH_POLE = +90;
   private static final int SOUTH_POLE = -90;
   private static final int MAX_EASTERN_MERIDIAN      = +180;
   private static final int WESTERN_MERIDIAN_BOUNDARY = -180;
   
   private double latitude;
   private double longitude;
   
   public GeographicCoordination(double longitude, double latitude) {
      validateLatitude(latitude);
      validateLongitude(longitude);  
      
      this.latitude  = latitude;
      this.longitude = longitude; 
   }
   
   public void setLatitude(double latitude) {
      validateLatitude(latitude); 
      
      this.latitude  = latitude; 
   }
   
   public double getLatitude() {
      return latitude; 
   }
   
   public static void validateLatitude(double latitude) {
      if (SOUTH_POLE > latitude || latitude > NORTH_POLE) {
         throw new IllegalArgumentException(String.format
            ("latitude of (%f) is not in range: %d <= latitude <= %d", 
                        latitude, SOUTH_POLE, NORTH_POLE));
      }
   }
   
   public static void validateLongitude(double longitude) {
      if (WESTERN_MERIDIAN_BOUNDARY >= longitude || longitude > MAX_EASTERN_MERIDIAN) {
         throw new IllegalArgumentException(String.format
            ("longitude of (%f) is not in range: %d < latitude <= %d", 
                        longitude, WESTERN_MERIDIAN_BOUNDARY, MAX_EASTERN_MERIDIAN));
      }
   }
   
   public void setLongitude(double longitude) {
      validateLongitude(longitude);  
      
      this.longitude = longitude; 
   }
   
   public double getLongitude() {
      return longitude; 
   }
   
   @Override
   public String toString() {
      return String.format("%s: %s    %s: %s", 
         "meridian", getMeridianString(), "circle of latitude", getCircleOfLatitudeString());
   } 
   
   public String getMeridianString() {
      if (longitude > 0) {
         return String.format("%f E", longitude);
      }
      else if (longitude < 0) {
         return String.format("%f W", -longitude);
      }
      else { // longitude == 0
         return String.format("%f", 0f);
      }
   }
   
   public String getCircleOfLatitudeString() {
      if (latitude > 0) {
         return String.format("%f N", latitude);
      }
      else if (latitude < 0) {
         return String.format("%f S", -latitude);
      }
      else { // latitude == 0
         return String.format("%f", 0f);
      }
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      GeographicCoordination cloned = (GeographicCoordination) super.clone();

      return cloned;
   }
   
}
