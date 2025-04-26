import java.awt.Color;
import java.util.*;
import javalib.impworld.*;
import javalib.worldimages.*;

// represents the maze world class that handles the animation
// of searching for the path to find the maze and handles 
// user inputs
class MazeWorld extends World {

  // represents the Maze that refers to this MazeWorld
  private final Maze maze;

  
  // The following three booleans: 
  // - this.automatic
  // - this.constructing
  // - this.displaySolution
  // are not made final because these fields can change 
  // as the MazeWorld class keeps running. For constructing, this MazeWorld
  // it starts off with a Maze that has all the walls, and constructs it by 
  // removing walls on each tick. Once it is done constructing, then the MazeWorld 
  // must modify its constructing field to be false as construction is no longer needed
  // For automatic and displaySolution, the user should have the choice to be able to 
  // display the solution of the maze or to have the maze automatically search for the end
  // in which the fields must be modified if the user chooses to do so
  
  // Determines whether the user wants this MazeWorld
  // to be in automatic mode or not so that they 
  // can control the maze themselves
  private boolean automatic;

  // Determines whether the maze in this MazeWorld 
  // is still being constructed/taking down walls or not
  private boolean constructing;

  // Determines whether the user wants to solution of the Maze
  // in this MazeWorld to be displayed
  private boolean displaySolution;

  // Constructs a MazeWorld given a Maze
  MazeWorld(Maze maze) {
    this.maze = maze;
    this.automatic = false;
    this.constructing = true;
    this.displaySolution = false;
  }

  // makes a scene containing the visual representation of a Maze
  public WorldScene makeScene() {
    WorldScene background = new WorldScene(1000, 1000);
    background.placeImageXY(this.maze.drawMaze(10), 500, 350);

    return background;
  }

  // EFFECT: when the "d" key is pressed, animates DFS on the grid
  // when the "b" key is pressed, animates BFS on the grid
  // when the "r" key is pressed, generates a new maze and resets
  // all of the lists in maze
  // when the direction keys are pressed, if this.automatic is false,
  // moves the player cell in that direction if it is possible for
  // the player to move to that position
  // if the "s" key is pressed, then show the solution of the paths, 
  // and if the "v" key is pressed toggle between seeing the visited cells
  // or not
  public void onKeyEvent(String key) {
    if (!this.constructing) {
      if (key.equals("r")) {
        this.maze.createNewSpanningTree();
        this.constructing = true;
        this.automatic = false;
      }

      if (key.equals("b")) {
        this.maze.updateSolver(true);
        this.automatic = !this.automatic;
      }

      if (key.equals("d")) {
        this.maze.updateSolver(false);
        this.automatic = !this.automatic;
      }

      if (!this.automatic && key.equals("up")) {
        this.maze.visitPlayerCell(new Posn(0, -1));
      }

      if (!this.automatic && key.equals("down")) {
        this.maze.visitPlayerCell(new Posn(0, 1));
      }

      if (!this.automatic && key.equals("right")) {
        this.maze.visitPlayerCell(new Posn(1, 0));
      }

      if (!this.automatic && key.equals("left")) {
        this.maze.visitPlayerCell(new Posn(-1, 0));
      }

      if (key.equals("s")) {
        this.displaySolution = !this.displaySolution;

      }

      if (key.equals("v")) {
        this.maze.togglePaths();
      }
    }
  }

  // EFFECT: When the maze is solved, colors a cell in the
  // path blue from back to front. Otherwise, visits a new cell
  // using maze's searching algorithm if this.automatic is true.
  // If the path has finished drawing, ends the world
  public void onTick() {
    if (this.constructing) {
      this.maze.createCellRelationship();
      if (this.maze.isConstructed()) {
        this.maze.updateSolver(true);
      }
    }
    else {
      if (this.maze.mazeSolved() || this.displaySolution) {
        this.maze.visitOnPath();
      }
      else if (this.automatic) {
        this.maze.visitCell();
      }
      if (this.maze.completedMaze() && !this.automatic) {
        this.endOfWorld("");
      }
    }
    this.constructing = !this.maze.isConstructed();
  }

  // The last scene that will be shown when the world ends
  public WorldScene lastScene(String msg) {
    WorldScene background = new WorldScene(1000, 1000);
    background.placeImageXY(this.maze.drawMaze(10), 500, 350);
    background.placeImageXY(new TextImage("You have won!", 100, Color.BLACK), 500, 350);
    return background;
  }
}

