#!/bin/bash
 
javac -Xlint -Werror -cp .:.. --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml *.java
