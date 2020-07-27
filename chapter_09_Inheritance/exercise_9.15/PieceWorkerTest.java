/* =====================================================================================
 *       Filename:  PieceWorkerTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.15 - PieceWorker class test program
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class PieceWorkerTest {
   public static void main(String[] args) { 
      
      PieceWorker employee = new PieceWorker(
         "Philip", "August", "222-22-2222", 5787, 100);       
      
      System.out.println("Piece worker information obtained by get methods:");
      System.out.printf("%n%s %s%n", "First name is",
         employee.getFirstName());
      System.out.printf("%s %s%n", "Last name is", 
         employee.getLastName());
      System.out.printf("%s %s%n", "Social security number is", 
         employee.getSocialSecurityNumber());
      System.out.printf("%s %,d%n", "Working pieces are", 
         employee.getPieces());
      System.out.printf("%s %.2f%n", "Wage rate is",
         employee.getWage());
      System.out.printf("%s %,.2f%n", "Earnings is",
         employee.earnings());

      employee.setPieces(2000);  
      employee.setWage(0.1);
      
      System.out.printf("%n%s:%n%n%s%n",                                
         "Updated employee information obtained by toString", employee);
      System.out.printf("%s %,.2f%n", "Earnings is",
         employee.earnings());
   } 
} 
