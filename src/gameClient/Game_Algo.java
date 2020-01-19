package gameClient;

import Server.game_service;
import algorithms.Graph_Algo;
import dataStructure.*;
import element.Fruits;
import element.FruitsList;
import element.Robots;
import element.RobotsList;
import utils.Point3D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Game_Algo {

    private DGraph GraphGame;
    private FruitsList fruits;
    private RobotsList robots;
    private game_service server;
    public int numOfRobot;

    /**
     * A constructor that accepts the game type and initializes the fields according to it.
     * @param game is the type of game.
     */
    public Game_Algo(game_service game) {
        this.server = game;
        String graph = this.server.getGraph();
        this.GraphGame = new DGraph();
        this.GraphGame.init(graph);
        this.fruits = new FruitsList(this.server);
        this.robots = new RobotsList(this.server);
        RobotsList robots = new RobotsList(this.server);
        this.numOfRobot = robots.getAmountRobots();
    }

    /**
     *This function finds the edge on which the fruit is located.
     * @param fruit is the fruit we are looking for.
     * @return the edge we found.
     */
    public edge_data getEdge(Fruits fruit) {
        edge_data ans;
        for (node_data n : this.GraphGame.getV()) {
            if (this.GraphGame.getE(n.getKey()) != null) {
                Iterator it = this.GraphGame.getE(n.getKey()).iterator();
                while (it.hasNext()) {
                    ans = (edge_data) it.next();
                    node_data dest = this.GraphGame.getNode(ans.getDest());
                    node_data src = this.GraphGame.getNode(ans.getSrc());
                    double dis = distance(src.getLocation(), dest.getLocation());
                    double sTf = distance(src.getLocation(), fruit.getLocation());
                    double fTd = distance(fruit.getLocation(), dest.getLocation());
                    if (sTf + fTd <= dis + 0.0001) {
                        if (fruit.getType() == -1 && src.getKey() > dest.getKey()) {
                            return ans;
                        } else if (fruit.getType() == 1 && src.getKey() < dest.getKey()) {
                            return ans;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * This is an auxiliary function for calculating distance between two points.
     * @param p1 is the first point.
     * @param p2 is the second point.
     * @return the distance.
     */
    public double distance(Point3D p1, Point3D p2) {
        double x = Math.pow(p1.x() - p2.x(), 2);
        double y = Math.pow(p1.y() - p2.y(), 2);
        return Math.sqrt(x + y);
    }

    /**
     * This function collects all the edges that have fruit in a list.
     * @return the list the function has built.
     */
    public List<edge_data> getListOfEdgeF() {
        List<edge_data> edgeOfFruit = new LinkedList<>();
        this.fruits = new FruitsList(this.server);
        for (Fruits f : this.fruits.fruits) {
            edgeOfFruit.add(getEdge(f));
        }
        return edgeOfFruit;
    }

    /**
     *This function checks which nodes to place the robots on.
     */
    public void locationRobot() {
        List<edge_data> edgeOfFruit = getListOfEdgeF();
        for (int i = 0; i < this.numOfRobot; i++) {
            double min = Integer.MAX_VALUE;
            edge_data ans = new Edge();
            for (edge_data e : edgeOfFruit) {
                if (e.getWeight() < min) {
                    min = e.getWeight();
                    ans = e;
                }
            }
            this.server.addRobot(this.GraphGame.getNode(ans.getSrc()).getKey());
            edgeOfFruit.remove(ans);
        }
    }

    /**
     * This function checks which node should send the robots.
     * @param r is the robot.
     * @param graphGame is the graph of this game.
     */
    public void nextNode(Robots r, DGraph graphGame) {
        this.robots.listR(this.server.move());
        Graph_Algo g = new Graph_Algo();
        g.init(graphGame);
        List<edge_data> edgeOfFruit = getListOfEdgeF();
        edge_data minDest = new Edge();
        double min = Integer.MAX_VALUE;
        for (edge_data e : edgeOfFruit) {
            double temp = g.shortestPathDist(r.getSrc(), e.getSrc());
            if (temp < min) {
                min = temp;
                minDest = e;
            }
        }
        List<node_data> shortestPath = g.shortestPath(r.getSrc(), minDest.getSrc());
        shortestPath.add(this.GraphGame.getNode(minDest.getDest()));
        if (shortestPath.size() > 1) {
            if (r.getLocation().equalsXY(this.GraphGame.getNode(minDest.getSrc()).getLocation())){
                this.server.chooseNextEdge(r.getId(), minDest.getDest());
            }
            else this.server.chooseNextEdge(r.getId(), shortestPath.get(1).getKey());
            this.server.move();
        }
    }
}
