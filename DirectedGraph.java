import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

	
public class DirectedGraph<V,E> {
	class Vertex<V>{
		private V data;
		private int vertexListIndex;
		private HashMap<Vertex<V>,Edge<E>> adjList;
		private ArrayList<Vertex<V>> outgoingEndPoints;
		
		public Vertex(V d, int n){
			data=d;
			vertexListIndex=n;
			adjList= new HashMap<Vertex<V>,Edge<E>>();
			outgoingEndPoints= new ArrayList<Vertex<V>>();
		}
		
		public int getSerialNumber(){
			return vertexListIndex;
		}
		
		public V getData(){
			return data;
		}
		
		//Serial number is automatic; So No Setter
		public void setData(V d){
			data=d;
		}
		
		public Edge<E> getEdge(Vertex<V> to) {
			return adjList.get(to);
		}

		public void setAdjList(Vertex<V> to,Edge<E> e) {
			adjList.put(to, e);
		}
		
		public HashMap<Vertex<V>,Edge<E>> getAdjList(){
			return adjList;
		}
		public ArrayList<Vertex<V>> getOutgoingEndPointsList(){
			return outgoingEndPoints;
		}
		
		public int getOutgoingEndPointsListSize(){
			return outgoingEndPoints.size();
		}
		public Vertex<V> getOutgoingEndVertex(int position){
			return outgoingEndPoints.get(position);
		}
	}

	class Edge<E>{
		private int weight;
		private int edgeListIndex;
		private Vertex<V> from;
		private Vertex<V> to;
		//private Vertex<V>[] endpoints;
		private String dfsLabel;
		
		public Edge(Vertex n1 , Vertex n2, int w, int p){
			from=n1;
			to=n2;
			weight=w;
			edgeListIndex=p;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}


		public String getDfsLabel() {
			return dfsLabel;
		}
		public void setDfsLabel(String dfsLabel) {
			this.dfsLabel = dfsLabel;
		}
 } // Nested Classes ends

	//*** Graph representation
	
	ArrayList<Vertex<V>> VSet= new ArrayList<Vertex<V>>();
	ArrayList<Edge<E>> ESet= new ArrayList<Edge<E>>();
	private int[][] adjMatrix;
	private int[][] adjMatrixTranspose;
	
	//*** Constructors
	public DirectedGraph(){}
	
	public DirectedGraph(int numOfVertices){
		
		for(int i=0;i<numOfVertices;i++){
			VSet.add(null);
		}
	}
	
	//Initialize Adj Matrix
	
	public void initializeAdjMatrices(int numOfVertices){
		adjMatrix= new int[numOfVertices][numOfVertices];
		adjMatrixTranspose = new int[numOfVertices][numOfVertices];
		for(int i=0;i<numOfVertices;i++){
			for(int j=0;j<numOfVertices;j++){
				adjMatrix[i][j]=0;
				adjMatrixTranspose[i][j]=0;
			}
		}
	}
	
	public void setEdgeInAdjMatrix(int u,int v){
		adjMatrix[u][v]=1;
	}
	public int isEdgeInAdjMatrix(int u,int v){
		return adjMatrix[u][v];
	}
	
	//*** Add a vertex
	public Vertex<V> addVertice(V data){
		Vertex<V> temp=new Vertex<V>(data,VSet.size());
		VSet.add(VSet.size(), temp);
		return temp;
	}
	
	public Vertex<V> getVertice(int position){
		Vertex<V> temp=VSet.get(position);
		return temp;
	}
	
	//*** Add a vertex at specific Index
	public Vertex<V> addVertice(int position,V data){
		Vertex<V> temp;
		if(VSet.get(position)==null){
			temp=new Vertex<V>(data,position);
			VSet.set(position, temp);
		}else{
			temp=VSet.get(position);
		}
		return temp;
	}
	
	//*** Add an Edge
	public Edge<E> addEdge(Vertex<V> n1,Vertex<V> n2, int weight){
		Edge<E> e= new Edge<E>(n1,n2,weight,ESet.size());
		
		// Adding to Edge List
		ESet.add(ESet.size(),e);
		
		//adding to Adj list of n1
		n1.setAdjList(n2,e);
		//Setting Outgoing End Points
		n1.outgoingEndPoints.add(n2);
		return e;
	}
	
	//*** Print Vertices List
	public void verticeList(){
		System.out.println("Vertices List:");
		for(int i=0;i<VSet.size();i++){
			System.out.println("Serial No: "+ VSet.get(i).getSerialNumber() + "; data: "+ VSet.get(i).getData()); 
		}
	}
	

	
}
