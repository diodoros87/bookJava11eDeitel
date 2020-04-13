/* =====================================================================================
 *       Filename:  PrintStarTriangles.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.22 - Printing of triangles using asterisks and
                                                spaces in one level
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class PrintStarTriangles {
   
   public static void main(String[] args) {
      int counter;
      char asterisk = '*';
      char space = ' ';
      final int triangleSideLength = 10;
      
      for (counter = 1; counter <= triangleSideLength; ++counter) {                                     
         printCharacters(asterisk, counter, false); 
         printCharacters(space, triangleSideLength - counter, false);
         
         printCharacters(asterisk, triangleSideLength - counter + 1, false);  
         printCharacters(space, counter - 1, false);
         
         printCharacters(space, counter - 1, false);
         printCharacters(asterisk, triangleSideLength - counter + 1, false);
         
         printCharacters(space, triangleSideLength - counter, false);
         printCharacters(asterisk, counter, true);  
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
