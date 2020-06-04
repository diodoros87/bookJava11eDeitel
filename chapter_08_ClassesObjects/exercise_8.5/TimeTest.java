/* =====================================================================================
 *       Filename:  TimeTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.5 - Overloaded constructors used to initialize
                                Time objects
                           
                             
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
      displayTime("t1 after change hour, minute, second:", t1);
      
      t1.setHour(4);
      AssertTesting.assertTime(t1, 4, 45, 6);
      displayTime("t1 after change hour:", t1);
      
      t1.setMinute(19);
      AssertTesting.assertTime(t1, 4, 19, 6);
      displayTime("t1 after change minute:", t1);
      
      t1.setSecond(56);
      AssertTesting.assertTime(t1, 4, 19, 56);
      displayTime("t1 after change second:", t1);
      
      t1.setTime(1, 1, 1);
      AssertTesting.assertTime(t1, 1, 1, 1);
      displayTime("t1 after change hour, minute, second:", t1);
   } 

   // displays a Time object in 24-hour and 12-hour formats
   private static void displayTime(String header, Time t) {
      System.out.printf("%s%n   %s%n   %s%n",
         header, t.toUniversalString(), t.toString());
   } 
   
   
}
