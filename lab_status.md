# P2 FS17 lab status -  group02 #
## Lab 01 ##
**Grade:** ok <br>
**Feedback**: Very nice and clean conversion of glob to regex, simple and elegant solution. Good job! Commit messages and size/contents of commits are also okay.


## Lab 02 ##
**Grade:**  ok<br>
**Feedback (initial)**: Your implementation is again very nice and clean, and you made good use of the existing architecture, good job! I just added one little comment about the naming convention for constants (i.e. final instance variables, they SHOULD_BE_ALL_CAPS). Your RollAgainSquareTest is rather minimal (this is just a remark you don't have to extend the test). E.g. you could have tested what happens when Jill enters that field after Jack. I like the fact that you commented the test though, it helps to convey its purpose.<br>

The problem are your class comments. First of all, they need to be of the form \/** ... \*/, rather than /* ... \*/, otherwise they are just comments (can only be seen right where they are written), instead of API documentation (can be seen when hovering over methods/classes, can be exported to HTML pages, etc). Further, you should be more specific. What do you mean by "player will be moved forward a certain amount of squares"? Who defines this "certain amount"? The square, the game, ...? And all child classes of Square are basically special squares, that’s sort of implied by the inheritance hierarchy. Also, don't talk about what methods a class provides and overrides, but what functionality it provides. E.g. a square defines its behaviour when entered or left by players, etc. Then, you don't always need to write "Provides following functionality:", because, except for annotations (@author, @version, …), this is what a class comment mainly talks about.<br>

About your method comments: the comments about params and return values are fine, plus, you did write real Javadoc comments there (with two starts in the beginning). However, most of them aren’t just accessor methods, so you need to comment on how they modify the receiver’s (i.e. the square’s) state/behaviour, not just @params and @return. For example: moveAndLand() defines the square’s entire behaviour when being left by a player. Also, landHereOrGoHome() isn’t just there to be overridden, it defines a specific behaviour as well. Lastly, I wouldn’t say enter and leave are callback methods… <br>

Please read the [doc comment guidelines](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html) again, improve your comments (on all of the square classes and the ISquare interface), and make sure they actually are Javadoc comments. Don’t spend too much time on this, but since it was an important part of this exercise, I need you to do some improvements. This shouldn’t take too long. Also, according to the exercise description, you need to add all the new fields to the Game.java at least once. You seem to have forgotten the RollAgainSquare, so please add that one the the game as well.<br>

**Feedback (revision)**: Very good, your class comments are better now. However, I still noticed some of the are only introduced with a single star, but you probably just missed them. What I forgot to tell you before: you shouldn't have any statements (like package, imports, etc.) between a class comment and the class definition. Eclipse will show doc comments when hovering over a class or method identifier, but it can only match the comment to a class/method if there are no statements in between.


## Lab 03 ##
**Grade:**  ok<br>
**Feedback**: Very good solution! You separated all the concerns in an elegant way, you mostly have short and concise methods, and your contracts are good as well. I also like that you clearly state what happens, if the turtle runs out of the field.  I commented on some of your contracts, because I think you forgot to cover some cases. For example, in MoveCommand, you never state what happens if an argument is null. This is important though, because it’s your decision whether you want to accept it (which means you’ll have to reasonably handle null values and also state how you will handle them) or you don’t accept it (assert(arg != null)) and then you do not define your behaviour any further, if received a null value.<br>

One remark about invariants: you should always check them before AND after public method calls. Usually, you have a method <code>private boolean invariant(){...}</code> and then you can check <code>assert(invariant);</code> as first and last line in all public methods.

Your UMLs are good too, they are both mostly accurate and exhaustive. You should maybe be more careful with composition vs. aggregation. For example, I think for program and ICommand, the composition makes sense, but between Program and Parser it's more of an aggregation (also, the diamond is most likely at the wrong end of the line there - a program doesn't have a parser, a parser has a program, right?). It's a fine line, but maybe you should have a look at this again. You don't need to revise anything though.


## Lab 04 ##
**Grade:** ok<br>
**Feedback**: Well done, you tested all the relevant cases and achieved great coverage. At some point in GameTest you mentioned that you didn’t mock the Player, since you had to implement a lot of business logic. That’s definitely true. Personally, I don’t think frameworks like Mockito help you much there. However, if you really need a mock version of the player, you could create a custom mock like you did with MockDie. Regarding your comment on custom mock objects: the God class code smell is a good observation. Still, if you have such God classes, you can’t always avoid using them in tests, so you might have to mock them anyway. I would say having to use custom mocks might be a sign to check for God classes, and consider a refactoring if necessary, but it should not affect your testing behaviour.<br>

One thing I missed in your statement were the advantages of mock frameworks. They seem to be more limited than whatever you can do by creating a custom mock, so why do people use them anyway?