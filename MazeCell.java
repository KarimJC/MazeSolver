import java.util.Optional;
import javalib.worldimages.*;
import java.awt.Color;

//represents a single cell in maze, which is a grid of MazeCells
public class MazeCell {

    // The directional neighbors cannot be private because they are used by
    // the search iterators. Similarly to how type-checking is allowed in
    // iterators as they are intrinsically intertwined with the data, the
    // iterators are able to access the field of a MazeCell as the MazeCells
    // and iterators are intrinsically intertwined
    // These cannot be final because all of the Optionals are initially
    // empty and they must be set to a value. Consequently, it is impossible
    // for them to be final.
  
    // Represents a cell that is a neighbor of this cell in the up direction
    // if one exists. Two cells are neighbors if the search or the player can
    // move from one cell to the other, meaning that they are not separated
    // by a wall and they border each other.
    Optional<MazeCell> up;
  
    // Represents a cell that is a neighbor of this cell in the down direction
    // if one exists. Two cells are neighbors if the search or the player can
    // move from one cell to the other, meaning that they are not separated
    // by a wall and they border each other.
    Optional<MazeCell> down;
  
    // Represents a cell that is a neighbor of this cell in the left direction
    // if one exists. Two cells are neighbors if the search or the player can
    // move from one cell to the other, meaning that they are not separated
    // by a wall and they border each other.
    Optional<MazeCell> left;
  
    // Represents a cell that is a neighbor of this cell in the right direction
    // if one exists. Two cells are neighbors if the search or the player can
    // move from one cell to the other, meaning that they are not separated
    // by a wall and they border each other.
    Optional<MazeCell> right;
  
    // Represents the coordinate of the MazeCell in the maze
    private final Posn coordinate;
  
    // constructor
    MazeCell(Posn coordinate) {
      this.coordinate = coordinate;
      this.up = Optional.empty();
      this.down = Optional.empty();
      this.left = Optional.empty();
      this.right = Optional.empty();
    }
  
    // Returns whether two cells are horizontally adjacent to each other.
    // Whether or not it is possible to traverse from this MazeCell to
    // the other and vice versa is irrelevant.
    boolean horizNextToInGrid(MazeCell other) {
      return Math.abs(this.coordinate.x - other.coordinate.x) == 1
          && this.coordinate.y == other.coordinate.y;
    }
  
    // Returns whether two cells are vertically adjacent to each other.
    // Whether or not it is possible to traverse from this MazeCell to
    // the other and vice versa is irrelevant.
    boolean vertNextToInGrid(MazeCell other) {
      return Math.abs(this.coordinate.y - other.coordinate.y) == 1
          && this.coordinate.x == other.coordinate.x;
    }
  
    // sets the value of the up neighbor of this cell. This setter is
    // necessary because this.up is an optional and has an initial value
    // of empty because when the maze is created, it is not possible to
    // traverse from any cell to any other cell. As the connections between
    // cells are created, the values of the neighbors must be set to represent
    // these neighbors
    void createUp(MazeCell cell) {
      this.up = Optional.of(cell);
    }
  
    // sets the value of the down neighbor of this cell. This setter is
    // necessary because this.up is an optional and has an initial value
    // of empty because when the maze is created, it is not possible to
    // traverse from any cell to any other cell. As the connections between
    // cells are created, the values of the neighbors must be set to represent
    // these neighbors
    void createDown(MazeCell cell) {
      this.down = Optional.of(cell);
    }
  
    // sets the value of the left neighbor of this cell. This setter is
    // necessary because this.up is an optional and has an initial value
    // of empty because when the maze is created, it is not possible to
    // traverse from any cell to any other cell. As the connections between
    // cells are created, the values of the neighbors must be set to represent
    // these neighbors
    void createLeft(MazeCell cell) {
      this.left = Optional.of(cell);
    }
  
    // sets the value of the right neighbor of this cell. This setter is
    // necessary because this.up is an optional and has an initial value
    // of empty because when the maze is created, it is not possible to
    // traverse from any cell to any other cell. As the connections between
    // cells are created, the values of the neighbors must be set to represent
    // these neighbors
    void createRight(MazeCell cell) {
      this.right = Optional.of(cell);
    }
  
    // Produces the image of this MazeCell the appropriate borders or edges
    // EFFECT: Modifies the cell image to create multiple visible edges of a cell
    WorldImage draw(int size, Color c) {
      WorldImage cell = new RectangleImage(size, size, OutlineMode.SOLID, c);
      if (this.up.isEmpty()) {
        cell = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
            new LineImage(new Posn(size, 0), Color.BLACK), 0, 0, cell);
      }
      if (this.down.isEmpty()) {
        cell = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
            new LineImage(new Posn(size, 0), Color.BLACK), 0, 0, cell);
      }
      if (this.left.isEmpty()) {
        cell = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE,
            new LineImage(new Posn(0, size), Color.BLACK), 0, 0, cell);
      }
      if (this.right.isEmpty()) {
        cell = new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE,
            new LineImage(new Posn(0, size), Color.BLACK), 0, 0, cell);
      }
  
      return cell;
    }
  
    // Determines if this MazeCell is a neighbor of the given MazeCell
    boolean isNeighborOf(MazeCell other) {
      return this.up.orElse(new MazeCell(new Posn(-1, 0))).equals(other)
          || this.down.orElse(new MazeCell(new Posn(-1, 0))).equals(other)
          || this.left.orElse(new MazeCell(new Posn(-1, 0))).equals(other)
          || this.right.orElse(new MazeCell(new Posn(-1, 0))).equals(other);
    }
  }
  
