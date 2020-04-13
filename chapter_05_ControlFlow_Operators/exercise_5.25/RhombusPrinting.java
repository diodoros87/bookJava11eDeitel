/* =====================================================================================
 *       Filename:  RhombusPrinting.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.25 - Printing of rhombus using asterisks and
                                                spaces according to number of rows in 
                                                rhombus entered by User
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import standardInputDataPackage.GettingDataFromStandardInput;
 
public class RhombusPrinting {
   
   public static void main(String[] args) {
      final short minSideLength = 1;
      final short maxSideLength = 19;
      final String prompt = "To quit enter sequence other than integer. Enter number of rows in rhombus: ";
      
      System.out.printf("*** This program displays rhombus using asterisks and spaces.%n");

      short rows;
      boolean isDisplayRhombus = false; 

      while (true) {
         isDisplayRhombus = false;
         
         rows = GettingDataFromStandardInput.getShortInteger(prompt);
         if (1 == rows % 2 && minSideLength <= rows && rows <= maxSideLength) {
            isDisplayRhombus = displayRhombus(rows, ' ', '*');
         }
         
         if (false == isDisplayRhombus) {
            System.err.printf("Program displays rhombus for an odd integer which is number of rows in rhombus between not less than %d ",
                                 minSideLength);
            System.err.printf("and not greater than %d %n", maxSideLength);
         }
      }

   } 
   
   public static boolean displayRhombus(short rows, char outside, char inside) {
      if (rows < 1 || rows % 2 == 0)
         return false;
         
      short diagonalHalfLength = (short)(rows / 2 + 1);
      int counter;
      
      for (counter = 1; counter <= diagonalHalfLength; ++counter) {  
         printCharacters(outside, diagonalHalfLength - counter, false);
         printCharacters(inside, counter, false); 
         
         printCharacters(inside, counter - 1, true);
      }
         
      for (counter = diagonalHalfLength - 1; counter >= 1; --counter) {     
         printCharacters(outside, diagonalHalfLength - counter, false);
         printCharacters(inside, counter, false);
         
         printCharacters(inside, counter - 1, true);  
      } 
      
      return true;
   }
   
   public static void printCharacters(char character, int width, boolean printNextLine) {
   
      for (int counter = 1; counter <= width; ++counter) {                                     
         System.out.print(character);       
      }     
      
      if (true == printNextLine)
         System.out.println();
   }
   
} 
