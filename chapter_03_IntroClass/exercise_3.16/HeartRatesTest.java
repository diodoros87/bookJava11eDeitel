/* =====================================================================================
 *       Filename:  HeartRatesTest.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 3.16 - Inputting and outputting 
                                personal data with HeartRates objects
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

public class HeartRatesTest {

   public static void main(String[] args) {
      HeartRates human = UserInterface.createPersonalDataByUser();
      UserInterface.displayPersonalData(human);
      UserInterface.modifyPersonalDataByUser(human);
      UserInterface.displayPersonalData(human);  
   } 
   
} 
