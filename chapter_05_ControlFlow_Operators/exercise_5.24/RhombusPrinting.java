/* =====================================================================================
 *       Filename:  RhombusPrinting.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.24 - Printing rhombus using asterisks and
                                                spaces
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class RhombusPrinting {
   
   public static void main(String[] args) {
      int counter;
      char asterisk = '*';
      char space = ' ';
      final int diagonalHalfLength = 8;
      
      for (counter = 1; counter <= diagonalHalfLength; ++counter) {  
         printCharacters(space, diagonalHalfLength - counter, false);
         printCharacters(asterisk, counter, false); 
         
         printCharacters(asterisk, counter - 1, true);
      }
         
      for (counter = diagonalHalfLength - 1; counter >= 1; --counter) {     
         printCharacters(space, diagonalHalfLength - counter, false);
         printCharacters(asterisk, counter, false);
         
         printCharacters(asterisk, counter - 1, true);  
      } 

   } 
   
   public static void printCharacters(char character, int width, boolean printNextLine) {
   
      for (int counter = 1; counter <= width; ++counter) {                                     
         System.out.print(character);       
      }     
      
      if (true == printNextLine)
         System.out.println();
   }
   
} 
