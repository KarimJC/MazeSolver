import java.util.*;
import java.util.function.Consumer;

// iterator that iterates through all the cells that would be visited
// in a BFS search of a grid in order
class BFSGridIterator implements Iterator<MazeCell> {

    // represents a queue of cells that may
    // need to be visited in this BFSGridIterator
    private final Queue<MazeCell> workList;
  
    // represents the list of cells that have already been seen or visited
    // in this BFSGridIterator
    private final ArrayList<MazeCell> alreadySeen;
  
    // Constructor
    BFSGridIterator(MazeCell origin) {
      this.alreadySeen = new ArrayList<>();
      this.workList = new LinkedList<MazeCell>();
      this.workList.add(origin);
      this.alreadySeen.add(origin);
    }
  
    // Determines if this BFSGridIterator has a next cell
    public boolean hasNext() {
      return workList.size() > 0;
    }
  
    // Returns the next cell of this BFSGridIterator
    // EFFECT: Modifies this BFSGridIterator's workList and alreadySeen
    // by adding the curCell's neighbors if it has not been visited
    public MazeCell next() {
      if (!this.hasNext()) {
        throw new RuntimeException("Cannot call next as this BFSGridIterator does not have a next.");
      }
  
      MazeCell curCell = workList.remove();
  
      curCell.right.ifPresent(new AddToListsConsumer(workList, alreadySeen));
      curCell.down.ifPresent(new AddToListsConsumer(workList, alreadySeen));
      curCell.left.ifPresent(new AddToListsConsumer(workList, alreadySeen));
      curCell.up.ifPresent(new AddToListsConsumer(workList, alreadySeen));
  
      return curCell;
    }
  
  }
  
  // iterator that iterates through all the cells that would be visited
  // in a DFS search of a grid in order
  class DFSGridIterator implements Iterator<MazeCell> {
  
    // represents a stack of cells that may
    // need to be visited in this DFSGridIterator
    private final Stack<MazeCell> workList;
  
    // represents the list of cells that have already been seen or visited
    // in this DFSGridIterator
    private final ArrayList<MazeCell> alreadySeen;
  
    // constructor
    DFSGridIterator(MazeCell origin) {
      this.workList = new Stack<MazeCell>();
      this.alreadySeen = new ArrayList<>();
      this.workList.add(origin);
      this.alreadySeen.add(origin);
    }
  
    // Determines if this DFSGridIterator has a next
    public boolean hasNext() {
      return workList.size() > 0;
    }
  
    // Returns the next cell of this DFSGridIterator
    // EFFECT: Modifies this DFSGridIterator's workList and alreadySeen
    // by adding the curCell's neighbors if it has not been visited
    public MazeCell next() {
      if (!this.hasNext()) {
        throw new RuntimeException("Cannot call next as this DFSGridIterator does not have a next.");
      }
  
      MazeCell curCell = workList.pop();
  
      curCell.up.ifPresent(new AddToListsConsumer(workList, alreadySeen));
      curCell.left.ifPresent(new AddToListsConsumer(workList, alreadySeen));
      curCell.down.ifPresent(new AddToListsConsumer(workList, alreadySeen));
      curCell.right.ifPresent(new AddToListsConsumer(workList, alreadySeen));
  
      return curCell;
    }
  
  }
  
  // represents a consumer that when given a cell, adds it to a workList
  // and alreadySeen list if that cell is not already seen or visited
class AddToListsConsumer implements Consumer<MazeCell> {
  
    // represents a collection of cells that may
    // need to be visited
    private final Collection<MazeCell> workList;
  
    // represents the list of cells that have already been seen or visited
    private final ArrayList<MazeCell> alreadySeen;
  
    // constructor
    AddToListsConsumer(Collection<MazeCell> workList, ArrayList<MazeCell> alreadySeen) {
      this.workList = workList;
      this.alreadySeen = alreadySeen;
    }
  
    // EFFECT: Modifies the workList and alreadySeen to have the given cell added
    // if the given cell has not been visited yet
    public void accept(MazeCell cell) {
      if (!this.alreadySeen.contains(cell)) {
        this.workList.add(cell);
        this.alreadySeen.add(cell);
      }
    }
  }
  
  // represents an iterator used for drawing on tick that iterates
  // through all the cells that must be drawn 
  class DrawOnTickIterator implements Iterator<MazeCell> {
  
    // the index of this DrawOnTickIterator that is used
    // to get the current cell that must be drawn
    int index;
  
    // a list of cells that represents all the cells that must be drawn
    // in this DrawOnTickIterator
    ArrayList<MazeCell> list;
  
    // Constructor
    DrawOnTickIterator(ArrayList<MazeCell> list) {
      this.list = list;
      this.index = 0;
    }
  
    // Determines if this DrawOnTickIterator has a next
    public boolean hasNext() {
      return index < list.size();
    }
  
    // Gets the next value of this DrawOnTickIterator
    // EFFECT: Modifies the index of this DrawOnTickIterator to
    // be incremented by one after each iteration
    public MazeCell next() {
      if (!this.hasNext()) {
        throw new RuntimeException(
            "Cannot call next as this DrawOnTickIterator does not have a next.");
      }
      MazeCell curCell = list.get(index);
      index += 1;
      return curCell;
    }
  }
  