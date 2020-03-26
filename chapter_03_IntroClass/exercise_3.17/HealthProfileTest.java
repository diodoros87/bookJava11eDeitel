/* =====================================================================================
 *       Filename:  HealthProfileTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.17 - Inputting and outputting 
                                personal data with HealthProfile objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class HealthProfileTest {

   public static void main(String[] args) {
      HealthProfile human = UserInterface.createHealthProfileByUser();
      UserInterface.displayHealthProfile(human);
      UserInterface.modifyHealthProfileByUser(human);
      UserInterface.displayHealthProfile(human);  
   } 
   
} 
