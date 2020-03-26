/* =====================================================================================
 *       Filename:  UserInterface.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.16 - class of user inferface in
                                program with HeartRates objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class UserInterface {

   private static final String firstNamePrompt   = "Enter first name: ";
   private static final String lastNamePrompt    = "Enter last name: ";
   private static final String dayPrompt   = "Enter number of day for birthday: ";
   private static final String monthPrompt = "Enter number of month for birthday: ";
   private static final String yearPrompt  = "Enter number of year for birthday: ";

   public static HeartRates createPersonalDataByUser() {
      System.out.printf("%n****** Enter names and date of birthday" 
                         + " to calculate heart rates.%n");
      String firstName = GettingDataFromStandardInput.getString(firstNamePrompt);
      String lastName  = GettingDataFromStandardInput.getString(lastNamePrompt);
      int day   = GettingDataFromStandardInput.getInteger(dayPrompt);
      int month = GettingDataFromStandardInput.getInteger(monthPrompt);
      int year  = GettingDataFromStandardInput.getInteger(yearPrompt);
      
      HeartRates newHeartRates  = new HeartRates(firstName, lastName, day, month, year);
      return newHeartRates;
   }
   
   public static HeartRates modifyPersonalDataByUser(HeartRates human) {
      System.out.printf("%n****** Enter names and date of birthday" 
            + String.format(" to modify personal data with names \'%s\' \'%s\' and calculate heart rates%n",
                                 human.getFirstName(), human.getLastName()));
      
      modifyFirstName(human);
      modifyLastName(human);
      modifyDay(human);
      modifyMonth(human);
      modifyYear(human);
      
      return human;
   }
   
   public static HeartRates modifyFirstName(HeartRates human) {
      String firstName = GettingDataFromStandardInput.getString(firstNamePrompt);
      human.setFirstName(firstName);
    
      return human;
   }
   
   public static HeartRates modifyLastName(HeartRates human) {
      String lastName = GettingDataFromStandardInput.getString(lastNamePrompt);
      human.setLastName(lastName);
    
      return human;
   }
   
   public static HeartRates modifyDay(HeartRates human) {
      int day = GettingDataFromStandardInput.getInteger(dayPrompt);
      human.setDay(day);
    
      return human;
   }
   
   public static HeartRates modifyMonth(HeartRates human) {
      int month = GettingDataFromStandardInput.getInteger(monthPrompt);
      human.setMonth(month);
    
      return human;
   }
   
   public static HeartRates modifyYear(HeartRates human) {
      int year = GettingDataFromStandardInput.getInteger(yearPrompt);
      human.setYear(year);
    
      return human;
   }
   
   public static void displayPersonalData(HeartRates human) {
      System.out.printf("-------Personal data: %n"); 
      System.out.printf("first name: \'%s\'\t last name: \'%s\'%n", human.getFirstName(), human.getLastName()); 
      System.out.printf("-------Birthday: %n"); 
      System.out.printf("day: %d\t month: %d\t year: %d%n", human.getDay(), human.getMonth(), human.getYear());
      System.out.printf("Age in years is %d %n", human.getAgeInYears());
      System.out.printf("Maximum heart rates is %d beats per minute %n", human.getMaximumHeartRates());
      System.out.printf("Target heart rates is %d beats per minute %n", human.getTargetHeartRates());
   }
   
}
