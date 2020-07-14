/* =====================================================================================
 *       Filename:  Person.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.21 - Person class
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Person {
   private String firstName;
   private String lastName;
   private final Date BIRTH_DATE;
   private Address address;
   private String telephone;

   public Person(String firstName, String lastName, final Date BIRTH_DATE, Address address, String telephone) {
      ValidateParameters.checkNullPointer(BIRTH_DATE, address);
      ValidateParameters.checkBlankString(firstName, lastName, telephone);

      this.firstName = firstName;
      this.lastName = lastName;
      this.BIRTH_DATE = BIRTH_DATE;
      this.address = address;
      this.telephone = telephone;
   } 
   
   public String getFirstName() {
      return firstName;
   }
   
   public void setFirstName(String firstName) {
      ValidateParameters.checkBlankString(firstName);
      
      this.firstName = firstName;
   }
   
   public String getLastName() {
      return lastName;
   }
   
   public void setLastName(String lastName) {
      ValidateParameters.checkBlankString(lastName);
      
      this.lastName = lastName;
   }
   
   public Date getBirthDate() {
      return BIRTH_DATE;
   }
   
   public String getTelephone() {
      return telephone;
   }
   
   public void setTelephone(String telephone) {
      ValidateParameters.checkBlankString(telephone);
      
      this.telephone = telephone;
   }
   
   public Address getAddress() {
      return address;
   }
   
   public void setAddress(Address address) {
      this.address.set(address);
   }
   
   @Override
   public String toString() {
      return String.format("First name: %s  Last name: %s %n Address: %s %n Birthdate: %s  Telephone: %s", 
         getFirstName(), getLastName(), getAddress(), getBirthDate(), getTelephone());
   } 
} 
