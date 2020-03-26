/* =====================================================================================
 *       Filename:  HeartRates.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.16 - class of heart rates
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.time.LocalDate;

public class HeartRates {

   private String firstName = "";
   private String lastName  = "";
   private int day;    // day of birthday
   private int month;  // month of birthday 
   private int year;   // year of birthday
   
   private static final int baseOfHeartRates = 220;
   private static final double centerOfPercentTargetHeartRates = (85 + 50) / 2.0 / 100;
   
   public HeartRates(String firstName, String lastName, int day, int month, int year) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.day = day;
      this.month = month; 
      this.year = year;      
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
}
