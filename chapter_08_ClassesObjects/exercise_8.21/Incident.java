/* =====================================================================================
 *       Filename:  Incident.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.21 - class to describe incident
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Incident {    
   private DateAndTime dateAndTime;    
   private Address address;        
   private String description;
   
   public Incident(DateAndTime dateAndTime, Address address, String description) {
      ValidateParameters.checkNullPointer(dateAndTime, address);
      ValidateParameters.checkBlankString(description);

      this.dateAndTime = dateAndTime;
      this.address = address;
      this.description = description;
   } 
   
   public DateAndTime getDateAndTime() {
      return dateAndTime;
   }
   
   public void setDateAndTime(DateAndTime dateAndTime) {
      ValidateParameters.checkNullPointer(dateAndTime);
      
      this.dateAndTime.setDate(dateAndTime.getDate());
      this.dateAndTime.setTime(dateAndTime.getTime());
   }
   
   public Address getAddress() {
      return address;
   }
   
   public void setAddress(Address address) {
      this.address.set(address);
   }
   
   public String getDescription() {
      return description;
   }
   
   public void setDescription(String description) {
      ValidateParameters.checkBlankString(description);
      
      this.description = description;
   }
   
   @Override
   public String toString() {
      String incidentData = "Incident's data: ";
      incidentData += String.format("%n %s %n %s %n Description: %s ", 
         getDateAndTime(), getAddress(), getDescription());
         
      return incidentData;
   }
}
