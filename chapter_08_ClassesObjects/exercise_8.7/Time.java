/* =====================================================================================
 *       Filename:  Time.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.7 - class represents a time using only
                                seconds, with methods increment: day, hour, minute,
                                   second
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Time {
   public static final int START_TIME_VALUE = 0;
   public static final int HOURS_PER_DAY = 24;
   public static final int MINUTES_PER_HOUR = 60;
   public static final int SECONDS_PER_MINUTE = 60;
   public static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
   public static final int SECONDS_PER_DAY = HOURS_PER_DAY * SECONDS_PER_HOUR;
   
   private int second; // 0 - (24 * 60 * 60 - 1 = 86399)

   // Time no-argument constructor: 
   // initializes each instance variable to zero  
   public Time() {                                             
      this(START_TIME_VALUE, START_TIME_VALUE, START_TIME_VALUE); // invoke constructor with three arguments
   } 

   // Time constructor: hour supplied, minute and second defaulted to 0
   public Time(int hour) {                                               
      this(hour, START_TIME_VALUE, START_TIME_VALUE); // invoke constructor with three arguments 
   } 

   // Time constructor: hour and minute supplied, second defaulted to 0
   public Time(int hour, int minute) {
      this(hour, minute, START_TIME_VALUE); // invoke constructor with three arguments 
   } 

   // Time constructor: hour, minute and second supplied   
   public Time(int hour, int minute, int second) {                    
      if (hour < START_TIME_VALUE || hour >= HOURS_PER_DAY) {
         throw new IllegalArgumentException("hour must be " + START_TIME_VALUE + "-" + (HOURS_PER_DAY - 1));
      } 

      if (minute < START_TIME_VALUE || minute >= MINUTES_PER_HOUR) {
         throw new IllegalArgumentException("minute must be " + START_TIME_VALUE + "-" + (MINUTES_PER_HOUR - 1));
      } 

      if (second < START_TIME_VALUE || second >= SECONDS_PER_MINUTE) {
         throw new IllegalArgumentException("second must be " + START_TIME_VALUE + "-" + (SECONDS_PER_MINUTE - 1));
      } 

      this.second = SECONDS_PER_HOUR * hour + SECONDS_PER_MINUTE * minute + second; 
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
      setHour(hour);
      setMinute(minute);
      setSecond(second);
   } 

   // validate and set hour 
   public void setHour(int hour) {
      if (hour < START_TIME_VALUE || hour >= HOURS_PER_DAY) {
         throw new IllegalArgumentException("hour must be " + START_TIME_VALUE + "-" + (HOURS_PER_DAY - 1));
      }
      
      this.second -= SECONDS_PER_HOUR * getHour();
      this.second += SECONDS_PER_HOUR * hour;
   } 

   // validate and set minute 
   public void setMinute(int minute) {
      if (minute < START_TIME_VALUE || minute >= MINUTES_PER_HOUR) {
         throw new IllegalArgumentException("minute must be " + START_TIME_VALUE + "-" + (MINUTES_PER_HOUR - 1));
      }
      
      this.second -= SECONDS_PER_MINUTE * getMinute();
      this.second += SECONDS_PER_MINUTE * minute;
   } 

   // validate and set second 
   public void setSecond(int second) {
      if (second < START_TIME_VALUE || second >= SECONDS_PER_MINUTE) {
         throw new IllegalArgumentException("second must be " + START_TIME_VALUE + "-" + (SECONDS_PER_MINUTE - 1));
      }
      
      this.second -= getSecond();
      this.second += second;
   } 

   // Get Methods         
   // get hour value      
   public int getHour() {
      return second / SECONDS_PER_HOUR;
   }

   // get minute value      
   public int getMinute() {
      return second % SECONDS_PER_HOUR / SECONDS_PER_MINUTE;
   } 

   // get second value      
   public int getSecond() {
      return second % SECONDS_PER_MINUTE;
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
   
   public void tick() {
      second = ++second % SECONDS_PER_DAY;
   }
   
   public void incrementMinute() {
      second = (second + SECONDS_PER_MINUTE) % SECONDS_PER_DAY;
   }
   
   public void incrementHour() {
      second = (second + SECONDS_PER_HOUR) % SECONDS_PER_DAY;
   }
}
