#!/bin/bash
 
javac -Xlint -Werror --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml *.java
