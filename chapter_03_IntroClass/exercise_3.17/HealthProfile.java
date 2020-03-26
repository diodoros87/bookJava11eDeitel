/* =====================================================================================
 *       Filename:  HealthProfile.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.17 - class of health profile
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.time.LocalDate;

public class HealthProfile {

   private String firstName = "";
   private String lastName  = "";
   private int day;    // day of birthday
   private int month;  // month of birthday 
   private int year;   // year of birthday
   private boolean isMale;    // for male value is true, for female value is false
   private int height;  // height in centimetres
   private int weight;   // weight in kilograms
   
   private static final int baseOfHeartRates = 220;
   private static final double centerOfPercentTargetHeartRates = (85 + 50) / 2.0 / 100;
   
   private static final String isMaleString = "male";
   
   public HealthProfile(String firstName, String lastName, int day, int month, int year,
                        String isMale, int height, int weight) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.day = day;
      this.month = month; 
      this.year = year; 
      if (0 == isMale.compareToIgnoreCase(isMaleString)) {
         this.isMale = true;
      }
      this.height = height; 
      this.weight = weight;     
   }
   
   public double getBodyMassIndex() {
      return weight / (height * height / (double)(100 * 100));
   }
   
   public int getAgeInYears() {
      LocalDate currentDate = LocalDate.now();
      int ageInYears = currentDate.getYear() - year;
      
      return ageInYears; 
   }
   
   public int getMaximumHeartRates() {
      return baseOfHeartRates - getAgeInYears(); 
   }
   
   public int getTargetHeartRates() {
      return (int)(centerOfPercentTargetHeartRates * getMaximumHeartRates()); 
   }
   
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   } 

   public String getFirstName() {
      return firstName; 
   } 
   
   public void setLastName(String lastName) {
      this.lastName = lastName;
   } 

   public String getLastName() {
      return lastName; 
   } 

   public void setDay(int day) {
      this.day = day;
   } 

   public int getDay() {
      return day; 
   } 
   
   public void setMonth(int month) {
      this.month = month; 
   } 

   public int getMonth() {
      return month; 
   } 
   
   public void setYear(int year) {
      this.year = year;
   } 

   public int getYear() {
      return year; 
   } 
   
   public void setIsMale(String isMale) {
      if (0 == isMale.compareToIgnoreCase(isMaleString)) {
         this.isMale = true;
      }
      else
         this.isMale = false;
   }
   
   public boolean getIsMale() {
      return isMale; 
   } 
   
   public void setHeight(int height) {
      this.height = height;
   } 

   public int getHeight() {
      return height; 
   } 
   
   public void setWeight(int weight) {
      this.weight = weight; 
   } 

   public int getWeight() {
      return weight; 
   }
   
}
