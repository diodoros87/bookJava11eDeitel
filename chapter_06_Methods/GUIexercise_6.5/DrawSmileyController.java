/* =====================================================================================
 *       Filename:  DrawSmileyController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 6.5 - Drawing a smiley face with teeth and
                                tongue and eyes with pupils
                                using color and filled shapes
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

   
public class DrawSmileyController { 
   @FXML private Canvas canvas;
   
   // draws a smiley face
   @FXML
   void drawSmileyButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();

      // draw the face
      gc.setFill(Color.YELLOW);
      gc.fillOval(10, 10, 280, 280);
      gc.strokeOval(10, 10, 280, 280); 
      
      // draw the eyes
      gc.setFill(Color.BLACK);
      gc.fillOval(75, 85, 40, 40);
      gc.fillOval(185, 85, 40, 40);
      
      // draw the pupils
      gc.setFill(Color.BROWN);
      gc.fillOval(85, 95, 20, 20);
      gc.fillOval(195, 95, 20, 20);
      
      // draw the mouth
      gc.fillOval(50, 130, 200, 120);
      
      // "touch up" the mouth into a smile
      gc.setFill(Color.YELLOW);
      gc.fillOval(50, 140, 200, 90);
      
      // draw the teeth
      for (int counter = 0; counter < 3; counter++) {
         gc.setFill(Color.WHITE);
         gc.fillRect(90 + counter * 20, 160 - 10 * counter, 20, 20);
         gc.fillRect(150 + counter * 20, 140 + 10 * counter, 20, 20);
         gc.setFill(Color.PINK);
         gc.strokeRect(90 + counter * 20, 160 - 10 * counter, 20, 20);
         gc.strokeRect(150 + counter * 20, 140 + 10 * counter, 20, 20);
         
         gc.setFill(Color.WHITE);
         gc.fillRect(90 + counter * 20, 190 + 10 * counter, 20, 20);
         gc.fillRect(150 + counter * 20, 210 - 10 * counter, 20, 20);
         gc.setFill(Color.PINK);
         gc.strokeRect(90 + counter * 20, 190 + 10 * counter, 20, 20);
         gc.strokeRect(150 + counter * 20, 210 - 10 * counter, 20, 20);
      }
     
      // draw the tongue
      gc.setFill(Color.PINK);
      gc.fillOval(110, 180, 80, 100);
   }
   
}


