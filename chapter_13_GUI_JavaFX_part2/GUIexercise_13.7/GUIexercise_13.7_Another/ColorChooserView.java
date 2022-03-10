/* =====================================================================================
 *       Filename:  ColorChooserView.java
 *
 *    Description:  learning Java from book
                       P. Deitel H. Deitel "Java How to Program, 11/e (Early Objects)"
                          Polish Edition (chapters from 1 to 28)
                             GUI Exercise 13.7 - class that create scene to
                                 the ColorChooser GUI (view component of MVC pattern)
                             
                             
 *
 *        @Author:  diodoros87
 *
 * =====================================================================================
 */
 
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
 
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import javafx.scene.paint.Color;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

import javafx.scene.layout.GridPane;

import javafx.collections.ObservableList;

import javafx.geometry.Insets;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.geometry.HPos;
import javafx.scene.layout.Priority;

import java.util.Iterator;

public class ColorChooserView {
   private final ColorChooserModel MODEL = new ColorChooserModel();
   private GridPane gridPane         = new GridPane();
   private SceneCreator sceneCreator = new SceneCreator(gridPane);
   
   public Scene createScene() {
      sceneCreator.customizeGridPane();
      
      sceneCreator.customizeColorSliders();
      sceneCreator.customizeAlphaSlider();
      
      sceneCreator.customizeTextFields();
      
      sceneCreator.customizeRectangle();
      sceneCreator.customizeCircle();
      
      //Group root = new Group(gridPane);  // to non-resizable gridPane
      
      Scene scene = new Scene(gridPane);
      
      return scene;
   }
   
   GridPane getGridPane() {
      return sceneCreator.gridPane;
   }
   
   Slider getRedSlider() {
      return sceneCreator.redSlider;
   }
   
   Slider getGreenSlider() {
      return sceneCreator.greenSlider;
   }
   
   Slider getBlueSlider() {
      return sceneCreator.blueSlider;
   }
   
   Slider getAlphaSlider() {
      return sceneCreator.alphaSlider;
   }
   
   TextField getRedTextField() {
      return sceneCreator.redTextField;
   }
   
   TextField getGreenTextField() {
      return sceneCreator.greenTextField;
   }
   
   TextField getBlueTextField() {
      return sceneCreator.blueTextField;
   }
   
   TextField getAlphaTextField() {
      return sceneCreator.alphaTextField;
   }
   
   Rectangle getColorRectangle() {
      return sceneCreator.colorRectangle;
   }
   
   void setRedValue(int value) {
      MODEL.setRedValue(value);
   }
   
   void setGreenValue(int value) {
      MODEL.setGreenValue(value);
   }
   
   void setBlueValue(int value) {
      MODEL.setBlueValue(value);
   }
   
   void setAlphaValue(double value) {
      MODEL.setAlphaValue(value);
   }
   
   int getRedValue() {
      return MODEL.getRedValue();
   }
   
   int getGreenValue() {
      return MODEL.getGreenValue();
   }
   
   int getBlueValue() {
      return MODEL.getBlueValue();
   }
   
   double getAlphaValue() {
      return MODEL.getAlphaValue();
   }
   
   private class SceneCreator {
      GridPane gridPane        = null;
      
      Slider redSlider         = new Slider();
      Slider greenSlider       = new Slider();
      Slider blueSlider        = new Slider();
      Slider alphaSlider       = new Slider();
      
      private TextField redTextField   = new TextField();  
      private TextField greenTextField = new TextField();
      private TextField blueTextField  = new TextField(); 
      private TextField alphaTextField = new TextField();
      
      private Label redLabel    = new Label(MODEL.getRedName());  
      private Label greenLabel  = new Label(MODEL.getGreenName());
      private Label blueLabel   = new Label(MODEL.getBlueName());
      private Label alphaLabel  = new Label(ColorChooserModel.getAlphaComponentName());
      
      Rectangle         colorRectangle = new Rectangle();
      private Circle    circle         = new Circle();
      
      private SceneCreator(GridPane gridPane) {
         this.gridPane = gridPane;
      }
      
