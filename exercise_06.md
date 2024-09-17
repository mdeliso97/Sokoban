# Exercise 6

This week, you will continue to improve the Sokoban game. 

*Before you start with the third stage you should read the entire exercise
 description first.*


## Third Stage of the Sokoban Game

- During the second stage you implemented player movement but you haven't
  added any validation yet. Check that players are only allowed to perform
  valid moves. Invalid moves, such as moving through walls, should not be
  allowed.
- When the player is in front of a box and moves in the direction of the box, 
  the box should be moved in the same direction if possible.
- Show that your solution of movement validation for both player and boxes
  works by writing appropriate unit tests.
- Introduce a new tile  
  `C`: Completed tile
  that represents a goal tile with a box on top of it. Make sure the parser
  can read the new tile and that the renderer can visualize it. Extend your
  tests for both classes to check your implementation.
- Make the game interactive. Write a main routine that can be used to run the 
  program and which takes input of some sorts (e.g. when running from the 
  console, you could enter commands for the player like `up` or `left`, 
  similar to what we did for the turtle game). Ensure that the player always
  sees the current representation of the game by re-rendering the level.
- Draw a sequence diagram that covers a scenario where the player writes an
  input command that pushes a box onto a goal tile.
  
  // Ask about the diagram sequence: all classes? all dependencies and communication between all classes and interfaces?
  // Shall we test something for the main method? (SokobanPlay)
  // Ask if it's correct how we did the things (SokobanPlay, ParserIndication, C case, Lower/upper character in inputs, NotMove => In      case doesn't recognize the input of the player, good idea?, corrected interfaces). 
  // toChar: isn't too complicated? It's fine how we did it? can we do it better? (looks a bit chaotic)

Tag the solution with `sokoban3` and make sure the tag is pushed to the
remote repository.


## Fourth Stage - Sticking to the principles

You now have implemented all three stages of your Sokoban game. We did not
provide any restrictions, but encouraged to apply the principles and
techniques you learned in the past weeks (such as design by contract, unit
testing, ...). In this part of the exercise, we explicitly ask you to do some
of these things, if you did not already do them.

To pass this part, your game __must__ implement the tasks described in the
following subsections.


#### Refactoring

For this sub-task, you have to refactor a class of your choosing. Maybe the
Parser class has very long methods that could be split up or your implementation of
the main game logic is a bit of a mess that could be improved.  
Create a markdown file named `refactoring.md` and document your refactoring process. 
You can add images to demonstrate how you clean up the code.


#### Group your classes into packages

So far, most of our exercises were solved by placing all classes in the same
package in the `src` folder. However, as you may have realized, having lots of
unrelated classes grouped together may become confusing quickly. Fortunately,
Java allows you to create
[packages](https://docs.oracle.com/javase/tutorial/java/package/packages.html),
enabling you to group related classes together.

In this sub-task, you __must__ create different packages for your classes. You
are free to choose how the packages are organized, but should make sure that
related classes are close to each other, while mixing unrelated classes should
be avoided as much as possible. You are not allowed to have test classes in
the same package as the other classes.


#### Always override `toString()` in each class (except test classes)

You must provide a reasonable `toString()` method in all classes that are
included in your game (except for the test classes). Ideally, the output of
`toString()` is easy to read and reveals key information of the object. For
example, a `toString()` method of a game could return a representation of
the current state (and should in turn make use of the `toString()` methods of
the tiles and objects it contains).


#### Minimize mutability

Whenever an instance variable is not modified after initialization, declare it
`final`. If you work with lists, use [unmodifiable
lists](http://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#unmodifiableList-java.util.List-)
instead.

// Advice: where else out of board in Game?

#### Encapsulation and information hiding

Use the appropriate access modifiers for all your methods and instance
variables. Keep in mind that apart from `public`, `private`, and `protected`,
having no modifier at all is also an option (and results in the method or
variable being visible to the class and to classes in the same package). See
[the
documentation](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html)
for more details on access modifiers.


#### Check parameters for validity

For each method that takes parameters, you should think about the valid inputs
(e.g., "must not be null"). Write preconditions in the form of `assert`
statements for all methods in your code. In addition, write JavaDoc comments
for all _public_ methods in your code, explaining what the restrictions on the
parameters are.


Tag the solution with `sokoban4` and make sure the tag is pushed to the
remote repository.


## Deadline

Submit your solutions by pushing your code to the git repository by
___Friday, 24 April, 13:00___.
