/* =====================================================================================
 *       Filename:  PieceWorker.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 9.15 - PieceWorker class inherits from 
                                Employee
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public class PieceWorker extends Employee {
   private static final int MIN_PIECES = 0;
   
   private static final double MIN_WAGE = 0;
              
   private int pieces    = MIN_PIECES;      
   private double wage   = MIN_WAGE;

   public PieceWorker(String firstName, String lastName, String socialSecurityNumber,
                           int pieces, double wage) {
      super(firstName, lastName, socialSecurityNumber);
      
      validatePieces(pieces);
      validateWage(wage);
       
      this.pieces = pieces;
      this.wage = wage;
   } 
   
   public static void validatePieces(int pieces) {     
      if (pieces < MIN_PIECES) {
         throw new IllegalArgumentException(String.format(
            "Requirement: %d <= 'working pieces'", MIN_PIECES));
      }
   }
   
   public static void validateWage(double wage) {     
      if (wage < MIN_WAGE) {
         throw new IllegalArgumentException(String.format(
            "Requirement: %.2f <= 'wage for piece'", MIN_WAGE));
      }
   }

   public void setPieces(int pieces) {
      validatePieces(pieces);   
      this.pieces = pieces;
   } 

   public int getPieces() {return pieces;}

   public void setWage(double wage) {
      validateWage(wage);
      this.wage = wage;
   } 

   public double getWage() {return wage;}

   public double earnings() {  
      return getWage() * getPieces();
   } 

   @Override 
   public String toString() {
      return String.format("%s %s%n%s: %,d%n%s: %.2f", 
         "piece worker", super.toString(),
         "working pieces", getPieces(), 
         "wage for piece", getWage());
   } 
} 