      private void customizeGridPane() {
         Insets insets = new Insets(8);
         gridPane.setPadding(insets);
         gridPane.setHgap(8);
         gridPane.setVgap(8);
         gridPane.setStyle("-fx-background-color: WHITE;");
         
         addNodesToGridPane();
         addRowConstrainsToGridPane(4);
         addColumnConstrainsToGridPane(4);
         
         customizeGridPaneRows();
         customizeGridPaneColumns();
         
         gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
         gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
      }
      
      private void addRowConstrainsToGridPane(int size) {
         ObservableList<RowConstraints> constrains = gridPane.getRowConstraints();
         
         for (int counter = 0; counter < size; counter++) {
            constrains.add(new RowConstraints());
         }
      }
      
      private void addColumnConstrainsToGridPane(int size) {
         ObservableList<ColumnConstraints> constrains = gridPane.getColumnConstraints();
         
         for (int counter = 0; counter < size; counter++) {
            constrains.add(new ColumnConstraints());
         }
      }
      
      private void customizeGridPaneRows() {
         ObservableList<RowConstraints> rows = gridPane.getRowConstraints();
         Iterator<RowConstraints> iterator   = rows.iterator();
         
         while (true == iterator.hasNext()) {
            RowConstraints row = iterator.next();
            row.setPrefHeight(Region.USE_COMPUTED_SIZE);
            row.setVgrow(Priority.SOMETIMES);
            row.setFillHeight(true);
         }
      }
      
      private void customizeGridPaneColumns() {
         ObservableList<ColumnConstraints> columns = gridPane.getColumnConstraints();
         
         for (int counter = 0; counter < columns.size(); counter++) {
            ColumnConstraints column = columns.get(counter);
            column.setPrefWidth(Region.USE_COMPUTED_SIZE);
            column.setHgrow(Priority.SOMETIMES);
            column.setFillWidth(true);
         }
         
         columns.get(0).setHalignment(HPos.RIGHT);
         columns.get(3).setHalignment(HPos.CENTER);
      }
      
      private void addNodesToGridPane() {
         gridPane.addColumn(0, redLabel, greenLabel, blueLabel, alphaLabel);
         gridPane.addColumn(1, redSlider, greenSlider, blueSlider, alphaSlider);
         gridPane.addColumn(2, redTextField, greenTextField, blueTextField, alphaTextField);
         gridPane.add(circle, 3, 0, 1, GridPane.REMAINING);
         gridPane.add(colorRectangle, 3, 0, 1, GridPane.REMAINING);
      }
      
      private void customizeColorSliders() {
         customizeColorSlider(redSlider);
         customizeColorSlider(greenSlider);
         customizeColorSlider(blueSlider);
         redSlider.setValue(MODEL.getRedValue());
         greenSlider.setValue(MODEL.getGreenValue());
         blueSlider.setValue(MODEL.getBlueValue());
      }
      
      private void customizeColorSlider(Slider slider) {
         slider.setMin(ColorChooserModel.getColorComponentMin());
         slider.setMax(ColorChooserModel.getColorComponentMax());
         slider.setBlockIncrement(ColorChooserModel.getColorComponentIncrement());
      }
      
      private void customizeAlphaSlider() {
         alphaSlider.setValue(MODEL.getAlphaValue());
         alphaSlider.setMin(ColorChooserModel.getAlphaComponentMin());
         alphaSlider.setMax(ColorChooserModel.getAlphaComponentMax());
         alphaSlider.setBlockIncrement(ColorChooserModel.getAlphaComponentIncrement());
      }
      
      private void customizeTextFields() {
         redTextField.setPrefWidth(50);
         greenTextField.setPrefWidth(50);
         blueTextField.setPrefWidth(50);
         alphaTextField.setPrefWidth(50);
      }
      
      private void customizeRectangle() {
         colorRectangle.setWidth(100);
         colorRectangle.setHeight(100);
      }
      
      private void customizeCircle() {
         circle.setRadius(40);
         circle.setFill(Color.DODGERBLUE);
         circle.setStroke(Color.BLACK);
         circle.setStrokeType(StrokeType.INSIDE);
      }
   }
}


