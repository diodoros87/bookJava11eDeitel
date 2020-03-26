/* =====================================================================================
 *       Filename:  UserInterface.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.14 - class of user inferface in
                                program with Date objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class UserInterface {

   private static final String dayPrompt   = "Enter number of day for date: ";
   private static final String monthPrompt = "Enter number of month for date: ";
   private static final String yearPrompt  = "Enter number of year for date: ";

   public static Date createDateByUser() {
   
      System.out.printf("%n****** Enter data of date (day, month, year)" 
                         + " to create date.%n");
      int day   = GettingDataFromStandardInput.getInteger(dayPrompt);
      int month = GettingDataFromStandardInput.getInteger(monthPrompt);
      int year  = GettingDataFromStandardInput.getInteger(yearPrompt);
      
      Date newDate  = new Date(day, month, year);
      return newDate;
   }
   
   public static Date modifyDateByUser(Date date) {
   
      System.out.printf("%n****** Enter data of date (day, month, year)" 
                + String.format(" to modify date \'%d %d %d\' %n",
                                 date.getDay(), date.getMonth(), date.getYear()));
      
      modifyDay(date);
      modifyMonth(date);
      modifyYear(date);
      
      return date;
   }
   
   public static Date modifyDay(Date date) {
   
      int day = GettingDataFromStandardInput.getInteger(dayPrompt);
      date.setDay(day);
    
      return date;
   }
   
   public static Date modifyMonth(Date date) {
   
      int month = GettingDataFromStandardInput.getInteger(monthPrompt);
      date.setMonth(month);
    
      return date;
   }
   
   public static Date modifyYear(Date date) {
   
      int year = GettingDataFromStandardInput.getInteger(yearPrompt);
      date.setYear(year);
    
      return date;
   }
   
   public static void displayDate(Date date) {
      Integer day = date.getDay();
      Integer month = date.getMonth();
      Integer year = date.getYear();
      
      System.out.printf("-------Date's data: %n"); 
      System.out.printf("day: %d\t month: %d\t year: %d%n", day, month, year);
      System.out.printf("Date's format separated by a hyphen: %s-%s-%s %n", 
         day < 10 ? "0" + day : day,
         month < 10 ? "0" + month : month,
         year < 0 ? -year + "BC" : year);
   }
   
}
