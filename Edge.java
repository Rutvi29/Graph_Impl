package undirgraph;

public class Edge<E> {
	private int id;
	private Node<E> stnode;
	private Node<E> destnode;
	double wt=0.0;
	boolean visited = false;
	public Edge(int id1, Node<E> st, Node<E> dest){
		id = id1;
		stnode = st;
		destnode = dest;
	}
	
	public Node<E> getStart(){
		return stnode;
	}
	
	public Node<E> getDest(){
		return destnode;
	}
	
	public double getWt(){
		return wt;
	}
	
	public void setWt(double w1){
		wt=w1;
	}
	public Node<E> opposite(Node<E> cur){
		if (cur == stnode)
			return destnode;
		else
			return stnode;
	}
}
