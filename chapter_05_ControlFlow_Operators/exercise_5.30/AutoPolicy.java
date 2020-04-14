/* =====================================================================================
 *       Filename:  AutoPolicy.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.30 - Class that represents an auto insurance 
                                policy
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
*/

public class AutoPolicy {
   private int accountNumber; // policy account number
   private String makeAndModel; // car that the policy applies to
   private String state; // two-letter state abbreviation

   // constructor
   public AutoPolicy(int accountNumber, String makeAndModel, String state) {
      this.accountNumber = accountNumber;
      this.makeAndModel = makeAndModel;
      
      if (state == "MA" || state == "NJ" || state == "NY" || state == "PA" ||
            state == "ME" || state == "NH" || state == "VT" || state == "CT") {
            
         this.state = state;
      }
      else {
         this.state = "";
         System.err.println("Unrecognized state of: " + state);
      }
   }

   // sets the accountNumber
   public void setAccountNumber(int accountNumber) {
      this.accountNumber = accountNumber;
   }

   // returns the accountNumber
   public int getAccountNumber() {
      return accountNumber;
   } 

   // sets the makeAndModel
   public void setMakeAndModel(String makeAndModel) {
      this.makeAndModel = makeAndModel;
   }

   // returns the makeAndModel
   public String getMakeAndModel() {
      return makeAndModel;
   } 

   // sets the state
   public void setState(String state) {
      if (state == "MA" || state == "NJ" || state == "NY" || state == "PA" ||
            state == "ME" || state == "NH" || state == "VT" || state == "CT") {
            
         this.state = state;
      }
      else {
         System.err.println("Unrecognized state of: " + state);
      }
   }

   // returns the state
   public String getState() {
      return state;
   }

   // predicate method returns whether the state has no-fault insurance
   public boolean isNoFaultState() {
      boolean noFaultState; 

      // determine whether state has no-fault auto insurance             
      switch (getState()) { // get AutoPolicy object's state abbreviation
         case "MA": case "NJ": case "NY": case "PA":                     
            noFaultState = true;                                         
            break;                                                       
         default:                                                        
            noFaultState = false;                                                                                              
      }                                                                  

      return noFaultState;
   } 
} 
 
