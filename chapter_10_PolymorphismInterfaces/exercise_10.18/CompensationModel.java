/* =====================================================================================
 *       Filename:  CompensationModel.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             Exercise 10.18 - CompensationModel interface to calculate
                                earnings instead of CompensationModel class
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
public interface CompensationModel extends Cloneable {            

   public abstract double earnings(); 
   
   public abstract Object clone() throws CloneNotSupportedException;

} 

