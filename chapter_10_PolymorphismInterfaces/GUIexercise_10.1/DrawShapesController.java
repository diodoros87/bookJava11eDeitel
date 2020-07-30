/* =====================================================================================
 *       Filename:  DrawShapesController.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 10.1 - Drawing 10 shapes using random:
                                 - selection if shape is filled or outlined,
                                 - colors, localizations, dimensions.
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.application.Application;
import javafx.application.Application.Parameters;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.security.SecureRandom;
import java.util.List;

public class DrawShapesController { 
   @FXML private Canvas canvas;
   
   // create secure random number generator for use in method getRandomNumber
   private static final SecureRandom randomNumbers = new SecureRandom();
   
   private static final short shapeTypesNumber = 4;
   
   // draws shapes
   @FXML
   void drawShapesButtonPressed(ActionEvent event) {
      // get the GraphicsContext, which is used to draw on the Canvas
      GraphicsContext gc = canvas.getGraphicsContext2D();
      
      int  shapesNumber = 10;
      
      DrawShapes drawShapes = new DrawShapes();
      System.out.println("drawShapes = " + drawShapes);
      /*
      List<String> parametersList = drawShapes.getUnnamedParameters();
      System.out.println("parametersList = " + parametersList);*/
      
      List<String> parametersList = drawShapes.getParametersList(DrawShapes.CommandLineParameters.UNNAMED);
      System.out.println("parametersList = " + parametersList);
      
      String shapesNumberParameter = parametersList.get(0);
      System.out.println("shapesNumberParameter = " + shapesNumberParameter);
      
      shapesNumber = Integer.parseUnsignedInt​(shapesNumberParameter);
      System.out.println("shapesNumber = " + shapesNumber);
      
      //shapesNumber = DrawShapes.NUMBER_OF_SHAPES;
      
      //Application.Parameters parameters = this.getParameters();
      //List<String> list = parameters.getRaw();
      //Application.Parameters parameters = new Parameters();
      /*
      List<String> list = Application.Parameters.getUnnamed();
      String shapesNumberParameter = list.get(1);
      System.out.println("shapesNumberParameter = " + list.get(1));
      
      shapesNumber = Integer.parseUnsignedInt​(shapesNumberParameter);
      
      
      System.out.println("shapesNumber = " + shapesNumber);*/
      
      
      final short  colorComponentRange = 256;
      final double canvasWidth = canvas.getWidth();
      final double canvasHeight = canvas.getHeight();
      final int widthCenter = (int)canvasWidth / 2;   // center of width localization (half of width's length)
      final int heightCenter = (int)canvasHeight / 2; // center of height localization (half of height's length)
      
      // after "Draw filled or outlined shapes" button was pressed, clear the canvas for next set of shapes
      gc.clearRect(0, 0, canvasWidth, canvasHeight);

      for (int counter = shapesNumber; counter >= 1; counter--) {
         // set random color with 3 random color's components with range from 0 to 255
         gc.setFill(Color.rgb(getRandomNumber(colorComponentRange), 
                           getRandomNumber(colorComponentRange), getRandomNumber(colorComponentRange)));
         
         drawShapes(getRandomNumber(shapeTypesNumber), widthCenter, heightCenter, gc);
      } 

   }
   
   void drawShapes(int number, int widthCenter, int heightCenter, GraphicsContext gc) {
      switch (number) {
         case 0:
            gc.fillOval(getRandomNumber(widthCenter), getRandomNumber(heightCenter),
                        getRandomNumber(widthCenter), getRandomNumber(heightCenter));
            break;
         case 1:
            gc.fillRect(getRandomNumber(widthCenter), getRandomNumber(heightCenter),
                        getRandomNumber(widthCenter), getRandomNumber(heightCenter));
            break;
         case 2:
            gc.strokeOval(getRandomNumber(widthCenter), getRandomNumber(heightCenter),
                        getRandomNumber(widthCenter), getRandomNumber(heightCenter));
            break;
         case 3:
            gc.strokeRect(getRandomNumber(widthCenter), getRandomNumber(heightCenter),
                        getRandomNumber(widthCenter), getRandomNumber(heightCenter));
            break;
         default:
            System.err.println("argument of number must be less than " + shapeTypesNumber);
      }
   }
   
   // method returns values in range from 0 (inclusive) to bound (exclusive)
   public static int getRandomNumber(int bound) {
      if (bound <= 0) {
         System.err.println("bound must be greater than zero");
         return 0;
      }
      
      return randomNumbers.nextInt(bound);
   } 
}

