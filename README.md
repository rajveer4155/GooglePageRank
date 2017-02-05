##### GooglePageRank
Google Page Rank Algorithm(1998 version) implementation in Java

### Usage
$ java GooglePageRank <iterations> <initialvalue> <filenameContainingGraph>


### Arguments Information

# A.) Iterations specify to run the ’algorithm’ for a fixed number of iterations or for a fixed errorrate (an alias for iterations); 
1. An iterations equal to 0 corresponds to a default errorrate of 10^−5 . 
2. A -1, -2, ...., -6 etc for iterations becomes an errorrate of 10^−1 , 10^−2, . . . , 10^−6 respectively. 


# B.) Initialvalue sets the intial vector values for the algorithm.It can take any of the value  from the set {-2, -1, 0, 1}. 

1. If its -2, the hub/authority values are initialized as 1/(sqrt(n)), for all the nodes, where n is the number of vertices.

2. If its -1, the hub/authority values are initialized as 1/(n), for all the nodes, where n is the number of vertices.

3. If its 0, the hub/authority values are initialized as 0 for all the vertices.

4. If its 1, the hub/authority values are initialized as 1 for all the vertices. 

# filenameContainingGraph contains the representation of a graph.

1. The first line represents the number of vertices (n) of the graph.

2. The second number of the first line represents the number of edges (m) of the graph.

3. Remaining line (u v) represents the edge from vertice u to vertice v in the graph.
