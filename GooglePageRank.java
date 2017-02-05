import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class GooglePageRank {
	public static String file;
	public static int iterations=-1;
	public static double initialVal;
	public static int numOfVertices;
	public static int numOfEdges;
	public static double[] Src; // Src Matrix
	public static double[] D; // D Values
	public double errorRate=0.00005;
	public static double d=0.85;
	DirectedGraph<Integer,Integer> dgraph;
	
	public GooglePageRank(){
		
	}
	
	public void initializeGraph() throws FileNotFoundException, IOException{
		
		//** Reading Graph Data from file for initialization

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    int i=0;
		   
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	String[] str=line.split(" ");
		    	
		    	if(i==0){ //If first line
		    		numOfVertices=Integer.parseInt(str[0]);
		    		System.out.println("Num of Vertices: "+numOfVertices);
		    		numOfEdges=Integer.parseInt(str[1]);
		    		System.out.println("Num of Edges: "+numOfEdges);
		    		//Also Initializing AdjMatrix
		    		dgraph=new DirectedGraph<Integer,Integer>(numOfVertices);
		    		dgraph.initializeAdjMatrices(numOfVertices);
		    		
		    	}else{ // For all other Lines
		    		
		    		int u=Integer.parseInt(str[0]);
		    		int v=Integer.parseInt(str[1]);
		    		System.out.println(u+"-"+v);
		    		
		    		//** Adding Vertices
		    		//dgraph.addVertice(u, null);
		    		//dgraph.addVertice(v, null);
		    		
		    		//** Adding Edge in Adj List
		    		dgraph.addEdge(dgraph.addVertice(u, u),dgraph.addVertice(v, v),-1);
		    		
		    		//** Adding Edge in AdjMatrix
		    		dgraph.setEdgeInAdjMatrix(u, v);
		    	}
		    	i++;
		    } //while ends
		} // try ends	
	}
	public void initializePageRank(){
		//Initializing Source and D Arrays
		Src = new double[numOfVertices];
		D = new double[numOfVertices];
		
		if(numOfVertices>10){
			initialVal=-1.00000000000000;
		}
		
		if(initialVal==0){
			initialVal=0.00000000000000;
		}else if(initialVal==1){
			initialVal=1.00000000000000;
		}else if(initialVal==-1){
			initialVal=(1.00000000000000)/numOfVertices;
		}else if(initialVal==-2){
			initialVal=(1.00000000000000)/(Math.sqrt(numOfVertices));
		}
		
		if(iterations!=0 && iterations <0){
			errorRate=Math.pow(1, iterations);
		}
		
		//Initializing Authority and Hub Values
		//double scaleFactor=Math.sqrt((numOfVertices*initialVal));
		for(int i=0;i<numOfVertices;i++){
			D[i]=initialVal;
		}
		
	}
	
	//*** Checking Convergence
	public boolean checkConvergence(){
		boolean flag=true;
		for(int i=0;i<numOfVertices;i++){
			if(Math.abs((Src[i]-D[i]))>errorRate){
				flag=false;
			}
		} 
		return flag;
	}
	
	//*** Starting PageRank
	public void runPageRank(){
		int iter=0;
		System.out.print("Base : 0 :");
		for(int i = 0; i < numOfVertices; i++) {
            System.out.print(" P[ "+i+"]="+D[i]); 
        }
		System.out.println();
		
		do{
			++iter;
			System.out.println("Iteration: "+iter);
			
			//Saving Previous D[i] in Src[i]
			for(int i = 0; i < numOfVertices; i++) {
				Src[i]=D[i];
	        }
			
			//Doing D[i] =0
			for(int i=0;i<numOfVertices;i++){
				D[i]=0;
			}
			
			for(int i=0;i<numOfVertices;i++){
				int m=dgraph.getVertice(i).getOutgoingEndPointsListSize();
				for(int k=0;k<m;k++){
					int j=dgraph.getVertice(i).getOutgoingEndVertex(k).getSerialNumber();
					D[j]=D[j]+Src[dgraph.getVertice(i).getSerialNumber()]/m;
				}
			}
			
			for(int i = 0; i < numOfVertices; i++) {
				D[i]= (d*D[i]) + ((1-d)/numOfVertices);
	        }
			
			System.out.print("Iter : "+iter+" :");
			for(int i = 0; i < numOfVertices; i++) {
				System.out.print(" P[ "+i+"]="+D[i]);  
	        }
			System.out.println();
			
		}while(!checkConvergence());
		
		System.out.println("Converged on Iteration: "+iter);
	}
	
	
	//*** Main Function
	public static void main(String args[]) throws IOException{
		
		  if (args.length < 1) {
	            System.out.println("Error!Please provide file name.");
	            System.out.println("Example to run:");
	            System.out.println("pgrank <iterations> <initialvalue> <graphfilename>");
	            return;
	        }
	        if (!new File(args[2]).exists()) {
	            System.out.println("File not found. Please enter an existing file name.");
	            return;
	        }
	     
	     //*** Reading Values
	        
	     iterations=Integer.parseInt(args[0]);
	 	 initialVal=Double.parseDouble(args[1]);
	 	 file=args[2];
	 	 
	     //*** Checking if file is empty
	     File fr = new File(file);
	     if(fr.length()==0){
	    	 System.out.println("Error! File is Empty");
	    	 return;
	     }
	     
	     GooglePageRank pagerank=new GooglePageRank();
	     pagerank.initializeGraph();
	     pagerank.initializePageRank();
	     pagerank.runPageRank();
			
	} // Main ends
}
