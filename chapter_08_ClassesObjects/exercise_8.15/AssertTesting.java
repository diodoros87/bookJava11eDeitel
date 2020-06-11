
/* =====================================================================================
 *       Filename:  AssertTesting.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.15 - class to testing Date objects by asserts 
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.time.LocalDate;

public class AssertTesting {

   public static void compareDateJavaAPI(Date date,
                      int month, int day, int year) {
                      
      if (0 > year) {
         ++year;   // java.time.LocalDate assume year 0, but in my class of Date year 0 is forbidden and is represented as year -1
                  // in BC years is shift by 1 position between my class of Date and java.time.LocalDate
      }
                      
      LocalDate expectedDate = LocalDate.of(year, month, day);
      int expectedMonth      = expectedDate.getMonthValue();
      int expectedDay        = expectedDate.getDayOfMonth();
      int expectedYear       = expectedDate.getYear();
      int expectedDayOfYear  = expectedDate.getDayOfYear();
      
      if (0 >= expectedYear) {
         --expectedYear;   // java.time.LocalDate assume year 0, but in my class of Date year 0 is forbidden and is represented as year -1
                  // in BC years is shift by 1 position between my class of Date and java.time.LocalDate
      }
      
      
      assert(expectedMonth == date.getMonth()) : String.format("unexpected month %d != %d%n", expectedMonth, date.getMonth());
      assert(expectedDay == date.getDay()) : String.format("unexpected day %d != %d%n", expectedDay, date.getDay());
      assert(expectedYear == date.getYear()) : String.format("unexpected year %d != %d%n", expectedYear, date.getYear());
      
      assert(expectedDayOfYear == date.getDayInYear()) : 
                                          String.format("unexpected day of year %d != %d%n", expectedDayOfYear, date.getDayInYear());
   } 

   public static void expectedResults(Date date,
                      int month, int day, int year) {
      
      expectedMonth(date, month);
      expectedDay(date, day);
      expectedYear(date, year);
   } 
   
   public static void expectedMonth(Date date, int month) {
      assert(date.getMonth() == month) : 
                     String.format("unexpected month %d != %d%n", date.getMonth(), month);
   } 
   
   public static void expectedDay(Date date, int day) {
      assert(date.getDay() == day) : 
                     String.format("unexpected day %d != %d%n", date.getDay(), day);
   }
   
   public static void expectedYear(Date date, int year) {
      assert(date.getYear() == year) : 
                     String.format("unexpected year %d != %d%n", date.getYear(), year);
   } 
   
} 
