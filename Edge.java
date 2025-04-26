// represents an edge in a graph that connects a MazeCell to another MazeCell
class Edge {

    // represents the first cell or the from cell in an edge that is
    // being connected to the second cell
    private final MazeCell cell1;
  
    // represents the second cell or the to cell that is being connected
    // from the first cell
    private final MazeCell cell2;
  
    // represents the weight of this Edge
    private final int weight;
  
    // Constructor
    Edge(MazeCell cell1, MazeCell cell2, int weight) {
      this.cell1 = cell1;
      this.cell2 = cell2;
      this.weight = weight;
    }
  
    // Gets the first cell of this Edge as needed by the KruskalAlgorithm and Maze
    // classes.
    // KruskalAlgorithm needs to get the first cell of this Edge in order to create
    // a HashMap of MazeCell keys and MazeCell values to keep track of the representatives 
    // of each cell to avoid cycles when creating a minimum spanning tree.
    // Since KruskalAlgorithm works with a list of edges, it needs access to each edges 
    // cells to ensure this functionality works.
    // Maze class in the createsCellRelationship() needs access to this Edge's first
    // cell in order to link each cell together. The Maze class has a list of edges
    // of a Maze, and each cell ought to have reference to its neighboring cells, so
    // to connect each cell together in a grid, it needs to get the first cell of
    // this edge
    MazeCell getFirst() {
      return this.cell1;
    }
  
    // Gets the second cell of this Edge as needed by the KruskalAlgorithm and Maze
    // classes.
    // KruskalAlgorithm needs to get the second cell of this Edge in order to create
    // a HashMap of MazeCell keys and MazeCell values to keep track of the representatives 
    // of each cell to avoid cycles when creating a minimum spanning tree.
    // Since KruskalAlgorithm works with a list of edges, it needs access to each edges 
    // cells to ensure this functionality works.
    // Maze class in the createsCellRelationship() needs access to this Edge's second
    // cell in order to link each cell together. The Maze class has a list of edges
    // of a Maze, and each cell ought to have reference to its neighboring cells, so
    // to connect each cell together in a grid, it needs to get the second cell of
    // this edge
    MazeCell getSecond() {
      return this.cell2;
    }
  
    // Gets the weight of this Edge as needed in the CompareEdges function object to
    // be used in the KruskalAlgorithm class. The KruskalAlgorithm class needs
    // access to this field as to use Kruskal's algorithm, it must work on a list of 
    // edges that are sorted from least weight to most weight, and therefore needs 
    // access to the weights.
    int getWeight() {
      return this.weight;
    }
  }
