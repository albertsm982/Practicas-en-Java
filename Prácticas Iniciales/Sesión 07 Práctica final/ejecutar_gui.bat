@ECHO OFF
set CLASSPATH=.
set CLASSPATH=%CLASSPATH%;./lib/*;./bin/;

java -classpath "%CLASSPATH%" quantik.gui.Quantik