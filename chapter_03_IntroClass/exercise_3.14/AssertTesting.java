
/* =====================================================================================
 *       Filename:  AssertTesting.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.14 - class to testing by asserts program of 
                                inputting and outputting floating-point numbers with 
                                   Date objects 
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class AssertTesting {

   public static void expectedResults(Date date,
                      int day, int month, int year) {
      
      expectedDay(date, day);
      expectedMonth(date, month);
      expectedYear(date, year);
   } 
   
   public static void expectedDay(Date date, int day) {
      assert(date.getDay() == day) : 
                     String.format("%d != %d%n", date.getDay(), day);
   } 
   
   public static void expectedMonth(Date date, int month) {
      assert(date.getMonth() == month) : 
                     String.format("%d != %d%n", date.getMonth(), month);
   } 
   
   public static void expectedYear(Date date, int year) {
      assert(date.getYear() == year) : 
                     String.format("%d != %d%n", date.getYear(), year);
   } 
   
} 
