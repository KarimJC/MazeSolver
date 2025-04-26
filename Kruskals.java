import java.util.*;

// Class that performs Kruskal's Algorithm on a given 
// list of edges
public class Kruskals {

  // stores the representatives for each unique maze cell contained
  // within the edges in workList. The representative of a MazeCell 
  // is another MazeCell. The values of the HashMap represent a 
  // MazeCell's representative and the key represent the unique 
  // MazeCells in the list of edges
  private final HashMap<MazeCell, MazeCell> representatives;
  private final ArrayList<Edge> workList;
  
  Kruskals(ArrayList<Edge> input) {
    this.representatives = new HashMap<MazeCell, MazeCell>();
    this.workList = input;
    this.workList.sort(new CompareEdges());
  
    // Adds every unique cell within the edges in the workList
    // to this.representatives. Every MazeCell is initially stored
    // with itself as its representative
    for (Edge e : this.workList) {
      if (!this.representatives.containsKey(e.getFirst())) {
        this.representatives.put(e.getFirst(), e.getFirst());
      }
      if (!this.representatives.containsKey(e.getSecond())) {
        this.representatives.put(e.getSecond(), e.getSecond());
      }
    }
  }
  
  // finds the highest representative of this cell. Does this 
  // through recursion: if the given cell's representative is 
  // not itself, calls this method on this cell's representative
  // until a cell's representative is itself, in which case that cell
  // is returned
  private MazeCell find(MazeCell cell) {
    if (this.representatives.get(cell).equals(cell)) {
      return cell;
    }
    else {
      return this.find(this.representatives.get(cell));
    }
  }
  
  // sets the representative of the cell2's representative to
  // be the same as cell1's representative
  private void union(MazeCell cell1, MazeCell cell2) {
    this.representatives.put(this.find(cell2), this.find(cell1));
  }
  
  // Connects every MazeCell in the edges in the list of edges 
  // by unioning the cells until they all form a tree, meaning that 
  // there exists a MazeCell for which find on every cell in
  // representatives will return that MazeCell. Every time a union
  // is formed, adds the edge between the two cells that the union 
  // method is called on to the result list and returns the result list
  ArrayList<Edge> fillEdgesInTree() {
    int connections = 0;
    int index = 0;
    ArrayList<Edge> result = new ArrayList<>();
  
    // While every MazeCell is not connected, meaning that the
    // number of connections is less than the size of the
    // representatives HashMap, if the two MazeCells in an
    // edge in EdgeList are not unioned, unions them and adds
    // the edge between them to the result. Increments the number
    // of connections every time cells are unioned and increments
    // the index every time
    while (connections < this.representatives.size() - 1) {
      Edge cur = this.workList.get(index);

      if (!this.find(cur.getFirst()).equals(this.find(cur.getSecond()))) {
        this.union(cur.getFirst(), cur.getSecond());
        result.add(new Edge(cur.getFirst(), cur.getSecond(), cur.getWeight()));
        connections += 1;
      }
      index += 1;  
    }
  
    return result;
  }
}
  
