/* =====================================================================================
 *       Filename:  Notification.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.21 - class to describe notification
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Notification {
   private final DateAndTime DATE_TIME;   // notification's date and time       
   private final Address ADDRESS;        // notification's ADDRESS
   private final String TELEPHONE;        // notification's TELEPHONE
   private Person notifier;
   private Incident incident;
   
   public Notification(DateAndTime dateAndTime, final Address ADDRESS, String TELEPHONE, Person notifier, Incident incident) {
      ValidateParameters.checkNullPointer(dateAndTime, ADDRESS, notifier, incident);
      ValidateParameters.checkBlankString(TELEPHONE);

      this.DATE_TIME = dateAndTime;
      this.ADDRESS   = ADDRESS;
      this.TELEPHONE = TELEPHONE;
      this.notifier  = notifier;
      this.incident  = incident;
   } 
   
   public DateAndTime getDateAndTime() {
      return DATE_TIME;
   }
   
   public Address getAddress() {
      return ADDRESS;
   }
   
   public String getTelephone() {
      return TELEPHONE;
   }
   
   public Person getNotifier() {
      return notifier;
   }
   
   public void setNotifier(Person notifier) {
      ValidateParameters.checkNullPointer(notifier);
      
      this.notifier = notifier;
   }
   
   public Incident getIncident() {
      return incident;
   }
   
   public void setIncident(Incident incident) {
      ValidateParameters.checkNullPointer(incident);
      
      this.incident = incident;
   }
   
   @Override
   public String toString() {
      String notificationData = "Notification's data:";
      notificationData += String.format(
      "%n%s  %n Location: %s Telephone: %s %n%n Notifier's personal data': %s %n %s", 
         getDateAndTime(), getAddress(), getTelephone(), getNotifier(), getIncident());
         
      return notificationData;
   } 
}
