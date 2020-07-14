/* =====================================================================================
 *       Filename:  ResponseType.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.21 - type of response to notification
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public enum ResponseType {
   POLICE("police"),
   AMBULANCE("ambulance"),
   FIRE("fire");
   
   private String name;
   
   private ResponseType(String name) {
      this.name = name;
   }
   
   @Override
   public String toString() {
      return name;
   }
} 

