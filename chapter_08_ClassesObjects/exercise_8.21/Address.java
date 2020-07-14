/* =====================================================================================
 *       Filename:  Address.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.21 - Address class
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Address {
   private String country;
   private String province;
   private String town;
   private String zip;
   private String street;

   public Address(String country, String province, String town, String zip, String street) {
      ValidateParameters.checkBlankString(country, province, town, zip, street);
      
      this.country = country;
      this.province = province;
      this.town = town;
      this.zip = zip;
      this.street = street;
   } 
   
   public Address(Address address) {
      Address.set(this, address);
   } 
   
   public void set(Address address) {
      Address.set(this, address);
   }
   
   public static void set(Address destination, Address source) {
      ValidateParameters.checkNullPointer(source);
      ValidateParameters.checkNullPointer(destination);
      
      destination.setCountry(source.getCountry());
      destination.setProvince(source.getProvince());
      destination.setTown(source.getTown());
      destination.setZip(source.getZip());
      destination.setStreet(source.getStreet());
   }
   
   public String getCountry() {
      return country;
   }
   
   public void setCountry(String country) {
      ValidateParameters.checkBlankString(country);
      
      this.country = country;
   }
   
   public String getProvince() {
      return province;
   }
   
   public void setProvince(String province) {
      ValidateParameters.checkBlankString(province);
      
      this.province = province;
   }
   
   public String getTown() {
      return town;
   }
   
   public void setTown(String town) {
      ValidateParameters.checkBlankString(town);
      
      this.town = town;
   }
   
   public String getZip() {
      return zip;
   }
   
   public void setZip(String zip) {
      ValidateParameters.checkBlankString(zip);
      
      this.zip = zip;
   }
   
   public String getStreet() {
      return street;
   }
   
   public void setStreet(String street) {
      ValidateParameters.checkBlankString(street);
      
      this.street = street;
   }
   
   @Override
   public String toString() {
      return String.format("Country: %s   Province: %s   Town: %s %n Zip: %s  Street: %s", 
         getCountry(), getProvince(), getTown(), getZip(), getStreet());
   } 
} 