//represents a maze that has a start and ending point
//of a path
class Maze {

  // represents the height of this Maze
  private final int height;

  // represents the width of this Maze
  private final int width;

  // represents the grid of cells in this Maze
  // The outer array list represents the rows in this Maze or the y coordinates
  // The inner array list represents columns or the x coordinates
  // The grid starts off at the top left cell of the Maze
  // In a coordinate plane, the top left cell's position refers
  // to a Posn(0,0) and the bottom right cell's position refers to
  // a Posn(this.width - 1, this.height - 1)
  private final ArrayList<ArrayList<MazeCell>> grid;

  // represents the list of edges that connect each cell
  // in this grid of this Maze
  private final ArrayList<Edge> edgeList;

  // stores the cells visited either by the player or by
  // the drawSearchOnTick iterator from the solver
  private final ArrayList<MazeCell> visitedCells;

  // stores the drawn path from the end of the maze to
  // to the beginning as visited by the 
  // drawPathOnTick iterator from the solver
  private final ArrayList<MazeCell> path;

  // the maze should always know the path from the beginning to
  // the end, even before it is drawn. This information about the 
  // order in which cells are searched and the path are calculated and
  // stored within the solver class. This field is not finalized 
  // as in the MazeWorld, the user can modify the solver depending if they want 
  // a BFS or DFS search.
  private MazeSolver solver;

  // the position of the player in the maze, cannot be final
  // as the player moves to different positions in the maze.
  // This field is not final as in the MazeWorld, the user can 
  // move in manual mode and modify the player position of this Maze
  private Posn playerPosn;

  // stores the number of deviations from the solution path
  // that the player has made in traversing the maze, does not
  // count cells that were visited repeatedly multiple times
  // This field is not final as in the MazeWorld, the user can 
  // move in manual mode and may need to modify the number of 
  // wrong moves that they take
  private int wrongMoves;

  // represents whether or not the visited paths or cells in the Maze should
  // be highlighted to be visibly seen in draw or not. This field is not final as 
  // in the MazeWorld, the user can toggle whether or not they want the paths 
  // taken to be seen.
  private boolean showVisitedPaths;

  // represents an iterator that iterates through all the edges of this Maze
  private Iterator<Edge> edgeIter;

  // Convenience constructor to take in a height, width, and
  // list of edgeWeights used for testing
  Maze(ArrayList<Integer> edgeWeights, int width, int height) {
    if (edgeWeights.size() != ((width * (height - 1)) + (height * (width - 1)))) {
      throw new IllegalArgumentException("Did not give enough edgeweights for a pre-formed Maze.");
    }

    this.grid = new ArrayList<ArrayList<MazeCell>>();
    this.width = width;
    this.height = height;
    this.edgeList = new ArrayList<>();
    this.visitedCells = new ArrayList<>();
    this.path = new ArrayList<>();
    this.playerPosn = new Posn(0, 0);
    this.wrongMoves = 0;
    this.showVisitedPaths = true;

    int i = 0;
    ArrayList<Edge> allEdges = new ArrayList<>();

    // The outer for loop iterates through the rows of the Maze
    // EFFECT: Modifies this Maze's grid by adding a row of
    // mazes each time it iterates
    for (int y = 0; y < this.height; y += 1) {
      this.grid.add(new ArrayList<MazeCell>());
      // The inner for loop iterates through the columns of the Maze
      // EFFECT: Modifies the list of edges in this Maze by connecting
      // the left and down cell to the current cell with an edge and assigning
      // a random weight to each edge based on the height and width,
      // modifies this Maze's grid by adding each current cell
      for (int x = 0; x < this.width; x += 1) {
        MazeCell curCell = new MazeCell(new Posn(x, y));
        if (x > 0) {
          allEdges.add(new Edge(this.grid.get(y).get(x - 1), curCell, edgeWeights.get(i)));
          i += 1;
        }

        if (y > 0) {
          allEdges.add(new Edge(this.grid.get(y - 1).get(x), curCell, edgeWeights.get(i)));
          i += 1;
        }

        this.grid.get(y).add(curCell);
      }
    }

    // Creates the spanning tree for this Maze
    this.edgeList.addAll(new CreateTree().apply(allEdges));
    this.edgeIter = this.edgeList.iterator();
  }

