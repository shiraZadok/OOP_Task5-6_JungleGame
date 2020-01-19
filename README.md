![Jungle-Game](https://user-images.githubusercontent.com/58064644/72680447-a2f12300-3ac2-11ea-86b5-a3ebffd9234b.png)

##### Submit: Yirat Peleg & Shira Zadok

# The Jungle Game
In this project we build a Jungle Game.
The game based on Grahs with nodes and edges taht represent the route. The player in the game represented by moneky. There are fruits scattered on the graph randomly and the goal of the game is to eat as much fruit as possible and earn as many points as possible.

In the game there is two option - Automatical game or Manual game. The player is asked before the play what he prefers. If he chose in Manual game - he needs to have a good strategy for navigating the graph best so he can eat as much fruit as possible.
If he chose in automatical game - we have programmed an algorithm that is based on the shortest path that exists between a certain monkey in graph and fruit.
There is few level in the game at a rising difficulty level.

Ia addition, there is an option at the end of the game to see it on GoogleEarth.

The game draw by STDraw class.

# Image of the game

### Select a level 
![choseLevel](https://user-images.githubusercontent.com/58064644/72608537-8ff81a80-392b-11ea-8b20-00d72f9c8ddf.png)

### Select type of game
![select_picture](https://user-images.githubusercontent.com/58064644/72687706-41a07280-3b09-11ea-9f88-dc6262fceba3.png)
### Visual game 
![visualGame](https://user-images.githubusercontent.com/58064644/72686440-92aa6980-3afd-11ea-86f4-1548e1d54203.png)

## Edge class
This class build single edge.

 An object from class Edge contain the follow feature:
* Src - the key of tne node that represent the source.
* Dest - the key of tne node that represent the destination.
* Tag - its flag that change when pass on edge.
* Weight - how much cost to pass on this edge in the path.
* Info.

## Node class
This class build single node.
An object from class Node contain the follow feature:
* Key - it the ID of this node in the graph.
* Tag - its flag that change when pass on node.
* Weight - it represent the cost of the path that take to get from Src to this node that represent the Dest.
* Location - represent the locatiobn of the node on the axis - X, Y, Z.
* Info

## DGraph class
This class build a graph that defined by nodes and edges. It contain a collection of node with use in Node Class and collection of edge with use in Edge Clas.

An object from class DGraph contain the follow feature:
* nodes - is a collection of HashMap of nodes in the graph.
* edges - is a collection of HashMap of edges in the graph.
* nodesCount - is count of the nodes in the graph  
* edgesCount - is count of the edges in the graph
* modeCount - is a count of the changes that implement on the graph.

## Fruits class
This class build single fruit.
An object from class Fruits contain the follow feature:
* Pos - represent the locatiobn of the fruit on the axis - X, Y, Z.
* Value - represent the point that the fruit is worth.
* Type - represent if the fruit is Banana or Apple.
* Pic - represent the picture of the fruit (Banana orApple).

## FruitsList class
An object from the FruitsList class is a collection of Fruits. In order to realize a FruitsList we used the LinkedList class where each node contains fruit.

An object from class FruitsList contain the follow feature:
* frutis - represent the list of the fruits.
* AmountFruits - represents the amount of fruit.
* numGame - represent the specific game service.

## Robots class
This class build single robot.
An object from class Robots contain the follow feature:
* Pos - represent the locatiobn of the robot on the axis - X, Y, Z.
* Value - represents the points earned by the robot.
* Id - represent the ID of the robot
* Pic - represent the picture of the robot (in our case Moneky).
* Dest - represent the destination node that the robot go to.
* Speed - represent the speed of the robot.

## RobotsList class
An object from the RobotsList class is a collection of Robots. In order to realize a RobotsList we used the LinkedList class where each node contains robot object.

An object from class RobotsList contain the follow feature:
* robots - represent the list of the robots.
* AmountRobots - represents the amount of robots.
* numGame - represent the specific game service.

## Graph_Algo
In this class we solved a algorithmic problems as - What is the shortest path in the graph, does the graph is a connective graph, how to read graph from file and how save graph as file.

An object from class Graph_Algo contain the follow feature:
* graph - this is the graph that we perform the algorithms on.

## Game_Algo
In this class we solved a algorithmic problems as - What is the best fruit eating strategy.

This class represent the Automatic game.

An object from class Graph_Algo contain the follow feature:
* GraphGame - is the graph taht represent the route of the game.
* fruits - represent the list of the fruits in the current game.
* server - the game service of current game.
* numOfRobot - the amount of the robots in the current game.

## My Game GUI
This class there are functions that are responsible for the operation of the game.

This class represent the Manual game.

For example, how all the method work when the game is running.

An object from class MyGameGUI contain the follow feature:
* fruits - represent the list of the fruits in the current game.
* GraphGame - is the graph taht represent the route of the game. 
* g - represent the GUI object for drawing the game.
* server - the game service of current game.
* game_algo - represent an object from the Game_ALgo class for used the algoritems.
* b - boolean variable for help in calculating functions.
* manual - boolean variable for help in calculating functions.
* auto - boolean variable for help in calculating functions.
* r

## KML_Logger
This class make a KML-file that in the end of the game the user can see his game on Google Earth application.  

## GUI
This class responsible for drawing all game data to user with help class STDraw.


#GOOD_LUCK!
