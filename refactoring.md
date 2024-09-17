# Refactoring

## Movable
Removed [setPosition(Tile)](../group29/exercise_06/setPosition().PNG) in all classes in the movable package.
It was not used from other classes and it seemed that it was not useful
(because it only changed the tile of the movable, but it did not made the movable enter it and leave the old one)

## Game
There were two types of method that did the same (given a valid X and Y coordinate they returned a tile of the board on such coordinates), because of that we have chosen the greatest of them (that is the most used in the code) and removed the other one.
Also [before](../group29/exercise_06/GameBefore.PNG) we initialized some of the variables of Game in the constructor.
We changed that too, [now](../group29/exercise_06/GameAfter.PNG) they were initialized where they were declared.
We decided that it was the responsibility of the play() method to check if the game is over (that is if each box is on top of a goal). Before it was the responsibility of increaseBoxOnGoalCounter().
We decided to change it, because in some test that we made, the game was over before it was finished to be parsed from Parser.

## Parser
We added a try in load(), in order to use the garbage collection of java.
We added also a default case in parser(), which purpose is to throw an Exception if there is an illegal character representing a not recognized tile from Parser.

## Goal
Just a minor thing, we changed the way that we check how the goal recognize the movable that tries to enter on it.
Before it was responsible for checking the toChar() method of the movable, but now it uses instanceof to recognize which type of movable is entering or leaving the goal.

## Conclusion
Because we started with a solid code by using what we learned in classes from the beginning (and also thank to the assistant Julius that
helped us each time and got in touch with us while the Easter holidays, we appreciate a lot ;) ), we did not have to change a lot of our
code for stage 4.

apiguardian-api-1.1.0.jar
byte-buddy-1.10.5.jar
byte-buddy-agent-1.10.5.jar
junit-jupiter-5.5.2.jar
junit-jupiter-api-5.5.2.jar
junit-jupiter-engine-5.5.2.jar
junit-jupiter-params-5.5.2.jar
junit-platform-commons-1.5.2.jar
junit-platform-engine-1.5.2.jar
mockito-core-3.2.4.jar
objenesis-2.6.jar
opentest4j-1.2.0.jar