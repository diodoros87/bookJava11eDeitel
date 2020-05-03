/* =====================================================================================
 *       Filename:  SalaryRangesCounter.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 7.10 - printing frequency of salaries
                                  according to 11 ranges 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class SalaryRangesCounter {

   public static final int    BASE_SALARY = 2000;
   public static final double SALES_PERCENT = 0.09;
   
   public static final int    NUMBER_OF_RANGES = 11;
   public static final int    SIZE_OF_RANGE = 100;

   public static int generateOfSalaries(final int[] salaries, boolean isAssigning) {
      int index = 0;
      int commission;
      
      for (int sales = 0; sales <= 14_000; index++, sales += 500) {
         if (true == isAssigning && index < salaries.length) {
            commission = (int)(sales * SALES_PERCENT);
            salaries[index] = BASE_SALARY + commission;
         }
      }
      
      return index;
   }
   
   public static void main(String[] args) { 
      System.out.printf("*** This program prints frequency of salaries according to %d ranges %n", NUMBER_OF_RANGES);
      
      final int [] TEMPORARY_ARRAY_ONLY_TO_GENERATE_SALARIES = new int [1];
      final int NUMBER_OF_GENERATED_SALARIES = generateOfSalaries(TEMPORARY_ARRAY_ONLY_TO_GENERATE_SALARIES, false);
      int [] salaries = new int [NUMBER_OF_GENERATED_SALARIES];
      generateOfSalaries(salaries, true);
      
      printArray(salaries, "Values of salaries have been printed below: ");
      
      int[] rangeFrequency = new int[NUMBER_OF_RANGES];
      
      for (int index = 0; index < NUMBER_OF_GENERATED_SALARIES; index++) {
         ++rangeFrequency[ getSalaryAsIndex(salaries[index]) ];
      }
      
      System.out.printf("%nFrequency of salaries according to %d ranges has been printed below: %n", NUMBER_OF_RANGES);
      System.out.printf("%20s %20s%n", "Range", "Frequency");
      for (int index = 0; index < NUMBER_OF_RANGES; index++) {
         System.out.printf("%20s %20d %n", getFormattedRange(index), rangeFrequency[index]);
      }
         
   }
   
   public static String getFormattedRange(int index) {
      if (index < 0 || index >= NUMBER_OF_RANGES) {
         throw new IllegalArgumentException(String.format("requirement of index's value: index > 0 and index < %d%n", 
                                             NUMBER_OF_RANGES));
      }
      
      int rangeBegin = BASE_SALARY + index * SIZE_OF_RANGE;
      String string = String.format("%d", rangeBegin);
      
      if (index < NUMBER_OF_RANGES - 1) {
         int rangeEnd   = rangeBegin + SIZE_OF_RANGE - 1;
         string += String.format(" - %d", rangeEnd); 
      }
      else {
         string += " and more";
      }
      
      return string;
   }
   
   public static int getSalaryAsIndex(int salary) {
      int index = (salary - BASE_SALARY) / SIZE_OF_RANGE;
      
      if (index < 0) {
         throw new IllegalArgumentException("value of index can not be less than zero");
      }
      
      if (index >= NUMBER_OF_RANGES) {
         return NUMBER_OF_RANGES - 1;
      }
      else {
         return index;
      }
   }

   public static void printArray(final int[] ARRAY, final String INFO) {
      System.out.println(INFO);
      final int ARRAY_LENGTH = ARRAY.length;
      
      for (int index = 0; index < ARRAY_LENGTH; index++) {
         System.out.printf("%2d. %8d%n", index + 1,  ARRAY[index]);
      }
      
      System.out.println();
   }
   
} 