  // Constructor that constructs a random maze given a width and height
  Maze(int width, int height) {
    this.grid = new ArrayList<ArrayList<MazeCell>>();
    this.width = width;
    this.height = height;
    this.edgeList = new ArrayList<>();
    this.visitedCells = new ArrayList<>();
    this.path = new ArrayList<>();
    this.createNewSpanningTree();
    this.playerPosn = new Posn(0, 0);
    this.wrongMoves = 0;
    this.showVisitedPaths = true;
  }

  // EFFECT: the maze is rest and a new grid is created by 
  // filling this.grid and connecting the cells horizontally
  // and vertically with edges which are weighted randomly. 
  void createNewSpanningTree() {
    this.resetValues();

    ArrayList<Edge> allEdges = new ArrayList<>();

    // The outer for loop iterates through the rows of the Maze
    // EFFECT: Modifies this Maze's grid by adding a row of
    // mazes each time it iterates
    for (int y = 0; y < this.height; y += 1) {
      this.grid.add(new ArrayList<MazeCell>());
      // The inner for loop iterates through the columns of the Maze
      // EFFECT: Modifies the list of edges in this Maze by connecting
      // the left and down cell to the current cell with an edge and assigning
      // a random weight to each edge based on the height and width,
      // modifies this Maze's grid by adding each current cell
      for (int x = 0; x < this.width; x += 1) {
        MazeCell curCell = new MazeCell(new Posn(x, y));
        if (x > 0) {
          int weight = (int) (Math.random() * this.width * this.height);
          allEdges.add(new Edge(this.grid.get(y).get(x - 1), curCell, weight));
        }

        if (y > 0) {
          int weight = (int) (Math.random() * this.width * this.height);
          allEdges.add(new Edge(this.grid.get(y - 1).get(x), curCell, weight));
        }

        this.grid.get(y).add(curCell);
      }
    }

    // Creates the spanning tree for this Maze
    this.edgeList.addAll(new CreateTree().apply(allEdges));
    this.edgeIter = this.edgeList.iterator();
  }

  // EFFECT: Updates each cell in this Maze such that they have reference
  // to its neighbors via the edges they have
  void createCellRelationship() {
    if (!this.edgeIter.hasNext()) {
      throw new RuntimeException("Iterator cannot produce a non-existent next");
    }
    Edge e = edgeIter.next();
    MazeCell first = e.getFirst();
    MazeCell second = e.getSecond();
    if (first.horizNextToInGrid(second)) {
      first.createRight(second);
      second.createLeft(first);
    }
    else if (first.vertNextToInGrid(second)) {
      first.createDown(second);
      second.createUp(first);
    }
    else {
      throw new RuntimeException("Malformed maze grid");
    }
  }

  // EFFECT: Sets this.solver to search through the list either using
  // Breath-first or Depth-first search based on the given boolean
  void updateSolver(boolean isBFS) {
    MazeCell start = this.grid.get(0).get(0);
    if (isBFS) {
      this.solver = new MazeSolver(this.grid, new BFSGridIterator(start));
    }
    else {
      this.solver = new MazeSolver(this.grid, new DFSGridIterator(start));
    }
  }

  // EFFECT: Removes all of the MazeCells from grid and visitedCells, removes all of the
  // Edges from edgeList and sets the wrongMoves counter back to 0
  private void resetValues() {
    this.edgeList.removeAll(this.edgeList);
    this.grid.removeAll(this.grid);
    this.visitedCells.removeAll(this.visitedCells);
    this.wrongMoves = 0;
  }

