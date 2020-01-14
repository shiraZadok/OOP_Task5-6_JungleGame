package gameClient;

import Server.Game_Server;
import Server.game_service;
import algorithms.Game_Algo;
import dataStructure.DGraph;
import dataStructure.node_data;
import element.Fruits;
import element.FruitsList;
import element.Robots;
import element.RobotsList;
import gui.GUI;
import oop_dataStructure.oop_graph;
import org.json.JSONException;
import org.json.JSONObject;
import utils.StdDraw;

import java.util.Iterator;
import java.util.List;

public class MyGameGUI extends Thread {

    private static DGraph GraphGame;
    private GUI g;
    private  FruitsList fruits;
    private  game_service server;
    private  RobotsList robots;
    private  Game_Algo game_algo;

    public MyGameGUI(int g){
        this.server = Game_Server.getServer(g);
        String graph= this.server.getGraph();
        this.GraphGame= new DGraph();
        this.GraphGame.init(graph);
        this.g = new GUI(this.GraphGame);
        this.g.GUIgraph();
        this.game_algo = new Game_Algo(this.server);
        FruitsGui();
        RobotsGui();
        StdDraw.show();
        this.server.startGame();
        this.start();
    }

    public void FruitsGui(){
        this.fruits = new FruitsList(this.server);
        for(Fruits f : this.fruits.fruits){
            StdDraw.picture(f.getLocation().x(),f.getLocation().y(),f.get_pic(),0.001,0.0008);
        }
    }

    public void RobotsGui(){
        this.robots = new RobotsList(this.server);
        this.game_algo.locationRobot();
        this.robots = new RobotsList(this.server);
        for(Robots c : this.robots.robots){
            StdDraw.picture(c.getLocation().x(), c.getLocation().y(), "robot.png",0.002,0.001);
        }
    }

    private void moveRobots() {
        List<String> log = this.server.move();
        if(log!=null) {
            this.robots.listR(log);
            for (Robots r : this.robots.robots){
                if (r.getDest() ==-1){
                    this.game_algo.nextNode(r, this.GraphGame);
                }
            }
        }
        this.server.move();
    }

    public void run(){
        while (this.server.isRunning()){
            RobotsGui();
            FruitsGui();
            moveRobots();
            StdDraw.show();
            this.g.DrawGraph(this.GraphGame);
        }
    }


    public static void main(String[] args) {
        MyGameGUI m =new MyGameGUI(23);
    }
}
