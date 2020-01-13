package algorithms;

import Server.game_service;
import dataStructure.*;
import element.Fruits;
import element.FruitsList;
import element.RobotsList;
import utils.Point3D;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Game_Algo {

    private DGraph GraphGame;
    private FruitsList fruits;
    private game_service server;


    public Game_Algo(game_service game){
        this.server = game;
        String graph= this.server.getGraph();
        this.GraphGame= new DGraph();
        this.GraphGame.init(graph);
        this.fruits = new FruitsList(this.server);
    }

    public edge_data getEdge(Fruits fruit) {
        edge_data ans = new Edge();
        for (node_data n : this.GraphGame.getV()) {
            if (this.GraphGame.getE(n.getKey()) != null) {
                Iterator it = this.GraphGame.getE(n.getKey()).iterator();
                while (it.hasNext()) {
                    ans = (edge_data) it.next();
                    node_data dest = this.GraphGame.getNode(ans.getDest());
                    node_data src = this.GraphGame.getNode(ans.getSrc());
                    double dis = distance(src.getLocation(),dest.getLocation());
                    double sTf = distance(src.getLocation(),fruit.getLocation());
                    double fTd = distance(fruit.getLocation(),dest.getLocation());
                    if(sTf+fTd<=dis+0.0001){
                        if(fruit.getType()==-1 && src.getKey()>dest.getKey()){
                            return ans;
                        }
                        else if(fruit.getType()==1 && src.getKey()<dest.getKey()){
                            return ans;
                        }
                    }
                }
            }
        }
        return null;
    }

    public double distance(Point3D p1, Point3D p2){
        double x = Math.pow(p1.x()-p2.x(), 2);
        double y = Math.pow(p1.y()-p2.y(), 2);
        return Math.sqrt(x+y);
    }

    public void locationRobot (){
        List<edge_data> edgeOfFruit = new LinkedList<>();
        for(Fruits f : this.fruits.fruits){
            edgeOfFruit.add(getEdge(f));
        }
        RobotsList robots = new RobotsList(this.server);
        int numOfRobot = robots.getAmountRobots();
        for (int i=0; i< numOfRobot; i++) {
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

    public void moveRobot(){

    }
}
