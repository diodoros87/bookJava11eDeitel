
/* =====================================================================================
 *       Filename:  AssertTesting.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.5 - class to testing by asserts class of time
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class AssertTesting {

   public static void assertTime(Time time, int expectedHour, int expectedMinute, int expectedSecond) {
      assertHour(time, expectedHour);
      assertMinute(time, expectedMinute);
      assertSecond(time, expectedSecond);
   }
   
   public static void assertHour(Time time, int expectedHour) {
      int hour = time.getHour();
      
      assert(hour == expectedHour) : 
                     String.format("%d != %d%n", hour, expectedHour);
   }
   
   public static void assertMinute(Time time, int expectedMinute) {
      int minute = time.getMinute();
      
      assert(minute == expectedMinute) : 
                     String.format("%d != %d%n", minute, expectedMinute);
   }
   
   public static void assertSecond(Time time, int expectedSecond) {
      int second = time.getSecond();
      
      assert(second == expectedSecond) : 
                     String.format("%d != %d%n", second, expectedSecond);
   }
   
} 
