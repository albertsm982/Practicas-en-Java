@echo off

REM Activamos codificacion para Windows
chcp 1252

echo Ejecutamos suite con todos los tests...
java -jar .\lib\junit-platform-console-standalone-1.9.0-RC1.jar ^
     --classpath .\bin ^
     --disable-ansi-colors ^
     --exclude-engine junit-vintage ^
     -c juego.SuiteAllTests
