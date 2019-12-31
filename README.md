![](https://static.inilah.com/data/berita/foto/2554271.jpg)
##### Submit: Yirat Peleg & Shira Zadok

# The Maze Of Waze
This project deal with graphs. There is some algoritims that applied on the graph and help to navigate on him.

For example, the user can find the shortest path between two vertex on the graph, one vertex represent the source and the second represent the destination.
The algoritim can returns the length between src to dest or the vertexes that should pass in the shortest path.

In addition, the user can draw the grph with the class GUI.

## Node Class:
This class build single node.
An object from class Node contain the follow feature:
* Key - it the ID of this node in the graph.
* Tag - it like flag that change if we pass in this node during the path.
* Weight - it represent the cost of the path that take to get from Src to this node that represent the Dest.  
* Location - represent the locatiobn of the node on the axis - X, Y, Z.
* Info.

## Edge Class
This class build single edge.
An object from class Edge contain the follow feature:
* Src - the key of tne node that represent the source.
* Dest - the key of tne node that represent the destination.
* Tag - it like flag that change if we pass in this edge during the path.
* Weight - how much cost to pass on this edge in the path.
* Info.

## DGraph Class
This class build a graph that defined by nodes and edges.
It contain a collection of node with use in Node Class and collection of edge with use in Edge Clas.
### Main Methods in DGraph:
* AddNode: This method add nodes to the grph.
* Connect: This method make the edge in the graph.
* RemoveNode: This method delete certain node and all the edges that connected it.
* RemoveEdge: This method delete certain edge from the graph.
* GetV: This method bring the nodes collection of the graph.
* GetE: This method bring the edges collection of the graph.

## GUI Class
This class draw the the grap.

## Graph_Algo Class
In this class we solved a algorithmic problems as - What is the shortest path in the graph, does the graph is a connective graph, how to read graph from file and how save graph as file.
### Main Methods in Graph_Algo:
* Init: This method get a graph from the use or from file and make him to the algo graph.
* Copy: This method compute a deep copy of this graph.
* Save: This method saves the graph to a file.
* IsConnected: This method check if exist a valid path from EVREY node to each.
* ShortestPathDist: This method returns the length of the shortest path between src to dest.
* ShortestPath: This method the vertexes that should pass in the shortest path.
* TSP: This method get group of Vertexes and return the most optional Path to visit each node in the targets List.

 ## Image for example of our graph:
