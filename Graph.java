package undirgraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
//import Heap.*;

import java.util.Queue;
//import java.util.LinkedHashSet;

public class Graph<E> implements IGraph<E>{
	public int sz=0;
	public ArrayList<Node<E>> nodeList = new ArrayList<Node<E>>();
	public ArrayList<Edge<E>> edgeList = new ArrayList<Edge<E>>();
	//public Node<E>[] dfsanc;
	//public Graph(int sz1){
	//	sz=sz1;
	//	dfsanc = (Node<E>[]) new Object[sz];
	//}
	public int size() {
		// TODO Auto-generated method stub
		return sz;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size()==0); 
	}

	public ArrayList<Node<E>> getNodes() {
		// TODO Auto-generated method stub
		return nodeList;
	}
	
	public ArrayList<Edge<E>> getEdges(){
		return edgeList;
	}

	public void addNode(Node<E> curnode) {
		// TODO Auto-generated method stub
		nodeList.add(curnode);
		sz++;
	}

	public int totalDegree() {
		// TODO Auto-generated method stub
		int deg = 0;
		for (int i=0;i<nodeList.size();i++){
			deg += nodeList.get(i).getDegree();
		}
		
		return deg;
	}

	public void DFS(Node<E> start) {
		// TODO Auto-generated method stub
		Node<E> curnode = start;
		System.out.println("DFSnext"+curnode.getId());
		curnode.visited= true;
		int pos = nodeList.indexOf(curnode);
		for (int i=0;i<curnode.getNeighbors().size();i++){
			Node<E> nbr = curnode.getNeighbors().get(i);
			Edge<E> curedge1 = getEdge(nbr,curnode);
			
			if (!nbr.visited){
				edgeList.get(edgeList.indexOf(curedge1)).visited=true;
				DFS(nbr);
			}
			else{
				if (!curedge1.visited ){
					System.out.println("Backedge"+curnode.getId()+":"+nbr.getId());
					edgeList.get(edgeList.indexOf(curedge1)).visited=true;
				}	
			}
		}
		
		
	}
	public void reset(){
		for (int i=0;i<nodeList.size();i++){
			nodeList.get(i).visited=false;
		}
		for (int i=0;i<edgeList.size();i++){
			edgeList.get(i).visited=false;
		}
	}

	public void BFS(Node<E> start) {
		// TODO Auto-generated method stub
		Queue<Node<E>> queue = new LinkedList<Node<E>>();
		queue.add(start); //enqueue
		Node<E> curnode = start;// dequeues an element
		curnode.visited=true;
		
		while(!queue.isEmpty())
		{  	curnode=queue.poll();
			System.out.println("BFS next" + curnode.getId() );
			for (int i=0;i<curnode.getNeighbors().size();i++){
				Node<E> nbr = curnode.getNeighbors().get(i);
				Edge<E> curedge1 = getEdge(nbr,curnode);
				if (!nbr.visited){
					nbr.visited=true;
					queue.add(nbr);
				}
				else{
					System.out.println("Crossedge"+curnode.getId()+":"+nbr.getId());
					
				}
			}
			
			
		}
	}
	
	public boolean adjacent(Node<E> n1, Node<E> n2){
		return (n1.getNeighbors().contains(n2));
	}

	public int[][] getAdjMatrix() {
		// TODO Auto-generated method stub
		int[][] adjMatrix = new int[sz][sz];
		for (int i=0;i<nodeList.size();i++){
			for (int j=0;j<nodeList.size();j++){
				if (adjacent(nodeList.get(i),nodeList.get(j)))
					adjMatrix[i][j]=1;
			}
		}
		return adjMatrix;
	}

	public void printGraph() {
		// TODO Auto-generated method stub
		for (int i=0;i<nodeList.size();i++){
			System.out.println("Node"+nodeList.get(i).getId());
			nodeList.get(i).printNeighbors();
		}
		
	}
	
	public void addEdge(Node<E> st, Node<E> dest, double wt) {
		// TODO Auto-generated method stub
		int pos1 = nodeList.indexOf(st);
		int pos2 = nodeList.indexOf(dest);
		nodeList.get(pos1).addNeighbor(dest);
		nodeList.get(pos2).addNeighbor(st);
		Edge<E> e1 = new Edge<E>(edgeList.size()+1,st,dest);
		e1.setWt(wt);
		edgeList.add(e1);
	}

	public Node<E> getNode(String id) {
		// TODO Auto-generated method stub
		for (int i=0;i<3;i++){
			if (nodeList.get(i).getId().equals(id)) return nodeList.get(i);
		}
		return null;	
		
	}


	public boolean hasCycle(){
		return false;
	}

	public Edge<E> getEdge(Node<E> s, Node<E> d) {
		// TODO Auto-generated method stub
		//Edge<E> temp;
		for (int i=0;i<edgeList.size();i++)
		{
			if ((edgeList.get(i).getStart().getId()==s.getId() && edgeList.get(i).getDest().getId()==d.getId()) ||
					(edgeList.get(i).getStart().getId()==d.getId() && edgeList.get(i).getDest().getId()==s.getId())){
				return edgeList.get(i);
			}
		}
		return null;
	}

	@Override
	public void MSTKruskal() {
		// TODO Auto-generated method stub
		
	}

	

	
}
