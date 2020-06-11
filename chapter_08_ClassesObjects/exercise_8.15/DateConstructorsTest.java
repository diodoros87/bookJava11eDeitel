/* =====================================================================================
 *       Filename:  DateConstructorsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.15 - test of overloaded Date constructors
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class DateConstructorsTest {

   public static void main(String[] args) {
      constructDate("DECEMBER", 31, 1999, 12, 31, 1999);
      constructDate("februaRy", 29, 2020, 2, 29, 2020);
      constructDate("March", 1, 2020, 3, 1, 2020);
      constructDate("december", 31, -2, 12, 31, -2);
      constructIncorrectDate("Dr", 3, 3);
      constructIncorrectDate("", 3, 3);
      
      Date date = new Date(12, 31, 1999);
      printNextDays(date, 2 * 370);
      
      testDateBC();
      
      constructIncorrectDate(366, 1900);
      constructIncorrectDate(366, 1990);
      
      Date date1900b = new Date(59, 1900);
      assert(59 == date1900b.getDayInYear());
      Date date1900a = new Date(60, 1900);
      assert(60 == date1900a.getDayInYear());
      Date date1900c = new Date(61, 1900);
      assert(61 == date1900c.getDayInYear());
      Date date1900d = new Date(62, 1900);
      assert(62 == date1900d.getDayInYear());
      
      Date date1896b = new Date(59, 1896);
      assert(59 == date1896b.getDayInYear());
      Date date1896a = new Date(60, 1896);
      assert(60 == date1896a.getDayInYear());
      Date date1896c = new Date(61, 1896);
      assert(61 == date1896c.getDayInYear());
      Date date1896d = new Date(62, 1896);
      assert(62 == date1896d.getDayInYear());
   }
   
   private static void constructDate(String monthString, int day, int year, int expectedMonth, int expectedDay, int expectedYear) {
      Date date = new Date(monthString, day, year);
      testingDisplayDate(date, expectedMonth, expectedDay, expectedYear);
   }
   
   private static void constructIncorrectDate(int day, int year) {
      try {
         Date date = new Date(day, year);
         System.out.printf("%n????? No Exception while construct date %s %n", date);
         assert(false);
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while construct date for %02d day in year %d : %s%n", day, year,
            e.getMessage());
      } 
   }
   
   private static void constructIncorrectDate(String monthString, int day, int year) {
      try {
         Date date = new Date(monthString, day, year);
         System.out.printf("%n????? No Exception while construct date %s %n", date);
         assert(false);
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while construct date for %02d day and month \'%s\' in year %d : %s%n", 
            day, monthString, year, e.getMessage());
      } 
   }
   
   private static Date testingDisplayDate(Date date, 
                              int expectedMonth, int expectedDay, int expectedYear) {
                              
      AssertTesting.expectedResults(date, expectedMonth, expectedDay, expectedYear);
      AssertTesting.compareDateJavaAPI(date, expectedMonth, expectedDay, expectedYear);
      
      System.out.printf("-------Date's data: %s %n", date); 
      return date;
   }
   
   private static void testDateBC() {
      Date testBCDate = new Date(366, -1);
      AssertTesting.expectedResults(testBCDate, 12, 31, -1);
      AssertTesting.compareDateJavaAPI(testBCDate, 12, 31, -1);
      testBCDate.nextDay();
      System.out.printf("-------Test of first AD date: %s %n", testBCDate.toStringMonthByLetters()); 
      AssertTesting.expectedResults(testBCDate, 1, 1, 1);
      AssertTesting.compareDateJavaAPI(testBCDate, 1, 1, 1);
      
      testBCDate.setYear(-2);
      testBCDate.setMonth(12);
      testBCDate.setDay(31);
      AssertTesting.expectedResults(testBCDate, 12, 31, -2);
      AssertTesting.compareDateJavaAPI(testBCDate, 12, 31, -2);
      printNextDays(testBCDate, 2 * 370);
   }
   
   private static void printNextDays(Date date, final int DAYS_COUNT) {
      System.out.printf("-------Print next %d days for date: %s %n", DAYS_COUNT, date.toStringMonthByLetters());
      
      for (int day = 0; day < DAYS_COUNT; day++) {
         date.nextDay();
         System.out.printf("-------Date: %s %n", date.toStringByDayInYear());
         
         Date dateByDayInYear = new Date(date.getDayInYear(), date.getYear());
         
         AssertTesting.compareDateJavaAPI(dateByDayInYear, dateByDayInYear.getMonth(), 
                                    dateByDayInYear.getDay(), dateByDayInYear.getYear());
          
         System.out.printf("-------Constructed Date by day in year: %s %n%n", dateByDayInYear.toStringMonthByLetters());
      }
   }
   
   
}
