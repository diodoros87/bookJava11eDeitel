/* =====================================================================================
 *       Filename:  CrapsStatisticsTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.18 - test of statistics of results 
                                generated during games simulations
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class CrapsStatisticsTest {
   
   public static void main(String[] args) {
      Craps craps = new Craps();
      CrapsStatistics statistics = new CrapsStatistics();
      
      statistics.setSimulationsNumber(1_000_000);
      statistics.run(craps);
      statistics.printResultsInTurns();
      statistics.printOverallStatistics();
      statistics.printStatisticsAfterTurns();
   } 
   
}
