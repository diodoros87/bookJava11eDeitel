/* =====================================================================================
 *       Filename:  Response.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.21 - class to describe response to notification
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.util.ArrayList;

public class Response {    
   private ArrayList<ResponseType> responseTypes = new ArrayList<>();        
   private ResponseStatus responseStatus;
   
   public Response(ArrayList<ResponseType> responseTypes, ResponseStatus responseStatus) {
      ValidateParameters.checkNullPointer(responseStatus);
      
      Response.setResponseTypes(this.responseTypes, responseTypes, responseTypes.size());
      this.responseStatus = responseStatus;
   } 
   
   public Response(ResponseStatus responseStatus, ResponseType... responseTypes) {
      ValidateParameters.checkNullPointer(responseStatus);
      
      Response.setResponseTypes(this.responseTypes, responseTypes);
      this.responseStatus = responseStatus;
   } 
   
   public static void setResponseTypes(ArrayList<ResponseType> destination, ArrayList<ResponseType> source, final int ELEMENTS) {
      if (ELEMENTS < 0) {
         throw new IllegalArgumentException(" ELEMENTS < 0 ");
      }
      ValidateParameters.checkNullPointer(destination, source);
      
      destination.clear();
      for (int index = 0; index < source.size() && index < ELEMENTS; index++) {
         ResponseType element = source.get(index);
         ValidateParameters.checkNullPointer(element);
         
         if (true == destination.contains(element)) {
            throw new IllegalArgumentException(" every type of response must be unique ");
         }
         
         destination.add(element);
      }
   }
   
   public static void setResponseTypes(ArrayList<ResponseType> destination, ResponseType...  source) {
      ValidateParameters.checkNullPointer(destination, source);
      
      destination.clear();
      for (int index = 0; index < source.length; index++) {
         ResponseType element = source[index];
         ValidateParameters.checkNullPointer(element);
         
         if (true == destination.contains(element)) {
            throw new IllegalArgumentException(" every type of response must be unique ");
         }
         
         destination.add(element);
      }
   }
   
   public ArrayList<ResponseType> getResponseTypes() {
      return responseTypes;
   }
   
   public void setResponseTypes(ArrayList<ResponseType> source) {
      Response.setResponseTypes(this.responseTypes, source, source.size());
   }
   
   public void setResponseTypes(ResponseType... source) {
      Response.setResponseTypes(this.responseTypes, source);
   }
   
   public ResponseStatus getResponseStatus() {
      return responseStatus;
   }
   
   public void setResponseStatus(ResponseStatus responseStatus) {
      ValidateParameters.checkNullPointer(responseStatus);
      
      this.responseStatus = responseStatus;
   }
   
   @Override
   public String toString() {
      String responseData = "Responses : ";
      
      for (ResponseType item : responseTypes) {
         responseData += "  " + item;
      }
      
      responseData += String.format("%n Status of responses: %s ", getResponseStatus());
         
      return responseData;
   }
}
