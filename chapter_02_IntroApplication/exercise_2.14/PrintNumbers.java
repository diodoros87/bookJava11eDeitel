/* =====================================================================================
 *       Filename:  PrintNumbers.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 2.14 - print numbers on screen
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */ 

public class PrintNumbers {   

   public static void main(String[] args) {
   
      System.out.print("mixed mode");                                                                                                                                                                                
      System.out.println("");                                                                                                                                                         
      System.out.print("1 ");                                                                                                                                                         
      System.out.print("2 ");                                                                                                                                                         
      System.out.print("3 ");                                                                                                                                                         
      System.out.print("4 ");                                                                                                                                                         
      System.out.printf("%n");
      
      System.out.println("\n mode a) System.out.println:");  
      System.out.println("1 2 3 4"); 
      
      System.out.println("1 2 " + '3' + " " + 4);
      
      System.out.print("\n mode b) System.out.print:\n"); 
      System.out.print("1 ");                                                                                                                                                         
      System.out.print("2 ");                                                                                                                                                         
      System.out.print("3 ");                                                                                                                                                         
      System.out.print("4 \n");                                                                                                                                                       
                                                                                                                                                                                       
      System.out.printf("%n mode c) System.out.printf:%n"); 
      System.out.printf("1 2 %d %d %n", 3, 4);                                                                                                                                                
      
      System.out.printf("%s %s %c %n", 1, "2 3", '4');

      System.out.printf("%o %x %h %h %n", 1, 2, 3, 4);
      
   }
   
}
