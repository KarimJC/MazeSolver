import java.util.*;
import java.util.function.Function;
// function object that compares two given edges, returning 
// a negative number if the first edge weight is less than the second
// edge weight, a positive number if the first edge weight is greater 
// than the second edge weight, and a zero if both edge weights are equal

class CompareEdges implements Comparator<Edge> {
  public int compare(Edge edge1, Edge edge2) {
    return edge1.getWeight() - edge2.getWeight();
  }
}
  
  // function object that returns a minimum spanning tree given a list of 
  // edges
class CreateTree implements Function<ArrayList<Edge>, ArrayList<Edge>> {
  
  public ArrayList<Edge> apply(ArrayList<Edge> input) {
    Kruskals kruskal = new Kruskals(input);
    return kruskal.fillEdgesInTree();
  }
  
}