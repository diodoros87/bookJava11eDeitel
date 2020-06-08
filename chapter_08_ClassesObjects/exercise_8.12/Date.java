/* =====================================================================================
 *       Filename:  Date.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.12 - class of date
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class Date {
   private int month; // 1-12
   private int day; // 1-31 based on month
   private int year; 

   private static final int[] DAYS_PER_MONTH = 
      {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
   
   // constructor: confirm proper value for month and day given the year
   public Date(int month, int day, int year) {
      if (0 == year) {
         throw new IllegalArgumentException("year can not be 0");
      }
      // check if month in range
      if (month <= 0 || month > 12) {
         throw new IllegalArgumentException(
            "month (" + month + ") must be 1-12");
      }

      // check if day in range for month
      if (day <= 0 || 
         (day > DAYS_PER_MONTH[month] && !(month == 2 && day == 29))) {
         throw new IllegalArgumentException("day (" + day + 
            ") out-of-range for the specified month and year");
      }
      
      if (0 > year) { // to convenient calculate of leap year assume years BC 
         ++year;      // increased by 1
      }

      // check for leap year if month is 2 and day is 29
      if (month == 2 && day == 29 && !(year % 400 == 0 || 
           (year % 4 == 0 && year % 100 != 0))) {
         throw new IllegalArgumentException("day (" + day +
            ") out-of-range for the specified month and year");
      }
      
      if (0 >= year) {   // after calculate of leap year 
         --year;   // revert value of years BC
      }
   
      this.month = month;
      this.day = day;
      this.year = year;

      System.out.printf("Date object constructor for date %s%n", this);
   }
   
   public void setMonth(int month) {
      // check if month in range
      if (month <= 0 || month > 12) {
         throw new IllegalArgumentException(
            "month (" + month + ") must be 1-12");
      }
      
      // check if day in range for month
      if (this.day > DAYS_PER_MONTH[month] && !(month == 2 && this.day == 29)) {
         throw new IllegalArgumentException("day (" + day + ") out-of-range for the specified month and year");
      }
      
      // check for leap year if month is 2 and day is 29
      if (month == 2 && this.day == 29 && false == isLeapYear(this.year)) {
         throw new IllegalArgumentException("day (" + day + ") out-of-range for the specified month and year");
      }
      
      this.month = month;
   } 
   
   public void setDay(int day) {
      // check if day in range for month
      if (day <= 0 || 
         (day > DAYS_PER_MONTH[this.month] && !(this.month == 2 && day == 29))) {
         throw new IllegalArgumentException("day (" + day + ") out-of-range for the specified month and year");
      }
      
      // check for leap year if month is 2 and day is 29
      if (this.month == 2 && day == 29 && false == isLeapYear(this.year)) {
         throw new IllegalArgumentException("day (" + day + ") out-of-range for the specified month and year");
      }
      
      this.day = day;
   } 
   
   public void setYear(int year) {
      if (0 == year) {
         throw new IllegalArgumentException("year can not be 0");
      }
      
      // check for leap year if month is 2 and day is 29
      if (this.month == 2 && this.day == 29 && false == isLeapYear(year)) {
         throw new IllegalArgumentException("day (" + day + ") out-of-range for the specified month and year");
      }
      
      this.year = year;
   } 
   
   public int getMonth() {
      return month; 
   }
   
   public int getDay() {
      return day; 
   }
   
   public int getYear() {
      return year; 
   }
   
   public void nextDay() {
      if (day < DAYS_PER_MONTH[month]) {
         setDay(day + 1);
      }
      else if (day == 28 && month == 2 && true == isLeapYear(year)) {
         setDay(day + 1);
      }
      else {
         setDay(1);
         
         if (12 != month) {
            setMonth(month + 1);
         }
         else {
            setMonth(1);
            
            if (-1 == year) {
               ++year;
            }
            setYear(year + 1);
         }
      }
   }
   
   public static boolean isLeapYear(int year) {
      if (0 == year) {
         throw new IllegalArgumentException("year can not be 0");
      }
      
      if (0 > year) { // to convenient calculate of leap year assume years BC 
         ++year;      // increased by 1
      }
      
      if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
         return true;
      }
      
      return false;
   }
   
   // return a String of the form month/day/year
   public String toString() {
      return String.format("%02d/%02d/%s", month, day, year > 0 ? year : -year + "BC"); 
   } 
} 
