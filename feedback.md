# Feedback

## General
- Nice class comments, but you should look up how to link to other classes:
  - `{@link Tile}` does not exist => `{@link ITile}`
  - `{@link Player}` does not exist => either import or use `{@link movable.Player}`
  - Don't link to non-existing methods.

## `indications`
### `indications.ParserIndication`
- `parse()`: You should make the input lowercase, otherwise the most trivial of human errors is
  an error in your program :-) (More to this in the lecture "Human Machine Interaction")
- `toString()`: Hello ParserIndication. I am Julius :-P

## `movable`
- The classes `Box` and `Player` have similarities. It might be worthwhile to create an abstract base class.

### `movable.IMovable`
- Missing method documentation

## `sokoban`
### `sokoban.Game`
- Nice documentation

- This is the wrong order and can lead to an `ArrayIndexOutOfBoundException`:
  ```java
  assert board[x][y] != null;
  assert isInRangeX(x) && isInRangeY(y);
  ```

### `Parser`
- Beautiful

# Status
OK
