![](https://images.app.goo.gl/pr8GZ4pZ2KeX2mwH7.jpg)
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
* Src - the key of tne node that represent thr source.
* Dest - the key of tne node that represent thr destination.
* Tag - it like flag that change if we pass in this edge during the path.
* Weight - how much cost to pass on this edge in the path.
* Info.

## DGraph Class
This class build a graph that defined by nodes and edges.
It contain a collection of node with use in Node Class and collection of edge with use in Edge Clas.

##### Main Methods in DGraph:
