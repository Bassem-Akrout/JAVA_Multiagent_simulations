# Example de makefile pour compiler le squelette de code distribué
# Vous pouvez compléter ce makefile, mais étant donnée la taille du projet, 
# il est FORTEMENT recommandé d'utiliser un IDE!

# Organisation:
#  1) Les sources (*.java) se trouvent dans le répertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) sont générés dans le répertoire bin
#     La hiérarchie des sources (par package) est conservée.
#
#  3) Une librairie gui.jar est distribuée pour l'interface grapique. 
#     Elle se trouve dans le sous-répertoire lib.
#
# Compilation:
#  Options de javac:
#   -d : répertoire dans lequel sont générés les .class compilés
#   -sourcepath : répertoire dans lequel sont cherchés les .java
#   -classpath : répertoire dans lequel sont cherchées les classes compilées (.class et .jar)


all : compileTestInvader compileTestBallsEvent compileTestBallsNoEvent compileTestCellsNoEvent compileTestCellsEvent compileTestBoidsNoEvent compileTestBoidsEvent
compileTestInvader:
	javac -d bin -classpath lib/gui.jar src/TestInvader.java


compileTestBallsEvent:
	javac -d bin -classpath lib/gui.jar:src/ src/SrcWithEvent/ballswithevent/*.java

compileTestBallsNoEvent:
	javac -d bin -classpath lib/gui.jar:src/ src/SrcNoEvent/balls/*.java

compileTestCellsNoEvent:
	javac -d bin -classpath lib/gui.jar:src/ src/SrcNoEvent/Cells/*.java

compileTestCellsEvent:
	javac -d bin -classpath lib/gui.jar:src/ src/SrcWithEvent/cells_with_event/*.java

compileTestBoidsNoEvent:
	javac -d bin -classpath lib/gui.jar:src/ src/SrcNoEvent/boidsNoEvent/*.java

compileTestBoidsEvent:
	javac -d bin -classpath lib/gui.jar:src/ src/SrcWithEvent/boids_with_event/*.java

runTestInvader:
	java -classpath bin:lib/gui.jar TestInvader

runTestBallsEvent: 
	java -classpath bin:lib/gui.jar TestBallsEventSimulator

runTestBallsNoEvent:
	java -classpath bin:lib/gui.jar TestBallsNoEventSimulator

runTestCellsNoEvent:
	java -classpath bin:lib/gui.jar TestCellsNoEventSimulator

runTestCellsEvent: 
	java -classpath bin:lib/gui.jar TestCellsEventSimulator

runTestBoidsNoEvent: 
	java -classpath bin:lib/gui.jar TestBoidsNoEventSimulator

runTestBoidsEvent: 
	java -classpath bin:lib/gui.jar TestBoidsEventSimulator

runall: runTestInvader runTestBallsEvent runTestBallsNoEvent runTestCellsNoEvent runTestCellsEvent runTestBoidsNoEvent runTestBoidsEvent

clean:
	rm -rf bin/




