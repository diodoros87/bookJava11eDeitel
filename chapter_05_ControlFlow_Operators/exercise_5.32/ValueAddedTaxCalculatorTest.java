/* =====================================================================================
 *       Filename:  ValueAddedTaxCalculatorTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.32 - value added tax calculator
                                                tested by User 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;

public class ValueAddedTaxCalculatorTest {

   public static void main(String[] args) {
      System.out.println("****** This program calculate value-added tax which is levied on the price of product or service");
      System.out.println("according to prices entered by User.");
      System.out.println(ValueAddedTaxCalculator.questionsNumberInfo);
      
      ValueAddedTaxCalculator calculator = new ValueAddedTaxCalculator();
      boolean isProgramContinue = true;
      
      for (short questionCounter = 1; 
           questionCounter <= ValueAddedTaxCalculator.numberOfQuestions  && isProgramContinue; 
                                    questionCounter++) {
                                    
         isProgramContinue = skipToNextStage();
         if (true == isProgramContinue) {
            isProgramContinue = calculateTaxesValues(calculator, questionCounter);
         }
      }
   } 
   
   public static boolean calculateTaxesValues(ValueAddedTaxCalculator calculator, short questionNumber) {
   
      double cost = getCostFromUser(questionNumber);
      
      if (0 <= cost) {
         displayTaxes(calculator, questionNumber, cost);
         displayTotalTaxes(calculator);
         return true;
      }
      
      return false;
   }
   
   public static double getCostFromUser(short questionNumber) {
      System.out.printf("%n%n##################       Question number %d:%n", questionNumber);
      System.out.printf("%nTo quit enter number less than zero%n");
      
      return GettingDataFromStandardInput.getDouble(ValueAddedTaxCalculator.getQuestion(questionNumber));
   }
   
   public static void displayTaxes(ValueAddedTaxCalculator calculator, short questionNumber, double cost) {
      System.out.println("*** Value added tax:");
      System.out.printf("nowadays is %.2f \t after changes is %.2f%n", 
                           calculator.calculateValueAddedTax(questionNumber, cost, false), 
                           calculator.calculateValueAddedTax(questionNumber, cost, true));
   }
   
   public static void displayTotalTaxes(ValueAddedTaxCalculator calculator) {
      System.out.println("*** Total value added tax:");
      System.out.printf("nowadays is %.2f \t after changes is %.2f%n", 
                           calculator.getTotalValueAddedTaxNowadays(), 
                           calculator.getTotalValueAddedTaxAfterChanges());
   }
   
   public static boolean skipToNextStage() {
      String quitProgram = "Q";
      String string = GettingDataFromStandardInput.getString(String.format
                              ("%n %s %s or only %s to quit %n", "***** To continue press ENTER or only", 
                                       quitProgram, quitProgram.toLowerCase()));
      if (true == quitProgram.equalsIgnoreCase(string)) {
         return false;
      }
      
      return true;
   }
   
} 
