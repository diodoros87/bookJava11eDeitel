
/* =====================================================================================
 *       Filename:  DateTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.14 - Inputting and outputting 
                                date with Date objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class DateTest {

   public static void main(String[] args) {
      manageCorrectData();
      
      createIncorrectData();
      
      manageDateByUser();   
   } 
   
   private static void manageCorrectData() {
      System.out.printf("+++	 Display date with correct data:%n");
      
      Date correctFirst1998  = testingDisplayDate(new Date(23, 05, 1998), 23, 5, 1998);
      Date correctSecond1998 = testingDisplayDate(new Date(23, 5, 1998), 23, 05, 1998);
      Date leapYear          = testingDisplayDate(new Date(29, 2, 2020), 29, 2, 2020);
      Date leapYearFirstBC   = testingDisplayDate(new Date(29, 2, -1), 29, 2, -1);
      Date leapYearFifthBC   = testingDisplayDate(new Date(29, 2, -5), 29, 2, -5);
      Date BC44              = testingDisplayDate(new Date(1, 3, -44), 1, 3, -44);
      
      // trying to set only one of instance's variable 
      System.out.printf("%n+++	 Modifications date:%n");
      UserInterface.displayDate(correctFirst1998);
      
      modifyDay(correctFirst1998, 77, 23);  // after instruction: 23-05-1998
      modifyDay(correctFirst1998, 31, 31);  // after instruction: 31-05-1998
      modifyDay(correctFirst1998, 0, 31);   // after instruction: 31-05-1998
      modifyDay(correctFirst1998, -3, 31);  // after instruction: 31-05-1998
      modifyDay(correctFirst1998, 14, 14);  // after instruction: 14-05-1998
      
      modifyMonth(correctFirst1998, 6, 6);  // after instruction: 14-06-1998
      modifyMonth(correctFirst1998, 0, 6);  // after instruction: 14-06-1998
      modifyMonth(correctFirst1998, 15, 6); // after instruction: 14-06-1998
      modifyMonth(correctFirst1998, -3, 6); // after instruction: 14-06-1998
      
      modifyYear(correctFirst1998, 156, 156);   // after instruction: 14-06-156
      modifyYear(correctFirst1998, -156, -156); // after instruction: 14-06-156BC
      modifyYear(correctFirst1998, 0, -156);    // after instruction: 14-06-156BC
      modifyYear(correctFirst1998, 1998, 1998); // after instruction: 14-06-1998
      modifyYear(correctFirst1998, 0, 1998);    // after instruction: 14-06-1998
      
      modifyDay(correctFirst1998, 31, 14);  // after instruction: 14-06-1998
      modifyDay(correctFirst1998, 30, 30);  // after instruction: 30-06-1998
      
      modifyMonth(correctFirst1998, 2, 6);  // after instruction: 30-06-1998
      
      modifyDay(correctFirst1998, 31, 30);  // after instruction: 30-06-1998
      modifyDay(correctFirst1998, 29, 29);  // after instruction: 29-06-1998
      
      modifyMonth(correctFirst1998, 2, 6);  // after instruction: 29-06-1998
      
      modifyDay(correctFirst1998, 28, 28);  // after instruction: 28-06-1998
      
      modifyMonth(correctFirst1998, 2, 2);  // after instruction: 28-02-1998
      
      modifyMonth(correctFirst1998, 3, 3);  // after instruction: 28-03-1998
      modifyMonth(correctFirst1998, 2, 2);  // after instruction: 28-02-1998
      
      modifyYear(correctFirst1998, 2000, 2000); // after instruction: 28-02-2000
      
      modifyDay(correctFirst1998, 29, 29);      // after instruction: 29-02-2000
      
      modifyYear(correctFirst1998, 1998, 2000); // after instruction: 29-02-2000
      modifyYear(correctFirst1998, 2004, 2004); // after instruction: 29-02-2004
      modifyYear(correctFirst1998, 1900, 2004); // after instruction: 29-02-2004
      modifyYear(correctFirst1998, 0, 2004);    // after instruction: 29-02-2004
      modifyYear(correctFirst1998, -7, 2004);   // after instruction: 29-02-2004
      modifyYear(correctFirst1998, -1900, 2004);  // after instruction: 29-02-2004
      modifyYear(correctFirst1998, -1901, 2004);  // after instruction: 29-02-2004
      modifyYear(correctFirst1998, -800, 2004);  // after instruction: 29-02-2004
      modifyYear(correctFirst1998, -799, 2004);  // after instruction: 29-02-2004
      modifyYear(correctFirst1998, -802, 2004);  // after instruction: 29-02-2004
      modifyYear(correctFirst1998, -801, -801);  // after instruction: 29-02-801BC
      modifyYear(correctFirst1998, -709, -709);  // after instruction: 29-02-709BC
      
      modifyDay(correctFirst1998, 28, 28);  // after instruction: 28-02-709BC
      
      modifyYear(correctFirst1998, -800, -800);  // after instruction: 28-02-800BC
      
      modifyDay(correctFirst1998, 29, 28);  // after instruction: 28-02-800BC
      
      modifyMonth(correctFirst1998, 3, 3);  // after instruction: 28-03-800BC
      modifyMonth(correctFirst1998, -3, 3);  // after instruction: 28-03-800BC
      
      modifyDay(correctFirst1998, -7, 28);  // after instruction: 28-03-800BC
      modifyDay(correctFirst1998, 0, 28);  // after instruction: 28-03-800BC
      modifyDay(correctFirst1998, 29, 29);  // after instruction: 29-03-800BC
      
      modifyMonth(correctFirst1998, 2, 3);  // after instruction: 29-03-800BC

      modifyYear(correctFirst1998, -709, -709);  // after instruction: 29-03-709BC
      
      modifyMonth(correctFirst1998, 2, 2);  // after instruction: 29-02-709BC
      modifyMonth(correctFirst1998, 3, 3);  // after instruction: 29-03-709BC
      
      modifyYear(correctFirst1998, -301, -301);  // after instruction: 29-03-301BC
      
      modifyMonth(correctFirst1998, 2, 3);  // after instruction: 29-03-301BC
      
      modifyDay(correctFirst1998, 28, 28);  // after instruction: 28-03-301BC
      
      modifyMonth(correctFirst1998, 2, 2);  // after instruction: 28-02-301BC
      
      modifyDay(correctFirst1998, 29, 28);  // after instruction: 28-02-301BC
   }
   
   private static void createIncorrectData() {
      System.out.printf("%n+++	 Display date with incorrect data:%n");
      // examples of incorrect date:
      Date zeroAll =       testingDisplayDate(new Date(0, 0, 0), 0, 0, 0);
      Date zeroDay =       testingDisplayDate(new Date(0, 1, 1), 0, 0, 0);
      Date zeroMonth =     testingDisplayDate(new Date(1, 0, 1), 0, 0, 0);
      Date zeroYear =      testingDisplayDate(new Date(1, 1, 0), 0, 0, 0);
      
      
      Date noLeapYear        = testingDisplayDate(new Date(29, 2, 2022), 0, 0, 0);
      Date noLeapYearSixthBC = testingDisplayDate(new Date(29, 2, -6), 0, 0, 0);
      Date month15AD253      = testingDisplayDate(new Date(1, 15, 253), 0, 0, 0);
      Date day55BC44         = testingDisplayDate(new Date(55, 10, -44), 0, 0, 0);
      Date month13BC44       = testingDisplayDate(new Date(1, 13, -44), 0, 0, 0);
      Date day31Month4BC44   = testingDisplayDate(new Date(31, 4, -44), 0, 0, 0);
      Date day31Month6AD1999 = testingDisplayDate(new Date(31, 6, 1999), 0, 0, 0);
   }
   
   private static Date testingDisplayDate(Date date, 
                              int expectedDay, int expectedMonth, int expectedYear) {
                              
      AssertTesting.expectedResults(date, expectedDay, expectedMonth, expectedYear);
      UserInterface.displayDate(date);
      
      return date;
   }
   
   private static void manageDateByUser() {
      // create date by user (user enter all elements of date: day, month, year)
      Date createdDateByUser = UserInterface.createDateByUser();
      UserInterface.displayDate(createdDateByUser);
      
      // modify date by user (user enter all elements of date: day, month, year)
      UserInterface.modifyDateByUser(createdDateByUser);
      UserInterface.displayDate(createdDateByUser);
      
      // modify date by user (user enter only one element):
      // modify date by user (user enter only day)
      UserInterface.modifyDay(createdDateByUser);
      UserInterface.displayDate(createdDateByUser);
      
      // modify date by user (user enter only month)
      UserInterface.modifyMonth(createdDateByUser);
      UserInterface.displayDate(createdDateByUser);
      
      // modify date by user (user enter only year)
      UserInterface.modifyYear(createdDateByUser);
      UserInterface.displayDate(createdDateByUser);
      
      // modify date by user (user enter only month)
      UserInterface.modifyMonth(createdDateByUser);
      UserInterface.displayDate(createdDateByUser);
      
      // modify date by user (user enter only year)
      UserInterface.modifyYear(createdDateByUser);
      UserInterface.displayDate(createdDateByUser);
      
      // modify date by user (user enter only day)
      UserInterface.modifyDay(createdDateByUser);
      UserInterface.displayDate(createdDateByUser);
   }
   
   private static void modifyDay(Date date, int day, int expectedValue) {
      System.out.printf("%n###	 Try to set new date's day %d%n", day);
      date.setDay(day);
      AssertTesting.expectedDay(date, expectedValue);
      UserInterface.displayDate(date);
   }
   
   private static void modifyMonth(Date date, int month, int expectedValue) {
      System.out.printf("%n###	 Try to set new date's month %d%n", month);
      date.setMonth(month);
      AssertTesting.expectedMonth(date, expectedValue);
      UserInterface.displayDate(date);
   }
   
   private static void modifyYear(Date date, int year, int expectedValue) {
      System.out.printf("%n###	 Try to set new date's year %d%n", year);
      date.setYear(year);
      AssertTesting.expectedYear(date, expectedValue);
      UserInterface.displayDate(date);
   }
} 
