/* =====================================================================================
 *       Filename:  Contact.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 13.4 - Declaring an enum type describing
                                various pen size
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Objects;

public class Contact {
   public  static final String WRONG_TELEPHONE_ERROR = "Telephone must be >= 0";
   
   private static final String WRONG_NAME_ERROR = "must contains only alphabetical ASCII characters "
                                                   + "and require only one upper letter as first character";               
   public  static final String WRONG_FIRST_NAME_ERROR = "First name must be empty string or "
                                                         + WRONG_NAME_ERROR;
   public  static final String WRONG_LAST_NAME_ERROR = "Last name " + WRONG_NAME_ERROR;
   
   private String firstName;
   private String lastName;
   private String email;
   private long   telephone;
   
   public Contact(String firstName, String lastName, String email, long telephone) {
      validateFirstName(firstName);
      validateLastName(lastName);
      validateTelephone(telephone);
      
      this.firstName = firstName;
      this.lastName  = lastName;
      generateEmail(email);
      this.telephone  = telephone;
   }
   
   public String getFirstName() {
      return firstName;
   }
   
   public String getLastName() {
      return lastName;
   }
   
   public void setFirstName(String firstName) {
      validateFirstName(firstName);
      this.firstName = firstName;
   }
   
   public void setLastName(String lastName) {
      validateLastName(firstName);
      this.lastName = lastName;
   }
   
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      generateEmail(email);
   }
   
   public static void validateFirstName(String firstName) {
      Objects.requireNonNull(firstName);
      if (false == firstName.matches("^\\p{Upper}?$|^\\p{Upper}\\p{Lower}+$")) {
         throw new IllegalArgumentException(WRONG_FIRST_NAME_ERROR);
      }
   }
   
   public static void validateLastName(String lastName) {
      Objects.requireNonNull(lastName);
      if (false == lastName.matches("^\\p{Upper}\\p{Lower}*$")) {
         throw new IllegalArgumentException(WRONG_LAST_NAME_ERROR);
      }
   }
   
   private final void generateEmail(String email) {
      Objects.requireNonNull(email);
      
      email = email.trim​();
      email = email.replaceAll​("\\p{Space}", "_");
      int firstIndexAt = email.indexOf("@");
      
      if (email.isEmpty() || -1 == firstIndexAt || isMoreThanOneCharacters(email, '@') 
         || email.startsWith("@") || email.endsWith("@")) {
         
         this.email = firstName.concat(lastName).toLowerCase() + "@gmail.com";
      }
      else {
         this.email = email;
      }
   }
   
   public static boolean isMoreThanOneCharacters(String string, char character) {
      Objects.requireNonNull(string);
      
      String characterString = String.valueOf(character);
      int firstIndexAt = string.indexOf("@");
      if (-1 == firstIndexAt) {
         return false;
      }
      
      int lastIndexAt  = string.lastIndexOf("@");
      if (firstIndexAt != lastIndexAt) {
         return true;
      }
      
      return false;
   }
   
   public long getTelephone() {
      return telephone;
   }
   
   public static void validateTelephone(long telephone) {
      if (telephone < 0) {
         throw new IllegalArgumentException(WRONG_TELEPHONE_ERROR);
      }
   }
   
   public void setTelephone (long telephone) {
      validateTelephone(telephone);
      this.telephone  = telephone;
   }
   
   @Override
   public String toString() {
      return getLastName();
   }
}
