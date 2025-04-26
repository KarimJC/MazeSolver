import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import javalib.worldimages.EmptyImage;
import tester.Tester;
import javalib.worldimages.*;

class ExamplesMaze {

    Maze maze2x2;
    Maze maze4x3;
  
    WorldImage image;
  
    MazeCell cell0;
    MazeCell cell1;
    MazeCell cell2;
    MazeCell cell3;
  
    ArrayList<ArrayList<MazeCell>> cell4x3List;
    ArrayList<Edge> edge4x3List;
  
    Maze random100x60;
  
    WorldImage initialMaze4x3;
  
    void testBigBang(Tester t) {
        this.initData();
        MazeWorld w = new MazeWorld(new Maze(10, 10));
        w.bigBang(1000, 1000, 0.2);
      }


    void initData() {
      ArrayList<Integer> maze2x2Weights = new ArrayList<>(Arrays.asList(15, 5, 5, 20));
      ArrayList<Integer> maze4x4Weights = new ArrayList<>(
          Arrays.asList(1, 1, 2, 3, 1, 7, 16, 8, 15, 4, 3, 4, 1, 3, 5, 2, 5));
      this.maze2x2 = new Maze(maze2x2Weights, 2, 2);
      this.maze4x3 = new Maze(maze4x4Weights, 4, 3);
  
      this.random100x60 = new Maze(100, 60);
  
      this.cell0 = new MazeCell(new Posn(0, 0));
      this.cell1 = new MazeCell(new Posn(1, 0));
      this.cell2 = new MazeCell(new Posn(0, 1));
      this.cell3 = new MazeCell(new Posn(0, 2));
      this.cell4x3List = new ArrayList<>();
      for (int y = 0; y < 3; y += 1) {
        this.cell4x3List.add(new ArrayList<MazeCell>());
        for (int x = 0; x < 4; x += 1) {
          this.cell4x3List.get(y).add(new MazeCell(new Posn(x, y)));
        }
      }
  
      this.edge4x3List = new ArrayList<>();
  
      this.cell4x3List.get(0).get(0).createRight(this.cell4x3List.get(0).get(1));
      this.cell4x3List.get(0).get(0).createDown(this.cell4x3List.get(1).get(0));
      this.cell4x3List.get(0).get(1).createLeft(this.cell4x3List.get(0).get(0));
      this.cell4x3List.get(0).get(1).createRight(this.cell4x3List.get(0).get(2));
  
      this.cell4x3List.get(0).get(2).createLeft(this.cell4x3List.get(0).get(1));
      this.cell4x3List.get(0).get(2).createRight(this.cell4x3List.get(0).get(3));
  
      this.cell4x3List.get(0).get(3).createLeft(this.cell4x3List.get(0).get(2));
      this.cell4x3List.get(0).get(3).createDown(this.cell4x3List.get(1).get(3));
  
      this.cell4x3List.get(1).get(0).createRight(this.cell4x3List.get(1).get(1));
      this.cell4x3List.get(1).get(0).createDown(this.cell4x3List.get(2).get(0));
      this.cell4x3List.get(1).get(0).createUp(this.cell4x3List.get(0).get(0));
  
      this.cell4x3List.get(1).get(1).createLeft(this.cell4x3List.get(1).get(0));
      this.cell4x3List.get(1).get(1).createDown(this.cell4x3List.get(2).get(1));
  
      this.cell4x3List.get(1).get(2).createDown(this.cell4x3List.get(2).get(2));
  
      this.cell4x3List.get(1).get(3).createUp(this.cell4x3List.get(0).get(3));
  
      this.cell4x3List.get(2).get(0).createUp(this.cell4x3List.get(1).get(0));
  
      this.cell4x3List.get(2).get(1).createRight(this.cell4x3List.get(2).get(2));
      this.cell4x3List.get(2).get(1).createUp(this.cell4x3List.get(1).get(1));
  
      this.cell4x3List.get(2).get(2).createRight(this.cell4x3List.get(2).get(3));
      this.cell4x3List.get(2).get(2).createUp(this.cell4x3List.get(1).get(2));
      this.cell4x3List.get(2).get(2).createLeft(this.cell4x3List.get(2).get(1));
  
      this.cell4x3List.get(2).get(3).createLeft(this.cell4x3List.get(2).get(2));
  
      this.edge4x3List
          .add(new Edge(this.cell4x3List.get(0).get(0), this.cell4x3List.get(0).get(1), 1));
      this.edge4x3List
          .add(new Edge(this.cell4x3List.get(0).get(1), this.cell4x3List.get(0).get(2), 1));
      this.edge4x3List
          .add(new Edge(this.cell4x3List.get(0).get(2), this.cell4x3List.get(0).get(3), 2));
      this.edge4x3List
          .add(new Edge(this.cell4x3List.get(0).get(0), this.cell4x3List.get(1).get(0), 3));
      this.edge4x3List
          .add(new Edge(this.cell4x3List.get(0).get(3), this.cell4x3List.get(1).get(3), 4));
      this.edge4x3List
          .add(new Edge(this.cell4x3List.get(1).get(0), this.cell4x3List.get(1).get(1), 1));
      this.edge4x3List
          .add(new Edge(this.cell4x3List.get(1).get(0), this.cell4x3List.get(2).get(0), 3));
      this.edge4x3List
          .add(new Edge(this.cell4x3List.get(1).get(1), this.cell4x3List.get(2).get(1), 1));
      this.edge4x3List
          .add(new Edge(this.cell4x3List.get(1).get(2), this.cell4x3List.get(2).get(2), 5));
      this.edge4x3List
          .add(new Edge(this.cell4x3List.get(2).get(1), this.cell4x3List.get(2).get(2), 3));
      this.edge4x3List
          .add(new Edge(this.cell4x3List.get(2).get(2), this.cell4x3List.get(2).get(3), 2));
  
      this.initialMaze4x3 = new EmptyImage();
  
      for (int y = 0; y < 3; y += 1) {
        WorldImage rowResult = new EmptyImage();
        for (int x = 0; x < 4; x += 1) {
          MazeCell cell = new MazeCell(new Posn(x, y));
          if (x == 0 && y == 0) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.red));
          }
          else if (x == 3 && y == 2) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.pink));
          }
          else {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.lightGray));
          }
        }
        this.initialMaze4x3 = new AboveImage(this.initialMaze4x3, rowResult);
      }
      this.initialMaze4x3 = new AboveImage(new TextImage("Wrong Moves: 0", 30, Color.black),
          this.initialMaze4x3);
  
    }
    
    /*
    // tests that the drawing of the grid is done correctly
    // before the cell relationships are created and after they
    // are created, showing that the minimum spanning tree is
    // being created and the cell relationships are being established
    // correctly
    void testCellListIs4x3Grid(Tester t) {
      this.initData();
      t.checkExpect(this.maze4x3.drawMaze(10), this.initialMaze4x3);
  
      WorldImage validMazeResult = new EmptyImage();
      for (int y = 0; y < 3; y += 1) {
        WorldImage rowResult = new EmptyImage();
        for (int x = 0; x < 4; x += 1) {
          MazeCell cell = this.cell4x3List.get(y).get(x);
          if (x == 0 && y == 0) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.red));
          }
          else if (x == 3 && y == 2) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.pink));
          }
          else {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.lightGray));
          }
        }
        validMazeResult = new AboveImage(validMazeResult, rowResult);
      }
      validMazeResult = new AboveImage(new TextImage("Wrong Moves: 0", 30, Color.black),
          validMazeResult);
      while (!this.maze4x3.isConstructed()) {
        this.maze4x3.createCellRelationship();
      }
      t.checkExpect(this.maze4x3.drawMaze(10), validMazeResult);
  
    }
    //-----------------------------------------------\\
  
    // MAZE TESTS
  
    void testVisitCellAndUpdateSolver(Tester t) {
      this.initData();
      t.checkExpect(this.maze4x3.drawMaze(10), this.initialMaze4x3);
      WorldImage validBFSMaze = new EmptyImage();
  
      for (int y = 0; y < 3; y += 1) {
        WorldImage rowResult = new EmptyImage();
        for (int x = 0; x < 4; x += 1) {
          MazeCell cell = this.cell4x3List.get(y).get(x);
          if (x == 0 && y == 0) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.red));
          }
          else if (x == 3 && y == 2) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.pink));
          }
          else if (x == 1 && y == 0) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.yellow));
          }
          else if (x == 0 && y == 1) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.yellow));
          }
          else {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.lightGray));
          }
        }
        validBFSMaze = new AboveImage(validBFSMaze, rowResult);
      }
      validBFSMaze = new AboveImage(new TextImage("Wrong Moves: 1", 30, Color.black), validBFSMaze);
      // Creates the cell relationships in the maze
      while (!this.maze4x3.isConstructed()) {
        this.maze4x3.createCellRelationship();
      }
      // Tests update solver by seeing if the visitCell
      // method behave in BFS and making sure it is highlighted on tick
      this.maze4x3.updateSolver(true);
      // Calls visit cell three times, the first call it visits the top left which is
      // colored red
      // cause it represents the player cell, the second call it visits the cell
      // to the right to color it yellow, and the third call it visits the cell down
      // of the topLeft
      // to color it yellow
      this.maze4x3.visitCell();
      this.maze4x3.visitCell();
      this.maze4x3.visitCell();
  
      t.checkExpect(this.maze4x3.drawMaze(10), validBFSMaze);
  
      this.initData();
      WorldImage validDFSMaze = new EmptyImage();
  
      for (int y = 0; y < 3; y += 1) {
        WorldImage rowResult = new EmptyImage();
        for (int x = 0; x < 4; x += 1) {
          MazeCell cell = this.cell4x3List.get(y).get(x);
          if (x == 0 && y == 0) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.red));
          }
          else if (x == 3 && y == 2) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.pink));
          }
          else if (x == 1 && y == 0 || x == 2 && y == 0) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.yellow));
          }
          else {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.lightGray));
          }
        }
        validDFSMaze = new AboveImage(validDFSMaze, rowResult);
      }
      validDFSMaze = new AboveImage(new TextImage("Wrong Moves: 2", 30, Color.black), validDFSMaze);
      // Creates the cell relationships in the maze
      while (!this.maze4x3.isConstructed()) {
        this.maze4x3.createCellRelationship();
      }
      // Tests update solver by seeing if the visitCell
      // method behave in DFS and making sure it is highlighted on tick
      this.maze4x3.updateSolver(false);
      // Calls visit cell three times, the first call it visits the top left which is
      // colored red
      // cause it represents the player cell, the second call it visits the cell
      // to the right to color it yellow, and the third call it visits the cell right
      // of the cell that
      // was called second to color it yellow
      this.maze4x3.visitCell();
      this.maze4x3.visitCell();
      this.maze4x3.visitCell();
  
      t.checkExpect(this.maze4x3.drawMaze(10), validDFSMaze);
  
    }
  
    void testVisitOnPath(Tester t) {
      this.initData();
      t.checkExpect(this.maze4x3.drawMaze(10), this.initialMaze4x3);
      WorldImage validMaze = new EmptyImage();
  
      for (int y = 0; y < 3; y += 1) {
        WorldImage rowResult = new EmptyImage();
        for (int x = 0; x < 4; x += 1) {
          MazeCell cell = this.cell4x3List.get(y).get(x);
          if (x == 0 && y == 0) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.red));
          }
          else if (x == 3 && y == 2) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.pink));
          }
          else if (x == 0 && y == 1 || x == 1 && y == 1 || x == 1 && y == 2 || x == 2 && y == 2) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.blue));
          }
          else {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.lightGray));
          }
        }
        validMaze = new AboveImage(validMaze, rowResult);
      }
      validMaze = new AboveImage(new TextImage("Wrong Moves: 0", 30, Color.black), validMaze);
      while (!this.maze4x3.isConstructed()) {
        this.maze4x3.createCellRelationship();
      }
      // Sets the solver for the maze, which does not matter if it is BFS or DFS since
      // the path
      // is the same for both
      this.maze4x3.updateSolver(true);
      // Visits each cell in the winning path and must be colored blue
      this.maze4x3.visitOnPath();
      this.maze4x3.visitOnPath();
      this.maze4x3.visitOnPath();
      this.maze4x3.visitOnPath();
      this.maze4x3.visitOnPath();
      this.maze4x3.visitOnPath();
  
      t.checkExpect(this.maze4x3.drawMaze(10), validMaze);
  
    }
  
    void testVisitPlayerCell(Tester t) {
      this.initData();
      t.checkExpect(this.maze4x3.drawMaze(10), this.initialMaze4x3);
      WorldImage validMaze = new EmptyImage();
      for (int y = 0; y < 3; y += 1) {
        WorldImage rowResult = new EmptyImage();
        for (int x = 0; x < 4; x += 1) {
          MazeCell cell = this.cell4x3List.get(y).get(x);
          if (x == 0 && y == 0) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.green));
          }
          else if (x == 3 && y == 2) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.pink));
          }
          else if (x == 1 && y == 1) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.red));
          }
          else if (x == 0 && y == 1 || x == 0 && y == 2) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.yellow));
          }
          else {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.lightGray));
          }
        }
        validMaze = new AboveImage(validMaze, rowResult);
      }
      validMaze = new AboveImage(new TextImage("Wrong Moves: 1", 30, Color.black), validMaze);
      // Creates the cell relationships in the maze
      while (!this.maze4x3.isConstructed()) {
        this.maze4x3.createCellRelationship();
      }
      // Sets the solver for the maze and does not matter if it is DFS or BFS since
      // the
      // path is the same and the user is controlling the maze
      this.maze4x3.updateSolver(false);
      // Visits each cell in the winning path and must be colored blue
      // Player position moves down twice, up once, then right once
      this.maze4x3.visitPlayerCell(new Posn(0, 1));
      this.maze4x3.visitPlayerCell(new Posn(0, 1));
      this.maze4x3.visitPlayerCell(new Posn(0, -1));
      this.maze4x3.visitPlayerCell(new Posn(1, 0));
  
      t.checkExpect(this.maze4x3.drawMaze(10), validMaze);
      // Player attempts to move to the right, but there is a border so the maze
      // should still be the same validMaze
      this.maze4x3.visitPlayerCell(new Posn(1, 0));
      t.checkExpect(this.maze4x3.drawMaze(10), validMaze);
  
    }
  
    void testMazeSolved(Tester t) {
      this.initData();
      t.checkExpect(this.maze2x2.mazeSolved(), false);
      // Creates the cell relationships in the maze
      while (!this.maze2x2.isConstructed()) {
        this.maze2x2.createCellRelationship();
      }
      // Sets the solver for the maze and does not matter if it is DFS or BFS since
      // the
      // path is the same and the user is controlling it
      this.maze2x2.updateSolver(false);
      // Moves the player position down and to the right which is the end of the maze
      this.maze2x2.visitPlayerCell(new Posn(0, 1));
      this.maze2x2.visitPlayerCell(new Posn(1, 0));
      t.checkExpect(this.maze2x2.mazeSolved(), true);
  
      t.checkExpect(this.maze4x3.mazeSolved(), false);
      // Creates the cell relationships in the maze
      while (!this.maze4x3.isConstructed()) {
        this.maze4x3.createCellRelationship();
      }
      // Sets the solver for the maze to be BFS
      this.maze4x3.updateSolver(true);
      // Calls visitCell 11 times as it takes 11 visits in a BFS to reach the end
      for (int i = 0; i < 11; i += 1) {
        this.maze4x3.visitCell();
      }
      t.checkExpect(this.maze4x3.mazeSolved(), true);
  
      this.initData();
      t.checkExpect(this.maze4x3.mazeSolved(), false);
      // Creates the cell relationships in the maze
      while (!this.maze4x3.isConstructed()) {
        this.maze4x3.createCellRelationship();
      }
      // Sets the solver for the maze to be DFS
      this.maze4x3.updateSolver(false);
      // Calls visitCell 10 times as it takes 10 visits in a DFS to reach the end
      for (int i = 0; i < 10; i += 1) {
        this.maze4x3.visitCell();
      }
      t.checkExpect(this.maze4x3.mazeSolved(), true);
  
    }
  
    void testCompletedMaze(Tester t) {
      this.initData();
      t.checkExpect(this.maze4x3.completedMaze(), false);
      // Creates the cell relationships in the maze
      while (!this.maze4x3.isConstructed()) {
        this.maze4x3.createCellRelationship();
      }
      // Sets the solver to DFS but does not matter as the path is the same and the
      // user is controlling
      // the cells being visited
      this.maze4x3.updateSolver(false);
      // Moves the player such that it reaches the end of the maze
      this.maze4x3.visitPlayerCell(new Posn(0, 1));
      this.maze4x3.visitPlayerCell(new Posn(0, 1));
      this.maze4x3.visitPlayerCell(new Posn(0, -1));
      this.maze4x3.visitPlayerCell(new Posn(1, 0));
      this.maze4x3.visitPlayerCell(new Posn(0, 1));
      this.maze4x3.visitPlayerCell(new Posn(1, 0));
      this.maze4x3.visitPlayerCell(new Posn(1, 0));
  
      // Creates the winning path for the maze
      for (int i = 0; i < 6; i += 1) {
        this.maze4x3.visitOnPath();
      }
      t.checkExpect(this.maze4x3.completedMaze(), true);
  
    }
  
    void testIsConstructed(Tester t) {
      this.initData();
      t.checkExpect(this.maze4x3.isConstructed(), false);
      // Calls cell relationships 11 times as it breaks down 11 different walls
      // to fully be constructed
      for (int i = 0; i < 11; i += 1) {
        this.maze4x3.createCellRelationship();
      }
      t.checkExpect(this.maze4x3.isConstructed(), true);
    }
  
    void testTogglePaths(Tester t) {
      this.initData();
      WorldImage showsVisitedCells = new EmptyImage();
  
      for (int y = 0; y < 3; y += 1) {
        WorldImage rowResult = new EmptyImage();
        for (int x = 0; x < 4; x += 1) {
          MazeCell cell = this.cell4x3List.get(y).get(x);
          if (x == 0 && y == 0) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.red));
          }
          else if (x == 3 && y == 2) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.pink));
          }
          else if (x == 1 && y == 0 || x == 2 && y == 0) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.yellow));
          }
          else {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.lightGray));
          }
        }
        showsVisitedCells = new AboveImage(showsVisitedCells, rowResult);
      }
      showsVisitedCells = new AboveImage(new TextImage("Wrong Moves: 2", 30, Color.black),
          showsVisitedCells);
      // Creates the cell relationships in the maze
      while (!this.maze4x3.isConstructed()) {
        this.maze4x3.createCellRelationship();
      }
      this.maze4x3.updateSolver(false);
      this.maze4x3.visitCell();
      this.maze4x3.visitCell();
      this.maze4x3.visitCell();
  
      // Tests for a picture with highlighted visited cells
      t.checkExpect(this.maze4x3.drawMaze(10), showsVisitedCells);
      WorldImage noShowVisitedCells = new EmptyImage();
  
      for (int y = 0; y < 3; y += 1) {
        WorldImage rowResult = new EmptyImage();
        for (int x = 0; x < 4; x += 1) {
          MazeCell cell = this.cell4x3List.get(y).get(x);
          if (x == 0 && y == 0) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.red));
          }
          else if (x == 3 && y == 2) {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.pink));
          }
          else {
            rowResult = new BesideImage(rowResult, cell.draw(10, Color.lightGray));
          }
        }
        noShowVisitedCells = new AboveImage(noShowVisitedCells, rowResult);
      }
      noShowVisitedCells = new AboveImage(new TextImage("Wrong Moves: 2", 30, Color.black),
          noShowVisitedCells);
  
      this.maze4x3.togglePaths();
      // Tests to see if there are no highlighted visited cells
      t.checkExpect(this.maze4x3.drawMaze(10), noShowVisitedCells);
  
    }
  
    //-----------------------------------------------\\
  
    // MAZESOLVER TESTS
  
    void testPathContains(Tester t) {
      this.initData();
      MazeSolver m = new MazeSolver(this.cell4x3List,
          new BFSGridIterator(this.cell4x3List.get(0).get(0)));
      t.checkExpect(m.pathContains(this.cell4x3List.get(0).get(0)), true);
      t.checkExpect(m.pathContains(this.cell4x3List.get(0).get(1)), false);
      t.checkExpect(m.pathContains(this.cell4x3List.get(0).get(2)), false);
      t.checkExpect(m.pathContains(this.cell4x3List.get(0).get(3)), false);
      t.checkExpect(m.pathContains(this.cell4x3List.get(1).get(0)), true);
      t.checkExpect(m.pathContains(this.cell4x3List.get(1).get(1)), true);
      t.checkExpect(m.pathContains(this.cell4x3List.get(1).get(2)), false);
      t.checkExpect(m.pathContains(this.cell4x3List.get(1).get(3)), false);
      t.checkExpect(m.pathContains(this.cell4x3List.get(2).get(0)), false);
      t.checkExpect(m.pathContains(this.cell4x3List.get(2).get(1)), true);
      t.checkExpect(m.pathContains(this.cell4x3List.get(2).get(2)), true);
      t.checkExpect(m.pathContains(this.cell4x3List.get(2).get(3)), true);
  
      this.initData();
      MazeSolver m1 = new MazeSolver(this.cell4x3List,
          new DFSGridIterator(this.cell4x3List.get(0).get(0)));
      t.checkExpect(m1.pathContains(this.cell4x3List.get(0).get(0)), true);
      t.checkExpect(m1.pathContains(this.cell4x3List.get(0).get(1)), false);
      t.checkExpect(m1.pathContains(this.cell4x3List.get(0).get(2)), false);
      t.checkExpect(m1.pathContains(this.cell4x3List.get(0).get(3)), false);
      t.checkExpect(m1.pathContains(this.cell4x3List.get(1).get(0)), true);
      t.checkExpect(m1.pathContains(this.cell4x3List.get(1).get(1)), true);
      t.checkExpect(m1.pathContains(this.cell4x3List.get(1).get(2)), false);
      t.checkExpect(m1.pathContains(this.cell4x3List.get(1).get(3)), false);
      t.checkExpect(m1.pathContains(this.cell4x3List.get(2).get(0)), false);
      t.checkExpect(m1.pathContains(this.cell4x3List.get(2).get(1)), true);
      t.checkExpect(m1.pathContains(this.cell4x3List.get(2).get(2)), true);
      t.checkExpect(m1.pathContains(this.cell4x3List.get(2).get(3)), true);
    }
  
    void testNextVisitedOnTick(Tester t) {
      this.initData();
      MazeSolver m = new MazeSolver(this.cell4x3List,
          new BFSGridIterator(this.cell4x3List.get(0).get(0)));
  
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(0).get(0));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(0).get(1));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(1).get(0));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(0).get(2));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(1).get(1));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(2).get(0));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(0).get(3));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(2).get(1));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(1).get(3));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(2).get(2));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(2).get(3));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(1).get(2));
  
      // Once there are no more values to return, returns the last value in the
      // list of outputs repeatedly
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(1).get(2));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(1).get(2));
      t.checkExpect(m.nextVisitedOnTick(), this.cell4x3List.get(1).get(2));
  
      this.initData();
      MazeSolver m1 = new MazeSolver(this.cell4x3List,
          new DFSGridIterator(this.cell4x3List.get(0).get(0)));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(0).get(0));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(0).get(1));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(0).get(2));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(0).get(3));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(1).get(3));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(1).get(0));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(1).get(1));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(2).get(1));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(2).get(2));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(2).get(3));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(1).get(2));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(2).get(0));
  
      // Once there are no more values to return, returns the last cell in the
      // list of outputs repeatedly
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(2).get(0));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(2).get(0));
      t.checkExpect(m1.nextVisitedOnTick(), this.cell4x3List.get(2).get(0));
    }
  
    void testNextInReturnPathOntick(Tester t) {
  
      // solution paths are the same for both BFS and DFS
      this.initData();
      MazeSolver m = new MazeSolver(this.cell4x3List,
          new BFSGridIterator(this.cell4x3List.get(0).get(0)));
      t.checkExpect(m.nextInReturnPathOnTick(), this.cell4x3List.get(2).get(3));
      t.checkExpect(m.nextInReturnPathOnTick(), this.cell4x3List.get(2).get(2));
      t.checkExpect(m.nextInReturnPathOnTick(), this.cell4x3List.get(2).get(1));
      t.checkExpect(m.nextInReturnPathOnTick(), this.cell4x3List.get(1).get(1));
      t.checkExpect(m.nextInReturnPathOnTick(), this.cell4x3List.get(1).get(0));
      t.checkExpect(m.nextInReturnPathOnTick(), this.cell4x3List.get(0).get(0));
  
      // Once there are no more values to return, returns the last cell in the
      // path list, which is the origin, repeatedly
      t.checkExpect(m.nextInReturnPathOnTick(), this.cell4x3List.get(0).get(0));
      t.checkExpect(m.nextInReturnPathOnTick(), this.cell4x3List.get(0).get(0));
      t.checkExpect(m.nextInReturnPathOnTick(), this.cell4x3List.get(0).get(0));
  
      this.initData();
      MazeSolver m1 = new MazeSolver(this.cell4x3List,
          new DFSGridIterator(this.cell4x3List.get(0).get(0)));
      t.checkExpect(m1.nextInReturnPathOnTick(), this.cell4x3List.get(2).get(3));
      t.checkExpect(m1.nextInReturnPathOnTick(), this.cell4x3List.get(2).get(2));
      t.checkExpect(m1.nextInReturnPathOnTick(), this.cell4x3List.get(2).get(1));
      t.checkExpect(m1.nextInReturnPathOnTick(), this.cell4x3List.get(1).get(1));
      t.checkExpect(m1.nextInReturnPathOnTick(), this.cell4x3List.get(1).get(0));
      t.checkExpect(m1.nextInReturnPathOnTick(), this.cell4x3List.get(0).get(0));
  
      // Once there are no more values to return, returns the last cell in the
      // path list, which is the origin, repeatedly
      t.checkExpect(m1.nextInReturnPathOnTick(), this.cell4x3List.get(0).get(0));
      t.checkExpect(m1.nextInReturnPathOnTick(), this.cell4x3List.get(0).get(0));
      t.checkExpect(m1.nextInReturnPathOnTick(), this.cell4x3List.get(0).get(0));
    }
  
    //-----------------------------------------------\\
  
    // EDGE TESTS
    void testEdge(Tester t) {
      this.initData();
      MazeCell c1 = new MazeCell(new Posn(0, 0));
      MazeCell c2 = new MazeCell(new Posn(0, 1));
      MazeCell c3 = new MazeCell(new Posn(0, 2));
      Edge e1 = new Edge(c1, c2, 5);
      t.checkExpect(e1.getFirst(), c1);
      t.checkExpect(e1.getSecond(), c2);
      t.checkExpect(e1.getWeight(), 5);
      
      Edge e2 = new Edge(c2, c3, 10);
      t.checkExpect(e2.getFirst(), c2);
      t.checkExpect(e2.getSecond(), c3);
      t.checkExpect(e2.getWeight(), 10);
    }
  
    //-----------------------------------------------\\
  
    // MAZECELL TESTS
    void testHorizNextToInGrid(Tester t) {
      this.initData();
      //vertical neighbors
      t.checkExpect(this.cell4x3List.get(0).get(0).horizNextToInGrid(
          this.cell4x3List.get(1).get(0)), false);
      t.checkExpect(this.cell4x3List.get(1).get(0).horizNextToInGrid(
          this.cell4x3List.get(0).get(0)), false);
      
      // diagonal cells
      t.checkExpect(this.cell4x3List.get(0).get(0).horizNextToInGrid(
          this.cell4x3List.get(1).get(1)), false);
      t.checkExpect(this.cell4x3List.get(1).get(1).horizNextToInGrid(
          this.cell4x3List.get(0).get(0)), false);
      
      // horizontal neighbors
      t.checkExpect(this.cell4x3List.get(1).get(0).horizNextToInGrid(
          this.cell4x3List.get(1).get(1)), true);
      t.checkExpect(this.cell4x3List.get(1).get(1).horizNextToInGrid(
          this.cell4x3List.get(1).get(0)), true);
      
      // next to each other, but separated by a wall
      t.checkExpect(this.cell4x3List.get(1).get(1).horizNextToInGrid(
          this.cell4x3List.get(1).get(2)), true);
      t.checkExpect(this.cell4x3List.get(1).get(2).horizNextToInGrid(
          this.cell4x3List.get(1).get(1)), true);
    }
  
    void testVertNextToInGrid(Tester t) {
      this.initData();
      //vertical neighbors
      t.checkExpect(this.cell4x3List.get(0).get(0).vertNextToInGrid(
          this.cell4x3List.get(1).get(0)), true);
      t.checkExpect(this.cell4x3List.get(1).get(0).vertNextToInGrid(
          this.cell4x3List.get(0).get(0)), true);
      
      // diagonal cells
      t.checkExpect(this.cell4x3List.get(0).get(0).vertNextToInGrid(
          this.cell4x3List.get(1).get(1)), false);
      t.checkExpect(this.cell4x3List.get(1).get(1).vertNextToInGrid(
          this.cell4x3List.get(0).get(0)), false);
      
      // horizontal neighbors
      t.checkExpect(this.cell4x3List.get(1).get(0).vertNextToInGrid(
          this.cell4x3List.get(1).get(1)), false);
      t.checkExpect(this.cell4x3List.get(1).get(1).vertNextToInGrid(
          this.cell4x3List.get(1).get(0)), false);
      
      // vertically next to each other, but separated by a wall
      t.checkExpect(this.cell4x3List.get(0).get(1).vertNextToInGrid(
          this.cell4x3List.get(1).get(1)), true);
      t.checkExpect(this.cell4x3List.get(1).get(1).vertNextToInGrid(
          this.cell4x3List.get(0).get(1)), true);
    }
  
    // tests drawing for cells with different numbers of
    // neighbors and a maze that contains only one MazeCell
    void testCellDraw(Tester t) {
      this.initData();
      WorldImage fourBorders = new RectangleImage(10, 10, OutlineMode.SOLID, Color.lightGray);
  
      fourBorders = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
          new LineImage(new Posn(10, 0), Color.BLACK), 0, 0, fourBorders);
      fourBorders = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          new LineImage(new Posn(10, 0), Color.BLACK), 0, 0, fourBorders);
      fourBorders = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE,
          new LineImage(new Posn(0, 10), Color.BLACK), 0, 0, fourBorders);
      fourBorders = new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE,
          new LineImage(new Posn(0, 10), Color.BLACK), 0, 0, fourBorders);
  
      t.checkExpect(this.cell2.draw(10, Color.lightGray), fourBorders);
  
      this.cell0.createRight(cell2);
  
      WorldImage threeBorders = new RectangleImage(10, 10, OutlineMode.SOLID, Color.lightGray);
      threeBorders = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
          new LineImage(new Posn(10, 0), Color.BLACK), 0, 0, threeBorders);
      threeBorders = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          new LineImage(new Posn(10, 0), Color.BLACK), 0, 0, threeBorders);
      threeBorders = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE,
          new LineImage(new Posn(0, 10), Color.BLACK), 0, 0, threeBorders);
  
      t.checkExpect(this.cell0.draw(10, Color.lightGray), threeBorders);
  
      MazeCell up = new MazeCell(new Posn(0, -1));
      this.cell0.createUp(up);
  
      WorldImage twoBorders = new RectangleImage(10, 10, OutlineMode.SOLID, Color.lightGray);
      twoBorders = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          new LineImage(new Posn(10, 0), Color.BLACK), 0, 0, twoBorders);
      twoBorders = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE,
          new LineImage(new Posn(0, 10), Color.BLACK), 0, 0, twoBorders);
  
      t.checkExpect(this.cell0.draw(10, Color.lightGray), twoBorders);
  
      this.cell0.createDown(this.cell1);
  
      WorldImage oneBorder = new RectangleImage(10, 10, OutlineMode.SOLID, Color.lightGray);
      oneBorder = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE,
          new LineImage(new Posn(0, 10), Color.BLACK), 0, 0, oneBorder);
  
      t.checkExpect(this.cell0.draw(10, Color.lightGray), oneBorder);
  
      MazeCell left = new MazeCell(new Posn(-1, 0));
      this.cell0.createLeft(left);
      left.createRight(this.cell0);
  
      WorldImage noBorders = new RectangleImage(10, 10, OutlineMode.SOLID, Color.lightGray);
  
      t.checkExpect(this.cell0.draw(10, Color.lightGray), noBorders);
  
      WorldImage fourBordersRed = new RectangleImage(10, 10, OutlineMode.SOLID, Color.red);
  
      fourBordersRed = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.TOP,
          new LineImage(new Posn(10, 0), Color.BLACK), 0, 0, fourBordersRed);
      fourBordersRed = new OverlayOffsetAlign(AlignModeX.CENTER, AlignModeY.BOTTOM,
          new LineImage(new Posn(10, 0), Color.BLACK), 0, 0, fourBordersRed);
      fourBordersRed = new OverlayOffsetAlign(AlignModeX.LEFT, AlignModeY.MIDDLE,
          new LineImage(new Posn(0, 10), Color.BLACK), 0, 0, fourBordersRed);
      fourBordersRed = new OverlayOffsetAlign(AlignModeX.RIGHT, AlignModeY.MIDDLE,
          new LineImage(new Posn(0, 10), Color.BLACK), 0, 0, fourBordersRed);
  
      Maze m1 = new Maze(1, 1);
  
      // color of the cell is red because the player is located at 0,0
      t.checkExpect(m1.drawMaze(10), new AboveImage(new TextImage("Wrong Moves: 0", 30, Color.black),
          new AboveImage(new EmptyImage(), new BesideImage(new EmptyImage(), fourBordersRed))));
    }
  
    void testIsNeighborOf(Tester t) {
      this.initData();
  
      // vertical neighbors
      t.checkExpect(this.cell4x3List.get(0).get(0).isNeighborOf(this.cell4x3List.get(1).get(0)),
          true);
      t.checkExpect(this.cell4x3List.get(1).get(0).isNeighborOf(this.cell4x3List.get(0).get(0)),
          true);
  
      // diagonal cells
      t.checkExpect(this.cell4x3List.get(0).get(0).isNeighborOf(this.cell4x3List.get(1).get(1)),
          false);
      t.checkExpect(this.cell4x3List.get(1).get(1).isNeighborOf(this.cell4x3List.get(0).get(0)),
          false);
  
      // horizontal neighbors
      t.checkExpect(this.cell4x3List.get(1).get(0).isNeighborOf(this.cell4x3List.get(1).get(1)),
          true);
      t.checkExpect(this.cell4x3List.get(1).get(1).isNeighborOf(this.cell4x3List.get(1).get(0)),
          true);
  
      // next to each other, but separated by a wall, so not neighbors
      t.checkExpect(this.cell4x3List.get(1).get(1).isNeighborOf(this.cell4x3List.get(1).get(2)),
          false);
      t.checkExpect(this.cell4x3List.get(1).get(2).isNeighborOf(this.cell4x3List.get(1).get(1)),
          false);
    }
  
    //-----------------------------------------------\\
  
    // FUNCTION OBJECT TESTS
    void testCompareEdges(Tester t) {
      this.initData();
      Edge e1 = new Edge(new MazeCell(new Posn(0, 1)), new MazeCell(new Posn(0, 0)), 20);
      Edge e2 = new Edge(new MazeCell(new Posn(0, 1)), new MazeCell(new Posn(0, 0)), 10);
      Edge e3 = new Edge(new MazeCell(new Posn(0, 1)), new MazeCell(new Posn(0, 0)), 2);
  
      t.checkExpect(new CompareEdges().compare(e1, e2), 10);
      t.checkExpect(new CompareEdges().compare(e2, e1), -10);
      t.checkExpect(new CompareEdges().compare(e1, e3), 18);
      t.checkExpect(new CompareEdges().compare(e3, e1), -18);
      t.checkExpect(new CompareEdges().compare(e3, e2), -8);
      t.checkExpect(new CompareEdges().compare(e2, e3), 8);
  
    }
  
    // In testCreateTree we are also testing all the functionality of the KruskalAlgorithm class,
    // which allows for the creation of the minimum spanning tree
    void testCreateTree(Tester t) {
      this.initData();
      ArrayList<Edge> initialEdges = new ArrayList<>();
      initialEdges.add(new Edge(this.cell4x3List.get(0).get(0), this.cell4x3List.get(0).get(1), 1));
      initialEdges.add(new Edge(this.cell4x3List.get(0).get(1), this.cell4x3List.get(0).get(2), 1));
      initialEdges.add(new Edge(this.cell4x3List.get(0).get(2), this.cell4x3List.get(0).get(3), 2));
      initialEdges.add(new Edge(this.cell4x3List.get(0).get(0), this.cell4x3List.get(1).get(0), 3));
      initialEdges.add(new Edge(this.cell4x3List.get(0).get(3), this.cell4x3List.get(1).get(3), 4));
      initialEdges.add(new Edge(this.cell4x3List.get(1).get(0), this.cell4x3List.get(1).get(1), 1));
      initialEdges.add(new Edge(this.cell4x3List.get(1).get(0), this.cell4x3List.get(2).get(0), 3));
      initialEdges.add(new Edge(this.cell4x3List.get(1).get(1), this.cell4x3List.get(2).get(1), 1));
      initialEdges.add(new Edge(this.cell4x3List.get(1).get(2), this.cell4x3List.get(2).get(2), 5));
      initialEdges.add(new Edge(this.cell4x3List.get(2).get(1), this.cell4x3List.get(2).get(2), 3));
      initialEdges.add(new Edge(this.cell4x3List.get(2).get(2), this.cell4x3List.get(2).get(3), 2));
      initialEdges.add(new Edge(this.cell4x3List.get(0).get(1), this.cell4x3List.get(1).get(1), 7));
      initialEdges.add(new Edge(this.cell4x3List.get(0).get(2), this.cell4x3List.get(1).get(2), 8));
      initialEdges.add(new Edge(this.cell4x3List.get(1).get(1), this.cell4x3List.get(1).get(2), 16));
      initialEdges.add(new Edge(this.cell4x3List.get(1).get(2), this.cell4x3List.get(1).get(3), 15));
      initialEdges.add(new Edge(this.cell4x3List.get(2).get(0), this.cell4x3List.get(2).get(1), 4));
      initialEdges.add(new Edge(this.cell4x3List.get(1).get(3), this.cell4x3List.get(2).get(3), 5));
  
      ArrayList<Edge> spanningTree = new CreateTree().apply(initialEdges);
  
      // checks whether every value within spanningTree, which
      // had Kruskal's performed on it is in the expected set
      // of edges that make up the maze
      for (int i = 0; i < spanningTree.size(); i += 1) {
        t.checkOneOf(spanningTree.get(i), this.edge4x3List.get(0), this.edge4x3List.get(1),
            this.edge4x3List.get(2), this.edge4x3List.get(3), this.edge4x3List.get(4),
            this.edge4x3List.get(5), this.edge4x3List.get(6), this.edge4x3List.get(7),
            this.edge4x3List.get(8), this.edge4x3List.get(9), this.edge4x3List.get(10));
      }
    }
  
    void testBFSGridIterator(Tester t) {
      this.initData();
      BFSGridIterator bfs = new BFSGridIterator(this.cell4x3List.get(0).get(0));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(0).get(0));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(0).get(1));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(1).get(0));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(0).get(2));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(1).get(1));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(2).get(0));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(0).get(3));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(2).get(1));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(1).get(3));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(2).get(2));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(2).get(3));
      t.checkExpect(bfs.hasNext(), true);
      t.checkExpect(bfs.next(), this.cell4x3List.get(1).get(2));
      t.checkExpect(bfs.hasNext(), false);
    }
  
    void testDFSGridIterator(Tester t) {
      this.initData();
      DFSGridIterator dfs = new DFSGridIterator(this.cell4x3List.get(0).get(0));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(0).get(0));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(0).get(1));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(0).get(2));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(0).get(3));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(1).get(3));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(1).get(0));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(1).get(1));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(2).get(1));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(2).get(2));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(2).get(3));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(1).get(2));
      t.checkExpect(dfs.hasNext(), true);
      t.checkExpect(dfs.next(), this.cell4x3List.get(2).get(0));
      t.checkExpect(dfs.hasNext(), false);
    }
  
    void testDrawOnTickIterator(Tester t) {
      this.initData();
      ArrayList<MazeCell> cells = new ArrayList<>();
      cells.add(new MazeCell(new Posn(0, 0)));
      cells.add(new MazeCell(new Posn(1, 0)));
      cells.add(new MazeCell(new Posn(5, 0)));
      cells.add(new MazeCell(new Posn(3, 0)));
  
      DrawOnTickIterator iter1 = new DrawOnTickIterator(cells);
  
      t.checkExpect(iter1.hasNext(), true);
      t.checkExpect(iter1.next(), cells.get(0));
      t.checkExpect(iter1.hasNext(), true);
      t.checkExpect(iter1.next(), cells.get(1));
      t.checkExpect(iter1.hasNext(), true);
      t.checkExpect(iter1.next(), cells.get(2));
      t.checkExpect(iter1.hasNext(), true);
      t.checkExpect(iter1.next(), cells.get(3));
      t.checkExpect(iter1.hasNext(), false);
  
      cells.add(new MazeCell(new Posn(4, 0)));
  
      t.checkExpect(iter1.hasNext(), true);
      t.checkExpect(iter1.next(), cells.get(4));
      t.checkExpect(iter1.hasNext(), false);
  
    }
  
    void testAddToListsConsumer(Tester t) {
      this.initData();
  
      MazeCell cell1 = new MazeCell(new Posn(0, 0));
      MazeCell cell2 = new MazeCell(new Posn(0, 0));
  
      ArrayList<MazeCell> alrSeen = new ArrayList<>();
      ArrayList<MazeCell> workList = new ArrayList<>();
  
      ArrayList<MazeCell> expectedAlrSeen = new ArrayList<>();
      ArrayList<MazeCell> expectedWorkList = new ArrayList<>();
  
      // adding first value
      expectedAlrSeen.add(cell1);
      expectedWorkList.add(cell1);
      new AddToListsConsumer(workList, alrSeen).accept(cell1);
      t.checkExpect(alrSeen, expectedAlrSeen);
      t.checkExpect(workList, expectedWorkList);
  
      // adding first value again, does nothing
      new AddToListsConsumer(workList, alrSeen).accept(cell1);
      t.checkExpect(alrSeen, expectedAlrSeen);
      t.checkExpect(workList, expectedWorkList);
  
      // adding second value
      expectedAlrSeen.add(cell2);
      expectedWorkList.add(cell2);
      new AddToListsConsumer(workList, alrSeen).accept(cell2);
      t.checkExpect(alrSeen, expectedAlrSeen);
      t.checkExpect(workList, expectedWorkList);
  
      // removes a value from the workList, but not the already
      // seen list, so it does not add the cell to the lists
      workList.remove(0);
      expectedWorkList.remove(0);
      new AddToListsConsumer(workList, alrSeen).accept(cell1);
      t.checkExpect(alrSeen, expectedAlrSeen);
      t.checkExpect(workList, expectedWorkList);
  
      // removes a value from the already seen list, but not the
      // workList, which means that cell2 is added to the lists,
      // showing that a cell's presence in the already seen list
      // determines whether it be added to the two lists
      alrSeen.remove(1);
      expectedWorkList.add(cell2);
      new AddToListsConsumer(workList, alrSeen).accept(cell2);
      t.checkExpect(alrSeen, expectedAlrSeen);
      t.checkExpect(workList, expectedWorkList);
    }
  
    //-----------------------------------------------\\
  
    // OTHER TESTS
    void testExceptions(Tester t) {
      this.initData();
      ArrayList<Integer> shortEdgeWeights = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
      t.checkConstructorException(
          new IllegalArgumentException("Did not give enough edgeweights for a pre-formed Maze."),
          "Maze", shortEdgeWeights, 10, 10);
      
    }
  */
  }
  