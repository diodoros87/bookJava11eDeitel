/* =====================================================================================
 *       Filename:  DateAndTimeTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.12 - test of DateAndTime objects
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class DateAndTimeTest {
   public static void main(String[] args) {
      DateAndTime dateAndTime = new DateAndTime();
      testDateAndTime(dateAndTime);
      
      dateAndTime.setDate(new Date (12, 31, 1999));
      testDateAndTime(dateAndTime);
      
      dateAndTime.setDate(new Date (12, 31, 2019));
      dateAndTime.setTime(new Time (23, 59, 59));
      displayDateAndTime("Date and time: ", dateAndTime);
      dateAndTime.tick();
      displayDateAndTime("Date and time after next second: ", dateAndTime);
      AssertTesting.assertTime(dateAndTime.getTime(), 0, 0, 0);
      AssertTesting.expectedResults(dateAndTime.getDate(), 1, 1, 2020);
   } 
   
   private static void testDateAndTime(DateAndTime dateAndTime) {
      displayDateAndTime("date and time: " , dateAndTime);
      
      int days = 3;
      incrementDays(dateAndTime, days);
      displayDateAndTime("date and time after increment " + days + " days:", dateAndTime);
      
      incrementDayByMinutes(dateAndTime);
      incrementDayByTics(dateAndTime);
   }

   private static void displayDateAndTime(String header, DateAndTime dateAndTime) {
      System.out.printf("%s%n   %s%n   %s%n",
         header, dateAndTime.toUniversalString(), dateAndTime.toString());
   } 
   
   private static void incrementDays(DateAndTime dateAndTime, final int DAYS) {
      final int HOURS = DAYS * Time.HOURS_PER_DAY;
      final int H = dateAndTime.getHour();
      final int M = dateAndTime.getMinute();
      final int S = dateAndTime.getSecond();
      
      int hour = Time.START_TIME_VALUE;
      
      while (hour < HOURS) {
         dateAndTime.incrementHour();
         hour++;
         displayDateAndTime("time after increment " + hour + " hour: ", dateAndTime);
      }
   }
   
   private static void incrementDayByMinutes(DateAndTime dateAndTime) {
      dateAndTime.setTime(new Time(23, 46, 7));
      
      AssertTesting.assertTime(dateAndTime.getTime(), 23, 46, 7);
      displayDateAndTime("date and time after set hour, minute, second: ", dateAndTime);
      
      final int MINUTES = Time.MINUTES_PER_HOUR - dateAndTime.getMinute() + 11;
      int minute = Time.START_TIME_VALUE;
      
      while (minute < MINUTES) {
         dateAndTime.incrementMinute();
         minute++;
         displayDateAndTime("time after increment " + minute + " minute: ", dateAndTime);
      }
      
      AssertTesting.assertTime(dateAndTime.getTime(), 0, 11, 7);
   }
   
   private static void incrementDayByTics(DateAndTime dateAndTime) {
      dateAndTime.setTime(new Time(23, 58, 52));
      AssertTesting.assertTime(dateAndTime.getTime(), 23, 58, 52);
      displayDateAndTime("date and time after set hour, minute, second: ", dateAndTime);
      
      final int MINUTES = Time.MINUTES_PER_HOUR - dateAndTime.getMinute() + 1;
      final int SECONDS = MINUTES * Time.SECONDS_PER_MINUTE - dateAndTime.getSecond() + 1;
      int second = Time.START_TIME_VALUE;
      
      while (second < SECONDS) {
         dateAndTime.tick();
         second++;
         displayDateAndTime("date and time after increment " + second + " second: ", dateAndTime);
      }
      
      AssertTesting.assertTime(dateAndTime.getTime(), 0, 1, 1);
   }
   
   
}
