/* =====================================================================================
 *       Filename:  AutoPolicyTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 5.30 - Demonstrating Strings using instructions
                                                of switch
                           
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
*/

public class AutoPolicyTest {
   public static void main(String[] args) {

      AutoPolicy policy1 = 
         new AutoPolicy(11111111, "Toyota Camry", "NJ");
      AutoPolicy policy2 = 
         new AutoPolicy(22222222, "Ford Fusion", "ME");

      // display whether each policy is in a no-fault state
      policyInNoFaultState(policy1);
      policy1.setState("State");
      policyInNoFaultState(policy2);
      policy2.setState("PA");
      policyInNoFaultState(policy2);
      
      AutoPolicy policy3 = 
         new AutoPolicy(3, "Honda Concerto", "ER");
      AutoPolicy policy4 = 
         new AutoPolicy(4, "Ford Escort", "qwerty");

      // display whether each policy is in a no-fault state
      policyInNoFaultState(policy3);
      policyInNoFaultState(policy4);
      
      AutoPolicy policy5 = 
         new AutoPolicy(5, "Toyota Corolla", "CT");
      AutoPolicy policy6 = 
         new AutoPolicy(6, "Volswagen Golf", "NH");

      // display whether each policy is in a no-fault state
      policyInNoFaultState(policy5);
      policyInNoFaultState(policy6);
   } 

   // method that displays whether an AutoPolicy 
   // is in a state with no-fault auto insurance 
   public static void policyInNoFaultState(AutoPolicy policy) {
      System.out.println("The auto policy:");
      System.out.printf(
         "Account #: %d; Car: %s;%nState \'%s\' %s a no-fault state%n%n", 
         policy.getAccountNumber(), policy.getMakeAndModel(), 
         policy.getState(), 
         (policy.isNoFaultState() ? "is": "is not"));
   } 
} 
 
