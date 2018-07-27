executable jar is found in dist folder
run: java -jar canvas-painter-1.0-SNAPSHOT.jar

from root folder:
to run test: mvn test
to create jar: mvn clean package

Used lombok in the project, if executed on IDE, may require lombok plugin.

Exceptions are thrown and caught when commands are malformed:
- canvas not created
- non existing commands
- lines drawn out of canvas
- first character in command is not capital letter
- more parameters are given than expected
- parameters are not integers

when user input negative value in commands, it will be turned to its absolute value.