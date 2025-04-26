import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


// represents a class that finds the solution to a maze and stores it
// such that the maze can have access to it. 
// A maze should always know the solution and path, even if it has not shown it
// gas been visibly visited yet.
public class MazeSolver {

  // represents the grid of cells that create the maze
  private final ArrayList<ArrayList<MazeCell>> grid;

  // iterator that iterates over the grid recursively to
  // apply a search algorithm which will visit every cell in the maze
  private final Iterator<MazeCell> mazeIter;

  // stores the output of the iterator that performs a search on the grid
  // in the order that the cells are visited
  private final ArrayList<MazeCell> iterOutput;

  // iterator that, when called, returns the next value in the iterOutput
  private final DrawOnTickIterator drawSearchOnTick;

  // iterator that, when called, returns the next value in the iterOutput
  private final DrawOnTickIterator drawPathOnTick;

  // stores the path from the end of the maze to the beginning
  private final ArrayList<MazeCell> pathList;

  // MazeSolverConstructor
  MazeSolver(ArrayList<ArrayList<MazeCell>> grid, Iterator<MazeCell> mazeIter) {
    this.grid = grid;
    this.mazeIter = mazeIter;
    this.iterOutput = new ArrayList<>();
    this.pathList = new ArrayList<MazeCell>();
    this.solveMaze();
    this.drawSearchOnTick = new DrawOnTickIterator(this.iterOutput);
    this.drawPathOnTick = new DrawOnTickIterator(this.pathList);
  }

  // EFFECT: Updates this MazeSolver's iterOutput to the contents of this mazeIter
  // and updates this pathList to be the correct path to the end of the Maze,
  // which
  // solves this grid and keeps track of the cells that were visited
  private void solveMaze() {
    this.storeSearchOutput();
    this.storePathAsList();
  }

  // EFFECT: Modifies this MazeSolver's iterOutput to store all the cells that
  // were visited
  // when solving the maze based upon this mazeIter
  private void storeSearchOutput() {
    // Iterates through the given maze iterator, adding to this iterOutput every
    // time
    while (this.mazeIter.hasNext()) {
      this.iterOutput.add(this.mazeIter.next());
    }
  }

  // EFFECT: Modifies the pathList of this MazeSolver such that it contains all
  // the MazeCells
  // that need to be visited to get from the start to the end of the maze,
  // including the starting
  // cell
  private void storePathAsList() {
    HashMap<MazeCell, MazeCell> pathHash = this.storePathHashMap();
    for (int i = this.iterOutput.size() - 1; i >= 0; i -= 1) {
      if (pathHash.containsKey(this.iterOutput.get(i))) {
        this.pathList.add(this.iterOutput.get(i));
      }
    }
    // adds the starting cell to the pathList
    this.pathList.add(this.iterOutput.get(0));
  }

  // Produces a HashMap where each MazeCell key has a MazeCell value where the
  // value represents
  // the cell that the key had come from based on this MazeSolver
  private HashMap<MazeCell, MazeCell> storePathHashMap() {
    MazeCell cell = this.grid.get(this.grid.size() - 1).get(this.grid.get(0).size() - 1);
    HashMap<MazeCell, MazeCell> pathHash = new HashMap<>();
    return this.storeCameFrom(cell, pathHash);
  }

  // Produces a HashMap where each MazeCell key has a MazeCell value where the
  // value
  // represents the cell that the key had come from based on this MazeSolver
  // Accumulator Statements:

  // cell and pathHash are accumulators.
  // cell represents the key value of the HashMap.
  // cell is initialized as the bottom right cell of the grid. cell is modified to
  // be
  // the cameFrom of the given cell in the method if they are not equal
  // cell is used to create and put values into the HashMap to be used in finding
  // the
  // path from the start to the end of the maze

  // pathHash represents the HashMap that has every cell used in solving
  // referenced to its cameFrom cell.
  // pathHash is initialized as an empty HashMap and is modified where each cell
  // key is and their cameFrom value is put into the HashMap
  // pathHash is used to accumulate the entire HashMap which is used to store the
  // list of cells that represents the path from the start to the end of the maze
  private HashMap<MazeCell, MazeCell> storeCameFrom(MazeCell cell,
      HashMap<MazeCell, MazeCell> pathHash) {
    MazeCell cameFrom = cell;
    int startIndex = this.iterOutput.indexOf(cell);

    // EFFECT: Iterates through the list of visited cells starting at the index of
    // the cell key
    // and traversing backwards until a neighbor is found and modifies the pathHash
    // to
    // put the given key with its found value
    for (int i = startIndex; i >= 0; i -= 1) {
      if (cell.isNeighborOf(this.iterOutput.get(i))) {
        pathHash.put(cell, this.iterOutput.get(i));
        cameFrom = this.iterOutput.get(i);
      }
    }

    // Recursively calls this method until the given cell has a value equal to its
    // cameFrom
    // meaning it is at the start
    if (!cell.equals(cameFrom)) {
      return this.storeCameFrom(cameFrom, pathHash);
    }
    else {
      return pathHash;
    }
  }

  // Determines if the pathList in this MazeSolver contains the
  // given cell
  boolean pathContains(MazeCell cell) {
    return this.pathList.contains(cell);
  }

  // Returns the next MazeCell out of all the total visited cells used for solving
  // that must be rendered on tick depending on the drawSearchOnTick of this
  // MazeSolver
  MazeCell nextVisitedOnTick() {
    if (this.drawSearchOnTick.hasNext()) {
      return this.drawSearchOnTick.next();
    }
    else {
      return this.iterOutput.get(this.iterOutput.size() - 1);
    }
  }

  // Returns the next MazeCell out of all the cells in the pathList used for
  // solving
  // that must be rendered on tick depending on the drawPathOntick of this
  // MazeSolver
  MazeCell nextInReturnPathOnTick() {
    if (this.drawPathOnTick.hasNext()) {
      return this.drawPathOnTick.next();
    }
    else {
      return this.pathList.get(pathList.size() - 1);
    }
  }

}
