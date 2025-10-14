if not exist "bin" mkdir bin

javac -classpath .\bin;.\lib\* ^
-encoding UTF-8 ^
-d bin ^
-sourcepath .\src ^
.\src\musica\*.java ^
.\src\genericidad\*.java