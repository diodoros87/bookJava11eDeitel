/* =====================================================================================
 *       Filename:  PenSize.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 13.3 - Declaring an enum type describing
                                various pen size
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Objects;

public class Contact {
   public  static final String WRONG_TELEPHONE_ERROR = "Telephone must be >= 0";
   
   private final String FIRST_NAME;
   private final String LAST_NAME;
   private       String email;
   private       long   telephone;
   
   public Contact(String FIRST_NAME, String LAST_NAME, String email, long telephone) {
      this.FIRST_NAME = Objects.requireNonNull(FIRST_NAME);
      this.LAST_NAME  = Objects.requireNonNull(LAST_NAME);
      
      generateEmail(email);
      
      validateTelephone(telephone);
      this.telephone  = telephone;
   }
   
   public String getFirstName() {
      return FIRST_NAME;
   }
   
   public String getLastName() {
      return LAST_NAME;
   }
   
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      generateEmail(email);
   }
   
   private final void generateEmail(String email) {
      Objects.requireNonNull(email);
      int firstIndexAt = email.indexOf("@");
      
      if (email.isEmpty() || -1 == firstIndexAt || isMoreThanOneCharacters(email, '@') 
         || email.startsWith("@") || email.endsWith("@")) {
            
         this.email = FIRST_NAME.concat(LAST_NAME).toLowerCase() + "@gmail.com";
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
