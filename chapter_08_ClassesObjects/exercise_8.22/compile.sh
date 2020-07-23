#!/bin/bash
# Compilation should be OK only in system's structure with files-directories below:
 
javac -Xlint -cp .:..:/home/mat/.m2/repository/org/javamoney/moneta/moneta-convert/1.4/moneta-convert-1.4.jar\
:/home/mat/.m2/repository/org/javamoney/moneta/moneta-convert-imf/1.4/moneta-convert-imf-1.4.jar\
:/home/mat/.m2/repository/javax/money/money-api/1.1/money-api-1.1.jar\
:/home/mat/.m2/repository/org/javamoney/moneta/moneta-core/1.4/moneta-core-1.4.jar\
:/home/mat/.m2/repository/javax/annotation/javax.annotation-api/1.3.2/javax.annotation-api-1.3.2.jar \
 *.java