  // Draws the maze and returns the drawing. Each cell is 
  // based factors such as whether it is the beginning or end, 
  // whether it has been visited, and whether it is in the solution
  // path once the end of the maze has been reached.
  WorldImage drawMaze(int cellSize) {

    WorldImage result = new EmptyImage();

    // draws a row of cells and draws it below the accumulated
    // drawing of the maze
    for (int y = 0; y < this.grid.size(); y += 1) {
      WorldImage rowResult = new EmptyImage();

      // draws a individual cells next to the accumulated
      // row of cells. draws cells in different colors based
      // on the different factors affecting the individual cells
      for (int x = 0; x < this.grid.get(y).size(); x += 1) {
        MazeCell cell = this.grid.get(y).get(x);

        // draws the cell where the user is at
        if (x == this.playerPosn.x && y == this.playerPosn.y) {
          rowResult = new BesideImage(rowResult, cell.draw(cellSize, Color.red));

        }
        // draws the starting cell
        else if (x == 0 && y == 0) {
          rowResult = new BesideImage(rowResult, cell.draw(cellSize, Color.green));
        }
        // draws the ending cell
        else if (x == this.grid.get(y).size() - 1 && y == this.grid.size() - 1) {
          rowResult = new BesideImage(rowResult, cell.draw(cellSize, Color.pink));
        }
        // draws the cell in the solution path
        else if (this.path.contains(cell)) {
          rowResult = new BesideImage(rowResult, cell.draw(cellSize, Color.blue));
        }
        // draws the cell if it has been visited
        else if (this.visitedCells.contains(cell) && this.showVisitedPaths) {
          rowResult = new BesideImage(rowResult, cell.draw(cellSize, Color.yellow));
        }
        // general case
        else {
          rowResult = new BesideImage(rowResult, cell.draw(cellSize, Color.lightGray));
        }
      }
      result = new AboveImage(result, rowResult);
    }

    return new AboveImage(
        new TextImage("Wrong Moves: " + Integer.toString(this.wrongMoves), 30, Color.black),
        result);
  }

  // EFFECT: adds the next value from the DrawSearchOnTick iterator in  
  // the solver class to this.visitedCells, which returns the values 
  // that the search visits in order so that the searching process can 
  // be animated. If the cell that is added is not in the solution path 
  // stored in solver, increments the wrongMoves counter by 1
  void visitCell() {
    if (!this.visitedCells
        .contains(this.grid.get(this.grid.size() - 1).get(this.grid.get(0).size() - 1))) {
      MazeCell cell = this.solver.nextVisitedOnTick();
      if (!this.solver.pathContains(cell)) {
        this.wrongMoves += 1;
      }
      this.visitedCells.add(cell);
    }
  }

  // EFFECT: adds the next value from the DrawPathOnTick iterator in
  // the solver class to this.path, which returns the cells contained 
  // in the solution path in order in order so that the drawing of the 
  // path can be animated
  void visitOnPath() {
    if (!this.path.contains(this.grid.get(0).get(0))) {
      this.path.add(this.solver.nextInReturnPathOnTick());
    }
  }

  // EFFECT: takes in a position which represents the change in x 
  // and y of the player's position. if the cell of the player 
  // is a neighbor of the cell that is offset by the dPson, then
  // visit that cell and set the player's posn to the offset posn;
  // increments the wrong moves counter by 1 if the targeted cell is 
  // not part of the solution path
  void visitPlayerCell(Posn dPosn) {
    int newPosnX = this.playerPosn.x + dPosn.x;
    int newPosnY = this.playerPosn.y + dPosn.y;

    if (newPosnX >= 0 && newPosnX < this.width && newPosnY >= 0 && newPosnY < this.height) {
      MazeCell curCell = this.grid.get(this.playerPosn.y).get(this.playerPosn.x);
      MazeCell targetedCell = this.grid.get(newPosnY).get(newPosnX);
      if (curCell.isNeighborOf(this.grid.get(newPosnY).get(newPosnX))) {
        this.visitedCells.add(targetedCell);
        this.playerPosn = new Posn(newPosnX, newPosnY);
        if (!this.solver.pathContains(targetedCell)) {
          this.wrongMoves += 1;
        }
      }

    }
  }

  // returns whether the bottom right corner of the maze, which is the
  // end is in visitedCells, showing that the maze has been solved visually
  boolean mazeSolved() {
    return this.visitedCells
        .contains(this.grid.get(this.grid.size() - 1).get(this.grid.get(0).size() - 1));
  }

  // returns whether the path has been completed visually and the player
  // has reached the end, which is determined by whether
  // whether the origin in contained in this.path and the player position is at
  // the
  // bottom right of this grid
  boolean completedMaze() {
    return this.path.contains(this.grid.get(0).get(0))
        && this.playerPosn.equals(new Posn(this.width - 1, this.height - 1));
  }

  // EFFECT: Modifies this Maze's showVisitedPaths to be the opposite
  void togglePaths() {
    this.showVisitedPaths = !this.showVisitedPaths;
  }

  // Determines if this Maze is fully constructed with all of the necessary
  // walls taken down
  boolean isConstructed() {
    return !this.edgeIter.hasNext();
  }

}