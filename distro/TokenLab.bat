@echo off
start javaw -classpath jars/TokenLab.jar -D:log4j.properties=./log4j.properties  net.sozinsoft.tokenlab.ui.TokenLabUI
exit