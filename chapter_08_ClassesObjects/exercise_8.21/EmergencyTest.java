/* =====================================================================================
 *       Filename:  EmergencyTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.21 - test of Emergency objects
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.ArrayList;

public class EmergencyTest {
   public static void main(String[] args) {
      Emergency emergency = DataTestCreating.createEmergency();
      System.out.println(emergency);
      
      Emergency emergency_1 = DataTestCreating.createEmergency_1();
      System.out.println(emergency_1);
   }
   
}

class DataTestCreating {
   static Person createNotifier() {
      String firstName = "Nicolaus";
      String lastName = "Copernicus";
      final Date BIRTH_DATE = new Date (12, 31, 1977);
      Address address = createNotifierAddress();
      String telephone = "1234567";
      
      Person notifier = new Person(firstName, lastName, BIRTH_DATE, address, telephone);
      
      return notifier;
   }
   
   static Address createNotifierAddress() {
      String country = "Poland";
      String province = "Lesser Poland";
      String town = "Cracow";
      String zip = "001-95";
      String street = "University 14C";
      
      Address address = new Address(country, province, town, zip, street);
      
      return address;
   }
   
   static Incident createIncident() {
      DateAndTime dateAndTime = new DateAndTime(new Date (3, 14, 2007), new Time(6, 39, 23));
      Address address = createIncidentAddress();
      String description = "battle in forest";
      
      Incident incident = new Incident(dateAndTime, address, description);
      
      return incident;
   }
   
   static Address createIncidentAddress() {
      String country = "Teutonic Order";
      String province = "Prussia";
      String town = "Konigsberg";
      String zip = "345";
      String street = "Forest 25B";
      
      Address address = new Address(country, province, town, zip, street);
      
      return address;
   }
   
   static Notification createNotification(Person notifier, Incident incident) {
      DateAndTime dateAndTime = new DateAndTime(new Date (4, 24, 2007), new Time(16, 9, 3));
      Address address = createNotificationAddress();
      String telephone = notifier.getTelephone();
      Notification notification;
      
      try {
         notification = new Notification(dateAndTime, address, telephone, null, null);
      } catch (NullPointerException exception) {
         System.err.println(exception.getMessage());
         exception.printStackTrace();   
      }
      
      try {
         notification = new Notification(dateAndTime, address, "", notifier, incident);
      } catch (IllegalArgumentException exception) {
         System.err.println(exception.getMessage());
         exception.printStackTrace();   
      }
      
      notification = new Notification(dateAndTime, address, telephone, notifier, incident);
      
      return notification;
   }
   
   static Address createNotificationAddress() {
      String country = "Poland";
      String province = "Greater Poland";
      String town = "Poznan";
      String zip = "1234-56";
      String street = "Castle 4";
      
      Address address = new Address(country, province, town, zip, street);
      
      return address;
   }
   
   static Response createResponse() {
      ArrayList<ResponseType> responseTypes = new ArrayList<>();        
      ResponseStatus responseStatus = ResponseStatus.IN_PROGRESS;
      
      responseTypes.add(ResponseType.AMBULANCE);
      responseTypes.add(ResponseType.FIRE);
      responseTypes.add(ResponseType.POLICE);
      responseTypes.add(ResponseType.POLICE);
      
      Response response;
      
      try {
         response = new Response(responseTypes, responseStatus);
      } catch (IllegalArgumentException exception) {
         System.err.println(exception.getMessage());
         exception.printStackTrace();   
      }
      
      responseTypes.remove(ResponseType.POLICE);
      response = new Response(responseTypes, responseStatus);
      
      return response;
   }
   
   static Emergency createEmergency() {
      Person notifier = createNotifier();
      Incident incident = createIncident();
      
      Notification notification = createNotification(notifier, incident);
      Response response = createResponse();
      
      Emergency emergency = new Emergency(notification, response);
      
      return emergency;
   }
   
   static Emergency createEmergency_1() {
      Person notifier = createNotifier();
      Incident incident_1 = createIncident_1();
      Notification notification = createNotification(notifier, incident_1);
      
      Response response = createResponse();
      response.setResponseTypes(ResponseType.AMBULANCE, ResponseType.POLICE);
      Emergency emergency = new Emergency(notification, response);
      
      return emergency;
   }
   
   static Incident createIncident_1() {
      Incident incident = createIncident();
      Address incidentAddress = incident.getAddress();
      incidentAddress.setCountry("Poland");
      incidentAddress.setProvince("Pomerania");
      incidentAddress.setTown("Torun");
      incidentAddress.setZip("4567-7787");
      incidentAddress.setStreet("Castle  ");
      incident.setDescription("stealing money");
      
      Address incidentAddress_1 = incident.getAddress();
      incidentAddress_1.set(incidentAddress);
      System.out.printf("%n Address incidentAddress_1 after set on incidentAddress: " + incidentAddress_1 + "%n");
      
      Address incidentAddress_2 = new Address(incidentAddress_1);
      System.out.printf("%n Address incidentAddress_2 after construct based on incidentAddress_1: " + incidentAddress_2 + "%n");
      
      incident.setDateAndTime(new DateAndTime(new Date(10, 25, 2010), new Time(0, 6, 5)));
      incident.setAddress(incidentAddress_1);
      incident.setDescription("stealing money");
       
      return incident;
   }
}
