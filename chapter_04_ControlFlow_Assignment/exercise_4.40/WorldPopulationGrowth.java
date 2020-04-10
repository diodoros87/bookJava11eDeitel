/* =====================================================================================
 *       Filename:  WorldPopulationGrowth.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.40 - calculate world's population growth
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */ 

public class WorldPopulationGrowth {   

   public static void main(String[] args) {
   
      final long currentWorldPopulation = 7_800_000_000L;
      final double growthRate    = 0.012;
      
      final short startYear      = 2020;
      short nextYear             = startYear + 0;   
      final short finalYear      = startYear + 74;
      short doubledPopulationYear = 0;
      
      long estimatedWorldPopulation = currentWorldPopulation; 
      double populationIncreasePercent = 0;
      
      System.out.printf("Projection of world population:%n%n");
      System.out.printf("%5s %20s \t %30s %d %n", "Year" ,"World's population", "population's % change with year", startYear);
      
      while (nextYear <= finalYear) {
         
         System.out.printf("%5d %,20d \t %30.5f %n", nextYear ,estimatedWorldPopulation, populationIncreasePercent);
         
         estimatedWorldPopulation += growthRate * estimatedWorldPopulation;

         populationIncreasePercent = 100.0 * estimatedWorldPopulation / currentWorldPopulation - 100;
         
         nextYear++;
         
         if (100 <= (int)populationIncreasePercent && 0 == doubledPopulationYear) {
            doubledPopulationYear = nextYear;
         }
         
         System.out.println("");
      }
      
      if (doubledPopulationYear <= finalYear) {
         System.out.printf("According to above projection, world's population will increase by not less than 100%% in %d year%n%n",
                                    doubledPopulationYear);
      }
		
   }
   
}
