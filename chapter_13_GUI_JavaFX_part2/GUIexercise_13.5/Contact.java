/* =====================================================================================
 *       Filename:  Contact.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 13.5 -class describing contact's data
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.Objects;
import java.util.Comparator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.InvalidPathException;

public class Contact implements Comparable<Contact> {
   public  static final String WRONG_TELEPHONE_ERROR = "Telephone must be integer >= 0 and <= " + Long.MAX_VALUE;
   
   private static final String WRONG_NAME_ERROR = "must contains only alphabetical ASCII characters "
                                                   + "and require only one upper letter as first character";               
   public  static final String WRONG_FIRST_NAME_ERROR = "First name must be empty string or "
                                                         + WRONG_NAME_ERROR;
   public  static final String WRONG_LAST_NAME_ERROR = "Last name " + WRONG_NAME_ERROR;
   
   private String firstName;
   private String lastName;
   private String email;
   private long   telephone;
   private String imageFilePath;
   
   public Contact(String firstName, String lastName, String email, long telephone, String imageFilePath) {
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
      validateLastName(lastName);
      this.lastName = lastName;
   }
   
   public String getEmail() {
      return email;
   }
   
   public void setEmail(String email) {
      generateEmail(email);
   }
   
   public String getImageFilePath() {
      return imageFilePath;
   }
   
   public void setImageFilePath(File file) {
      validateImageFilePath(file);
      imageFilePath = file.getAbsolutePath();
   }
   
   public static void validateImageFilePath(File file) throws InvalidPathException {
      Objects.requireNonNull(file);
      Path path = file.toPath();
      if (false == Files.exists(path))
         throw new IllegalArgumentException("Path " + path.toAbsolutePath() + " does not exist");
      if (true == Files.isDirectory(path))
         throw new IllegalArgumentException("Path " + path.toAbsolutePath() + " is directory, but regular file is required");
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
   /*
   @Override
   public final int compareTo(Object other) {
      if (false == other instanceof Contact) {
         throw new IllegalArgumentException(
            String.format("parameter class require type of Contact %s %s is not type of Contact",
               System.getProperty​("line.separator"), other.getClass().getName())
               );
      }
      
      String stringRepresentation = toString();
      String otherStringRepresentation = other.toString();
      int result = stringRepresentation.compareTo​(otherStringRepresentation);
      
      return result;
   }
   */
   @Override
   public final int compareTo(Contact other) {
      Objects.requireNonNull(other);
      
      String stringRepresentation = toString();
      String otherStringRepresentation = other.toString();
      int result = stringRepresentation.compareTo​(otherStringRepresentation);
      
      return result;
   }
   
   public static class ContactComparator implements Comparator<Contact> {
      @Override
      public final int compare(Contact first, Contact second) {
         Objects.requireNonNull(first);
         Objects.requireNonNull(second);
         
         int result = first.compareTo​(second);
         
         return result;
      }
   }
}
