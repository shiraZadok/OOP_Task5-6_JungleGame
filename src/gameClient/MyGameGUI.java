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

import javax.swing.*;
import java.util.Iterator;
import java.util.List;

public class MyGameGUI extends Thread {

    private DGraph GraphGame;
    private GUI g;
    private  FruitsList fruits;
    public   game_service server;
    private  RobotsList robots;
    public   Game_Algo game_algo;

    public MyGameGUI() {
        StdDraw.gameGui = this;
        String[] chooseNumOfGame = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
        Object selectedNumOfGame = JOptionPane.showInputDialog(null, "Choose a num of game", "Message", JOptionPane.INFORMATION_MESSAGE, null, chooseNumOfGame, chooseNumOfGame[0]);
        int num = Integer.parseInt((String) selectedNumOfGame);
        this.server = Game_Server.getServer(num);

        String[] chooseGame = {"Manual game", "Auto game"};
        Object selectedGame = JOptionPane.showInputDialog(null, "Choose a game mode", "Message", JOptionPane.INFORMATION_MESSAGE, null, chooseGame, chooseGame[1]);


        if (selectedGame == "Manual game"){
            String graph= this.server.getGraph();
            this.GraphGame= new DGraph();
            this.GraphGame.init(graph);
            this.g = new GUI(this.GraphGame);
            this.g.GUIgraph();
            this.game_algo = new Game_Algo(this.server);
            FruitsGui();
            StdDraw.show();
            this.start();
        }

        if (selectedGame=="Auto game") {
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

    public void AddRobot(int key){
        this.robots = new RobotsList(this.server);
        this.server.addRobot(key);
        StdDraw.picture(this.GraphGame.getNode(key).getLocation().x(), this.GraphGame.getNode(key).getLocation().y(), "robot.png",0.002,0.001);
        StdDraw.show();
    }

    private void moveRobots() {

        List<String> log = this.server.move();
        if(log!=null) {
            this.robots.listR(log);
            for (Robots r : this.robots.robots){
                System.out.println("\n");
                System.out.println("the Src of the robot - " + r.getId() +" before nextNode" +r.getSrc());
                if (r.getDest() ==-1){
                    this.game_algo.nextNode(r, this.GraphGame);
                }
            }
        }
        this.server.move();
    }

    public void run(){
        while (this.server.isRunning()){
            FruitsGui();
            RobotsGui();
            moveRobots();
            StdDraw.show();
            this.g.DrawGraph(this.GraphGame);
            try {
                sleep(10);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("YOUR GRADE IS:" + myGrade(this.server));
    }

    public double myGrade(game_service server){
        double myGrade =0 ;
        try {
            String json = server.toString();
            JSONObject gameJson = new JSONObject(json);
            JSONObject gameServer = gameJson.getJSONObject("GameServer");
            myGrade = gameServer.getDouble("grade");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return myGrade;
    }

    public static void main(String[] args) {
        MyGameGUI m =new MyGameGUI();
    }
}
