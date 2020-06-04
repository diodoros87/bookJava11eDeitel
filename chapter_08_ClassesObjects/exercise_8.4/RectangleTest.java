/* =====================================================================================
 *       Filename:  RectangleTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.4 - test of rectangle class
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class RectangleTest {

   public static void main(String[] args) {
      Rectangle defaultRectangle = new Rectangle();
      printRectangle("defaultRectangle after construct:", defaultRectangle);
      defaultRectangle.setWidth(15.6); 
      printRectangle("defaultRectangle after width's change", defaultRectangle);
      
      Rectangle rectangle = new Rectangle(12.5, 4.8);  
      printRectangle("rectangle after construct: ", rectangle);
      rectangle.setLength(4.56);
      printRectangle("rectangle after length's change", rectangle);
      
      
      invalidConstruct();
      invalidSetSize(defaultRectangle);
      invalidSetSize(rectangle);
   } 

   private static void printRectangle(String header, Rectangle rectangle) {
      System.out.println(header);
      System.out.println(rectangle);
      System.out.println();
   } 
   
   private static void invalidConstruct() {
      try {
         Rectangle invalidRectangle = new Rectangle(-12.5, 4.8); // invalid value
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while initializing rectangle: %s%n",
            e.getMessage());
      } 
      
      try {
         Rectangle invalidRectangle = new Rectangle(12.5, 0); // invalid value
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while initializing rectangle: %s%n",
            e.getMessage());
      } 
      
      try {
         Rectangle invalidRectangle = new Rectangle(32.5, 10); // invalid value
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while initializing rectangle: %s%n",
            e.getMessage());
      } 
   } 
   
   private static void invalidSetSize(Rectangle rectangle) {
      try {
         rectangle.setLength(-14.8); // invalid value
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while set size of rectangle: %s%n",
            e.getMessage());
            
         printRectangle("Current data of rectangle:", rectangle);
      } 
      
      try {
         rectangle.setWidth(54.8); // invalid value
      } 
      catch (IllegalArgumentException e) {
         System.out.printf("%nException while set size of rectangle: %s%n",
            e.getMessage());
            
         printRectangle("Current data of rectangle:", rectangle);
      } 
   } 
} 
