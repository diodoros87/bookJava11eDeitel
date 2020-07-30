/* =====================================================================================
 *       Filename:  DrawShapes.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 10.1 - Main application class that loads and 
                                 displays the DrawShapes GUI
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.Collections;

public class DrawShapes extends Application {
   
   public enum CommandLineParameters {RAW, UNNAMED, NAMED};
   
   private static List<String>         rawParametersList;
   private static List<String>         unnamedParametersList;
   private static Map<String, String>  namedParametersMap;
   
   @Override
   public void start(Stage stage) throws Exception {
      setParameters();
      //parametersList = getUnnamedParameters();
      // loads DrawShapes.fxml and configures the DrawShapesController
      Parent root = FXMLLoader.load(getClass().getResource("DrawShapes.fxml"));

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Drawing filled or outlined shapes"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      /*
      System.out.println("args = " + args);
      for (int index = 0; index < args.length; index++) {
         System.out.printf("args[%d] = %s%n", index , args[index]);
      }*/
      
      launch(args); // create a DrawShapes object and call its start method
      
      
      //List<String> list = Application.Parameters.getParameters().getRaw();
   }
   
   public List<String> getParametersList(CommandLineParameters commandLineParameters) {
      switch (commandLineParameters) {
         case RAW:
            return rawParametersList;
         case UNNAMED:
            return unnamedParametersList;
         default:
            throw new UnsupportedOperationException("CommandLineParameters " + commandLineParameters + " is not supported");
      }
   }
   
   public Map<String, String> getNamedParameters() {
      return namedParametersMap;
   }
   
   public void setParameters() {
      Application.Parameters parameters = getParameters();
      
      rawParametersList     = parameters.getRaw();
      rawParametersList     = Collections.unmodifiableList(rawParametersList);
      
      unnamedParametersList = parameters.getUnnamed();
      unnamedParametersList = Collections.unmodifiableList(unnamedParametersList);
      
      namedParametersMap    = parameters.getNamed();
      namedParametersMap    = Collections.unmodifiableMap(namedParametersMap);
   }
   
   public List<String> getUnnamedParameters() {
      Application.Parameters parameters = getParameters();
      List<String> parametersList = parameters.getUnnamed();
      
      return parametersList;
   }
   
   public int getParameter(int parameterNumber) {
      Application.Parameters parameters = getParameters();
      List<String> list = parameters.getUnnamed();
      String shapesNumberParameter = list.get(parameterNumber);
      System.out.printf("list.get(%d) = %d%n", parameterNumber, list.get(parameterNumber));
      
      
      int shapesNumber = Integer.parseUnsignedInt​(shapesNumberParameter);
      
      System.out.println("shapesNumber = " + shapesNumber);
      
      return shapesNumber;
      
      //int N = Integer.parseInt(getParameters().getUnnamed().get(parameterNumber));
      //return N;
   }
}
