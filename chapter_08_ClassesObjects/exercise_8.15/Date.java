/* =====================================================================================
 *       Filename:  Date.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.15 - class of date
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Arrays;
 
public class Date {
   private int month; // 1-12
   private int day; // 1-31 based on month
   private int year; 
   
   private static final int YEAR_DAYS      = 365;
   private static final int LEAP_YEAR_DAYS = YEAR_DAYS + 1;

   private static final int[] DAYS_PER_MONTH = 
      {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
      
   private static final String[] MONTHS = 
      {"", "January", "February", "March", "April", "May", "June", "July", "August", "September",
                                 "October", "November", "December"};
   
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
   
   public Date(String monthString, int day, int year) {
      if (0 == year) {
         throw new IllegalArgumentException("year can not be 0");
      }
      
      int monthIndex = getMonthIndex(monthString);
      // check if month in range
      if (monthIndex <= 0 || monthIndex > 12) {
         throw new IllegalArgumentException(
            "unrecognized month (" + monthString + ")");
      }

      // check if day in range for month
      if (day <= 0 || 
         (day > DAYS_PER_MONTH[monthIndex] && !(monthIndex == 2 && day == 29))) {
         throw new IllegalArgumentException("day (" + day + 
            ") out-of-range for the specified month and year");
      }
      
      if (0 > year) { // to convenient calculate of leap year assume years BC 
         ++year;      // increased by 1
      }

      // check for leap year if month is 2 and day is 29
      if (monthIndex == 2 && day == 29 && !(year % 400 == 0 || 
           (year % 4 == 0 && year % 100 != 0))) {
         throw new IllegalArgumentException("day (" + day +
            ") out-of-range for the specified month and year");
      }
      
      if (0 >= year) {   // after calculate of leap year 
         --year;   // revert value of years BC
      }
   
      this.month = monthIndex;
      this.day = day;
      this.year = year;

      System.out.printf("Date object constructor for date %s%n", this);
   }
   
   public Date(int day, int year) {
      if (0 == year) {
         throw new IllegalArgumentException("year can not be 0");
      }

      if (day <= 0 || day > LEAP_YEAR_DAYS) {
         throw new IllegalArgumentException("day (" + day + 
            ") out-of-range for year");
      }
      
      if (0 > year) { // to convenient calculate of leap year assume years BC 
         ++year;      // increased by 1
      }
      
      boolean leapYear = true;
      if (year % 4 != 0 || (year % 400 != 0 && year % 100 == 0)) {
         leapYear = false;
      }
      
      if (false == leapYear && day == LEAP_YEAR_DAYS) {
         throw new IllegalArgumentException("day (" + day + 
            ") out-of-range for the not leap year");
      }
      
      if (0 >= year) {   // after calculate of leap year 
         --year;   // revert value of years BC
      }
      
      int yearDay = 0;
      int monthCounter = 0;
      
      do {
         ++monthCounter;
         yearDay += DAYS_PER_MONTH[monthCounter];
         
         if (monthCounter == 2 && true == leapYear) {
            yearDay++;
         }
      } while (yearDay < day);
      
      int daysPerMonth = DAYS_PER_MONTH[monthCounter];
      if (monthCounter == 2 && true == leapYear) {
         daysPerMonth++;
      }
   
      this.month = monthCounter;
      this.day   = daysPerMonth - (yearDay - day);
      this.year  = year;

      System.out.printf("Date object constructor for day %d and date %s %b = leap year%n", getDayInYear(), this, leapYear);
   }
   
   public static int getMonthIndex(final String STRING) {
      for (int index = 1; index <= 12; index++) {
         if (true == STRING.equalsIgnoreCase(MONTHS[index])) {
            return index;
         }
      }
      
      return -99;
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
   
   public int getDayInYear() {
      int yearDay = this.day;
      
      for (int monthCounter = 1; monthCounter < this.month; monthCounter++) {
         yearDay += DAYS_PER_MONTH[monthCounter];
      }
      
      if (true == isLeapYear(this.year)) {
         if (this.month >= 3) {
            yearDay++;
         }
      }
      
      return yearDay;
   }
   
   public String toString() {
      return String.format("%s-%02d-%02d", year > 0 ? String.format("%04d", year) : String.format("%04dBC", -year),
                                   month, day); 
   } 
   
   public String toStringMonthByLetters() {
      return String.format(" %d %s %s", day, MONTHS[month],
                                       year > 0 ? year : -year + "BC"); 
   }
   
   public String toStringByDayInYear() {
      return String.format("%03d-%s", getDayInYear(), 
                  year > 0 ? String.format("%04d", year) : String.format("%04dBC", -year)); 
   } 
} 
