# Todo

## Exercise 02

- Improve Javadoc comments of various Square classes according to feedback in
  lab_status.md

## Exercise 03

- Add 'Board' class, tracks current position of turtle, provides move(int X,
  int Y) method which handles movement local to position. Gets to decide
  whether to do wrap-around / mirroring / ... on hitting board border.
  - Unit tests where it makes sense (e.g. wraparound)

- Classes for various 'move' commands (implementing a common interface /
  inherting from a base class?). Implements executeOnBoard(Board) method, which
  sets/modifies position on board according to its implementation.

- Parser class which parses user input and returns list of command objects.
  - Unit tests for parsing

- UML class diagram

- UML sequence diagram

- JavaDoc comments

- Enforcing contract via assertions/invariants
