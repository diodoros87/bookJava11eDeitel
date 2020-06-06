
/* =====================================================================================
 *       Filename:  AssertTesting.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.8 - class to testing Date objects by asserts 
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class AssertTesting {

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
