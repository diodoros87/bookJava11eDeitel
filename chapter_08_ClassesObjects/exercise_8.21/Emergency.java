/* =====================================================================================
 *       Filename:  Emergency.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.21 - class represents emergency response
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class Emergency {
   private final Notification NOTIFICATION;
   private Response response;

   public Emergency(final Notification NOTIFICATION, Response response) {                                             
      ValidateParameters.checkNullPointer(NOTIFICATION, response);
      
      this.NOTIFICATION = NOTIFICATION;
      this.response = response;
   } 
   
   public Notification getNotification() {
      return NOTIFICATION;
   }

   public void setResponse(Response response) {
      ValidateParameters.checkNullPointer(NOTIFICATION, response);
      
      this.response = response;
   } 
   
   public Response getResponse() {
      return response;
   }

   @Override
   public String toString() {
      String emergencyData = " Emergency's' data: ";
      emergencyData += String.format("%n %s %n %s", getNotification(), getResponse());
      
      return emergencyData;
   }

}
