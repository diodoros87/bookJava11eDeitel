/* =====================================================================================
 *       Filename:  UserInterface.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.17 - class of user inferface in
                                program with HealthProfile objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class UserInterface {

   private static final String firstNamePrompt   = "Enter first name: ";
   private static final String lastNamePrompt    = "Enter last name: ";
   private static final String genderPrompt      = 
   String.format("Enter gender (for male enter only word 'male' without spaces or tabulators,%n") +
    " otherwise gender will be set to female): ";
   private static final String dayPrompt         = "Enter number of day for birthday: ";
   private static final String monthPrompt       = "Enter number of month for birthday: ";
   private static final String yearPrompt        = "Enter number of year for birthday: ";
   private static final String heightPrompt      = "Enter human's height in centimetres: ";
   private static final String weightPrompt      = "Enter human's weight in kilograms: ";

   public static HealthProfile createHealthProfileByUser() {
      System.out.printf("%n****** Enter personal data to display health profile.%n");
      String firstName  = GettingDataFromStandardInput.getString(firstNamePrompt);
      String lastName   = GettingDataFromStandardInput.getString(lastNamePrompt);
      String gender     = GettingDataFromStandardInput.getString(genderPrompt);
      int day           = GettingDataFromStandardInput.getInteger(dayPrompt);
      int month         = GettingDataFromStandardInput.getInteger(monthPrompt);
      int year          = GettingDataFromStandardInput.getInteger(yearPrompt);
      int height        = GettingDataFromStandardInput.getInteger(heightPrompt);
      int weight        = GettingDataFromStandardInput.getInteger(weightPrompt);
      
      HealthProfile newHealthProfile = new HealthProfile(firstName, lastName, day, month, year,
                                                            gender, height, weight);
      return newHealthProfile;
   }
   
   public static HealthProfile modifyHealthProfileByUser(HealthProfile human) {
      System.out.printf("%n****** Enter personal data to modify health profile with " 
            + String.format("names \'%s\' \'%s\' and display health profile%n",
                                 human.getFirstName(), human.getLastName()));
      
      modifyFirstName(human);
      modifyLastName(human);
      modifyGender(human);
      modifyDay(human);
      modifyMonth(human);
      modifyYear(human);
      modifyHeight(human);
      modifyWeight(human);
      
      return human;
   }
   
   public static HealthProfile modifyFirstName(HealthProfile human) {
      String firstName = GettingDataFromStandardInput.getString(firstNamePrompt);
      human.setFirstName(firstName);
    
      return human;
   }
   
   public static HealthProfile modifyLastName(HealthProfile human) {
      String lastName = GettingDataFromStandardInput.getString(lastNamePrompt);
      human.setLastName(lastName);
    
      return human;
   }
   
   public static HealthProfile modifyGender(HealthProfile human) {
      String gender     = GettingDataFromStandardInput.getString(genderPrompt);
      human.setIsMale(gender);
    
      return human;
   }
   
   public static HealthProfile modifyDay(HealthProfile human) {
      int day = GettingDataFromStandardInput.getInteger(dayPrompt);
      human.setDay(day);
    
      return human;
   }
   
   public static HealthProfile modifyMonth(HealthProfile human) {
      int month = GettingDataFromStandardInput.getInteger(monthPrompt);
      human.setMonth(month);
    
      return human;
   }
   
   public static HealthProfile modifyYear(HealthProfile human) {
      int year = GettingDataFromStandardInput.getInteger(yearPrompt);
      human.setYear(year);
    
      return human;
   }
   
   public static HealthProfile modifyHeight(HealthProfile human) {
      int height        = GettingDataFromStandardInput.getInteger(heightPrompt);
      human.setHeight(height);
    
      return human;
   }
   
   public static HealthProfile modifyWeight(HealthProfile human) {
      int weight        = GettingDataFromStandardInput.getInteger(weightPrompt);
      human.setWeight(weight);
    
      return human;
   }
   
   public static void displayHealthProfile(HealthProfile human) {
      System.out.printf("-------Health Profile: %n"); 
      System.out.printf("first name: \'%s\'\t last name: \'%s\'%n", human.getFirstName(), human.getLastName()); 
      System.out.printf("gender: \'%s\' %n", human.getIsMale() ? "male" : "female");
      System.out.printf("-------Birthday: %n"); 
      System.out.printf("day: %d\t month: %d\t year: %d%n", human.getDay(), human.getMonth(), human.getYear());
      System.out.printf("Age in years is %d %n", human.getAgeInYears());
      System.out.printf("Maximum heart rates is %d beats per minute %n", human.getMaximumHeartRates());
      System.out.printf("Target heart rates is %d beats per minute %n", human.getTargetHeartRates());
      
      displayBodyMassIndex(human);
   }
      
   public static void displayBodyMassIndex(HealthProfile human) {
      double bodyMassIndex = human.getBodyMassIndex();
      String category = "";
      
      {  // to avoid conflict between:
         // - rounding in System.out.printf with %.1f and 
         // - in classify (according to ranges of BMI values) value of bodyMassIndex
         if (bodyMassIndex >= (int)bodyMassIndex + 0.95)    
            bodyMassIndex = (int)bodyMassIndex + 1;
         if (bodyMassIndex >= 18.45 && bodyMassIndex < 18.5)
            bodyMassIndex = 18.5;
      }
      
      if (bodyMassIndex < 15)
         category = "very severely underweight";
      else if (bodyMassIndex >= 15 && bodyMassIndex < 16)
         category = "severely underweight";
      else if (bodyMassIndex >= 16 && bodyMassIndex < 18.5)
         category = "underweight";
      else if (bodyMassIndex >= 18.5 && bodyMassIndex < 25)
         category = "normal (healthy) weight";
      else if (bodyMassIndex >= 25 && bodyMassIndex < 30)
         category = "overweight";
      else if (bodyMassIndex >= 30 && bodyMassIndex < 35)
         category = "obese class I";
      else if (bodyMassIndex >= 35 && bodyMassIndex < 40)
         category = "obese class II";
      else if (bodyMassIndex >= 40 && bodyMassIndex < 45)
         category = "obese class III";
      else if (bodyMassIndex >= 45 && bodyMassIndex < 50)
         category = "obese class IV";
      else
         category = "obese class V";
   
      System.out.printf("%n Body mass index is %.1f ", bodyMassIndex);
      System.out.printf("%n This result belongs to the category of %s %n", category);
      
      System.out.printf("%n This result has been classified according to ranges of BMI values: %n");
      System.out.println("very severely underweight : less than 15");
      System.out.println("severely underweight: contains 15 and less than 16");
      System.out.println("underweight : contains 16 and less than 18.5");
      System.out.println("normal (healthy) weight: contains 18.5 and less than 25");
      System.out.println("overweight: contains 25 and less than 30");
      System.out.println("obese class I : contains 30 and less than 35");
      System.out.println("obese class II: contains 35 and less than 40");
      System.out.println("obese class III : contains 40 and less than 45");
      System.out.println("obese class IV: contains 45 and less than 50");
      System.out.println("obese class V : contains 50 and greater than 50");
   }
   
}
