/* =====================================================================================
 *       Filename:  SceneCreator.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 13.7 - class that create scene to
                                 the ColorChooser GUI
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

import javafx.scene.layout.GridPane;

import javafx.geometry.Insets;

import javafx.scene.Group;
import javafx.scene.Scene;

public class SceneCreator {
   
   
   private GridPane gridPane        = new GridPane();
   
   private Slider redSlider         = new Slider();
   private Slider greenSlider       = new Slider();
   private Slider blueSlider        = new Slider();
   private Slider alphaSlider       = new Slider();
   
   private TextField redTextField   = new TextField();  
   private TextField greenTextField = new TextField();
   private TextField blueTextField  = new TextField(); 
   private TextField alphaTextField = new TextField();
   
   private Label redLabel    = new Label("Red");  
   private Label greenLabel  = new Label("Green");
   private Label blueLabel   = new Label("Blue");
   private Label alphaLabel  = new Label("Alpha");
   
   private Rectangle colorRectangle = new Rectangle();
   private Circle    circle         = new Circle();
   
   private Scene scene = createScene();
   
   public Scene getScene() {
      return scene;
   }
   
   private Scene createScene() {
      customizeGridPane();
      
      customizeColorSlider(redSlider);
      customizeColorSlider(greenSlider);
      customizeColorSlider(blueSlider);
      customizeAlphaSlider();
      
      customizeTextField(redTextField);
      customizeTextField(greenTextField);
      customizeTextField(blueTextField);
      customizeTextField(alphaTextField);
      
      Group group = new Group(gridPane);
      
      scene = new Scene(group);
      
      return scene;
   }
   
   private void customizeGridPane() {
      Insets insets = new Insets(8);
      gridPane.setPadding(insets);
      gridPane.setHgap(8);
      gridPane.setStyle("-fx-background-color: white;");
      
      addNodesToGridPane();
   }
   
   private void addNodesToGridPane() {
      gridPane.addColumn(0, redLabel, greenLabel, blueLabel, alphaLabel);
      gridPane.addColumn(1, redLabel, greenSlider, blueSlider, alphaSlider);
      gridPane.addColumn(2, redTextField, greenTextField, blueTextField, alphaTextField);
      gridPane.add(circle, 3, 0, 1, GridPane.REMAINING);
      gridPane.add(colorRectangle, 3, 0, 1, GridPane.REMAINING);
   }
   
   private void customizeColorSlider(Slider slider) {
      slider.setValue(0);
      slider.setMin(0);
      slider.setMax(255);
      slider.setBlockIncrement(1);
   }
   
   private void customizeAlphaSlider() {
      alphaSlider.setValue(1.0);
      alphaSlider.setMin(0.0);
      alphaSlider.setMax(1.0);
   }
   
   private void customizeTextField(TextField textField) {
      textField.setPrefWidth(50);
   }
   
   private void customizeRectangle() {
      colorRectangle.setWidth(100);
      colorRectangle.setHeight(100);
   }
   
   private void customizeCircle() {
      circle.setRadius(40);
   }
}
