/* =====================================================================================
 *       Filename:  StarRectangle.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.30 - displaying rectangle using asterisks
                                and spaces
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import standardInputDataPackage.GettingDataFromStandardInput;

public class StarRectangle {

   public static void main(String[] args) {
   
      final short minSideLength = 1;
      final short maxSideLength = 20;
      final String prompt = "To quit enter sequence other than integer. Enter rectangle's";
      
      System.out.printf("*** This program displays rectangle using asterisks and spaces.%n");

      short height = 0; 
      short width  = 0;
      boolean isDisplayRectangle = false; 

      while (true) {
         isDisplayRectangle = false;
         
         height = GettingDataFromStandardInput.getShortInteger(String.format("%s %s", prompt, "height: "));
         if (minSideLength <= height && height <= maxSideLength) {
            width = GettingDataFromStandardInput.getShortInteger(String.format("%s %s", prompt, "width: "));
            if (minSideLength <= width && width <= maxSideLength) {
               isDisplayRectangle = displayRectangle(width, height, "*", " ");
            }
         }
         
         if (false == isDisplayRectangle) {
            System.err.printf("Program displays rectangle for side between not less than %d ", minSideLength);
            System.err.printf("and not greater than %d %n", maxSideLength);
         }
      }
   }
      
   public static boolean displayRectangle(short width, short height, String border, String inside) {
      if (width < 1 || height < 1)
         return false;
         
      displayLine(width, border, border);
      
      short rows       = 2;
      while (rows < height) {
         displayLine(width, border, inside);
         rows++;
      }
      
      if (height > 1) {
         displayLine(width, border, border);
      }
      
      return true;
   }
   
   public static boolean displayLine(short length, String border, String inside) {
      if (length < 1)
         return false;
         
      System.out.printf("%s", border);
      
      short columns    = 2;
      while (columns < length) {
         System.out.printf("%s", inside);
         columns++;
      }
      
      if (length > 1) {
         System.out.printf("%s", border);
      }
      
      System.out.printf("%n");
       
      return true;
   }
    
}
