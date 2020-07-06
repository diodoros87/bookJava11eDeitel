/* =====================================================================================
 *       Filename:  GlobalLogger.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 8.18 - logger class of tic tac toe game
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.util.logging.Logger;

public class GlobalLogger {
   public static final Logger GLOBAL_LOGGER = Logger.getGlobal();
   
   public static void logInfo(String firstIntegerInfo, int firstInteger, String secondIntegerInfo, int secondInteger) {
      final String MESSAGE = String.format("%s %d             %s %d", 
                              firstIntegerInfo, firstInteger, secondIntegerInfo, secondInteger);
                              
      GLOBAL_LOGGER.info(MESSAGE);
   }
}
