/* =====================================================================================
 *       Filename:  Time.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.5 - class represents a time using only
                                seconds
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Time {
   private int second; // 0 - (24 * 60 * 60 - 1 = 86399)

   // Time no-argument constructor: 
   // initializes each instance variable to zero  
   public Time() {                                             
      this(0, 0, 0); // invoke constructor with three arguments
   } 

   // Time constructor: hour supplied, minute and second defaulted to 0
   public Time(int hour) {                                               
      this(hour, 0, 0); // invoke constructor with three arguments 
   } 

   // Time constructor: hour and minute supplied, second defaulted to 0
   public Time(int hour, int minute) {
      this(hour, minute, 0); // invoke constructor with three arguments 
   } 

   // Time constructor: hour, minute and second supplied   
   public Time(int hour, int minute, int second) {                    
      if (hour < 0 || hour >= 24) {
         throw new IllegalArgumentException("hour must be 0-23");
      } 

      if (minute < 0 || minute >= 60) {
         throw new IllegalArgumentException("minute must be 0-59");
      } 

      if (second < 0 || second >= 60) {
         throw new IllegalArgumentException("second must be 0-59");
      } 

      this.second = 3600 * hour + 60 * minute + second; 
   } 

   // Time constructor: another Time object supplied           
   public Time(Time time) {                                   
      // invoke constructor with three arguments
      this(time.getHour(), time.getMinute(), time.getSecond());
   } 

   // Set Methods
   // set a new time value using universal time;  
   // validate the data
   public void setTime(int hour, int minute, int second) {
      if (hour < 0 || hour >= 24) {
         throw new IllegalArgumentException("hour must be 0-23");
      } 

      if (minute < 0 || minute >= 60) {
         throw new IllegalArgumentException("minute must be 0-59");
      } 

      if (second < 0 || second >= 60) {
         throw new IllegalArgumentException("second must be 0-59");
      } 

      this.second = 3600 * hour + 60 * minute + second; 
   } 

   // validate and set hour 
   public void setHour(int hour) {
      if (hour < 0 || hour >= 24) {
         throw new IllegalArgumentException("hour must be 0-23");
      }
      
      this.second -= 3600 * getHour();
      this.second += 3600 * hour;
   } 

   // validate and set minute 
   public void setMinute(int minute) {
      if (minute < 0 || minute >= 60) {
         throw new IllegalArgumentException("minute must be 0-59");
      }
      
      this.second -= 60 * getMinute();
      this.second += 60 * minute;
   } 

   // validate and set second 
   public void setSecond(int second) {
      if (second < 0 || second >= 60) {
         throw new IllegalArgumentException("second must be 0-59");
      }
      
      this.second -= getSecond();
      this.second += second;
   } 

   // Get Methods         
   // get hour value      
   public int getHour() {
      return second / 3600;
   }

   // get minute value      
   public int getMinute() {
      return second % 3600 / 60;
   } 

   // get second value      
   public int getSecond() {
      return second % 60;
   }   

   // convert to String in universal-time format (HH:MM:SS)
   public String toUniversalString() {
      return String.format(
         "%02d:%02d:%02d", getHour(), getMinute(), getSecond());
   } 

   // convert to String in standard-time format (H:MM:SS AM or PM)
   public String toString() {
      return String.format("%d:%02d:%02d %s", 
         ((getHour() == 0 || getHour() == 12) ? 12 : getHour() % 12),
         getMinute(), getSecond(), (getHour() < 12 ? "AM" : "PM"));
   } 
}
