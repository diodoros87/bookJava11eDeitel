/* =====================================================================================
 *       Filename:  DisplayingDataTable.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 4.22 - Displaying data as table
                                                using counter-controlled iteration
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class DisplayingDataTable {

   public static void main(String[] args) {
      short rowsCounter = 0;
      short rows = 6;
      short columnsCounter = 0;
      short columns = 4;
      short factor = 10;
      short multiplicationResult = 1;
      
      while (rowsCounter < rows) { 
         System.out.printf ("%s", rowsCounter == 0 ? "N" : String.format("%d", rowsCounter));
         columnsCounter = 1;
         multiplicationResult = 1;
         while (columnsCounter < columns) {
            multiplicationResult *= factor;
            System.out.printf ("\t %s", rowsCounter == 0 ? 
                                 String.format("%d*N", multiplicationResult) : String.format("%d", rowsCounter * multiplicationResult));
            columnsCounter++;
         }
         System.out.println ("");
         rowsCounter++;
      }

   } 
} 
