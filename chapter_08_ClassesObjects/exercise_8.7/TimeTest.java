/* =====================================================================================
 *       Filename:  TimeTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.7 - test of Time objects
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class TimeTest {
   public static void main(String[] args) {
      Time t1 = new Time(); // 00:00:00          
      Time t2 = new Time(2); // 02:00:00         
      Time t3 = new Time(21, 34); // 21:34:00    
      Time t4 = new Time(12, 25, 42); // 12:25:42
      Time t5 = new Time(t4); // 12:25:42        

      System.out.println("Constructed with:");
      displayTime("t1: all default arguments", t1);
      displayTime("t2: hour specified; default minute and second", t2);
      displayTime("t3: hour and minute specified; default second", t3);
      displayTime("t4: hour, minute and second specified", t4);
      displayTime("t5: Time object t4 specified", t5);

      // attempt to initialize t6 with invalid values
      try {
         Time t6 = new Time(27, 74, 99); // invalid values
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while initializing t6: %s%n",
            e.getMessage());
      } 
      
      t1.setTime(13, 45, 6);
      AssertTesting.assertTime(t1, 13, 45, 6);
      displayTime("t1 after set hour, minute, second:", t1);
      
      t1.setHour(4);
      AssertTesting.assertTime(t1, 4, 45, 6);
      displayTime("t1 after set hour:", t1);
      
      t1.setMinute(19);
      AssertTesting.assertTime(t1, 4, 19, 6);
      displayTime("t1 after set minute:", t1);
      
      t1.setSecond(56);
      AssertTesting.assertTime(t1, 4, 19, 56);
      displayTime("t1 after set second:", t1);
      
      t1.setTime(1, 4, 8);
      AssertTesting.assertTime(t1, 1, 4, 8);
      displayTime("t1 after set hour, minute, second:", t1);
      
      t1.tick();
      AssertTesting.assertTime(t1, 1, 4, 9);
      displayTime("t1 after tick:", t1);
      
      t1.incrementMinute();
      AssertTesting.assertTime(t1, 1, 5, 9);
      displayTime("t1 after increment minute:", t1);
      
      t1.incrementHour();
      AssertTesting.assertTime(t1, 2, 5, 9);
      displayTime("t1 after increment hour:", t1);
      
      int days = 3;
      incrementDays(t1, days);
      AssertTesting.assertTime(t1, 2, 5, 9);
      displayTime("t1 after increment " + days + " days:", t1);
      
      incrementDayByMinutes(t1);
      incrementDayByTics(t1);
   } 

   // displays a Time object in 24-hour and 12-hour formats
   private static void displayTime(String header, Time t) {
      System.out.printf("%s%n   %s%n   %s%n",
         header, t.toUniversalString(), t.toString());
   } 
   
   private static void incrementDays(Time time, int days) {
      final int HOURS = days * Time.HOURS_PER_DAY;
      int hour = Time.START_TIME_VALUE;
      
      while (hour < HOURS) {
         time.incrementHour();
         hour++;
         displayTime("time after increment " + hour + " hour: ", time);
      }
   }
   
   private static void incrementDayByMinutes(Time time) {
      time.setTime(23, 46, 7);
      AssertTesting.assertTime(time, 23, 46, 7);
      displayTime("time after set hour, minute, second: ", time);
      
      final int MINUTES = Time.MINUTES_PER_HOUR - time.getMinute() + 11;
      int minute = Time.START_TIME_VALUE;
      
      while (minute < MINUTES) {
         time.incrementMinute();
         minute++;
         displayTime("time after increment " + minute + " minute: ", time);
      }
      
      AssertTesting.assertTime(time, 0, 11, 7);
   }
   
   private static void incrementDayByTics(Time time) {
      time.setTime(23, 58, 52);
      AssertTesting.assertTime(time, 23, 58, 52);
      displayTime("time after set hour, minute, second: ", time);
      
      final int MINUTES = Time.MINUTES_PER_HOUR - time.getMinute() + 1;
      final int SECONDS = MINUTES * Time.SECONDS_PER_MINUTE - time.getSecond() + 1;
      int second = Time.START_TIME_VALUE;
      
      while (second < SECONDS) {
         time.tick();
         second++;
         displayTime("time after increment " + second + " second: ", time);
      }
      
      AssertTesting.assertTime(time, 0, 1, 1);
   }
   
   
}
