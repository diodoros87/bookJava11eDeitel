/* =====================================================================================
 *       Filename:  PrintingStarTriangles.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.15 - Printing triangles using asterisks and
                                                spaces
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class PrintingStarTriangles {
   
   public static void main(String[] args) {
      int counter;
      char asterisk = '*';
      final int lineLength = 10;
      
      for (counter = 1; counter <= lineLength; ++counter) {                                     
         printCharacters(asterisk, counter, true);       
      } 
      System.out.println();
      
      for (counter = lineLength; counter >= 1; --counter) {                                     
         printCharacters(asterisk, counter, true);       
      }
      System.out.println();
      
      char space = ' ';
      
      for (counter = lineLength; counter >= 1; --counter) {
         printCharacters(space, lineLength - counter, false);
         printCharacters(asterisk, counter, true);       
      }
      System.out.println();    
      
      for (counter = 1; counter <= lineLength; ++counter) {
         printCharacters(space, lineLength - counter, false);
         printCharacters(asterisk, counter, true);       
      }
      System.out.println(); 
   } 
   
   public static void printCharacters(char character, int width, boolean printNextLine) {
   
      for (int counter = 1; counter <= width; ++counter) {                                     
         System.out.print(character);       
      }     
      
      if (true == printNextLine)
         System.out.println();
   }
   
} 
