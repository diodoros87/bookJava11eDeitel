/* =====================================================================================
 *       Filename:  PollingTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.40 - test of polling about social topics
                                importance
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class PollingTest {
   private static final String QUIT = "q";

   public static void main(String[] args) {
      System.out.printf("*** This program is polling about social topics importance %n");
      
      Polling polling = new Polling();
      
      do {
         setRatings(polling);
         polling.processResponses();
      } while(true == isProcessContinue());
      
   } 
   
   private static boolean isProcessContinue() {
      String processContinue = GettingDataFromStandardInput.getStringWaitingForInput(String.format
                              ("%n %s %s to end process %n", "***** To continue process press ENTER or only", QUIT));

      if (null == processContinue || QUIT.equals(processContinue.toLowerCase())) {
         return false;
      }
      
      return true;
   }
   
   private static void setRatings(Polling polling) {
      int rating;
      boolean correctRating;
      int index = 0;
      
      while (index < Polling.TOPICS_NUMBER) {
         rating = getRating(Polling.TOPICS[index]);
         
         correctRating = polling.setRating(Polling.TOPICS[index], rating);
         if (true == correctRating) {
            index++;
         }
         
      }
   }
   
   private static int getRating(String topic) {
      final String RATING_PROMPT = String.format("Enter rating of importance for %s: ", topic);

      int rating = GettingDataFromStandardInput.getInteger(RATING_PROMPT);
    
      return rating;
   }
} 
