package undirgraph;
import java.util.ArrayList;
public interface IGraph<E> {
	int size();
	boolean isEmpty();
	public ArrayList<Node<E>> getNodes();
	public ArrayList<Edge<E>> getEdges();
	public void addNode(Node<E> curnode);
	public int totalDegree();
	public void DFS(Node<E> start);
	public void BFS(Node<E> start);
	public int[][] getAdjMatrix();
	public void printGraph();
	public void addEdge(Node<E> st, Node<E> dest, double wt);
	public Node<E> getNode(String string); 
	public boolean adjacent(Node<E> n1, Node<E> n2);
	public void MSTKruskal();
	public boolean hasCycle();
	public Edge<E> getEdge(Node<E> s, Node<E> d);
	public void reset();
}
