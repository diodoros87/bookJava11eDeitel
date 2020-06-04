/* =====================================================================================
 *       Filename:  Polling.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.40 - class of polling about social topics
                                importance
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

 import java.util.ArrayList;

public class Polling {
   public static final String[] TOPICS = {"Economy", "Ecology", "Science", "Politics", "Law"};
   public static final int TOPICS_NUMBER = TOPICS.length;
   private static final int NO_TOPIC = -99;
   
   public static final int MIN_RATING = 1;
   public static final int MAX_RATING = 10;
   
   private int[][] responses = new int[TOPICS_NUMBER][MAX_RATING  + 1];
   
   private int maxRating;
   private int minRating;
   
   public void resetResponses() {
      for (int[] topicRatingsFrequency : responses)
         java.util.Arrays.fill(topicRatingsFrequency, 0);
   } 
   
   public boolean setRating(String topic, int rating) {
      int topicIndex = getArrayIndex(TOPICS, topic);
      
      if (NO_TOPIC == topicIndex) {
         System.err.printf("Unrecognized topic \'%s\'%n", topic);
         return false;
      }
      if (MIN_RATING > rating || MAX_RATING < rating) {
         System.err.printf("%nRating must be in range from %d to %d%n", MIN_RATING, MAX_RATING);
         return false;
      }
      
      responses[topicIndex][rating]++;
      return true;
   }
   
   public static int getArrayIndex(String[] array, String string) {
      int index = 0;
      
      for (String element : array) {
         if (true == string.equalsIgnoreCase(element)) {
            return index;
         }
         
         index++;
      }
      
      return NO_TOPIC;
   }

  // perform various operations on the data
   public void processResponses() {
      // output responses array
      outputResponses();
      
      ArrayList<String> minRatingTopics = getMinRatingTopics();
      ArrayList<String> maxRatingTopics = getMaxRatingTopics();
      
      printExtremums(minRatingTopics, maxRatingTopics);
   }
   
   private void printExtremums(ArrayList<String> minRatingTopics, ArrayList<String> maxRatingTopics) {
      System.out.printf("%nLowest rating in the responses is %d for: %n", this.minRating);
      printListElements(minRatingTopics);
      
      System.out.printf("%nHighest rating in the responses is %d for: %n", this.maxRating);
      printListElements(maxRatingTopics);
   }
   
   private void printListElements(ArrayList<String> list) {
      for (String string : list) {
         System.out.println(string); 
      }
   }
   
   private ArrayList<String> getMinRatingTopics() {
      int topicRatingFrequency;
      boolean minimumFinding = false;
      ArrayList<String> minRatingTopics = new ArrayList<>();
      
      for (int rating = MIN_RATING; false == minimumFinding && rating <= MAX_RATING; rating++) {
         for (int topicIndex = 0; topicIndex < TOPICS_NUMBER; topicIndex++) {
         
            topicRatingFrequency = responses[topicIndex][rating];
            if (topicRatingFrequency > 0) { 
               minimumFinding = true;
               minRatingTopics.add(TOPICS[topicIndex]); 
               this.minRating = rating;
            }
         }
      }
      
      return minRatingTopics;
   }
   
   private ArrayList<String> getMaxRatingTopics() {
      int topicRatingFrequency;
      boolean maximumFinding = false;
      ArrayList<String> maxRatingTopics = new ArrayList<>();
      
      for (int rating = MAX_RATING; false == maximumFinding && rating >= MIN_RATING; rating--) {
         for (int topicIndex = 0; topicIndex < TOPICS_NUMBER; topicIndex++) {
         
            topicRatingFrequency = responses[topicIndex][rating];
            if (topicRatingFrequency > 0) { 
               maximumFinding = true;
               maxRatingTopics.add(TOPICS[topicIndex]);  
               this.maxRating = rating;
            }
         }
      }
      
      return maxRatingTopics;
   }

   // determine average rating for particular set of responses
   private double getAverage(int[] topicRatingsFrequency) {          
      int totalFrequency = 0;
      int totalRatings = 0;
                                                                                
      for (int rating = MIN_RATING; rating < topicRatingsFrequency.length; rating++) {
         totalFrequency += topicRatingsFrequency[rating] * rating;  
         totalRatings   += topicRatingsFrequency[rating];
      }                                                   
                                                          
      return (double) totalFrequency / totalRatings;   
   }                               

   // output the contents of the responses array
   public void outputResponses() {
      System.out.printf("The responses are:%n%n");
      System.out.printf("%12s", ""); // align column heads

      // create a column heading for each of the ratings
      for (int rating = MIN_RATING; rating < responses[0].length; rating++) {
         System.out.printf("%12s", String.format("  Rating %d", rating));
      } 

      System.out.printf("%12s%n", "Average"); // topic average column heading

      // create rows/columns of text representing array responses
      for (int topic = 0; topic < responses.length; topic++) {
         System.out.printf("%12s", TOPICS[topic]);

         for (int rating = MIN_RATING; rating < responses[topic].length; rating++) {
            System.out.printf("%12d", responses[topic][rating]);  
         } 

         // call method getAverage to calculate topic's average rating;
         // pass row of responses as the argument to getAverage
         double average = getAverage(responses[topic]);
         System.out.printf("%12.2f%n", average);
      } 
   } 
} 
