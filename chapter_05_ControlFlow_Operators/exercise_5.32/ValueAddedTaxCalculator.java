/* =====================================================================================
 *       Filename:  ValueAddedTaxCalculator.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.32 - class of value added tax calculator
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class ValueAddedTaxCalculator {

   public static final short numberOfQuestions = 6;
   public static final String questionsNumberInfo = String.format(" In this form are %d questions ", numberOfQuestions);

   private double totalValueAddedTaxAfterChanges = 0;
   private double totalValueAddedTaxNowadays = 0;
   
   public double getTotalValueAddedTaxAfterChanges () {
      return totalValueAddedTaxAfterChanges;
   }
   
   public double getTotalValueAddedTaxNowadays () {
      return totalValueAddedTaxNowadays;
   }
   
   public double calculateValueAddedTax (short questionNumber, double cost, boolean afterTaxRateChanges) {
      if (cost < 0) {
         System.err.println("Cost can not be less than zero ");
         return -1;
      }
      
      double taxRate = 0;
      double taxValue = 0;

      switch (questionNumber) {
         case 1:
            taxRate = 0.05;
            break;
         case 2:
         case 5:
            taxRate = afterTaxRateChanges ? 0.2 : 0.08;
            break;
         case 3:
            taxRate = afterTaxRateChanges ? 0.2 : 0.05;
            break;
         case 4:
         case 6:
            taxRate = afterTaxRateChanges ? 0.2 : 0.23;
            break;
         default:
            System.out.println("No question with number " + questionNumber);
      }
      
      taxValue = taxRate * cost;

      if (true == afterTaxRateChanges) {
         totalValueAddedTaxAfterChanges += taxValue;
      }
      else {
         totalValueAddedTaxNowadays += taxValue;
      }
      
      return taxValue;
   }
   
   public static String getQuestion (short questionNumber) {
      
      String question = "Enter cost of ";
      
      switch (questionNumber) {
         case 1:
            question += "food";
            break;
         case 2:
            question += "home renovation";
            break;
         case 3:
            question += "books";
            break;
         case 4:
            question += "clothes";
            break;
         case 5:
            question += "firewood"; 
            break;
         case 6:
            question += "programming services"; 
            break;
         default:
            System.err.println("No question with number " + questionNumber);
      }
      
      return question += ": ";
      
   }

}
