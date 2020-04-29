/* =====================================================================================
 *       Filename:  ArithmeticOperations.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 6.39 - class of arithmetic operations 
                                 of integers (greater or equals zero) 
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import java.security.SecureRandom;

public class ArithmeticOperations {
   
   public static final SecureRandom randomNumbers = new SecureRandom();
   
   public static final int ADDITION = 0;
   public static final int SUBTRACTION = 1;
   public static final int MULTIPLICATION = 2;
   public static final int DIVISION = 3;
   public static final int MIXED = 4;
   
   private boolean nextLevel = false;
   
   private int arithmeticMode = ADDITION;
   private int currentArithmeticOperation = arithmeticMode;
   private int firstElement  = getRandomNumber();
   private int secondElement = getRandomNumber();
   private int arithmeticResult = firstElement + secondElement;
   
   public void setNextLevel(boolean isNextLevel) {
      this.nextLevel = isNextLevel;
      generateNextResult();
   }
   
   public boolean getNextLevel() {
      return this.nextLevel;
   }
   
   public void setArithmeticMode(int arithmeticMode) {
      switch (arithmeticMode) {
         case ADDITION:
         case SUBTRACTION:
         case MULTIPLICATION:
         case DIVISION:
         case MIXED:
            this.arithmeticMode = arithmeticMode;
            if (this.arithmeticMode != MIXED) {
               this.currentArithmeticOperation = this.arithmeticMode;
            }
            
            break;
         default:
            System.err.println("*** ERROR: This instruction should NOT be executed in method setArithmeticMode(int)");
      }
      
      generateNextResult();
   }
   
   public int getArithmeticMode() {
      return this.arithmeticMode;
   }
   
   public int getCurrentArithmeticOperation() {
      return this.currentArithmeticOperation;
   }
   
   public int getFirstElement() {
      return this.firstElement;
   }
   
   public int getSecondElement() {
      return this.secondElement;
   }
   
   public int getArithmeticResult() {
      return this.arithmeticResult;
   }
   
   private boolean validateElements(int firstElement, int secondElement) {
      if (true == isIdentityWithPreviousElements(firstElement, secondElement)) {
         // to eliminate identity with previous elements
         return false;
      }
      
      return setElements(firstElement, secondElement);
   }
   
   
   
   private boolean setElements(int firstElement, int secondElement) {
      if (this.currentArithmeticOperation == SUBTRACTION || this.currentArithmeticOperation == DIVISION) {
         if (secondElement > firstElement) {  
            int variable = firstElement;   // swap elements
            firstElement = secondElement;
            secondElement = variable;
         }
      }
      
      if (this.currentArithmeticOperation == DIVISION) {
         if (0 == secondElement) {
            if (0 == firstElement) { // to eliminate division 0 by 0
               return false;
            }
            else {
               secondElement = firstElement;  // swap elements to eliminate division by 0
               firstElement = 0;
            }
         }
         else if (0 != firstElement % secondElement) {  // firstElement must be multiple of secondElement
            return false;
         }
      }
      
      this.firstElement  = firstElement;
      this.secondElement = secondElement;
      
      return true;
   }
   
   private boolean isIdentityWithPreviousElements(int firstElement, int secondElement) {
      if ((this.firstElement == firstElement || this.secondElement == firstElement) && 
         (this.firstElement + this.secondElement == firstElement + secondElement)) {
         // to eliminate identity with previous elements
         return true;
      }
      
      return false;
   }
   
   private int calculateArithmeticResult() {
      switch (this.currentArithmeticOperation) {
         case ADDITION:
            return  this.firstElement + this.secondElement;
         case SUBTRACTION:
            return  this.firstElement - this.secondElement;
         case MULTIPLICATION:
            return  this.firstElement * this.secondElement;
         case DIVISION:
            return  this.firstElement / this.secondElement;
         default:
            System.err.println("*** ERROR: This instruction should NOT be executed in method calculateArithmeticResult");
            return  -1; // random result
      }
   }
   
   void generateNextResult() {
      if (this.arithmeticMode == MIXED) {
         this.currentArithmeticOperation = randomNumbers.nextInt(4);
      }
      
      do {
      
      } while(false == validateElements(getRandomNumber(), getRandomNumber()));
      
      this.arithmeticResult = calculateArithmeticResult();
   }

   public int getRandomNumber() {
      return this.nextLevel ? 10 + randomNumbers.nextInt(90) : randomNumbers.nextInt(10);
   } 
} 
