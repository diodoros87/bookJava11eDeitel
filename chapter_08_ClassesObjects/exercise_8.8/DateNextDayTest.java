/* =====================================================================================
 *       Filename:  DateNextDayTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.8 - test of Date object's method nextDay
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class DateNextDayTest {

   public static void main(String[] args) {
      testDateBC();
      
      Date date = new Date(12, 31, 1999);
      printNextDays(date, 2 * 370);
      
      date.setYear(2020);
      date.setMonth(2);
      date.setDay(28);
      date.nextDay();
      System.out.printf("-------Test of leap year's date: %s %n", date); 
      AssertTesting.expectedResults(date, 2, 29, 2020);
      date.nextDay();
      System.out.printf("-------Test of leap year's date: %s %n", date); 
      AssertTesting.expectedResults(date, 3, 1, 2020);
      
   }
   
   private static void testDateBC() {
      Date testBCDate = new Date(12, 31, -1);
      testBCDate.nextDay();
      System.out.printf("-------Test of first AD date: %s %n", testBCDate); 
      AssertTesting.expectedResults(testBCDate, 1, 1, 1);
      
      testBCDate.setYear(-2);
      testBCDate.setMonth(12);
      testBCDate.setDay(31);
      AssertTesting.expectedResults(testBCDate, 12, 31, -2);
      printNextDays(testBCDate, 2 * 370);
   }
   
   private static void printNextDays(Date date, final int DAYS_COUNT) {
      System.out.printf("-------Print next %d days for date: %s %n", DAYS_COUNT, date);
      
      for (int day = 0; day < DAYS_COUNT; day++) {
         date.nextDay();
         System.out.printf("-------Date: %s %n", date); 
      }
   }
   
   
}
