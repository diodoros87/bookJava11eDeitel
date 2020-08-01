/* =====================================================================================
 *       Filename:  DrawShapes.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 10.2 - Main application class that loads and 
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
import java.util.Objects;

public class DrawShapes extends Application {
   
   public enum CommandLineParameter {RAW, UNNAMED, NAMED};
   
   private static List<String>         rawParametersList;
   private static List<String>         unnamedParametersList;
   private static Map<String, String>  namedParametersMap;
   
   private static String[] argsArray;
   
   @Override
   public void start(Stage stage) throws Exception {
      setParameters();

      // loads DrawShapes.fxml and configures the DrawShapesController
      Parent root = FXMLLoader.load(getClass().getResource("DrawShapes.fxml"));

      Scene scene = new Scene(root); // attach scene graph to scene
      stage.setTitle("Drawing filled or outlined shapes"); // displayed in window's title bar
      stage.setScene(scene); // attach scene to stage
      stage.show(); // display the stage
   }

   public static void main(String[] args) {
      argsArray = args;
      
      launch(args); // create a DrawShapes object and call its start method
   }
   
   public static List<String> getParametersList(CommandLineParameter commandLineParameter) {
      switch (commandLineParameter) {
         case RAW:
            return rawParametersList;
         case UNNAMED:
            return unnamedParametersList;
         default:
            throw new UnsupportedOperationException("CommandLineParameter " + commandLineParameter + " is not supported");
      }
   }
   
   public static String[] getArgs() {
      return argsArray;
   }
   
   public static Map<String, String> getNamedParameters() {
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
   
   public static String getParameter(int parameterNumber, CommandLineParameter commandLineParameters) {
      if (0 > parameterNumber) {
         throw new ArrayIndexOutOfBoundsException(String.format("0 > parameterNumber(%d)", parameterNumber));
      }
      
      List<String>  parameterList = getParametersList(commandLineParameters);
      
      if (parameterList.size() <= parameterNumber) {
         throw new ArrayIndexOutOfBoundsException(String.format("size of parameterList <= parameterNumber(%d)", parameterNumber));
      }
      
      String parameter = parameterList.get(parameterNumber);
      
      return parameter;
   }
   
   public static String getParameter(String parameterName) {
      Objects.requireNonNull(parameterName, "parameterName must not be null");
      if (false == namedParametersMap.containsKey(parameterName)) {
         throw new IllegalArgumentException(String.format("parameterName(%s) is not a key of map value", parameterName));
      }
      
      String parameter = namedParametersMap.get(parameterName);
      
      return parameter;
   }
}
