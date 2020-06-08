/* =====================================================================================
 *       Filename:  DateAndTime.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.12 - class represents date and time
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.time.LocalDate;

public class DateAndTime {
   private Time time;
   private Date date;

   public DateAndTime() {                                             
      this.time = new Time();
      
      LocalDate currentDate = LocalDate.now();
      int month             = currentDate.getMonthValue();
      int day               = currentDate.getDayOfMonth();
      int year              = currentDate.getYear();
      
      this.date = new Date(month, day, year);
   } 
  
   public DateAndTime(int month, int day, int year, int hour, int minute, int second) {  
      this.date = new Date(month, day, year);
      this.time = new Time(hour, minute, second);
   } 
          
   public DateAndTime(Date date, Time time) { 
      this(date.getMonth(), date.getDay(), date.getYear(), 
            time.getHour(), time.getMinute(), time.getSecond());
   } 

   public void setTime(Time time) {
      if (null == time) {
         throw new NullPointerException("time can not be null");
      }
      
      setHour(time.getHour());
      setMinute(time.getMinute());
      setSecond(time.getSecond());
   } 
   
   public void setDate(Date date) {
      if (null == date) {
         throw new NullPointerException("date can not be null");
      }
      
      setMonth(date.getMonth());
      setDay(date.getDay());
      setYear(date.getYear());
   } 
   
   public Time getTime() {
      return time;
   }
    
   public Date getDate() {
      return date;
   }

   public void setHour(int hour) {
      time.setHour(hour);
   } 

   public void setMinute(int minute) {
      time.setMinute(minute);
   } 

   public void setSecond(int second) {
      time.setSecond(second);
   } 
    
   public int getHour() {
      return time.getHour();
   }
    
   public int getMinute() {
      return time.getMinute();
   } 
     
   public int getSecond() {
      return time.getSecond();
   } 
   
   public void setMonth(int month) {
      date.setMonth(month);
   } 

   public void setDay(int day) {
      date.setDay(day);
   } 

   public void setYear(int year) {
      date.setYear(year);
   } 
   
   public int getMonth() {
      return date.getMonth();
   }
   
   public int getDay() {
      return date.getDay();
   }
   
   public int getYear() {
      return date.getYear();
   }

   // convert to String in universal-time format (HH:MM:SS)
   public String toUniversalString() {
      return String.format("%s %s", time.toUniversalString(), date);
   } 

   // convert to String in standard-time format (H:MM:SS AM or PM)
   public String toString() {
      return String.format("%s %s", time, date);
   }
   
   public void tick() {
      time.tick();
      
      if (Time.START_TIME_VALUE == time.getHour()   &&
          Time.START_TIME_VALUE == time.getMinute() &&
          Time.START_TIME_VALUE == time.getSecond()) {
          
         date.nextDay();
      }
   }
   
   public void incrementMinute() {
      time.incrementMinute();
      
      if (Time.START_TIME_VALUE == time.getHour() &&
          Time.START_TIME_VALUE == time.getMinute()) {
          
         date.nextDay();
      }
   }
   
   public void incrementHour() {
      time.incrementHour();
      
      if (Time.START_TIME_VALUE == time.getHour()) {
         date.nextDay();
      }
   }
   
   public void nextDay() {
      date.nextDay();
   }
}
