/* =====================================================================================
 *       Filename:  MyShapesFactory.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 10.1 - Drawing random lines, rectangles and 
                                 ovals
                                 
                                                  
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import java.security.SecureRandom;
import java.util.Objects;

import javafx.scene.paint.Color;

public class MyShapesFactory { 
   private static final SecureRandom RANDOM_NUMBERS = new SecureRandom();
   
   private static final short SHAPES_TYPES_NUMBER   = 3;
   
   private final short COLOR_COMPONENT_RANGE;
   private final short WIDTH_RANGE;
   private final short HEIGTH_RANGE;
   private final short LINE_WIDTH_RANGE;
   
   private final int[] COORDINATIONS = new int[4];
      
   public MyShapesFactory(short colorComponentRange, short widthRange, short heightRange, 
                                                            short lineWidthRange) {
      validateParameters(colorComponentRange, widthRange, heightRange, lineWidthRange);
      
      COLOR_COMPONENT_RANGE = colorComponentRange;
      WIDTH_RANGE           = widthRange;
      HEIGTH_RANGE          = heightRange;
      LINE_WIDTH_RANGE      = lineWidthRange;
   }
   
   private final void validateParameters(int... integerArray) {
      int indexOfNonPositiveParameter = MyShapesFactory.getIndexOfNonPositiveParameter(integerArray);
      
      String parameterName = null;
      switch(indexOfNonPositiveParameter) {
         default:
            
            return;
         case 0:
            parameterName = "Range of color component";
            break;
         case 1:
            parameterName = "Range of shape width";
            break;
         case 2:
            parameterName = "Range of shape height";
            break;
         case 3:
            parameterName = "Range of line width";
      }

      throw new IllegalArgumentException(parameterName + " must be more than zero");
   }
   
   public static int getIndexOfNonPositiveParameter(int... integerArray) {
      Objects.requireNonNull(integerArray, "integerArray must not be null");
      
      for (int index = 0; index < integerArray.length; index++) {
         if (0 >= integerArray[index]) {
            
            return index;
         }
      }
      
      return -1;
   }
   
   public void setRandomCoordinations() {
      for (int index = 0; index < COORDINATIONS.length; index++) {
         if (index % 2 == 0) {
            COORDINATIONS[index] = RANDOM_NUMBERS.nextInt(WIDTH_RANGE);
         }
         else {
            COORDINATIONS[index] = RANDOM_NUMBERS.nextInt(HEIGTH_RANGE);
         }
      }
   }
   
   public Color getRandomColor() {
      return Color.rgb( RANDOM_NUMBERS.nextInt(COLOR_COMPONENT_RANGE), 
                        RANDOM_NUMBERS.nextInt(COLOR_COMPONENT_RANGE), 
                        RANDOM_NUMBERS.nextInt(COLOR_COMPONENT_RANGE));
   }
   
   public static boolean isFilled() {
      int filled = RANDOM_NUMBERS.nextInt(2);
 
      return (filled == 0) ? false : true;
   }
   
   public MyShape[] getRandomMyShapes(final int NUMBER_OF_SHAPES) {
      if (-1 != MyShapesFactory.getIndexOfNonPositiveParameter(NUMBER_OF_SHAPES)) {
         throw new IllegalArgumentException("number of shapes must be more than zero");
      }
      
      MyShape[] myShapesArray = new MyShape[NUMBER_OF_SHAPES];
      
      for (int index = 0; index < NUMBER_OF_SHAPES; index++) {
         myShapesArray[index]  = createRandomMyShape();
      }
      
      return myShapesArray;
   }
   
   public MyShape createRandomMyShape() {
      int randomNumber = RANDOM_NUMBERS.nextInt(SHAPES_TYPES_NUMBER);
      MyShape myShape  = createMyShape(randomNumber);
      
      return myShape;
   }
   
   public MyShape createMyShape(int number) {
      Color strokeColor = getRandomColor();
      setRandomCoordinations();
         
      switch (number) {
         case 0:
            int lineWidth = RANDOM_NUMBERS.nextInt(LINE_WIDTH_RANGE);
            MyShape line = new MyLine(COORDINATIONS[0], COORDINATIONS[1], COORDINATIONS[2], COORDINATIONS[3],
                           strokeColor, lineWidth);
            return line;
         case 1:
         case 2:
            MyShape shape = createMyShape(number, strokeColor);
            
            return shape;
         default:
            throw new IllegalArgumentException("argument of number must be non-negative and less than " + SHAPES_TYPES_NUMBER);
      }
   }
   
   private MyShape createMyShape(int number, Color strokeColor) {
      Color filledColor = getRandomColor();
      boolean filled    = isFilled();
      
      switch (number) {
         case 1:
            MyShape rectangle = new MyRectangle(COORDINATIONS[0], COORDINATIONS[1], COORDINATIONS[2], COORDINATIONS[3],
                           strokeColor, filledColor, filled);
            return rectangle;
         case 2:
            MyShape oval = new MyOval(COORDINATIONS[0], COORDINATIONS[1], COORDINATIONS[2], COORDINATIONS[3],
                           strokeColor, filledColor, filled);
            return oval;
         default:
            throw new IllegalArgumentException("argument of number must be positive and less than " + SHAPES_TYPES_NUMBER);
      }
   }
}
