
/* =====================================================================================
 *       Filename:  DateTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.15 - test of Date objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class DateTest {

   public static void main(String[] args) {
      manageCorrectData();
      
      createIncorrectData();   
   } 
   
   private static void manageCorrectData() {
      System.out.printf("+++	 Display date with correct data:%n");
      
      Date correctFirst1998  = testingDisplayDate(new Date(05, 23, 1998), 5, 23, 1998);
      Date correctSecond1998 = testingDisplayDate(new Date(3, 31, 1997), 3, 31, 1997);
      Date futureYear        = testingDisplayDate(new Date(3, 29, 20202), 3, 29, 20202);
      Date leapYear          = testingDisplayDate(new Date(2, 29, 2020), 2, 29, 2020);
      Date leapYearFirstBC   = testingDisplayDate(new Date(2, 29, -1), 2, 29, -1);
      Date leapYearFifthBC   = testingDisplayDate(new Date(2, 29, -5), 2, 29, -5);
      Date BC44              = testingDisplayDate(new Date(3, 1, -44), 3, 1, -44);
      
      // trying to set only one of instance's variable 
      System.out.printf("%n+++	 Modifications of date:%n");
      System.out.printf("-------Date's data: %s %n", correctFirst1998); 
      
      setWrongDay(correctFirst1998, 77);  // after instruction: 23-05-1998
      modifyDay(correctFirst1998, 31, 31);  // after instruction: 31-05-1998
      setWrongDay(correctFirst1998, 0);   // after instruction: 31-05-1998
      setWrongDay(correctFirst1998, -3);  // after instruction: 31-05-1998
      modifyDay(correctFirst1998, 14, 14);  // after instruction: 14-05-1998
      
      modifyMonth(correctFirst1998, 6, 6);  // after instruction: 14-06-1998
      setWrongMonth(correctFirst1998, 0);  // after instruction: 14-06-1998
      setWrongMonth(correctFirst1998, 15); // after instruction: 14-06-1998
      setWrongMonth(correctFirst1998, -3); // after instruction: 14-06-1998
      
      modifyYear(correctFirst1998, 156, 156);   // after instruction: 14-06-156
      modifyYear(correctFirst1998, -156, -156); // after instruction: 14-06-156BC
      setWrongYear(correctFirst1998, 0);    // after instruction: 14-06-156BC
      modifyYear(correctFirst1998, 1998, 1998); // after instruction: 14-06-1998
      setWrongYear(correctFirst1998, 0);    // after instruction: 14-06-1998
      
      setWrongDay(correctFirst1998, 31);  // after instruction: 14-06-1998
      modifyDay(correctFirst1998, 30, 30);  // after instruction: 30-06-1998
      
      setWrongMonth(correctFirst1998, 2);  // after instruction: 30-06-1998
      
      setWrongDay(correctFirst1998, 31);  // after instruction: 30-06-1998
      modifyDay(correctFirst1998, 29, 29);  // after instruction: 29-06-1998
      
      setWrongMonth(correctFirst1998, 2);  // after instruction: 29-06-1998
      
      modifyDay(correctFirst1998, 28, 28);  // after instruction: 28-06-1998
      
      modifyMonth(correctFirst1998, 2, 2);  // after instruction: 28-02-1998
      
      modifyMonth(correctFirst1998, 3, 3);  // after instruction: 28-03-1998
      modifyMonth(correctFirst1998, 2, 2);  // after instruction: 28-02-1998
      
      modifyYear(correctFirst1998, 2000, 2000); // after instruction: 28-02-2000
      
      modifyDay(correctFirst1998, 29, 29);      // after instruction: 29-02-2000
      
      setWrongYear(correctFirst1998, 1998); // after instruction: 29-02-2000
      modifyYear(correctFirst1998, 2004, 2004); // after instruction: 29-02-2004
      setWrongYear(correctFirst1998, 1900); // after instruction: 29-02-2004
      setWrongYear(correctFirst1998, 0);    // after instruction: 29-02-2004
      setWrongYear(correctFirst1998, -7);   // after instruction: 29-02-2004
      setWrongYear(correctFirst1998, -1900);  // after instruction: 29-02-2004
      setWrongYear(correctFirst1998, -1901);  // after instruction: 29-02-2004
      setWrongYear(correctFirst1998, -800);  // after instruction: 29-02-2004
      setWrongYear(correctFirst1998, -799);  // after instruction: 29-02-2004
      setWrongYear(correctFirst1998, -802);  // after instruction: 29-02-2004
      modifyYear(correctFirst1998, -801, -801);  // after instruction: 29-02-801BC
      modifyYear(correctFirst1998, -709, -709);  // after instruction: 29-02-709BC
      
      modifyDay(correctFirst1998, 28, 28);  // after instruction: 28-02-709BC
      
      modifyYear(correctFirst1998, -800, -800);  // after instruction: 28-02-800BC
      
      setWrongDay(correctFirst1998, 29);  // after instruction: 28-02-800BC
      
      modifyMonth(correctFirst1998, 3, 3);  // after instruction: 28-03-800BC
      setWrongMonth(correctFirst1998, -3);  // after instruction: 28-03-800BC
      
      setWrongDay(correctFirst1998, -7);  // after instruction: 28-03-800BC
      setWrongDay(correctFirst1998, 0);  // after instruction: 28-03-800BC
      modifyDay(correctFirst1998, 29, 29);  // after instruction: 29-03-800BC
      
      setWrongMonth(correctFirst1998, 2);  // after instruction: 29-03-800BC

      modifyYear(correctFirst1998, -709, -709);  // after instruction: 29-03-709BC
      
      modifyMonth(correctFirst1998, 2, 2);  // after instruction: 29-02-709BC
      modifyMonth(correctFirst1998, 3, 3);  // after instruction: 29-03-709BC
      
      modifyYear(correctFirst1998, -301, -301);  // after instruction: 29-03-301BC
      
      setWrongMonth(correctFirst1998, 2);  // after instruction: 29-03-301BC
      
      modifyDay(correctFirst1998, 28, 28);  // after instruction: 28-03-301BC
      
      modifyMonth(correctFirst1998, 2, 2);  // after instruction: 28-02-301BC
      
      setWrongDay(correctFirst1998, 29);  // after instruction: 28-02-301BC
   }
   
   private static void createIncorrectData() {
      // examples of incorrect date:
      constructIncorrectDate(0, 0, 0);
      constructIncorrectDate(0, 1, 1);
      constructIncorrectDate(1, 0, 1);
      constructIncorrectDate(1, 1, 0);
      
      constructIncorrectDate(2, 29, 2022);
      constructIncorrectDate(2, 29, -6);
      constructIncorrectDate(15, 1, 253);
      constructIncorrectDate(10, 55, -44);
      
      constructIncorrectDate(13, 1, -44);
      constructIncorrectDate(4, 31, -44);
      constructIncorrectDate(6, 31, 1999);
   }
   
   private static void constructIncorrectDate(int month, int day, int year) {
      try {
         Date date = new Date(month, day, year);
         System.out.printf("%n????? No Exception while construct date %s %n", date);
         assert(false);
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while construct date %02d/%02d/%d : %s%n", month, day, year,
            e.getMessage());
      } 
   }
   
   private static Date testingDisplayDate(Date date, 
                              int expectedMonth, int expectedDay, int expectedYear) {
                              
      AssertTesting.expectedResults(date, expectedMonth, expectedDay, expectedYear);
      
      System.out.printf("-------Date's data: %s %n", date); 
      return date;
   }
   
   private static void setWrongDay(Date date, int day) {
      try {
         date.setDay(day);
         System.out.printf("%n????? No Exception while set day %d in date %s %n", day, date);
         assert(false);
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while set day %d in date %s : %s%n", day, date,
            e.getMessage());
      } 
   }
   
   private static void setWrongMonth(Date date, int month) {
      try {
         date.setMonth(month);
         System.out.printf("%n????? No Exception while set month %d in date %s %n", month, date);
         assert(false);
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while set month %d in date %s : %s%n", month, date,
            e.getMessage());
      } 
   }
   
   private static void setWrongYear(Date date, int year) {
      try {
         date.setYear(year);
         System.out.printf("%n????? No Exception while set year %d in date %s %n", year, date);
         assert(false);
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while set year %d in date %s : %s%n", year, date,
            e.getMessage());
      } 
   }
   
   private static void modifyDay(Date date, int day, int expectedValue) {
      System.out.printf("%n###	 Try to set new date's day %d%n", day);
      date.setDay(day);
      AssertTesting.expectedDay(date, expectedValue);
      System.out.printf("-------Date's data: %s %n", date); 
   }
   
   private static void modifyMonth(Date date, int month, int expectedValue) {
      System.out.printf("%n###	 Try to set new date's month %d%n", month);
      date.setMonth(month);
      AssertTesting.expectedMonth(date, expectedValue);
      System.out.printf("-------Date's data: %s %n", date); 
   }
   
   private static void modifyYear(Date date, int year, int expectedValue) {
      System.out.printf("%n###	 Try to set new date's year %d%n", year);
      date.setYear(year);
      AssertTesting.expectedYear(date, expectedValue);
      System.out.printf("-------Date's data: %s %n", date); 
   }
} 
