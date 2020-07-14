/* =====================================================================================
 *       Filename:  ResponseStatus.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.21 - status of response to notification
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public enum ResponseStatus {
   FINISHED("finished"),
   NOT_STARTED("not started"),
   IN_PROGRESS("in progress"),
   STOPPED("stopped"),
   CANCELED("canceled");
   
   private String name;
   
   private ResponseStatus(String name) {
      this.name = name;
   }
   
   @Override
   public String toString() {
      return name;
   }
} 

